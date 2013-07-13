package br.com.cs.controlmoto.vo;

public class FecharOrdemVO {
	
	private java.lang.Integer ordemPK;
	private java.lang.Integer clienteFk;
	private java.lang.Double valorHora;
	private java.lang.Double taxa;
	
	private java.lang.String motoristaFk;
	private java.lang.String dataInicio;
	private java.lang.String horaInicio;
	
	private java.lang.String dataTermino;
	private java.lang.String horaTermino;
	
	private java.lang.String totalHoras;
	private java.lang.Double totalCliente;
	private java.lang.Double totalMotorista;
	
	private java.lang.String obsInicio;
	private java.lang.String obsTermino;
	
	private java.lang.String dataVencimento;
	private java.lang.String status;


	private java.lang.String tipoOrdem;
	private java.lang.String aberta;
	private java.lang.String fechada;
	private java.lang.String cancelada;
	private java.lang.String opcao;

	private java.sql.Timestamp dataInicios;
	private java.sql.Timestamp dataTerminos;
	private java.sql.Timestamp dataVencimentos;


	
	
	
	
	public FecharOrdemVO(){
	}

	/**
	 * @return the ordemPK
	 */
	public final java.lang.Integer getOrdemPK() {
		return ordemPK;
	}

	/**
	 * @param ordemPK the ordemPK to set
	 */
	public final void setOrdemPK(java.lang.Integer ordemPK) {
		this.ordemPK = ordemPK;
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
	 * @return the totalCliente
	 */
	public final java.lang.Double getTotalCliente() {
		return totalCliente;
	}

	/**
	 * @param totalCliente the totalCliente to set
	 */
	public final void setTotalCliente(java.lang.Double totalCliente) {
		this.totalCliente = totalCliente;
	}

	/**
	 * @return the totalMotorista
	 */
	public final java.lang.Double getTotalMotorista() {
		return totalMotorista;
	}

	/**
	 * @param totalMotorista the totalMotorista to set
	 */
	public final void setTotalMotorista(java.lang.Double totalMotorista) {
		this.totalMotorista = totalMotorista;
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
	 * @return the dataTermino
	 */
	public final java.lang.String getDataTermino() {
		return dataTermino;
	}

	/**
	 * @param dataTermino the dataTermino to set
	 */
	public final void setDataTermino(java.lang.String dataTermino) {
		this.dataTermino = dataTermino;
	}

	/**
	 * @return the horaTermino
	 */
	public final java.lang.String getHoraTermino() {
		return horaTermino;
	}

	/**
	 * @param horaTermino the horaTermino to set
	 */
	public final void setHoraTermino(java.lang.String horaTermino) {
		this.horaTermino = horaTermino;
	}

	/**
	 * @return the totalHoras
	 */
	public final java.lang.String getTotalHoras() {
		return totalHoras;
	}

	/**
	 * @param totalHoras the totalHoras to set
	 */
	public final void setTotalHoras(java.lang.String totalHoras) {
		this.totalHoras = totalHoras;
	}

	/**
	 * @return the dataVencimento
	 */
	public final java.lang.String getDataVencimento() {
		return dataVencimento;
	}

	/**
	 * @param dataVencimento the dataVencimento to set
	 */
	public final void setDataVencimento(java.lang.String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	/**
	 * @return the tipoOrdem
	 */
	public final java.lang.String getTipoOrdem() {
		return tipoOrdem;
	}

	/**
	 * @param tipoOrdem the tipoOrdem to set
	 */
	public final void setTipoOrdem(java.lang.String tipoOrdem) {
		this.tipoOrdem = tipoOrdem;
	}

	/**
	 * @return the aberta
	 */
	public final java.lang.String getAberta() {
		return aberta;
	}

	/**
	 * @param aberta the aberta to set
	 */
	public final void setAberta(java.lang.String aberta) {
		this.aberta = aberta;
	}

	/**
	 * @return the fechada
	 */
	public final java.lang.String getFechada() {
		return fechada;
	}

	/**
	 * @param fechada the fechada to set
	 */
	public final void setFechada(java.lang.String fechada) {
		this.fechada = fechada;
	}

	/**
	 * @return the cancelada
	 */
	public final java.lang.String getCancelada() {
		return cancelada;
	}

	/**
	 * @param cancelada the cancelada to set
	 */
	public final void setCancelada(java.lang.String cancelada) {
		this.cancelada = cancelada;
	}

	/**
	 * @return the opcao
	 */
	public final java.lang.String getOpcao() {
		return opcao;
	}

	/**
	 * @param opcao the opcao to set
	 */
	public final void setOpcao(java.lang.String opcao) {
		this.opcao = opcao;
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
	 * @return the clienteFk
	 */
	public java.lang.Integer getClienteFk() {
		return clienteFk;
	}

	/**
	 * @param clienteFk the clienteFk to set
	 */
	public void setClienteFk(java.lang.Integer clienteFk) {
		this.clienteFk = clienteFk;
	}

	/**
	 * @return the motoristaFk
	 */
	public java.lang.String getMotoristaFk() {
		return motoristaFk;
	}

	/**
	 * @param motoristaFk the motoristaFk to set
	 */
	public void setMotoristaFk(java.lang.String motoristaFk) {
		this.motoristaFk = motoristaFk;
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
	 * @return the dataTerminos
	 */
	public java.sql.Timestamp getDataTerminos() {
		return dataTerminos;
	}

	/**
	 * @param dataTerminos the dataTerminos to set
	 */
	public void setDataTerminos(java.sql.Timestamp dataTerminos) {
		this.dataTerminos = dataTerminos;
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

	/**
	 * @return the dataVencimentos
	 */
	public java.sql.Timestamp getDataVencimentos() {
		return dataVencimentos;
	}

	/**
	 * @param dataVencimentos the dataVencimentos to set
	 */
	public void setDataVencimentos(java.sql.Timestamp dataVencimentos) {
		this.dataVencimentos = dataVencimentos;
	}

}
