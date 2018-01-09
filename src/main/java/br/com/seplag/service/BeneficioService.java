package br.com.seplag.service;

import br.com.seplag.exception.BusinessException;
import br.com.seplag.model.Beneficio;
import br.com.seplag.model.Movimento;
import br.com.seplag.repository.BeneficioRepository;
import br.com.seplag.repository.MovimentoRepository;
import br.com.seplag.util.FileUtil;
import br.com.seplag.util.GeneralSetupUtil;
import org.apache.commons.io.IOUtils;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import java.io.InputStream;
import java.lang.annotation.Inherited;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Transactional
public class BeneficioService extends BaseService<Beneficio, Long>{

    @Inject
    private BeneficioRepository beneficioRepository;

    @Inject
    private MovimentoRepository movimentoRepository;

    @Inject
    private GeneralSetupUtil generalSetupUtil;

    @Inject
    private Logger logger;

    public void salvarArquivo(Long id, MultipartFormDataInput input) {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");

        Beneficio beneficio = get(id);
        inputParts.forEach(i -> saveBeneficioArquivo(beneficio, i));
    }

    private void saveBeneficioArquivo(Beneficio beneficio, InputPart input) {
        validaArquivo(input);
        try {
            String fileName = "beneficio_" + LocalTime.now().getNano() + "_" + beneficio.getId() +
                    "." + input.getMediaType().getSubtype();
            InputStream inputStream = input.getBody(InputStream.class, null);
            byte[] bytes = IOUtils.toByteArray(inputStream);

            String fullFileName = generalSetupUtil.getCaminhoFisicoArquivo().replace('\\', '/');
            fullFileName += "/" + fileName;
            FileUtil.writeFile(bytes, fullFileName);

            beneficio.setArquivo(fileName);
            update(beneficio);
        }catch (Exception e) {
            remove(beneficio.getId());
            logger.log(Level.SEVERE, e.getMessage());
            throw new BusinessException("Falha ao tentar salvar o arquivo do beneficio, beneficio cancelado.");
        }
    }

    private void validaArquivo(InputPart i) {
        if (!Objects.equals(i.getMediaType().getSubtype(), "pdf")) {
            throw new BusinessException("É necessário um arquivo do tipo pdf para criar um beneficio.");
        }
    }

    public Beneficio atualizar(Beneficio beneficio) {


        Beneficio beneficioSource = get(beneficio.getId());

        Movimento movimento = new Movimento();
        movimento.setBeneficio(beneficio);
        movimento.setData(LocalDateTime.now());
        movimento.setTramitacaoOrigem(beneficioSource.getTramitacao());
        movimento.setTramitacaoDestino(beneficio.getTramitacao());

        beneficio = update(beneficio);
        movimentoRepository.save(movimento);
        return beneficio;
    }

    @Override
    protected AbstractEntityRepository<Beneficio, Long> getRepository() {
        return beneficioRepository;
    }
}
