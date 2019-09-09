package com.conversao.xml.dom;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Conversao {

	private static final String ARQUIVO_XML = "<agenda>\r\n" + "    <contato id=\"01\">\r\n"
			+ "        <nome>Pedro</nome>\r\n" + "        <email>pedrohenriquea.6@hotmail.com</email>\r\n"
			+ "    </contato>\r\n" + "     <contato id=\"02\">\r\n" + "        <nome>João</nome>\r\n"
			+ "        <email>joaopedroa.6@hotmail.com</email>\r\n" + "    </contato>\r\n" + "</agenda>";

	public static void main(String[] args) {
		try {
			Document doc = converterStrToDocumento(ARQUIVO_XML);
			Element raiz = doc.getDocumentElement();
			System.out.println("O elemento raíz é: " + raiz.getNodeName());
			NodeList listaContatos = raiz.getElementsByTagName("contato");
			
			for (int i=0; i < listaContatos.getLength(); i++){
				Element contato = (Element) listaContatos.item(i);
				Attr id = contato.getAttributeNode("id");
				System.out.println("Id: " + id.getNodeValue());
				System.out.println("Nome: " + obterValorElemento(contato, "nome"));
				System.out.println("Email: " + obterValorElemento(contato, "email"));
				System.out.println("___________________________________________");
			
			}
			
		} catch (Exception e) {
			System.out.println("Erro inesperado");
		}

	}

	public static Document converterStrToDocumento(String strXML)
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.parse(new InputSource(new StringReader(ARQUIVO_XML)));
	}
	
	public static String obterValorElemento(Element elemento, String nomeElemento) {
		
		String valorElemento = "";
		
		NodeList lista = elemento.getElementsByTagName(nomeElemento);
		if(lista.getLength() > 0) {
			Node node = lista.item(0).getFirstChild();
			valorElemento = node.getNodeValue();
		}
		
		return valorElemento;
	}

}
