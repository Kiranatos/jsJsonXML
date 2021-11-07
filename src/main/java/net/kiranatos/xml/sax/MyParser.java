package net.kiranatos.xml.sax;

import java.util.Arrays;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class MyParser extends DefaultHandler {
    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        System.out.println("Тег: " + tag);
        System.out.println("uri = [" + uri + "]");
        if(tag.equals("course")) {
            System.out.println("id : " + attributes.getValue("id"));
        }
        super.startElement(uri, localName, tag, attributes);
    }

    @Override
    public void characters(char[] c, int start, int length) throws SAXException {
        super.characters(c, start,  length);
        //System.out.printf("[%s,%d,%d]%n", new String(c), start,  length);
        //System.out.printf("[%d,%d]%n", start,  length);
        for(int i = start; i < start + length; i++) {
            //System.out.print("*");
            System.err.print(c[i]);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("Тег разобран: " + qName);
        super.endElement(uri, localName, qName);
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Начало разбора документа!");
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("Разбор документа окончен!");
    }
}