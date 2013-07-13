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
public class DevedorVO {
    
    private java.lang.Integer devedorPk;
    private java.lang.String nomeDevedor;
    
    public DevedorVO(){
      
    }

    public String getNomeDevedor() {
        return nomeDevedor;
    }

    public void setNomeDevedor(String nomeDevedor) {
        this.nomeDevedor = nomeDevedor;
    }

    public Integer getDevedorPk() {
        return devedorPk;
    }

    public void setDevedorPk(Integer devedorPk) {
        this.devedorPk = devedorPk;
    }
    
}
