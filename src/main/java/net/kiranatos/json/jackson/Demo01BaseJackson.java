package net.kiranatos.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.kiranatos.json.model.ClassAforJackson;
import net.kiranatos.json.model.ClassBforJackson;
import net.kiranatos.json.model.ClassCforJackson;

public class Demo01BaseJackson {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("\tExamples of Jackson library's work:");        
        ObjectMapper objMap = new ObjectMapper();
        
        ClassAforJackson a01 = ClassAforJackson.initializeMe();
        String jsonStr1 = objMap.writeValueAsString(a01);
        System.out.println("\n\tObject has serialized: \n" + jsonStr1);
        
        a01 = objMap.readValue(jsonStr1, ClassAforJackson.class);
        System.out.println("\n\tObject has deserialized: \n" + a01);
        
        System.out.println("\n\tObject has serialized via JsonNode.");
        JsonNode jsnSer = objMap.valueToTree(a01);
        
        System.out.println("\tHow to get Writer.");
        ObjectWriter objW = objMap.writer();
        System.out.println(objW.writeValueAsString(jsnSer));
        
        System.out.println("Тут должен быть красивый вывод лесенкой, но что-то не сработало:");
        objW.with(SerializationFeature.INDENT_OUTPUT);
        System.out.println(objW.writeValueAsString(jsnSer));
        
        System.out.println("\n\tDeserialization via JsonNode.");
        JsonNode jsn = objMap.readTree(jsonStr1);
        a01 = objMap.treeToValue(jsn, ClassAforJackson.class);
        System.out.println("\tObject has deserialized: \n" + a01);
        System.out.println("\tGetting parameter value:\n" + jsn.get("paramStr").asText());
        
        System.out.println("\n\tObjectMapper can be configured even in case of incorrect data in JSON:");
        String jsonStr2 = "{\"paramInt\":3021,\"paramNotExistIntPOJO\":true}";
        System.out.println(jsonStr2);
        objMap.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ClassBforJackson a02 = objMap.readValue(jsonStr2, ClassBforJackson.class);
        System.out.println(a02);
        
        System.out.println("\nExample of working with Date and LocalDate.");
        System.out.println("\tDate works correctly.");
        System.out.println("\tLocalDate throws exception, so need to register module.");
        String jsonStr3 = "{\"today\":\"2021-06-09\",\"tomorrow\":\"2021-06-10\"}";
        objMap.registerModules(new JavaTimeModule()); // dependency #3Jackson in pom.xml
        ClassCforJackson c = objMap.readValue(jsonStr3, ClassCforJackson.class);
        System.out.println("Deserialized class C:\n" + c);
    }    
    
}
