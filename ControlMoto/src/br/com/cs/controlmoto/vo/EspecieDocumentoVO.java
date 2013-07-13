/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.vo;

/**
 * @author clovis
 * @version 1.0.1
 * @since 15092012
 */
public class EspecieDocumentoVO {
    
  private java.lang.Integer especieDocumentoPk;
  private java.lang.String codEspDocumento;
  private java.lang.String descricao;
  private java.lang.String status;

  public EspecieDocumentoVO() {
  }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getEspecieDocumentoPk() {
        return especieDocumentoPk;
    }

    public String getCodEspDocumento() {
        return codEspDocumento;
    }

    public void setCodEspDocumento(String codEspDocumento) {
        this.codEspDocumento = codEspDocumento;
    }

    public void setEspecieDocumentoPk(Integer especieDocumentoPk) {
        this.especieDocumentoPk = especieDocumentoPk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
