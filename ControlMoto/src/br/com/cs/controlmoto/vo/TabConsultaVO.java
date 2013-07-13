package br.com.cs.controlmoto.vo;

public class TabConsultaVO {
	
	private java.lang.Integer clientePk = null;
	private java.lang.String motoristaPk = null;
	private java.lang.Integer fornecedorPk = null;
	private java.lang.Integer enderecoPk = null;
	private java.lang.Integer enderecoFK = null;
	private java.lang.Integer telefonePk = null;
	private java.lang.Integer telefoneFk = null;
	private java.lang.String nome = null;
	private java.lang.String endereco = null;
	private java.lang.String numero = null;
	private java.lang.String cidade = null;
	private java.lang.String estado = null;
	private java.lang.String telefoneA = null;
	private java.lang.String celularA = null;
	private java.lang.Integer qtde = null;
	private java.lang.String dataCadastro = null;
	private java.lang.String dataInicio = null;
	private java.lang.String dataFinal = null;
	private java.sql.Timestamp dataInicios = null;
	private java.sql.Timestamp dataFinals = null;
	private java.sql.Timestamp dataCadastros = null;

	public TabConsultaVO(){
		
	}
	
	Object[] objects = null;

	/**
	 * @return the clientePk
	 */
	public java.lang.Integer getClientePk() {
		return clientePk;
	}

	/**
	 * @param clientePk the clientePk to set
	 */
	public void setClientePk(java.lang.Integer clientePk) {
		this.clientePk = clientePk;
	}

	/**
	 * @return the motoristaPk
	 */
	public java.lang.String getMotoristaPk() {
		return motoristaPk;
	}

	/**
	 * @param motoristaPk the motoristaPk to set
	 */
	public void setMotoristaPk(java.lang.String motoristaPk) {
		this.motoristaPk = motoristaPk;
	}

	public java.lang.Integer getFornecedorPk() {
		return fornecedorPk;
	}

	public void setFornecedorPk(java.lang.Integer fornecedorPk) {
		this.fornecedorPk = fornecedorPk;
	}

	/**
	 * @return the enderecoPk
	 */
	public java.lang.Integer getEnderecoPk() {
		return enderecoPk;
	}

	/**
	 * @param enderecoPk the enderecoPk to set
	 */
	public void setEnderecoPk(java.lang.Integer enderecoPk) {
		this.enderecoPk = enderecoPk;
	}

	/**
	 * @return the enderecoFK
	 */
	public java.lang.Integer getEnderecoFK() {
		return enderecoFK;
	}

	/**
	 * @param enderecoFK the enderecoFK to set
	 */
	public void setEnderecoFK(java.lang.Integer enderecoFK) {
		this.enderecoFK = enderecoFK;
	}

	/**
	 * @return the telefonePk
	 */
	public java.lang.Integer getTelefonePk() {
		return telefonePk;
	}

	/**
	 * @param telefonePk the telefonePk to set
	 */
	public void setTelefonePk(java.lang.Integer telefonePk) {
		this.telefonePk = telefonePk;
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
	 * @return the endereco
	 */
	public java.lang.String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(java.lang.String endereco) {
		this.endereco = endereco;
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
	 * @return the cidade
	 */
	public java.lang.String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(java.lang.String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the estado
	 */
	public java.lang.String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(java.lang.String estado) {
		this.estado = estado;
	}

	/**
	 * @return the telefoneA
	 */
	public java.lang.String getTelefoneA() {
		return telefoneA;
	}

	/**
	 * @param telefoneA the telefoneA to set
	 */
	public void setTelefoneA(java.lang.String telefoneA) {
		this.telefoneA = telefoneA;
	}

	/**
	 * @return the celularA
	 */
	public java.lang.String getCelularA() {
		return celularA;
	}

	/**
	 * @param celularA the celularA to set
	 */
	public void setCelularA(java.lang.String celularA) {
		this.celularA = celularA;
	}

	/**
	 * @return the dataCadastro
	 */
	public java.lang.String getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(java.lang.String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the dataInicio
	 */
	public java.lang.String getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(java.lang.String dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFinal
	 */
	public java.lang.String getDataFinal() {
		return dataFinal;
	}

	/**
	 * @param dataFinal the dataFinal to set
	 */
	public void setDataFinal(java.lang.String dataFinal) {
		this.dataFinal = dataFinal;
	}

	/**
	 * @return the dataInicios
	 */
	public java.sql.Timestamp getDataInicios() {
		return dataInicios;
	}

	/**
	 * @param dataInicios the dataInicios to set
	 */
	public void setDataInicios(java.sql.Timestamp dataInicios) {
		this.dataInicios = dataInicios;
	}

	/**
	 * @return the dataFinals
	 */
	public java.sql.Timestamp getDataFinals() {
		return dataFinals;
	}

	/**
	 * @param dataFinals the dataFinals to set
	 */
	public void setDataFinals(java.sql.Timestamp dataFinals) {
		this.dataFinals = dataFinals;
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

	/**
	 * @return the qtde
	 */
	public java.lang.Integer getQtde() {
		return qtde;
	}

	/**
	 * @param qtde the qtde to set
	 */
	public void setQtde(java.lang.Integer qtde) {
		this.qtde = qtde;
	}

	/**
	 * @return the objects
	 */
	public Object[] getObjects() {
		return objects;
	}

	/**
	 * @param objects the objects to set
	 */
	public void setObjects(Object[] objects) {
		this.objects = objects;
	}

}
