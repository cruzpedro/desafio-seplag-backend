package br.com.seplag.service;

import br.com.seplag.exception.BusinessException;
import br.com.seplag.model.Beneficio;
import br.com.seplag.repository.BeneficioRepository;
import br.com.seplag.util.FileUtil;
import br.com.seplag.util.GeneralSetupUtil;
import org.apache.commons.io.IOUtils;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import java.io.InputStream;
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
    private GeneralSetupUtil generalSetupUtil;

    @Inject
    private Logger logger;

    public void save(Beneficio beneficio, MultipartFormDataInput input) {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");

        Beneficio beneficioSalvo = save(beneficio);
        inputParts.forEach(i -> saveBeneficioArquivo(beneficioSalvo, i));
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
        if (!Objects.equals(i.getMediaType().getType(), "pdf")) {
            throw new BusinessException("É necessário um arquivo do tipo pdf para criar um beneficio.");
        }

        /*if (FileUtil.getVideoExtensions().stream().noneMatch(ex -> ex.contains(i.getMediaType().getSubtype()))) {
            throw new BusinessException("É necessário que o arquivo de video seja do tipo " + FileUtil.getVideoExtensions());
        }*/
    }

    @Override
    protected AbstractEntityRepository<Beneficio, Long> getRepository() {
        return beneficioRepository;
    }
}
