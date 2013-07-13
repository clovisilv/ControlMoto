/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @company Fictec Cons. Inform.
 * @author Clovis
 * @since 02/11/2012
 * @version 1.0.0
 */
public class FormataValor {
    java.text.DecimalFormat df;
    java.lang.String vlFormat;
    java.lang.String vlDecimal;
    
    public String formatValor(String vlF){
       
        vlFormat = NumberFormat.getCurrencyInstance().format(Double.valueOf(vlF));
        return vlFormat.replace("R$","").trim();        
    
    }
    /*
     * Converte um numero com ponto e casa decimal para virgula e duas casas decimais
     */
    public String decimalFormat(String vlD){
        df = new DecimalFormat(",##0.00");
        vlDecimal = df.format(Double.valueOf(vlD));
        
        return vlDecimal;
    }
    /*
     * Converte string em decimal para o banco de dados
     */
    public String decimalFormatToDB(String vlD){
        df = new DecimalFormat("0.00");
        vlDecimal = df.format(Double.valueOf(vlD));
        
        return vlDecimal;
    }
    //
    public String decimalFormatToVigula(String vlD){
        if(vlD.replace(".","").length() <= 5){
            vlDecimal = vlD.replace(".",",");
        }else{
            vlDecimal = vlD.replace(".",",");
            char v1; String v2 = null;
            for(int i = vlDecimal.length(); i > 0; i--){
                v1= vlDecimal.charAt(i);
                if(i==5){
                    v2 = ","+String.valueOf(v1)+v2;
                }
                if(i==2){
                    v2 = "."+String.valueOf(v1)+v2;
                }
            }
        }
        return vlDecimal;
    }
    //
    public Double fomataEmDouble(String valor){
    	
    	return null;
    	
    }
    
    
}

