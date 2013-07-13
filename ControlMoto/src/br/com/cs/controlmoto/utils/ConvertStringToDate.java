/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author clovis
 * @since 02/08/2012
 * @version 1.0.2
 */
public class ConvertStringToDate {
    
    private java.util.Date data;
    private java.lang.String dataF;
    private java.sql.Date datasql;
    private java.text.SimpleDateFormat sdf;
    
    public String convertStringToData(String dt) throws ParseException{
        if(!"  /  /    ".equals(dt) && null != dt){
            sdf = new SimpleDateFormat("dd/MM/yyyy");      
            data = sdf.parse( dt.replace("-","").substring(6,8)+"/"+(Integer.valueOf(dt.replace("-","").substring(4,6)) < 10 ? "0"+dt.replace("-","").substring(4,6) : dt.replace("-","").substring(4,6) )+"/"+dt.replace("-","").substring(0,4) );

            return String.valueOf(sdf.format(data));    
        }
        return null;
    }
    
    public java.sql.Date convertStringToDates(String dt) throws ParseException{
        if(!"null".equals(dt) || !"  /  /    ".equals(dt)){        
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            datasql = new java.sql.Date(sdf.parse(dt).getTime());

            return datasql;    
        }
        return null;
    }
    
    public String formatDate(java.util.Date dt) throws ParseException{
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        dataF = sdf.format(dt);

        return dataF;
    }
    
    public String formatDateAmerinaca(java.util.Date dt) throws ParseException{
        sdf = new SimpleDateFormat("yyyy/MM/dd");
        dataF = sdf.format(dt);

        return dataF;
    }
    
    /*
     * Metodo que formata a data para nomear arquivos 
     */
    public String formatDateHora(java.util.Date dt) throws ParseException{
        sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        dataF = sdf.format(dt);

        return dataF;
    }
    /*
     * Metodo que formata a data para ir no arquivo RPS no padrao ano, mes e dia 
     */
    public String formatDateRps(java.util.Date dt) throws ParseException{
        sdf = new SimpleDateFormat("yyyyMMdd");
        dataF = sdf.format(dt);

        return dataF;
    }

}
