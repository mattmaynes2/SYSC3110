package xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

public class Main {
	
	public static void readSAX(File f) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser s = spf.newSAXParser();
		
		DefaultHandler dh = new DefaultHandler(){
			public void startElement(String u, String ln, String qName, Attributes a){
				System.out.println("START: " + qName);
			}
			
			public void endElement(String uri, String localName, String qName){
				System.out.println("END: " + qName);
			}
			
			public void characters(char[] ch, int start, int length){
				System.out.println("CHARS: " + new String(ch, start, length));
			}
			
			
		};
		s.parse(f, dh);
	}
	
	public static void readDOM(File f) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder d = factory.newDocumentBuilder();
		Document doc = d.parse(f);
		
		System.out.println("Root: " +  doc.getDocumentElement().getNodeName());
		
		NodeList list = doc.getDocumentElement().getChildNodes();
		for(int i = 0; i < list.getLength(); i++){
			Node n = list.item(i);
			System.out.println("Child: " + n.getNodeName() + " --> " + n.getTextContent());
			} 
		
	}
	
	
	public static void main(String[] args){
		File f = new File("file.xml");
		try {
			readSAX(f);
			readDOM(f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
