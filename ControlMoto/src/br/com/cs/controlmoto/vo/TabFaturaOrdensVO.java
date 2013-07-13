package br.com.cs.controlmoto.vo;



public class TabFaturaOrdensVO {
	
	private java.lang.Boolean faturar = null;
	
	private java.lang.Integer ordemPk = null;
	private java.lang.Integer clienteFk = null;
	private java.lang.String nomeCliente = null;
	private java.lang.String totalCliente = null;
	private java.lang.Integer numeroRps = null;
	private java.lang.Integer numeroNF = null;
	private java.lang.String ordensFaturadas = null;
	private java.util.Date dataFaturamento = null;
	private java.lang.String dataOS = null;
	
	public TabFaturaOrdensVO(){
	}

	public java.lang.Boolean getFaturar() {
		return faturar;
	}

	public void setFaturar(java.lang.Boolean faturar) {
		this.faturar = faturar;
	}

	/**
	 * @return the ordemPk
	 */
	public java.lang.Integer getOrdemPk() {
		return ordemPk;
	}

	/**
	 * @param ordemPk the ordemPk to set
	 */
	public void setOrdemPk(java.lang.Integer ordemPk) {
		this.ordemPk = ordemPk;
	}

	public java.lang.Integer getClienteFk() {
		return clienteFk;
	}

	public void setClienteFk(java.lang.Integer clienteFk) {
		this.clienteFk = clienteFk;
	}

	/**
	 * @return the nomeCliente
	 */
	public java.lang.String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente the nomeCliente to set
	 */
	public void setNomeCliente(java.lang.String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return the totalCliente
	 */
	public java.lang.String getTotalCliente() {
		return totalCliente;
	}

	/**
	 * @param totalCliente the totalCliente to set
	 */
	public void setTotalCliente(java.lang.String totalCliente) {
		this.totalCliente = totalCliente;
	}

	public java.lang.Integer getNumeroRps() {
		return numeroRps;
	}

	public void setNumeroRps(java.lang.Integer numeroRps) {
		this.numeroRps = numeroRps;
	}

	public java.lang.Integer getNumeroNF() {
		return numeroNF;
	}

	public void setNumeroNF(java.lang.Integer numeroNF) {
		this.numeroNF = numeroNF;
	}

	public java.lang.String getOrdensFaturadas() {
		return ordensFaturadas;
	}

	public void setOrdensFaturadas(java.lang.String ordensFaturadas) {
		this.ordensFaturadas = ordensFaturadas;
	}

	public java.util.Date getDataFaturamento() {
		return dataFaturamento;
	}

	public void setDataFaturamento(java.util.Date dataFaturamento) {
		this.dataFaturamento = dataFaturamento;
	}

	public java.lang.String getDataOS() {
		return dataOS;
	}

	public void setDataOS(java.lang.String dataOS) {
		this.dataOS = dataOS;
	}

}
