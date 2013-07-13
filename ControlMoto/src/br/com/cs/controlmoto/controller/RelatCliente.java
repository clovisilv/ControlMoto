package br.com.cs.controlmoto.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.com.cs.controlmoto.domain.ConnectionFactory;
import br.com.cs.controlmoto.utils.LerArquivo;
import br.com.cs.controlmoto.vo.LerArquivoVO;

/**
 * Company Fictec Cons. Inf.
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 */

public class RelatCliente {
	
	ResultSet rs;
	Statement minhaSTM;
	Connection minhaConexao;
	static String report="", monstrarCaminho="", separadorPath="", caminho="";
	JasperViewer jasperViewer;
	java.util.Properties props;
	File file=null;

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
			minhaConexao = ConnectionFactory.getConnection();
			minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			HashMap map = new HashMap();   
			String arquivoJasper = "rpt_cliente.jasper";
			if(!caminho.contains("workspace")){
				//CAMINHO DE PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			}else{
				//CAMINHO DE ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
			}
		}catch (JRException e){   
			e.printStackTrace();   
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
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
			minhaConexao = ConnectionFactory.getConnection();
			HashMap map = new HashMap();
			map.put("OrdemPk",ordem);
			String arquivoJasper = "rpt_abrir_os.jasper";
			if(!caminho.contains("workspace")){
				//CAMINHO DE PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			}else{
				//CAMINHO DE ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
			}
		}catch(JRException e){
			e.printStackTrace();
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
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
			minhaConexao = ConnectionFactory.getConnection();
			HashMap map = new HashMap();
			map.put("OrdemPk",ordem);
			String arquivoJasper = "rpt_fechar_os.jasper";
			if(!caminho.contains("workspace")){
				//CAMINHNO PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			}else{
				//CAMINHO DE ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
			}
		}catch(JRException e){
			e.printStackTrace();
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
		}
		return rel;
	}
	/*
	 * Metodo que realiza a geracao do relatorio  de ordem de servico pelo status e data de inicio 
	 */	
	public JasperPrint gerarRelatoriosOSFechadaPdf(int ordem) throws Exception{
		java.lang.String pdf=null; JasperPrint rel = null; this.caminhoReport();
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		java.util.Date data = new Date();
    	
		/* Le arquivo de configuracao do projeto */
    	LerArquivoVO lerArquivoVO = new LerArquivoVO();
    	LerArquivo lerArquivo = new LerArquivo();
    	lerArquivoVO = lerArquivo.getInformacao(lerArquivoVO);
		//Cria a pasta pdf
		File dir = new File(lerArquivoVO.getCaminhoPdf()+separadorPath+"os_pdf");
		if (dir.mkdir()) { System.out.println("Diretório criado com sucesso!");	} else { System.out.println("Erro ao criar diretório!"); }
		
		//Cria a pasta para guardar os arquivos pdf
		File dirs = new File(dir+separadorPath+( (data.getMonth()+01) < 10 ? "0"+(data.getMonth()+01) : (data.getMonth()+01) )+(data.getYear()+1900));
		if (dirs.mkdir()) { System.out.println("Diretorio criado com sucesso!");	} else { System.out.println("Erro ao criar diretorio!"); }
		
		//pdf = caminho+separadorPath+"pdf"+separadorPath+( (data.getMonth()+01) < 10 ? "0"+(data.getMonth()+01) : (data.getMonth()+01) )+(data.getYear()+1900)+separadorPath+"os_numero_"+ordem+"_"+(data.getDate())+(data.getMonth()+01)+(data.getYear()+1900)+".pdf";
		pdf = dirs+separadorPath+"os_numero_"+ordem+"_"+(data.getDate())+(data.getMonth()+01)+(data.getYear()+1900)+".pdf";
		System.out.println(pdf);
		try{
			minhaConexao = ConnectionFactory.getConnection();
			HashMap map = new HashMap();
			map.put("OrdemPk",ordem);
			String arquivoJasper = "rpt_fechar_os_pdf.jasper";
			if(!caminho.contains("workspace")){
				//CAMINHNO PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
				JasperExportManager.exportReportToPdfFile(rel, pdf);
			}else{
				//CAMINHO DE ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
				JasperExportManager.exportReportToPdfFile(rel, pdf);				
			}
		}catch(JRException e){
			e.printStackTrace();
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
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
			minhaConexao = ConnectionFactory.getConnection();
			minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			HashMap map = new HashMap();
			String arquivoJasper = "rpt_motorista.jasper";
			if(!caminho.contains("workspace")){
				//CAMINHNO PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			}else{
				//CAMINHO DE ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
			}
		}catch(JRException e){
			e.printStackTrace();
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
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
			minhaConexao = ConnectionFactory.getConnection();
			minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			HashMap map = new HashMap();
			String arquivoJasper = "rpt_os_fechadas.jasper";
			if(!caminho.contains("workspace")){
				//CAMINHNO PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			}else{
				//CAMINHO DE ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper,map,minhaConexao);
			}
		}catch(JRException e){
			e.printStackTrace();
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
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
			minhaConexao = ConnectionFactory.getConnection();
			HashMap map = new HashMap();
			String arquivoJasper = "rpt_os_abertas.jasper";
			if(!caminho.contains("workspace")){
				//CAMINHO DE PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper,map,minhaConexao);
			}else{
				//CAMINHO PARA ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper,map,minhaConexao);
			}
		}catch(JRException e){
			e.printStackTrace();
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
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
			minhaConexao = ConnectionFactory.getConnection();
			HashMap map = new HashMap();
			map.put("status",status);
			map.put("dataInicio",dataInicio);
			map.put("dataFinal",dataFinal);
			String arquivoJasper  = "rpt_total_mot_data_fechadas.jasper";
			if(!caminho.contains("workspace")){
				//CAMINHNO PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			}else{
				//CAMINHO DE ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
			}
		}catch(JRException e){
			e.printStackTrace();
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
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
			minhaConexao = ConnectionFactory.getConnection();
			HashMap map = new HashMap();
			map.put("status",status);
			map.put("dataInicio",dataInicio);
			map.put("dataFinal",dataFinal);
			String arquivoJasper  = "rpt_total_mot_data_abertas.jasper";
			if(!caminho.contains("workspace")){
				//CAMINHNO PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			}else{
				//CAMINHO DE ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
			}
		}catch(JRException e){
			e.printStackTrace();
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
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
			minhaConexao = ConnectionFactory.getConnection();
			HashMap map = new HashMap();
			map.put("status",status);
			map.put("dataInicio",dataInicio);
			map.put("dataFinal",dataFinal);
			String arquivoJasper  = "rpt_total_mot_data_canceladas.jasper";
			if(!caminho.contains("workspace")){
				//CAMINHNO PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			}else{
				//CAMINHO DE ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
			}
		}catch(JRException e){
			e.printStackTrace();
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
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
			minhaConexao = ConnectionFactory.getConnection();
			HashMap map = new HashMap();
			map.put("nomeMotorista",nomeMotorista);
			map.put("datainicio", datainicio);
			map.put("datafinal",datafinal);
			String arquivoJasper  = "rpt_total_mot_nome_data.jasper";
			if(!caminho.contains("workspace")){
				//CAMINHNO PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			}else{
				//CAMINHO DE ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
			}
		}catch(JRException e){
			e.printStackTrace();
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
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
			minhaConexao = ConnectionFactory.getConnection();
			HashMap map = new HashMap();
			map.put("status",status);
			map.put("datainicio",datainicio);
			map.put("datafinal",datafinal);
			String arquivoJasper = "rpt_total_cli_data_fechada.jasper";
			if(!caminho.contains("workspace")){
				//CAMINHNO PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			}else{
				//CAMINHO DE ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
			}
		}catch(JRException e){
			e.printStackTrace();
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
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
//		java.lang.String report1="";
		try{
			minhaConexao = ConnectionFactory.getConnection();
			HashMap map = new HashMap();
			map.put("status",status);
			map.put("datainicio",datainicio);
			map.put("datafinal",datafinal);
			String arquivoJasper = "rpt_total_cli_data_aberta.jasper";
			if(!caminho.contains("workspace")){			
				//CAMINHNO PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			}else{
				//CAMINHO DE ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
			}
		}catch(JRException e){
			e.printStackTrace();
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
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
			minhaConexao = ConnectionFactory.getConnection();
			HashMap map = new HashMap();
			map.put("status",status);
			map.put("datainicio",datainicio);
			map.put("datafinal",datafinal);
			String arquivoJasper = "rpt_total_cli_data_cancelada.jasper";
			if(!caminho.contains("workspace")){
				//CAMINHNO PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			}else{
				//CAMINHO DE ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
			}
		}catch(JRException e){
			e.printStackTrace();
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
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
			minhaConexao = ConnectionFactory.getConnection();
			HashMap map = new HashMap();
			map.put("nomeCliente",nomeCliente);			
			map.put("datainicio",datainicio);
			map.put("datafinal",datafinal);
			String arquivoJasper = "rpt_total_cli_nome_data.jasper";
			if(!caminho.contains("workspace")){
				//CAMINHNO PRODUCAO
				rel = JasperFillManager.fillReport(caminho+separadorPath+report+separadorPath+arquivoJasper, map, minhaConexao);
			}else{
				//CAMINHO DE ECLIPSE
				rel = JasperFillManager.fillReport(report+separadorPath+arquivoJasper, map, minhaConexao);
			}
		}catch(JRException e){
			e.printStackTrace();
		}finally{
			try {
				ConnectionFactory.getClosedConnection();
			} catch (IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
		}
		return rel;
	}
	
    }