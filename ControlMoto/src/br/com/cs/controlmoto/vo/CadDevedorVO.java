/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.vo;

/**
 * @author clovis
 * @version 1.0.1
 */
public class CadDevedorVO {

   private java.lang.String jrbAtivo;
   private java.lang.String lblCodigoCampo;
   private java.lang.String txtRazaoSocial;
   
   public CadDevedorVO(){
       
   }

    public String getJrbAtivo() {
        return jrbAtivo;
    }

    public void setJrbAtivo(String jrbAtivo) {
        this.jrbAtivo = jrbAtivo;
    }

    public String getLblCodigoCampo() {
        return lblCodigoCampo;
    }

    public void setLblCodigoCampo(String lblCodigoCampo) {
        this.lblCodigoCampo = lblCodigoCampo;
    }

    public String getTxtRazaoSocial() {
        return txtRazaoSocial;
    }

    public void setTxtRazaoSocial(String txtRazaoSocial) {
        this.txtRazaoSocial = txtRazaoSocial;
    }
    
}
