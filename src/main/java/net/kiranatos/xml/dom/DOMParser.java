package net.kiranatos.xml.dom;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParser {    
    private static final String PATH = "rfiles/coursesDOMParser.xml";
    public static void main(String args[]) {
        try {
            File fileXML = new File(PATH);
            System.out.println(fileXML.getAbsolutePath());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fileXML);
            
            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("course");
            System.out.println("----------------------------");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.printf("\n%s) Current Element : %s%n", String.valueOf(i), node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Course id : " + element.getAttribute("id"));
                    System.out.println("Name : " + element.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Teacher : " + element.getElementsByTagName("teacher").item(0).getTextContent());
                    System.out.println("Hours : " + element.getElementsByTagName("hours").item(0).getTextContent());
                    System.out.println("Day : " + element.getElementsByTagName("day").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}