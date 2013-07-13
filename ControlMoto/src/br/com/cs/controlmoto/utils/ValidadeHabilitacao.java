package br.com.cs.controlmoto.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidadeHabilitacao {
	
	Timestamp vencimentoHabilitacao=null, dataNascimento=null, dataAtual=null;
	SimpleDateFormat sdf = null;
	Date dataSistema = null;
	java.lang.Long dtAtual=null, dtNascimento = null, diferenca=null;
			
	public boolean verificarValidadeHabilitacao(java.lang.String dataHabilitacao){
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		dataSistema = new Date();
		dataAtual = new Timestamp(dataSistema.getTime());
		try {
			vencimentoHabilitacao = new java.sql.Timestamp(sdf.parse(dataHabilitacao).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(vencimentoHabilitacao.before(dataAtual)){
			return false;
		}
		return true;
	}
	
	public boolean calularIdade(java.lang.String dataNascimento1){
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		dataSistema = new Date();
		dataAtual = new Timestamp(dataSistema.getTime());
		try{
			dataNascimento = new java.sql.Timestamp(sdf.parse(dataNascimento1).getTime());
		}catch(ParseException e){
			e.printStackTrace();
		}
		diferenca = ((dataAtual.getTime()-dataNascimento.getTime())/(24*60*60*1000)/360); 
		if(diferenca<18){
			return false;
		}		
		return true;
	}

}
