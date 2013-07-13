package br.com.cs.controlmoto.vo;

public class PesqRelatMotVO {

	private java.lang.String nome;
	private java.sql.Timestamp dataInicio;
	private java.sql.Timestamp datafinal;	
	
	public PesqRelatMotVO() {
	}
	
	/**
	 * 
	 * @return the nome
	 */
	public java.lang.String getNome(){
		return nome;
	}

	/**
	 * 
	 * @param nome the nome to set
	 */
	public void setNome(java.lang.String nome){
		this.nome = nome;
	}
	
	/**
	 * 
	 * @return the dataInicio
	 */
	public java.sql.Timestamp getDataInicio(){
		return dataInicio;
	}
	
	/**
	 * 
	 * @param dataInicio the to set
	 */
	public void setDataInicio(java.sql.Timestamp dataInicio){
		this.dataInicio = dataInicio;
	}
	
	/**
	 * 
	 * @return dataFinal
	 */
	public java.sql.Timestamp getDataFinal(){
		return datafinal;
	}
	
	/**
	 * 
	 * @param dataFinal the dataFinal to set
	 */
	public void setDataFinal(java.sql.Timestamp dataFinal){
		this.datafinal = dataFinal;
	}

}
