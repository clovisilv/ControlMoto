package br.com.cs.controlmoto.vo;

public class CadTabelaPrecoVO {
	
    private java.lang.Integer IdTabelaPreco;
    private java.lang.String codigoTabela;
    private java.lang.String descricaoTabPreco;    
    private java.lang.Integer sequencia;
    private java.lang.String descricaoItem;
    private java.lang.Double preco;
    private java.lang.Integer usuarioCriacaoFk;    
    private java.sql.Date dataCriacao;
    private java.sql.Date dataAlteracao;
    private java.lang.Integer usuarioAlteracaoFk;
    private java.lang.String comboTabPreco;
    private java.lang.String situacao;
    
    public void CadTabelaPrecoVO(){
    	
    }

	public java.lang.Integer getIdTabelaPreco() {
		return IdTabelaPreco;
	}

	public void setIdTabelaPreco(java.lang.Integer idTabelaPreco) {
		IdTabelaPreco = idTabelaPreco;
	}

	public java.lang.String getCodigoTabela() {
		return codigoTabela;
	}

	public void setCodigoTabela(java.lang.String codigoTabela) {
		this.codigoTabela = codigoTabela;
	}

	public java.lang.String getDescricaoTabPreco() {
		return descricaoTabPreco;
	}

	public void setDescricaoTabPreco(java.lang.String descricaoTabPreco) {
		this.descricaoTabPreco = descricaoTabPreco;
	}

	public java.lang.Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(java.lang.Integer sequencia) {
		this.sequencia = sequencia;
	}

	public java.lang.String getDescricaoItem() {
		return descricaoItem;
	}

	public void setDescricaoItem(java.lang.String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}

	public java.lang.Double getPreco() {
		return preco;
	}

	public void setPreco(java.lang.Double preco) {
		this.preco = preco;
	}

	public java.lang.Integer getUsuarioCriacaoFk() {
		return usuarioCriacaoFk;
	}

	public void setUsuarioCriacaoFk(java.lang.Integer usuarioCriacaoFk) {
		this.usuarioCriacaoFk = usuarioCriacaoFk;
	}

	public java.sql.Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(java.sql.Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public java.sql.Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(java.sql.Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public java.lang.Integer getUsuarioAlteracaoFk() {
		return usuarioAlteracaoFk;
	}

	public void setUsuarioAlteracaoFk(java.lang.Integer usuarioAlteracaoFk) {
		this.usuarioAlteracaoFk = usuarioAlteracaoFk;
	}

	public java.lang.String getComboTabPreco() {
		return comboTabPreco;
	}

	public void setComboTabPreco(java.lang.String comboTabPreco) {
		this.comboTabPreco = comboTabPreco;
	}

	public java.lang.String getSituacao() {
		return situacao;
	}

	public void setSituacao(java.lang.String situacao) {
		this.situacao = situacao;
	}
    
}
