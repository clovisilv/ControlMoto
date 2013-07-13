package br.com.cs.controlmoto.view;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.com.cs.controlmoto.domain.ConexaoDbs_arquivo;
import br.com.cs.controlmoto.utils.LerArquivo;
import br.com.cs.controlmoto.vo.LerArquivoVO;

public class RelatClienteView {
	
	ResultSet rs;
	Statement minhaSTM;
	Connection minhaConexao;
	static String report="", monstrarCaminho="", separadorPath="", caminho="";
	JasperViewer jasperViewer;
	java.util.Properties props;
	File file=null;
	
//	public RelatCliente(){
//		separadorPath = System.getProperty("file.separator");
//		caminho = new File("").getAbsolutePath();
//		//CAMINHO CORRETO PARA FUNCIONAR NO JAR caminho+separadorPath+report+separadorPath+
//		file = new File(caminho+separadorPath+"ControlMoto"+separadorPath+"resources"+separadorPath+"propertys.properties");
//		//CAMINHO CORRETO PARA FUNCIONAR NO ECLIPSE
//		//File file = new File("./resources/propertys.properties");
//		props = new Properties();
//		FileInputStream fis = null;
//		try{
//			fis = new FileInputStream(file);
//			props.load(fis);
//			report = props.getProperty("reports");
//			fis.close();
//			}catch (IOException e) {
//				JOptionPane.showMessageDialog(null, e.getMessage()+"\n"+"Erro ao ler informações do sistema","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
//			}
//	}
	
//	public String caminhoReport(){
//		separadorPath = System.getProperty("file.separator");
//		caminho = new File("").getAbsolutePath();
//		//CAMINHO CORRETO PARA FUNCIONAR NO JAR caminho+separadorPath+report+separadorPath+
//		file = new File(caminho+separadorPath+"ControlMoto"+separadorPath+"resources"+separadorPath+"propertys.properties");
//		//CAMINHO CORRETO PARA FUNCIONAR NO ECLIPSE
//		//File file = new File("./resources/propertys.properties");
//		props = new Properties();
//		FileInputStream fis = null;
//		try{
//			fis = new FileInputStream(file);
//			props.load(fis);
//			report = props.getProperty("reports");
//			fis.close();
//			}catch (IOException e) {
//				JOptionPane.showMessageDialog(null, e.getMessage()+"\n"+"Erro ao ler informações do sistema","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
//			}
//		return report;
//	}
	public String caminhoReport(){
		LerArquivoVO lerArquivoVO = new LerArquivoVO();
		LerArquivo lerArquivo = new LerArquivo();
		lerArquivoVO = lerArquivo.getInformacao(lerArquivoVO);
		return report = String.valueOf(lerArquivoVO.getReports());
	}
	
	
	
	public JasperPrint gerarRelatoriosClientes() throws Exception{
		JasperPrint rel=null;
		this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		try{
			ConexaoDbs_arquivo conDbs = new ConexaoDbs_arquivo();
			conDbs.Conexao();
			minhaConexao = conDbs.getConexao();
			minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			HashMap map = new HashMap();   
			String arquivoJasper = "rpt_cliente.jasper";
			//CAMINHO DE PRODUCAO
			rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			//CAMINHO DE ECLIPSE
			//rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
		}catch (JRException e){   
			e.printStackTrace();   
		}
		return rel;   
	} 
	/*
	 * Metodo que realiza a geracao do relatorio  de ordem de servico pelo status e data de inicio 
	 */
	public JasperPrint gerarRelatoriosAbrirOS(int ordem) throws Exception{
		JasperPrint rel = null;
		this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		try{
			ConexaoDbs_arquivo conDbs = new ConexaoDbs_arquivo();
			conDbs.Conexao();
			minhaConexao = conDbs.getConexao();
			HashMap map = new HashMap();
			map.put("OrdemPk",ordem);
			String arquivoJasper = "rpt_abrir_os.jasper";
			//CAMINHO DE PRODUCAO
			rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			//CAMINHO DE ECLIPSE
			//rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
		}catch(JRException e){
			e.printStackTrace();
		}
		return rel;
	}
	/*
	 * Metodo que realiza a geracao do relatorio  de ordem de servico pelo status e data de inicio 
	 */	
	public JasperPrint gerarRelatoriosOSFechada(int ordem) throws Exception{
		JasperPrint rel = null;
		this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		try{
			ConexaoDbs_arquivo conDbs = new ConexaoDbs_arquivo();
			conDbs.Conexao();
			minhaConexao = conDbs.getConexao();
			HashMap map = new HashMap();
			map.put("OrdemPk",ordem);
			String arquivoJasper = "rpt_fechar_os.jasper";
			//CAMINHNO PRODUCAO
			rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			//CAMINHO DE ECLIPSE
			//rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
		}catch(JRException e){
			e.printStackTrace();
		}
		return rel;
	}	
	/*
	 * Metodo que realiza a geracao do relatorio  de ordem de servico pelo status e data de inicio 
	 */
	public JasperPrint gerarRelatoriosMotorista() throws Exception{
		JasperPrint rel = null;
		this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		try{
			ConexaoDbs_arquivo conDbs = new ConexaoDbs_arquivo();
			conDbs.Conexao();
			minhaConexao = conDbs.getConexao();
			minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			HashMap map = new HashMap();
			String arquivoJasper = "rpt_motorista.jasper";
			//CAMINHNO PRODUCAO
			rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			//CAMINHO DE ECLIPSE
			//rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
		}catch(JRException e){
			e.printStackTrace();
		}
		return rel;
	}
	/*
	 * Metodo que realiza a geracao do relatorio  de ordem de servico pelo status e data de inicio 
	 */
	public JasperPrint gerarRelatorioOSFechadas() throws Exception{
		JasperPrint rel = null;
		this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		try{
			ConexaoDbs_arquivo conDbs = new ConexaoDbs_arquivo();
			conDbs.Conexao();
			minhaConexao = conDbs.getConexao();
			minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			HashMap map = new HashMap();
			String arquivoJasper = "rpt_os_fechadas.jasper";
			//CAMINHNO PRODUCAO
			rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			//CAMINHO DE ECLIPSE
			//rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper,map,minhaConexao);
		}catch(JRException e){
			e.printStackTrace();
		}
		return rel;
	}
	/*
	 * Metodo que realiza a geracao do relatorio  de ordem de servico pelo status e data de inicio 
	 */
	public JasperPrint gerarRelatorioOSAbertas() throws Exception{
		JasperPrint rel = null;
		this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		try{
			ConexaoDbs_arquivo conDbs = new ConexaoDbs_arquivo();
			conDbs.Conexao();
			minhaConexao = conDbs.getConexao();
			HashMap map = new HashMap();
			String arquivoJasper = "rpt_os_abertas.jasper";
			//CAMINHO DE PRODUCAO
			rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper,map,minhaConexao);
			//CAMINHO PARA ECLIPSE
			//rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper,map,minhaConexao);
		}catch(JRException e){
			e.printStackTrace();
		}
		return rel;
	}
	/*
	 * Metodo que realiza a geracao do relatorio  de ordem de servico pelo status e data de inicio 
	 */
	public JasperPrint gerarRelatTotalMotDataFechada(String status, java.sql.Timestamp dataInicio, java.sql.Timestamp dataFinal) throws Exception{
		JasperPrint rel = null;
		this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		try{
			ConexaoDbs_arquivo conDbs = new ConexaoDbs_arquivo();
			conDbs.Conexao();
			minhaConexao = conDbs.getConexao();
			HashMap map = new HashMap();
			map.put("status",status);
			map.put("dataInicio",dataInicio);
			map.put("dataFinal",dataFinal);
			String arquivoJasper  = "rpt_total_mot_data_fechadas.jasper";
			//CAMINHNO PRODUCAO
			rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			//CAMINHO DE ECLIPSE
			//rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
		}catch(JRException e){
			e.printStackTrace();
		}
		return rel;
	}
	/*
	 * Metodo que realiza a geracao do relatorio  de ordem de servico pelo status e data de inicio 
	 */
	public JasperPrint gerarRelatTotalMotDataAberta(String status, java.sql.Timestamp dataInicio, java.sql.Timestamp dataFinal) throws Exception{
		JasperPrint rel = null;
		this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		try{
			ConexaoDbs_arquivo conDbs = new ConexaoDbs_arquivo();
			conDbs.Conexao();
			minhaConexao = conDbs.getConexao();
			HashMap map = new HashMap();
			map.put("status",status);
			map.put("dataInicio",dataInicio);
			map.put("dataFinal",dataFinal);
			String arquivoJasper  = "rpt_total_mot_data_abertas.jasper";
			//CAMINHNO PRODUCAO
			rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			//CAMINHO DE ECLIPSE
			//rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
		}catch(JRException e){
			e.printStackTrace();
		}
		return rel;
	}
	/*
	 * Metodo que realiza a geracao do relatorio  de ordem de servico pelo status e data de inicio 
	 */
	public JasperPrint gerarRelatTotalMotDataCancelada(String status, java.sql.Timestamp dataInicio, java.sql.Timestamp dataFinal) throws Exception{
		JasperPrint rel = null;
		this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		try{
			ConexaoDbs_arquivo conDbs = new ConexaoDbs_arquivo();
			conDbs.Conexao();
			minhaConexao = conDbs.getConexao();
			HashMap map = new HashMap();
			map.put("status",status);
			map.put("dataInicio",dataInicio);
			map.put("dataFinal",dataFinal);
			String arquivoJasper  = "rpt_total_mot_data_canceladas.jasper";
			//CAMINHNO PRODUCAO
			rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			//CAMINHO DE ECLIPSE
			//rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
		}catch(JRException e){
			e.printStackTrace();
		}
		return rel;
	}
	/*
	 * Metodo que realiza a geracao do relatorio  de ordem de servico pelo status e data de inicio 
	 */
	public JasperPrint gerarRelatTotalMotDataNome(String nomeMotorista, java.sql.Timestamp datainicio, java.sql.Timestamp datafinal) throws Exception{
		JasperPrint rel = null;
		this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		try{
			ConexaoDbs_arquivo conDbs = new ConexaoDbs_arquivo();
			conDbs.Conexao();
			minhaConexao = conDbs.getConexao();
			HashMap map = new HashMap();
			map.put("nomeMotorista",nomeMotorista);
			map.put("datainicio", datainicio);
			map.put("datafinal",datafinal);
			String arquivoJasper  = "rpt_total_mot_nome_data.jasper";
			//CAMINHNO PRODUCAO
			rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			//CAMINHO DE ECLIPSE
			//rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
		}catch(JRException e){
			e.printStackTrace();
		}
		return rel;
	}
	/*
	 * Metodo que realiza a geracao do relatorio  de ordem de servico pelo status e data de inicio 
	 */
	public JasperPrint gerarRelatTotalCliDataFechada(java.lang.String status, java.sql.Timestamp datainicio, java.sql.Timestamp datafinal) throws Exception{
		JasperPrint rel = null;
		this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		try{
			ConexaoDbs_arquivo conDbs = new ConexaoDbs_arquivo();
			conDbs.Conexao();
			minhaConexao = conDbs.getConexao();
			HashMap map = new HashMap();
			map.put("status",status);
			map.put("datainicio",datainicio);
			map.put("datafinal",datafinal);
			String arquivoJasper = "rpt_total_cli_data_fechada.jasper";
			//CAMINHNO PRODUCAO
			rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			//CAMINHO DE ECLIPSE
			//rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
		}catch(JRException e){
			e.printStackTrace();
		}
		return rel;
	}
	/*
	 * Metodo que realiza a geracao do relatorio  de ordem de servico pelo status e data de inicio 
	 */
	public JasperPrint gerarRelatTotalCliDataAberta(java.lang.String status, java.sql.Timestamp datainicio, java.sql.Timestamp datafinal) throws Exception{
		JasperPrint rel = null;
		this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		java.lang.String report1="";
		try{
			//Pega a cenexão para gerar o relatório 
			ConexaoDbs_arquivo conDbs = new ConexaoDbs_arquivo();
			conDbs.Conexao();
			minhaConexao = conDbs.getConexao();
			HashMap map = new HashMap();
			map.put("status",status);
			map.put("datainicio",datainicio);
			map.put("datafinal",datafinal);
			String arquivoJasper = "rpt_total_cli_data_aberta.jasper";
			//CAMINHNO PRODUCAO
			rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			//CAMINHO DE ECLIPSE
			//rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
		}catch(JRException e){
			e.printStackTrace();
		}
		return rel;
	}
	/*
	 * Metodo que realiza a geracao do relatorio  de ordem de servico pelo status e data de inicio 
	 */
	public JasperPrint gerarRelatTotalCliDataCancelada(java.lang.String status, java.sql.Timestamp datainicio, java.sql.Timestamp datafinal) throws Exception{
		JasperPrint rel = null;
		this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		try{
			ConexaoDbs_arquivo conDbs = new ConexaoDbs_arquivo();
			conDbs.Conexao();
			minhaConexao = conDbs.getConexao();
			HashMap map = new HashMap();
			map.put("status",status);
			map.put("datainicio",datainicio);
			map.put("datafinal",datafinal);
			String arquivoJasper = "rpt_total_cli_data_cancelada.jasper";
			//CAMINHNO PRODUCAO
			rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			//CAMINHO DE ECLIPSE
			//rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
		}catch(JRException e){
			e.printStackTrace();
		}
		return rel;
	}
	/*
	 * Metodo que realiza a geracao do relatorio  de ordem de servico pelo status e data de inicio e nome 
	 */
	public JasperPrint gerarRelatTotalCliNomeData(String nomeCliente, java.sql.Timestamp datainicio, java.sql.Timestamp datafinal) throws Exception{
		JasperPrint rel = null;
		this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		try{
			ConexaoDbs_arquivo conDbs = new ConexaoDbs_arquivo();
			conDbs.Conexao();
			minhaConexao = conDbs.getConexao();
			HashMap map = new HashMap();
			map.put("nomeCliente",nomeCliente);			
			map.put("datainicio",datainicio);
			map.put("datafinal",datafinal);
			String arquivoJasper = "rpt_total_cli_nome_data.jasper";
			//CAMINHNO PRODUCAO
			rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			//CAMINHO DE ECLIPSE
			//rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
		}catch(JRException e){
			e.printStackTrace();
		}
		return rel;
	}
	
    }