package br.com.cs.controlmoto.vo;

public class CadUsuariosVO {
	
	private java.lang.Integer usuarioPK;
	private java.lang.String nome;
	private java.lang.String usuario;
	private java.lang.String senha;
	private java.lang.String confirmaSenha;
	private java.lang.String tipoUsuario;
	private java.lang.String status;
	private static java.lang.String usuarioLogin;
	private static java.lang.String tipoUsuarioLogin;
	private static java.lang.String statusLogin;
	
	
	public void CadUsuarioVO(){
	}
	
	public java.lang.Integer getUsuarioPK() {
		return usuarioPK;
	}
	public void setUsuarioPK(java.lang.Integer usuarioPK) {
		this.usuarioPK = usuarioPK;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the usuarioLogin
	 */
	public static java.lang.String getUsuarioLogin() {
		return usuarioLogin;
	}

	/**
	 * @param usuarioLogin the usuarioLogin to set
	 */
	public static void setUsuarioLogin(java.lang.String usuarioLogin) {
		CadUsuariosVO.usuarioLogin = usuarioLogin;
	}

	/**
	 * @return the tipoUsuarioLogin
	 */
	public java.lang.String getTipoUsuarioLogin() {
		return tipoUsuarioLogin;
	}

	/**
	 * @param tipoUsuarioLogin the tipoUsuarioLogin to set
	 */
	public static void setTipoUsuarioLogin(java.lang.String tipoUsuarioLogin) {
		CadUsuariosVO.tipoUsuarioLogin = tipoUsuarioLogin;
	}

	/**
	 * @return the statusLogin
	 */
	public java.lang.String getStatusLogin() {
		return statusLogin;
	}

	/**
	 * @param statusLogin the statusLogin to set
	 */
	public static void setStatusLogin(java.lang.String statusLogin) {
		CadUsuariosVO.statusLogin = statusLogin;
	}
	
}
