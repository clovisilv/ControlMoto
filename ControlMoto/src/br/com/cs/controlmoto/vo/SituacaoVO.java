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
public class SituacaoVO {
    
   private java.lang.Integer situacaoPk;
   private java.lang.String descricao;
   private java.lang.String utilizacao;
   private java.lang.Integer emUso;

   public SituacaoVO() {
   }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getEmUso() {
        return emUso;
    }

    public void setEmUso(Integer emUso) {
        this.emUso = emUso;
    }

    public Integer getSituacaoPk() {
        return situacaoPk;
    }

    public void setSituacaoPk(Integer situacaoPk) {
        this.situacaoPk = situacaoPk;
    }

    public String getUtilizacao() {
        return utilizacao;
    }

    public void setUtilizacao(String utilizacao) {
        this.utilizacao = utilizacao;
    }
  
}
