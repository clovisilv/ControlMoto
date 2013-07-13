/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.vo;

/**
 * @author clovis
 * @version 1.0.0
 * @since 15092012
 */
public class FormaCobrancaJurosVO {
    
  private java.lang.Integer formaCobrancaJurosPk;
  private java.lang.String codFormaCobrancaJuros;

    public String getCodFormaCobrancaJuros() {
        return codFormaCobrancaJuros;
    }

    public void setCodFormaCobrancaJuros(String codFormaCobrancaJuros) {
        this.codFormaCobrancaJuros = codFormaCobrancaJuros;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getFormaCobrancaJurosPk() {
        return formaCobrancaJurosPk;
    }

    public void setFormaCobrancaJurosPk(Integer formaCobrancaJurosPk) {
        this.formaCobrancaJurosPk = formaCobrancaJurosPk;
    }
  private java.lang.String descricao;

  public FormaCobrancaJurosVO() {
  }
  
  
    
}
