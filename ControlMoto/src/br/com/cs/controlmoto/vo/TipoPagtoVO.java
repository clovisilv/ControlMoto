/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.vo;

/**
 * @author Clovis
 * @version 1.0.01
 * @since 15092012
 */
public class TipoPagtoVO {
    
  private java.lang.Integer tipoPagtoPk;
  private java.lang.String codTpPagto;
  private java.lang.String descricao;

  public TipoPagtoVO() {
  }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getTipoPagtoPk() {
        return tipoPagtoPk;
    }

    public void setTipoPagtoPk(Integer tipoPagtoPk) {
        this.tipoPagtoPk = tipoPagtoPk;
    }
    
    public String getCodTpPagto() {
        return codTpPagto;
    }

    public void setCodTpPagto(String codTpPagto) {
        this.codTpPagto = codTpPagto;
    }
      
}
