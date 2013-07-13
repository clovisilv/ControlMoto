package br.com.cs.controlmoto.vo;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;


public class CadMotoristaVO {
	
	private java.lang.String motoristaPK;
	private java.lang.Integer enderecoFk;
	private java.lang.Integer telefoneFk;
	private java.lang.String nome;
	private java.lang.String numero;
	private java.lang.String banco;
	private java.lang.String conta;
	private java.lang.String agencia;
	private java.lang.String tipo;
	private java.lang.String comissao;
	private java.lang.String rg;
	private java.lang.String cpf;
	private java.lang.String habilitacao;
	private java.lang.String categoria;
	private java.lang.String dataVencimento;
	private java.util.Date dataVencimentos;
	private java.lang.String dataNascimento;
	private java.util.Date dataNascimentos;
	private java.lang.String dataCadastro;
	private java.util.Date dataCadastros;
	
	public CadMotoristaVO(){
	}
	
	/**
	 * @return the motoristaPK
	 */
	public final java.lang.String getMotoristaPK() {
		return motoristaPK;
	}

	/**
	 * @param motoristaPK the motoristaPK to set
	 */
	public final void setMotoristaPK(java.lang.String motoristaPK) {
		this.motoristaPK = motoristaPK;
	}

	/**
	 * @return the enderecoFk
	 */
	public java.lang.Integer getEnderecoFk() {
		return enderecoFk;
	}

	/**
	 * @param enderecoFk the enderecoFk to set
	 */
	public void setEnderecoFk(java.lang.Integer enderecoFk) {
		this.enderecoFk = enderecoFk;
	}

	/**
	 * @return the telefoneFk
	 */
	public java.lang.Integer getTelefoneFk() {
		return telefoneFk;
	}

	/**
	 * @param telefoneFk the telefoneFk to set
	 */
	public void setTelefoneFk(java.lang.Integer telefoneFk) {
		this.telefoneFk = telefoneFk;
	}

	/**
	 * @return the nome
	 */
	public final java.lang.String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public final void setNome(java.lang.String nome) {
		this.nome = nome;
	}

	/**
	 * @return the numero
	 */
	public java.lang.String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(java.lang.String numero) {
		this.numero = numero;
	}

	/**
	 * @return the banco
	 */
	public final java.lang.String getBanco() {
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	public final void setBanco(java.lang.String banco) {
		this.banco = banco;
	}

	/**
	 * @return the conta
	 */
	public final java.lang.String getConta() {
		return conta;
	}

	/**
	 * @param conta the conta to set
	 */
	public final void setConta(java.lang.String conta) {
		this.conta = conta;
	}

	/**
	 * @return the agencia
	 */
	public final java.lang.String getAgencia() {
		return agencia;
	}

	/**
	 * @param agencia the agencia to set
	 */
	public final void setAgencia(java.lang.String agencia) {
		this.agencia = agencia;
	}

	/**
	 * @return the tipo
	 */
	public final java.lang.String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public final void setTipo(java.lang.String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the comissao
	 */
	public final java.lang.String getComissao() {
		return comissao;
	}

	/**
	 * @param comissao the comissao to set
	 */
	public final void setComissao(java.lang.String comissao) {
		this.comissao = comissao;
	}

	/**
	 * @return the rg
	 */
	public final java.lang.String getRg() {
		return rg;
	}

	/**
	 * @param rg the rg to set
	 */
	public final void setRg(java.lang.String rg) {
		this.rg = rg;
	}

	/**
	 * @return the cpf
	 */
	public final java.lang.String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public final void setCpf(java.lang.String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the habilitacao
	 */
	public final java.lang.String getHabilitacao() {
		return habilitacao;
	}

	/**
	 * @param habilitacao the habilitacao to set
	 */
	public final void setHabilitacao(java.lang.String habilitacao) {
		this.habilitacao = habilitacao;
	}

	/**
	 * @return the categoria
	 */
	public final java.lang.String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public final void setCategoria(java.lang.String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the dataVencimento
	 */
	public final java.lang.String getDataVencimento() {
		return dataVencimento;
	}

	/**
	 * @param dataVencimento the dataVencimento to set
	 */
	public final void setDataVencimento(java.lang.String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	/**
	 * @return the dataVencimento
	 */
	public final java.util.Date getVencimentoAsDate() {
		Date data = null;
		try{
			data = DateFormat.getDateInstance(DateFormat.SHORT,Locale.FRANCE).parse(this.dataVencimento);
		}catch (ParseException pe) {}
		catch(Exception e){}
			return data;
		}

	/**
	 * @param dataVencimento the dataVencimento to set
	 */
	public final void setVencimentoAsDate(java.util.Date dataVencimento) {
		try{
			this.dataVencimento = DateFormat.getDateInstance(DateFormat.SHORT,Locale.FRANCE).format((Date)dataVencimento);
		}catch (Exception e) {}
	}

	/**
	 * @return the dataNascimento
	 */
	public final java.lang.String getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento the dataNascimento to set
	 */
	public final void setDataNascimento(java.lang.String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	/**
	 * @return the dataNascimento
	 */
	public final java.util.Date getDataNascimentoAsDate() {
		Date data = null;
		try{
			data = DateFormat.getDateInstance(DateFormat.SHORT,Locale.FRANCE).parse(this.dataNascimento);
		}catch (ParseException pe) {}
		catch(Exception e){}
			return data;
		}

	/**
	 * @param dataNascimento the dataNascimento to set
	 */
	public final void setDataNascimentoAsDate(java.util.Date dataNascimento) {
		try{
			this.dataNascimento = DateFormat.getDateInstance(DateFormat.SHORT,Locale.FRANCE).format((Date)dataNascimento);
		}catch (Exception e) {}
	}

	/**
	 * @return the dataCadastro
	 */
	public final java.lang.String getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public final void setDataCadastro(java.lang.String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	/**
	 * @return the dataVencimentos
	 */
	public java.util.Date getDataVencimentos() {
		return dataVencimentos;
	}

	/**
	 * @param date the dataVencimentos to set
	 */
	public void setDataVencimentos(java.util.Date date) {
		this.dataVencimentos = date;
	}

	/**
	 * @return the dataNascimentos
	 */
	public java.util.Date getDataNascimentos() {
		return dataNascimentos;
	}

	/**
	 * @param date the dataNascimentos to set
	 */
	public void setDataNascimentos(java.util.Date date) {
		this.dataNascimentos = date;
	}

	/**
	 * @return the dataCadastros
	 */
	public java.util.Date getDataCadastros() {
		return dataCadastros;
	}

	/**
	 * @param dataCadastros the dataCadastros to set
	 */
	public void setDataCadastros(java.sql.Timestamp dataCadastros) {
		this.dataCadastros = dataCadastros;
	}

	/**
	 * @return the dataCadastro
	 */
	public final java.util.Date getDataCadastroAsDate() {
		Date data = null;
		try{
			data = DateFormat.getDateInstance(DateFormat.SHORT,Locale.FRANCE).parse(this.dataCadastro);
		}catch (ParseException pe) {}
		catch(Exception e){}
			return data;
		}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public final void setDataCadastroAsDate(java.util.Date dataCadastro) {
		try{
			this.dataCadastro = DateFormat.getDateInstance(DateFormat.SHORT,Locale.FRANCE).format((Date)dataCadastro);
		}catch (Exception e) {}
	}
	
}
