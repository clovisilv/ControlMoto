package br.com.cs.controlmoto.vo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Fictec
 */
public class AgendamentoVO {
    
   private java.lang.Integer agendaPk;
   private java.sql.Date dataAgendada;
   private java.lang.String dtAgendada;
   private java.sql.Time horaAgendada;
   private java.lang.String hrAgendada;   
   private java.lang.Integer clienteAFk;
   private java.lang.String nomeCliente;
   private java.lang.Integer tarefaAFk;
   private java.lang.String nomeTarefa;
   private java.lang.String statusAgenda;
   private java.lang.String motoristaAFk;
   private java.lang.String nomeMotorista;
   private java.lang.Integer numeroOS;
   private java.lang.Integer dia;
   private java.lang.Integer mes;
   private java.lang.Integer ano;
   
   
   
   public AgendamentoVO(){
       
   }

    public Integer getAgendaPk() {
        return agendaPk;
    }

    public void setAgendaPk(Integer agendaPk) {
        this.agendaPk = agendaPk;
    }

    public Integer getClienteAFk() {
        return clienteAFk;
    }

    public void setClienteAFk(Integer clienteAFk) {
        this.clienteAFk = clienteAFk;
    }

    public Date getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(Date dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public String getDtAgendada() {
        return dtAgendada;
    }

    public void setDtAgendada(String dtAgendada) {
        this.dtAgendada = dtAgendada;
    }

    public Time getHoraAgendada() {
        return horaAgendada;
    }

    public void setHoraAgendada(Time horaAgendada) {
        this.horaAgendada = horaAgendada;
    }

    public String getHrAgendada() {
        return hrAgendada;
    }

    public void setHrAgendada(String hrAgendada) {
        this.hrAgendada = hrAgendada;
    }
    
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getMotoristaAFk() {
        return motoristaAFk;
    }

    public void setMotoristaAFk(String motoristaAFk) {
        this.motoristaAFk = motoristaAFk;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public Integer getNumeroOS() {
        return numeroOS;
    }

    public void setNumeroOS(Integer numeroOS) {
        this.numeroOS = numeroOS;
    }

    public String getStatusAgenda() {
        return statusAgenda;
    }

    public void setStatusAgenda(String statusAgenda) {
        this.statusAgenda = statusAgenda;
    }

    public Integer getTarefaAFk() {
        return tarefaAFk;
    }

    public void setTarefaAFk(Integer tarefaAFk) {
        this.tarefaAFk = tarefaAFk;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

}