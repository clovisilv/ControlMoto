/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.vo;

/**
 *
 * @author Fictec
 */
public class TarefaVO {
    
   java.lang.Integer tarefaPk;
   java.lang.String descricaoTarefa;
   
   public TarefaVO(){
       
   }

    public String getDescricaoTarefa() {
        return descricaoTarefa;
    }

    public void setDescricaoTarefa(String descricaoTarefa) {
        this.descricaoTarefa = descricaoTarefa;
    }

    public Integer getTarefaPk() {
        return tarefaPk;
    }

    public void setTarefaPk(Integer tarefaPk) {
        this.tarefaPk = tarefaPk;
    }
      
}