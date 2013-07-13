package br.com.cs.controlmoto.vo;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class CadEmpresaVO {
	
	private java.lang.Integer empresaPK;
	private java.lang.String nome;
	private java.lang.String cnpj;
	private java.lang.String ccm;
	private java.lang.String inscricaoEstadual;
	private br.com.cs.controlmoto.vo.CadEnderecoVO enderecoVO;
	private java.lang.Integer enderecoFk;
//	private java.lang.String cidade;
//	private java.lang.String estado;
//	private java.lang.String cep;
	private br.com.cs.controlmoto.vo.CadTelefoneVO telefoneVO;
	private java.lang.Integer telefoneFk;
//	private java.lang.String telefones;	
	private java.lang.String site;
	private java.lang.String email;
	private java.sql.Timestamp dataCadastros;
	private java.lang.String dataCadastro;
	
	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(java.lang.String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void CadEmpresa(){		
	}

	/**
	 * @return the empresaPK
	 */
	public final java.lang.Integer getEmpresaPK() {
		return empresaPK;
	}

	/**
	 * @param empresaPK the empresaPK to set
	 */
	public final void setEmpresaPK(java.lang.Integer empresaPK) {
		this.empresaPK = empresaPK;
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
	 * @return the cnpj
	 */
	public final java.lang.String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj the cnpj to set
	 */
	public final void setCnpj(java.lang.String cnpj) {
		this.cnpj = cnpj;
	}

	public java.lang.String getCcm() {
		return ccm;
	}

	public void setCcm(java.lang.String ccm) {
		this.ccm = ccm;
	}

	/**
	 * @return the inscricaoEstadual
	 */
	public final java.lang.String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	/**
	 * @param inscricaoEstadual the inscricaoEstadual to set
	 */
	public final void setInscricaoEstadual(java.lang.String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	/**
	 * @return the enderecoVO
	 */
	public br.com.cs.controlmoto.vo.CadEnderecoVO getEnderecoVO() {
		return enderecoVO;
	}

	/**
	 * @param enderecoVO the enderecoVO to set
	 */
	public void setEnderecoVO(br.com.cs.controlmoto.vo.CadEnderecoVO enderecoVO) {
		this.enderecoVO = enderecoVO;
	}

	/**
	 * @return the telefoneVO
	 */
	public br.com.cs.controlmoto.vo.CadTelefoneVO getTelefoneVO() {
		return telefoneVO;
	}

	/**
	 * @param telefoneVO the telefoneVO to set
	 */
	public void setTelefoneVO(br.com.cs.controlmoto.vo.CadTelefoneVO telefoneVO) {
		this.telefoneVO = telefoneVO;
	}

//
//	/**
//	 * @return the bairro
//	 */
//	public final java.lang.String getBairro() {
//		return bairro;
//	}
//
//	/**
//	 * @param bairro the bairro to set
//	 */
//	public final void setBairro(java.lang.String bairro) {
//		this.bairro = bairro;
//	}
//
//	/**
//	 * @return the cidade
//	 */
//	public final java.lang.String getCidade() {
//		return cidade;
//	}
//
//	/**
//	 * @param cidade the cidade to set
//	 */
//	public final void setCidade(java.lang.String cidade) {
//		this.cidade = cidade;
//	}
//
//	/**
//	 * @return the estado
//	 */
//	public final java.lang.String getEstado() {
//		return estado;
//	}
//
//	/**
//	 * @param estado the estado to set
//	 */
//	public final void setEstado(java.lang.String estado) {
//		this.estado = estado;
//	}
//
//	/**
//	 * @return the cep
//	 */
//	public final java.lang.String getCep() {
//		return cep;
//	}
//
//	/**
//	 * @param cep the cep to set
//	 */
//	public final void setCep(java.lang.String cep) {
//		this.cep = cep;
//	}

//
//	/**
//	 * @return the fax
//	 */
//	public final java.lang.String getFax() {
//		return fax;
//	}
//
//	/**
//	 * @param fax the fax to set
//	 */
//	public final void setFax(java.lang.String fax) {
//		this.fax = fax;
//	}
//
//	/**
//	 * @return the telefones
//	 */
//	public final java.lang.String getTelefones() {
//		return telefones;
//	}
//
//	/**
//	 * @param telefones the telefones to set
//	 */
//	public final void setTelefones(java.lang.String telefones) {
//		this.telefones = telefones;
//	}

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
	 * @return the site
	 */
	public final java.lang.String getSite() {
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public final void setSite(java.lang.String site) {
		this.site = site;
	}

	/**
	 * @return the email
	 */
	public final java.lang.String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public final void setEmail(java.lang.String email) {
		this.email = email;
	}

	/**
	 * @return the dataCadastro
	 */
	public final java.sql.Timestamp getDataCadastros() {
		return dataCadastros;
	}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public final void setDataCadastros(java.sql.Timestamp dataCadastros) {
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
