/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.cs.controlmoto.vo;

/**
 * @author clovis
 * @version 1.0.1
 */
public class CadTarefaVO {
    
  private java.lang.Integer codigoTarefa;
  private java.lang.String descricaoTarefa;
  private java.lang.String statusTarefa;

  public CadTarefaVO () {
  }

	public java.lang.Integer getCodigoTarefa() {
		return codigoTarefa;
	}
	
	public void setCodigoTarefa(java.lang.Integer codigoTarefa) {
		this.codigoTarefa = codigoTarefa;
	}
	
	public java.lang.String getDescricaoTarefa() {
		return descricaoTarefa;
	}
	
	public void setDescricaoTarefa(java.lang.String descricaoTarefa) {
		this.descricaoTarefa = descricaoTarefa;
	}
	
	public java.lang.String getStatusTarefa() {
		return statusTarefa;
	}
	
	public void setStatusTarefa(java.lang.String statusTarefa) {
		this.statusTarefa = statusTarefa;
	}
	
}

