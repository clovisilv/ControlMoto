package br.com.cs.controlmoto.vo;

public class CadFornecedorVO {
	
	private java.lang.Integer fornecedorPk;
	private java.lang.Integer enderecoFk;
	private java.lang.Integer telefoneFk;
	private java.lang.String nome;
	private java.lang.String numero;
	private java.lang.String cnpj;
	private java.lang.String Inscricaoestadual;
	private java.lang.String contato;
	private java.lang.String site;
	private java.lang.String email;
	private java.lang.String valorHora;
	private java.lang.String taxa;
	private java.lang.String dataCadastro;
	private java.sql.Timestamp dataCadastros;

	public CadFornecedorVO(){
	}

	/**
	 * @return the fornecedorPk
	 */
	public final java.lang.Integer getFornecedorPk() {
		return fornecedorPk;
	}

	/**
	 * @param fornecedorPk the fornecedorPk to set
	 */
	public final void setFornecedorPk(java.lang.Integer fornecedorPk) {
		this.fornecedorPk = fornecedorPk;
	}

	/**
	 * @return the enderecoFk
	 */
	public java.lang.Integer getEnderecoFk() {
		return enderecoFk;
	}

	/**
	 * @param enderecoFk the enderecoFk to set
	 */
	public void setEnderecoFk(java.lang.Integer enderecoFk) {
		this.enderecoFk = enderecoFk;
	}

	/**
	 * @return the telefoneFk
	 */
	public java.lang.Integer getTelefoneFk() {
		return telefoneFk;
	}

	/**
	 * @param telefoneFk the telefoneFk to set
	 */
	public void setTelefoneFk(java.lang.Integer telefoneFk) {
		this.telefoneFk = telefoneFk;
	}

	/**
	 * @return the nome
	 */
	public final java.lang.String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public final void setNome(java.lang.String nome) {
		this.nome = nome;
	}

	/**
	 * @return the numero
	 */
	public java.lang.String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(java.lang.String numero) {
		this.numero = numero;
	}

	/**
	 * @return the cnpj
	 */
	public final java.lang.String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj the cnpj to set
	 */
	public final void setCnpj(java.lang.String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * @return the inscricaoestadual
	 */
	public final java.lang.String getInscricaoestadual() {
		return Inscricaoestadual;
	}

	/**
	 * @param inscricaoestadual the inscricaoestadual to set
	 */
	public final void setInscricaoestadual(java.lang.String inscricaoestadual) {
		Inscricaoestadual = inscricaoestadual;
	}

	/**
	 * @return the contato
	 */
	public final java.lang.String getContato() {
		return contato;
	}

	/**
	 * @param contato the contato to set
	 */
	public final void setContato(java.lang.String contato) {
		this.contato = contato;
	}

	/**
	 * @return the site
	 */
	public final java.lang.String getSite() {
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public final void setSite(java.lang.String site) {
		this.site = site;
	}

	/**
	 * @return the email
	 */
	public final java.lang.String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public final void setEmail(java.lang.String email) {
		this.email = email;
	}

	/**
	 * @return the valorHora
	 */
	public final java.lang.String getValorHora() {
		return valorHora;
	}

	/**
	 * @param valorHora the valorHora to set
	 */
	public final void setValorHora(java.lang.String valorHora) {
		this.valorHora = valorHora;
	}

	/**
	 * @return the taxa
	 */
	public final java.lang.String getTaxa() {
		return taxa;
	}

	/**
	 * @param taxa the taxa to set
	 */
	public final void setTaxa(java.lang.String taxa) {
		this.taxa = taxa;
	}

	/**
	 * @return the dataCadastro
	 */
	public final java.lang.String getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public final void setDataCadastro(java.lang.String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the dataCadastros
	 */
	public java.sql.Timestamp getDataCadastros() {
		return dataCadastros;
	}

	/**
	 * @param dataCadastros the dataCadastros to set
	 */
	public void setDataCadastros(java.sql.Timestamp dataCadastros) {
		this.dataCadastros = dataCadastros;
	}

}
