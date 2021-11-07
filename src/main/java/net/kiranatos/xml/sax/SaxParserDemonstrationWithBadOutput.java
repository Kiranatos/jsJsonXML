package net.kiranatos.xml.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.SAXParser;

public class SaxParserDemonstrationWithBadOutput {
    private static final String PATH = "rfiles/coursesDOMParser.xml";
    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //factory.setValidating(true);
        //factory.setNamespaceAware(false);
        SAXParser parser;
        InputStream xmlData = null;
        try {
            xmlData = new FileInputStream(PATH);
            parser = factory.newSAXParser();
            parser.parse(xmlData, new MyParser());
        } catch (FileNotFoundException e) {  // обработки ошибки, файл не найден
            e.printStackTrace();            
        } catch (ParserConfigurationException e) { // обработка ошибки Parser
            e.printStackTrace();            
        } catch (SAXException e) { // обработка ошибки SAX
            e.printStackTrace();            
        } catch (IOException e) { // обработка ошибок ввода
            e.printStackTrace();            
        }
    }
}