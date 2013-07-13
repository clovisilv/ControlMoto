/**
 * 
 */
package br.com.cs.controlmoto.vo;

/**
 * @author Clovis Silverio
 * @since 07/09/2012
 * @version 1.0.0
 */
public class CadParametrosRpsVO {
	
	private java.lang.String caminhoSaida = null;
	private java.lang.String nomeArquivo = null;
	private java.lang.String tipoDeRegistro="2"; 
	private java.lang.String tipoDoRPS="RPS  ";
	private java.lang.String serieDoRPS="     ";
	private java.lang.String numeroDoRPS="            ";
	private java.lang.String situacaoDoRPS="T";
	private java.lang.String codigoDoServicoPrestado="CodSe";
	private java.lang.String aliquota="0275";
	private java.lang.String iSSRetido="2";
	private java.lang.String discriminacaoDosServicos="";

	/**
	 * 
	 */
	public CadParametrosRpsVO() {
		// TODO Auto-generated constructor stub
	}

	public java.lang.String getCaminhoSaida() {
		return caminhoSaida;
	}

	public void setCaminhoSaida(java.lang.String caminhoSaida) {
		this.caminhoSaida = caminhoSaida;
	}

	public java.lang.String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(java.lang.String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public java.lang.String getTipoDeRegistro() {
		return tipoDeRegistro;
	}

	public void setTipoDeRegistro(java.lang.String tipoDeRegistro) {
		this.tipoDeRegistro = tipoDeRegistro;
	}

	public java.lang.String getTipoDoRPS() {
		return tipoDoRPS;
	}

	public void setTipoDoRPS(java.lang.String tipoDoRPS) {
		this.tipoDoRPS = tipoDoRPS;
	}

	public java.lang.String getSerieDoRPS() {
		return serieDoRPS;
	}

	public void setSerieDoRPS(java.lang.String serieDoRPS) {
		this.serieDoRPS = serieDoRPS;
	}

	public java.lang.String getNumeroDoRPS() {
		return numeroDoRPS;
	}

	public void setNumeroDoRPS(java.lang.String numeroDoRPS) {
		this.numeroDoRPS = numeroDoRPS;
	}

	public java.lang.String getSituacaoDoRPS() {
		return situacaoDoRPS;
	}

	public void setSituacaoDoRPS(java.lang.String situacaoDoRPS) {
		this.situacaoDoRPS = situacaoDoRPS;
	}

	public java.lang.String getCodigoDoServicoPrestado() {
		return codigoDoServicoPrestado;
	}

	public void setCodigoDoServicoPrestado(java.lang.String codigoDoServicoPrestado) {
		this.codigoDoServicoPrestado = codigoDoServicoPrestado;
	}

	public java.lang.String getAliquota() {
		return aliquota;
	}

	public void setAliquota(java.lang.String aliquota) {
		this.aliquota = aliquota;
	}

	public java.lang.String getiSSRetido() {
		return iSSRetido;
	}

	public void setiSSRetido(java.lang.String iSSRetido) {
		this.iSSRetido = iSSRetido;
	}

	public java.lang.String getDiscriminacaoDosServicos() {
		return discriminacaoDosServicos;
	}

	public void setDiscriminacaoDosServicos(
			java.lang.String discriminacaoDosServicos) {
		this.discriminacaoDosServicos = discriminacaoDosServicos;
	}

}
