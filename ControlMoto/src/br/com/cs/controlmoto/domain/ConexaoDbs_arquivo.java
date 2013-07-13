package br.com.cs.controlmoto.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.swing.JOptionPane;

import br.com.cs.controlmoto.utils.LerArquivo;
import br.com.cs.controlmoto.vo.LerArquivoVO;

/** 
* 
* @author clovis 
*/
public class ConexaoDbs_arquivo {
		public Connection conn;
		public boolean status;
		public String banco, user, senha, servidor, mensagem, url;
		public int porta;
		public String caminho, separadorPath = "";
		protected java.util.Properties props;
		
		/** Creates a new instance of Conexao */
//		public void carregaArq(){
//			caminho = new File("").getAbsolutePath();
//			separadorPath = System.getProperty("file.separator");
//			//CAMINHO CORRETO PARA FUNCIONAR NO JAR
//			//File file = new File(caminho+separadorPath+"ControlMoto"+separadorPath+"resources"+separadorPath+"propertys.properties");
//			//CAMINHO CORRETO PARA FUNCIONAR NO ECLIPSE
//			File file = new File(caminho+separadorPath+"resources"+separadorPath+"propertys.properties");
//			props = new Properties();
//			FileInputStream fis = null;
//			try{
//				fis = new FileInputStream(file);
//				props.load(fis);
//				banco = props.getProperty("nome");
//				user = props.getProperty("user");
//				senha = props.getProperty("pass");
//				url = props.getProperty("url");
//				fis.close();
//			}		
//			catch (IOException e) {
//				JOptionPane.showMessageDialog(null,e.getMessage()+"\nErro ao ler informações do sistema","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
//			}
//		}
		
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
			//Estabelecendo a conexão através do ODBC criado no Painel de Controle:
			//CAMINHO CORRETO PARA FUNCIONAR NO JAR
			//conn = DriverManager.getConnection("jdbc:hsqldb:file:"+caminho+""+separadorPath+""+url+""+banco,user,senha);
			conn = DriverManager.getConnection("jdbc:hsqldb:file:"+caminho+separadorPath+"ControlMoto"+separadorPath+lerArquivoVO.getUrlBanco()+separadorPath+lerArquivoVO.getBancoDados(),lerArquivoVO.getUserBanco(),lerArquivoVO.getSenhaBanco());
			//URL para conexao com local em pasta
			//conn = DriverManager.getConnection("jdbc:hsqldb:file:"+caminho+""+separadorPath+""+url+""+banco,user,senha);
			//URL para conexao com um servidor
			//conn = DriverManager.getConnection("jdbc:hsqldb:hsql:/+url+separadorPath+banco",user,senha);
			//System.out.println(caminho+""+separadorPath+""+url);
			status=true;
			} catch(Exception e) {
				mensagem = "Problemas ao conectar o banco de dados. ";
				status=false;
				JOptionPane.showMessageDialog(null,"Erro ao conectar com o banco de dados.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
//				JOptionPane.showMessageDialog(null,"Problemas ao conectar o banco de dados.\n("+e.toString()+")","PROBLEMAS",JOptionPane.ERROR_MESSAGE);
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