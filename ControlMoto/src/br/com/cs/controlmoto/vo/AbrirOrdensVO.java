package br.com.cs.controlmoto.vo;

public class AbrirOrdensVO {
	
	private java.lang.Integer ordemPk;
	private java.lang.Integer clienteFk;
	private java.lang.String nomecliente;
	private java.lang.String endereco;
	private java.lang.String bairro;
	private java.lang.String cidade;
	private java.lang.String telefone;
	private java.lang.String contato;
	private java.lang.Double valorHora;
	private java.lang.Double taxa;
	private java.lang.String motoristaFk;
	private java.lang.String nomeMotorista;
	private java.lang.Double comissao;
	private java.lang.String dataInicio;
	private java.lang.String horaInicio;
	private java.lang.String status;
	private java.lang.String obsInicio;
	private java.lang.String obsTermino;
	private java.sql.Timestamp dataInicios;
	
	
	
	public AbrirOrdensVO(){
	}

	/**
	 * @return the ordemPK
	 */
	public final java.lang.Integer getOrdemPk() {
		return ordemPk;
	}

	/**
	 * @param ordemPK the ordemPK to set
	 */
	public final void setOrdemPk(java.lang.Integer ordemPk) {
		this.ordemPk = ordemPk;
	}

	/**
	 * @return the clientePK
	 */
	public final java.lang.Integer getClienteFk() {
		return clienteFk;
	}

	/**
	 * @param clientePK the clientePK to set
	 */
	public final void setClienteFk(java.lang.Integer clienteFk) {
		this.clienteFk = clienteFk;
	}

	/**
	 * @return the nomecliente
	 */
	public final java.lang.String getNomecliente() {
		return nomecliente;
	}

	/**
	 * @param nomecliente the nomecliente to set
	 */
	public final void setNomecliente(java.lang.String nomecliente) {
		this.nomecliente = nomecliente;
	}

	/**
	 * @return the endereco
	 */
	public final java.lang.String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public final void setEndereco(java.lang.String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return the bairro
	 */
	public final java.lang.String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro the bairro to set
	 */
	public final void setBairro(java.lang.String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return the cidade
	 */
	public final java.lang.String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public final void setCidade(java.lang.String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the telefone
	 */
	public final java.lang.String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public final void setTelefone(java.lang.String telefone) {
		this.telefone = telefone;
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
	 * @return the valorHora
	 */
	public final java.lang.Double getValorHora() {
		return valorHora;
	}

	/**
	 * @param valorHora the valorHora to set
	 */
	public final void setValorHora(java.lang.Double valorHora) {
		this.valorHora = valorHora;
	}

	/**
	 * @return the motoristaPK
	 */
	public final java.lang.String getMotoristaFk() {
		return motoristaFk;
	}

	/**
	 * @param motoristaPK the motoristaPK to set
	 */
	public final void setMotoristaFk(java.lang.String motoristaFk) {
		this.motoristaFk = motoristaFk;
	}

	/**
	 * @return the nomeMotorista
	 */
	public final java.lang.String getNomeMotorista() {
		return nomeMotorista;
	}

	/**
	 * @param nomeMotorista the nomeMotorista to set
	 */
	public final void setNomeMotorista(java.lang.String nomeMotorista) {
		this.nomeMotorista = nomeMotorista;
	}

	/**
	 * @return the comissao
	 */
	public final java.lang.Double getComissao() {
		return comissao;
	}

	/**
	 * @param comissao the comissao to set
	 */
	public final void setComissao(java.lang.Double comissao) {
		this.comissao = comissao;
	}

	/**
	 * @return the dataInicio
	 */
	public final java.lang.String getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public final void setDataInicio(java.lang.String dataInicio) {
		this.dataInicio = dataInicio;
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
	 * @return the horaInicio
	 */
	public final java.lang.String getHoraInicio() {
		return horaInicio;
	}

	/**
	 * @param horaInicio the horaInicio to set
	 */
	public final void setHoraInicio(java.lang.String horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * @return the status
	 */
	public final java.lang.String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public final void setStatus(java.lang.String status) {
		this.status = status;
	}

	/**
	 * @return the taxa
	 */
	public final java.lang.Double getTaxa() {
		return taxa;
	}

	/**
	 * @param taxa the taxa to set
	 */
	public final void setTaxa(java.lang.Double taxa) {
		this.taxa = taxa;
	}

	/**
	 * @return the obsInicio
	 */
	public java.lang.String getObsInicio() {
		return obsInicio;
	}

	/**
	 * @param obsInicio the obsInicio to set
	 */
	public void setObsInicio(java.lang.String obsInicio) {
		this.obsInicio = obsInicio;
	}

	/**
	 * @return the obsTermino
	 */
	public java.lang.String getObsTermino() {
		return obsTermino;
	}

	/**
	 * @param obsTermino the obsTermino to set
	 */
	public void setObsTermino(java.lang.String obsTermino) {
		this.obsTermino = obsTermino;
	}
	
	

}
