/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.vo;

/**
 * @author Clovis
 * @version 1.0.0
 * @since 15092012
 */
public class FormaPagtoVO {
    
  private java.lang.Integer formaPagtoPk;
  private java.lang.Integer codForma;
  private java.lang.String descricao;

  public FormaPagtoVO() {
  }

    public Integer getCodForma() {
        return codForma;
    }

    public void setCodForma(Integer codForma) {
        this.codForma = codForma;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getFormaPagtoPk() {
        return formaPagtoPk;
    }

    public void setFormaPagtoPk(Integer formaPagtoPk) {
        this.formaPagtoPk = formaPagtoPk;
    }
    
}
