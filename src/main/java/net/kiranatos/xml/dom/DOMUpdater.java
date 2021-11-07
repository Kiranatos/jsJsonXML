package net.kiranatos.xml.dom;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMUpdater {    
    private static final String PATH_TO_INCOMING_FILE = "rfiles/coursesDOMParser.xml";
    private static final String PATH_TO_OUTCOMING_FILE = "wfiles/updatedCoursesDOMUpdater.xml";
    public static void main(String argv[]) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(PATH_TO_INCOMING_FILE);
            System.out.printf("Information from file \"%s\" has read.%n", PATH_TO_INCOMING_FILE);

            Node course = doc.getElementsByTagName("course").item(0);

            NamedNodeMap attr = course.getAttributes();
            Node nodeAttr = attr.getNamedItem("id");
            nodeAttr.setTextContent("25");
            System.out.println("Attribute \"id\" has updated.");

            Element category = doc.createElement("category");
            category.appendChild(doc.createTextNode("programming"));
            course.appendChild(category);
            System.out.println("Element <category> has added.");

            NodeList list = course.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);                
                if ("hours".equals(node.getNodeName())) {
                    node.setTextContent("100");
                    System.out.println("<hours> has updated.");
                }                
                if ("day".equals(node.getNodeName())) {
                    course.removeChild(node);
                    System.out.println("<day> has removed.");
                }
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(PATH_TO_OUTCOMING_FILE));
            transformer.transform(source, result);
            System.out.printf("Updated information has writen in file \"%s\".%n", PATH_TO_OUTCOMING_FILE);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }
    }
}