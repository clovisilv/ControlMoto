package br.com.cs.controlmoto.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class ValidaData {
	
	private SimpleDateFormat sdf;
	private Date datas;
	private Timestamp dataTimestamp;
	private Date data;
    private boolean isDate;
	
	public ValidaData(){
		
		sdf = new SimpleDateFormat("dd/mm/yyyy");
		datas = new Date();
	}
	
	public  void getValidaData(String data){
		try {
			datas = sdf.parse(data);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Data inválida\n"+e.getMessage(), "", JOptionPane.INFORMATION_MESSAGE);

		}
	}
    
    public Boolean validarData(String dt){
        if(!"null".equals(dt) || !"  /  /    ".equals(dt)){
            try {
                sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                data = sdf.parse(dt);

                isDate = true;
            } catch (ParseException ex) {
                Logger.getLogger(ValidaData.class.getName()).log(Level.SEVERE, null, ex);
                isDate = false;
            }
            return isDate;
        }
        return null;
    }

}
