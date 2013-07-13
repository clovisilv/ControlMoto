<?xml version= "1.0"  encoding= "ISO-8859-1"  ?> 
<!DOCTYPE helpset PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 1.0//EN" "http://java.sun.com/products/javahelp/helpset_1_0.dtd" > 
<helpset version= "1.0" >
	<!-- maps --> 
		<maps> 
			<homeID>item1</homeID> 
			<mapref location="help.jhm" />
		</maps>
	<!-- views --> 
		<view> 
			<name>TOC</name>
			<label>Lista de Conteudo</label>
			<type>javax.help.TOCView</type> 
			<data>helpTOC.xml</data>
		</view>
		<view> 
			<name>SEARCH</name>
			<label>Busca de Conteudo</label>
			<type>javax.help.SearchView</type>
			<data engine="com.sun.java.help.search.DefaultSearchEngine">Pesquisa</data>
			<!--
			<image>back</image>
			<image>next</image>
			<image>print</image>
			<image>bock</image>
			<image>lupa</image>
			-->
			
		</view>
		
	<!-- presentation -->
		<presentation default="true" displayviewimages="true">
			<toolbar>
				<helpaction>javax.help.BackAction</helpaction>			
				<helpaction>javax.help.ForwardAction</helpaction>
				<helpaction>javax.help.SeparatorAction</helpaction>
				<helpaction>javax.help.PrintAction</helpaction>
				<helpaction>javax.help.PrintSetupAction</helpaction>
			</toolbar>
		</presentation>
		 
</helpset>