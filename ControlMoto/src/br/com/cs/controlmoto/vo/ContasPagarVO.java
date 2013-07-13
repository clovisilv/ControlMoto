/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.vo;

/**
 *
 * @author marcio
 */
public class ContasPagarVO {
    
  private java.lang.Integer contasPagarPk;
  private java.lang.Integer credorFk;
  private java.lang.String dtDocumento;
  private java.lang.Integer especieDocumentoFk;
  private java.lang.Integer numDocumento;
  private java.lang.Integer tpPagto;
  private java.lang.Integer formaPagto;
  private java.lang.Double vlDocumento;
  private java.lang.String dtVencimento;
  private java.lang.String codBarra;
  private java.lang.Double vlPagar;
  private java.lang.Double vlJuros;
  private java.lang.Double vlMulta;
  private java.lang.Double vlDesconto;
  private java.lang.String dtQuitacao;
  private java.lang.String status;
  
  public ContasPagarVO() {
  }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public Integer getContasPagarPk() {
        return contasPagarPk;
    }

    public void setContasPagarPk(Integer contasPagarPk) {
        this.contasPagarPk = contasPagarPk;
    }

    public Integer getCredorFk() {
        return credorFk;
    }

    public void setCredorFk(Integer credorFk) {
        this.credorFk = credorFk;
    }

    public String getDtDocumento() {
        return dtDocumento;
    }

    public void setDtDocumento(String dtDocumento) {
        this.dtDocumento = dtDocumento;
    }

    public String getDtQuitacao() {
        return dtQuitacao;
    }

    public void setDtQuitacao(String dtQuitacao) {
        this.dtQuitacao = dtQuitacao;
    }

    public String getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(String dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public Integer getEspecieDocumentoFk() {
        return especieDocumentoFk;
    }

    public void setEspecieDocumentoFk(Integer especieDocumentoFk) {
        this.especieDocumentoFk = especieDocumentoFk;
    }

    public Integer getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(Integer formaPagto) {
        this.formaPagto = formaPagto;
    }

    public Integer getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(Integer numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTpPagto() {
        return tpPagto;
    }

    public void setTpPagto(Integer tpPagto) {
        this.tpPagto = tpPagto;
    }

    public Double getVlDocumento() {
        return vlDocumento;
    }

    public void setVlDocumento(Double vlDocumento) {
        this.vlDocumento = vlDocumento;
    }

    public Double getVlJuros() {
        return vlJuros;
    }

    public void setVlJuros(Double vlJuros) {
        this.vlJuros = vlJuros;
    }

    public Double getVlMulta() {
        return vlMulta;
    }

    public void setVlMulta(Double vlMulta) {
        this.vlMulta = vlMulta;
    }

    public Double getVlPagar() {
        return vlPagar;
    }

    public void setVlPagar(Double vlPagar) {
        this.vlPagar = vlPagar;
    }
    
    public Double getVlDesconto() {
        return vlDesconto;
    }

    public void setVlDesconto(Double vlDesconto) {
        this.vlDesconto = vlDesconto;
    }

  
}
