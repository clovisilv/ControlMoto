package br.com.cs.controlmoto.vo;

public class PesqRelatClieVO {
	
	private java.lang.String nome;
	private java.sql.Timestamp dataInicio;
	private java.sql.Timestamp datafinal;
	private java.lang.String osAberta;
	private java.lang.String osFechada;
	private java.lang.String osCancelada;
	
	public PesqRelatClieVO(){
	}
	
	/**
	 * @return the nome
	 */
	public java.lang.String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(java.lang.String nome) {
		this.nome = nome;
	}
	/**
	 * @return the dataInicio
	 */
	public java.sql.Timestamp getDataInicio() {
		return dataInicio;
	}
	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(java.sql.Timestamp dataInicio) {
		this.dataInicio = dataInicio;
	}
	/**
	 * @return the datafinal
	 */
	public java.sql.Timestamp getDatafinal() {
		return datafinal;
	}
	/**
	 * @param datafinal the datafinal to set
	 */
	public void setDatafinal(java.sql.Timestamp datafinal) {
		this.datafinal = datafinal;
	}
	
	public java.lang.String getOsAberta(){
		return osAberta;
	}
	
	public void setOsAberta(java.lang.String osAberta){
		this.osAberta = osAberta;
	}
	
	public java.lang.String getOsFechada(){
		return osFechada;
	}
	
	public void setOsFechada(java.lang.String osFechada){
		this.osFechada = osFechada;
	}
	
	public java.lang.String getOsCancelada(){
		return osCancelada;
	}
	
	public void setOsCancelada(java.lang.String osCancelada){
		this.osCancelada = osCancelada;
	}

}
