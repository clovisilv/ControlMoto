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
public class CredorVO {
    
    private java.lang.Integer credorPk;
    private java.lang.String nomeCredor;
    
    public CredorVO(){
      
    }

    public String getNomeCredor() {
        return nomeCredor;
    }

    public void setNomeCredor(String nomeCredor) {
        this.nomeCredor = nomeCredor;
    }

    public Integer getCredorPk() {
        return credorPk;
    }

    public void setCredorPk(Integer credorPk) {
        this.credorPk = credorPk;
    }
    
}
