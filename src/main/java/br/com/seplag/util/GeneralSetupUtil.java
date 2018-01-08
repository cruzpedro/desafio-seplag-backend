package br.com.seplag.util;

import br.com.seplag.model.GeneralSetup;
import br.com.seplag.model.GeneralSetupEnum;
import br.com.seplag.service.GeneralSetupService;

import javax.inject.Inject;
import java.io.File;

public class GeneralSetupUtil {

	@Inject
	private GeneralSetupService generalSetupService;

	private String caminhoFisicoArquivo;
	private GeneralSetup caminhoHttpArquivo;

	public String getCaminhoFisicoArquivo(){
		return existsPath(caminhoFisicoArquivo, GeneralSetupEnum.CAMINHO_FISICO_ARQUIVOS);
	}

	public String getCaminhoHttpArquivo(){
		if(caminhoHttpArquivo == null){
			caminhoHttpArquivo = generalSetupService.findByKey(GeneralSetupEnum.CAMINHO_HTTP_ARQUIVOS);
		}
		return caminhoHttpArquivo.getValue();
	}

	private String existsPath(String pathToValidate, GeneralSetupEnum key) {
		if(pathToValidate == null){
			pathToValidate = generalSetupService.findByKey(key).getValue();

			File path = new  File(pathToValidate);

			if (!path.exists()){
				path.mkdirs();
			}
		}
		return pathToValidate;
	}
}
