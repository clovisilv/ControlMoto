package br.com.cs.controlmoto.domain;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import br.com.cs.controlmoto.utils.LerArquivo;
import br.com.cs.controlmoto.vo.LerArquivoVO;

/** 
* @author clovis 
*/
public class ConexaoDbs {
		public Connection conn;
		public boolean status;
		public String banco, user, senha, servidor, mensagem;
		public String caminho, separadorPath = "";
		protected java.util.Properties props;
		
		public void Conexao() {
			separadorPath = System.getProperty("file.separator");
			caminho = new File("").getAbsolutePath();
			//carrega todas as variaveis do arquivo para conectar o BD...  
			//carregaArq();
			LerArquivoVO lerArquivoVO = new LerArquivoVO();
			LerArquivo lerArquivo = new LerArquivo();
			lerArquivoVO = lerArquivo.getInformacao(lerArquivoVO);
			try {
				//Registrando o driver:
				Class.forName("org.hsqldb.jdbcDriver").newInstance();
				}
			catch(Exception ex){
				mensagem = "Driver de BD não carregado. ";
				status=false;
				JOptionPane.showMessageDialog(null,"Problemas ao carregar o Driver do BD.\n("+ex.toString()+")","PROBLEMAS",JOptionPane.ERROR_MESSAGE);
				}
			try{
			//URL para conexao com um servidor
			//conn = DriverManager.getConnection("jdbc:hsqldb:hsql:"+separadorPath+"127.0.0.1"+separadorPath+"controlmotoip", user, senha);
			//conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/ControlMotoServer", "sa", "");
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql:"+separadorPath+""+separadorPath+lerArquivoVO.getServerUrl()+":"+lerArquivoVO.getServerPort()+""+separadorPath+""+lerArquivoVO.getServerDatabase(), lerArquivoVO.getServerUser(), lerArquivoVO.getServerPass());
			status=true;
			} catch(Exception e) {
				mensagem = "Problemas ao conectar o banco de dados. ";
				status=false;
				JOptionPane.showMessageDialog(null,"Erro ao conectar com o banco de dados.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		
		public Connection getConexao() {
			return conn;
		}
		
		public void disconnect(){
			//Fechando a conexão:
			try{
				conn.close();
				status=false;
				}
			catch(Exception e) {
				mensagem = "Problemas ao encerrar conexão. ";
				JOptionPane.showMessageDialog(null,"Problemas ao encerrar conexão.\n("+e.toString()+")","PROBLEMAS",JOptionPane.ERROR_MESSAGE);
				}
		}
		
		
	}