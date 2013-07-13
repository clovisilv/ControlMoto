package br.com.cs.controlmoto.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

import br.com.cs.controlmoto.vo.LerArquivoVO;


public class LerArquivo{
	
	public static java.lang.String separadorPath="", caminho="";
	java.util.Properties props;
	
	public LerArquivoVO getInformacao(LerArquivoVO lerArquivoVO){
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		File file = null;
		if(!caminho.contains("workspace") ){
			// Caminho correto para funcionar o arquivo jar
			file = new File(caminho+separadorPath+"ControlMoto"+separadorPath+"ControlMoto"+separadorPath+"resources"+separadorPath+"propertys.properties");
		}else{
			// Caminho corretopara funcionar no Eclipse
			file = new File(caminho+separadorPath+"resources"+separadorPath+"propertys.properties");
		}
		props = new Properties();
		LerArquivoVO lerArquivosVO = new LerArquivoVO();
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(file);
			props.load(fis);
			//Campo para a leitura do arquivo de propriedade
			lerArquivosVO.setLogoTipo(props.getProperty("logo_tipo"));
			lerArquivosVO.setSobreImg(props.getProperty("sobre_img"));
			lerArquivosVO.setAjudaImg(props.getProperty("ajuda_arq"));
			lerArquivosVO.setReports(props.getProperty("reports"));
			lerArquivosVO.setAjudaArq(props.getProperty("ajuda_arq"));
			lerArquivosVO.setCaminhoPdf(props.getProperty("pdf"));
			//Servidor
			//lerArquivosVO.setServerUrl(props.getProperty("urlServer"));
			//lerArquivosVO.setServerPort(props.getProperty("portServer"));
			//lerArquivosVO.setServerDatabase(props.getProperty("databaseServer"));
			//lerArquivosVO.setServerPass(props.getProperty("passServer"));
			//lerArquivosVO.setServerUser(props.getProperty("userServer"));

			fis.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n"+"Erro ao ler informações do sistema","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
		}
	
		return lerArquivosVO;
	}

}
