package br.com.cs.controlmoto.utils;

public class Elemento {
	
	public String fornecedorPK;
	public String nome;
	
	public String toString(){
		return this.nome;
	}
	
	public String getFornecedorPK(){
		return fornecedorPK;
	}
	
	public String setFornecedorPK(String fornecedorPK){
		return this.fornecedorPK = fornecedorPK;
	}
	
	public String getNome(String nome){
		return nome;
	}
	
	public String setNome(String nome){
		return this.nome = nome;
	}

}
