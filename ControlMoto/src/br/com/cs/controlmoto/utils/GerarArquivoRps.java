package br.com.cs.controlmoto.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JOptionPane;

import br.com.cs.controlmoto.domain.CadEmpresaDAO;
import br.com.cs.controlmoto.vo.CadEmpresaVO;
import br.com.cs.controlmoto.vo.TabGerarRpsFaturaVO;

public class GerarArquivoRps {
	
	//3.3 REGISTRO TIPO 2 – DETALHE
	private java.lang.String caminhoSaida = null;
	private java.lang.String nomeArquivo = null;
	private java.util.Date data;
	private java.lang.String cabecalho = null, linhaDados = null;
	private java.lang.String tipoDeRegistro="2"; 
	private java.lang.String tipoDoRPS="RPS  ";
	private java.lang.String serieDoRPS="     ";
	private java.lang.String numeroDoRPS="000000000000";
	private java.lang.String dataDeEmissaoDoRPS="";
	private java.lang.String situacaoDoRPS="T";
	private java.lang.String valorDosServicos="000000000000000";
	private java.lang.String valorDasDeducoes="000000000000000";
	private java.lang.String codigoDoServicoPrestado="CodSe";
	private java.lang.String aliquota="0275";
	private java.lang.String iSSRetido="2";
	private java.lang.String indicadorDeCpfCnpjDoTomador="";
	private java.lang.String cpfOuCnpjDoTomador="";
	private java.lang.String inscricaoMunicipalDoTomador="        ";
	private java.lang.String inscricaoEstadualDoTomador="            ";
	private java.lang.String nomeRazaoSocialDoTomador="                                                                           ";
	private java.lang.String nomeRazaoSocialDoTomadorApaga="                                                                           ";
	private java.lang.String tipoDoEnderecoDoTomador="   ";
	private java.lang.String tipoDoEnderecoDoTomadorApaga="   ";
	private java.lang.String enderecoDoTomador="                                                  ";
	private java.lang.String enderecoDoTomadorApaga="                                                  ";
	private java.lang.String numeroDoEnderecoDoTomador="0000000000";
	private java.lang.String complementoDoEndereçoDoTomador="                              ";
	private java.lang.String bairroDoTomador="                              ";
	private java.lang.String bairroDoTomadorApaga="                              ";
	private java.lang.String cidadeDoTomador="                                                  ";
	private java.lang.String cidadeDoTomadorApaga="                                                  ";
	private java.lang.String ufDoTomador="  ";
	private java.lang.String cepDoTomador="       ";
	private java.lang.String emailDoTomador="                                                                           ";
	private java.lang.String emailDoTomadorApaga="                                                                           ";
	private java.lang.String discriminacaoDosServicos="";
	private java.lang.String ccm=null;
	private java.lang.Integer numLinhas = 0;
	//3.5 REGISTRO TIPO 9 – RODAPÉ
	private java.lang.String tipoDeRegistroRodape = "9";
	private java.lang.String numeroDeLinhasDeDetalheDoArquivo = "0000000";
	private java.lang.String valorTotalDosServicosContidoNoArquivo = "000000000000000";
	private java.lang.String valorTotalDasDeducoesContidasNoArquivo = "000000000000000";
	private java.lang.String caractereDeFimDeLinha = "";
	private java.lang.String rodape = "";
	
	public void gerarArquivoRps(java.util.List<TabGerarRpsFaturaVO> listaTabGerarRpsFatVO) throws IOException, ParseException, IllegalAccessException, InstantiationException, SQLException{
		//
		//Pega a data atual
		data = new java.util.Date();
		//Recebe o caminho onde o arquivo rps vai ser gerado
		caminhoSaida = String.valueOf(JOptionPane.showInputDialog(null,"Indique o local para gravar o arquivo.\nEx.: C:\\Teste\\tes","ControlMoto",JOptionPane.INFORMATION_MESSAGE));
		//Gera o nome do arquivo
		nomeArquivo = "rps"+String.valueOf(new ConvertStringToDate().formatDateHora(data))+".txt";
		//Trata o CCM para o arquivo rps
		CadEmpresaVO cadEmpVO = new CadEmpresaVO(); cadEmpVO = new CadEmpresaDAO().getCcmRps();
		cadEmpVO.setCcm(cadEmpVO.getCcm().replace(".", "").replace("-", ""));
		if(!cadEmpVO.getCcm().equals(null)){
			if(cadEmpVO.getCcm().substring(0,1).equals("0"))
				cadEmpVO.setCcm(cadEmpVO.getCcm().substring(1,cadEmpVO.getCcm().length()));
		}
		//Linha de cabeçalho do arquivo. 3.2 REGISTRO TIPO 1 – CABEÇALHO
		cabecalho = "1001"+cadEmpVO.getCcm().replace(".", "").replace("-", "")+new ConvertStringToDate().formatDateRps(data)+new ConvertStringToDate().formatDateRps(data)+System.getProperty("line.separator");
		if(!caminhoSaida.equals("")){
			//Cria o arquivo
			java.io.File file = new java.io.File(String.valueOf(caminhoSaida +"\\"+ nomeArquivo));
			java.io.FileWriter fileWriter =  new java.io.FileWriter(file);
			//Escreve no arquivo
			fileWriter.write(cabecalho);
			for(TabGerarRpsFaturaVO rpsVO : listaTabGerarRpsFatVO){
				if(!rpsVO.getNumeroRps().equals(null))
					numeroDoRPS = (numeroDoRPS.substring(0, (numeroDoRPS.length()- String.valueOf(rpsVO.getNumeroRps()).length()))+rpsVO.getNumeroRps());
				if(!rpsVO.getValorStr().equals(null))
					valorDosServicos = (valorDosServicos.substring(0, (valorDosServicos.length()- rpsVO.getValorStr().replace(",","").length()))+rpsVO.getValorStr().replace(",",""));
				if(!rpsVO.getCnpj().equals(null) && rpsVO.getCnpj().replace(".","").replace("/","").replace("-","").length() < 14  ){
					cpfOuCnpjDoTomador = (cpfOuCnpjDoTomador.substring(0, (cpfOuCnpjDoTomador.length()- rpsVO.getCnpj().replace(".","").replace("/","").replace("-","").length()))+rpsVO.getCnpj().replace(".","").replace("/","").replace("-",""));
					indicadorDeCpfCnpjDoTomador = "1";
				}else{
					cpfOuCnpjDoTomador = rpsVO.getCnpj().replace(".","").replace("/","").replace("-","");
					indicadorDeCpfCnpjDoTomador = "2";
				}
				//Trata a inscricao estadual da empresa
				if(!rpsVO.getIe().equals(null) && rpsVO.getEstado().equals("SP"))
					inscricaoEstadualDoTomador = (inscricaoEstadualDoTomador.substring(0, (inscricaoEstadualDoTomador.length()- rpsVO.getIe().replace(".","").length()))+rpsVO.getIe().replace(".",""));
				//Trata a razao social da empresa
				if(!rpsVO.getNome().equals(null))
					nomeRazaoSocialDoTomador = (rpsVO.getNome()+nomeRazaoSocialDoTomador.substring(0, (nomeRazaoSocialDoTomador.length()- rpsVO.getNome().length())));
				//Trata tipo de endereco da empresa
				if(!rpsVO.getEndereco().equals(null))
					tipoDoEnderecoDoTomador = (rpsVO.getEndereco().substring(0, 3));
				//Trata endereco da empresa
				if(!rpsVO.getEndereco().equals(null))
					enderecoDoTomador = (rpsVO.getEndereco().substring(4, rpsVO.getEndereco().trim().length())+enderecoDoTomador.substring(0, (enderecoDoTomador.length()- rpsVO.getEndereco().substring(4, rpsVO.getEndereco().length()).length())));
				//Trata numero do endereco da empresa
				if(!rpsVO.getNumero().equals(null))
					numeroDoEnderecoDoTomador = (numeroDoEnderecoDoTomador.substring(0, (numeroDoEnderecoDoTomador.length()- rpsVO.getNumero().length()))+rpsVO.getNumero());
				//Trata bairro da empresa
				if(!rpsVO.getBairro().equals(null))
					bairroDoTomador = (rpsVO.getBairro()+bairroDoTomador.substring(0, (bairroDoTomador.length()- rpsVO.getBairro().length())));
				//Trata cidade da empresa
				if(!rpsVO.getCidade().equals(null))
					cidadeDoTomador = (rpsVO.getCidade()+cidadeDoTomador.substring(0, (cidadeDoTomador.length()- rpsVO.getCidade().length())));
				//Trata estado da empresa
				if(!rpsVO.getEstado().equals(null))
					ufDoTomador = (ufDoTomador.substring(0, (ufDoTomador.length()- rpsVO.getEstado().length()))+rpsVO.getEstado());
				//Trata cep da empresa
				if(!rpsVO.getCep().equals(null))
					cepDoTomador = rpsVO.getCep().replace("-","");//(cepDoTomador.substring(0, (cepDoTomador.length()- rpsVO.getCep().replace("-","").length()))+rpsVO.getCep());
				//Trata email da empresa
				if(!rpsVO.getEmail().equals(null))
					emailDoTomador = (rpsVO.getEmail()+emailDoTomador.substring(0, (emailDoTomador.length()- rpsVO.getEmail().length())));
				
				//Linha para o tipo 2 do arquivo. Dados da nota. 3.3 REGISTRO TIPO 2 – DETALHE
				linhaDados = tipoDeRegistro+tipoDoRPS+serieDoRPS+numeroDoRPS+dataDeEmissaoDoRPS+new ConvertStringToDate().formatDateRps(data)
					+situacaoDoRPS+valorDosServicos+valorDasDeducoes+codigoDoServicoPrestado+aliquota+iSSRetido+indicadorDeCpfCnpjDoTomador+cpfOuCnpjDoTomador
					+inscricaoMunicipalDoTomador//+rpsVO.getCcmTomador().replace(".", "")
					+inscricaoEstadualDoTomador+nomeRazaoSocialDoTomador+tipoDoEnderecoDoTomador+enderecoDoTomador
					+numeroDoEnderecoDoTomador+complementoDoEndereçoDoTomador+bairroDoTomador+cidadeDoTomador
					+ufDoTomador+cepDoTomador+emailDoTomador+discriminacaoDosServicos
					+"|Prestação de serviço de motoboy conforme ordem de serviço "+rpsVO.getOrdensFaturadas()+System.getProperty("line.separator");
				fileWriter.write(linhaDados);
				//Variaveis para arrumar duplicidade de informacao
				nomeRazaoSocialDoTomador = ""; nomeRazaoSocialDoTomador = nomeRazaoSocialDoTomadorApaga;
				tipoDoEnderecoDoTomador = ""; tipoDoEnderecoDoTomador = tipoDoEnderecoDoTomadorApaga;
				enderecoDoTomador = ""; enderecoDoTomador = enderecoDoTomadorApaga;
				bairroDoTomador = ""; bairroDoTomador = bairroDoTomadorApaga;
				cidadeDoTomador = ""; cidadeDoTomador = cidadeDoTomadorApaga;
				emailDoTomador = ""; emailDoTomador = emailDoTomadorApaga;
				
				numLinhas++;
			}
			if(numLinhas > 0){
				numeroDeLinhasDeDetalheDoArquivo = numeroDeLinhasDeDetalheDoArquivo+String.valueOf(numLinhas);
				numeroDeLinhasDeDetalheDoArquivo = numeroDeLinhasDeDetalheDoArquivo.substring(String.valueOf(numLinhas).length(), numeroDeLinhasDeDetalheDoArquivo.length());
			}
			rodape = tipoDeRegistroRodape+numeroDeLinhasDeDetalheDoArquivo+valorTotalDosServicosContidoNoArquivo+valorTotalDasDeducoesContidasNoArquivo;
			
			fileWriter.write(rodape);
			fileWriter.close();
		}else{
			JOptionPane.showMessageDialog(null,"O local para gravar o arquivo não foi informado.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
