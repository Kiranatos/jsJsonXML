package net.kiranatos.xml.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import javax.xml.bind.Unmarshaller;

/* В Java 11 JAXB removed from Core. 
For JavaEE need 3-4 dependencies in Maven.
For JakartaEE need 1 dependency in Maven. 
DO NOT FORGET ABOUT ANNOTATIONS IN MODEL CLASS <<Course>>
*/

public class JAXBConverter {
    private String PATH_TO_FILE = "wfiles/coursesJAXBConverter.xml";
    
    public static void main(String[] args) {        
        Course course = new Course();
        course.setId(1);
        course.setName("Java Level 1");
        course.setTeacher("Ivanov Ivan");
        course.setHours(20);
        course.setDay("friday");
        
        System.out.println("\tMarshallization:");
        JAXBConverter jaxbc = new JAXBConverter();
        jaxbc.objectToXML(course, jaxbc.PATH_TO_FILE);
        
        System.out.println("\tDemarshallization:");
        course = jaxbc.xmlToObject(jaxbc.PATH_TO_FILE, Course.class);
        System.out.println(course);
    }
    
    public <T> void objectToXML(T obj, String path) {
        try {
            T t = (T)obj;            
            File file = new File(path);            
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass()); // Course.class
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            jaxbMarshaller.marshal(t, System.out);
            jaxbMarshaller.marshal(t, file);
            System.out.printf("\tXML has written in file:%s!!!%n%n", path);
        } catch (JAXBException e) {          
            e.printStackTrace();        
        }
    }
    
    public <T> T xmlToObject(String path, Class<?> clazz) {        
        T t = null;
        try {            
            File file = new File(path);            
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            t = (T) jaxbUnmarshaller.unmarshal(file);            
        } catch (JAXBException e) {          
            e.printStackTrace();        
        }
        System.out.printf("\tXML has read from file:%s successfully!!!%n", path);
        
        return t;
    }
}
    