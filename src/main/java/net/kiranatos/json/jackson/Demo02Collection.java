package net.kiranatos.json.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.kiranatos.OzoHelper;
import net.kiranatos.json.model.ClassBforJackson;

public class Demo02Collection {
    private static final String PATH = "wfiles/demo02collection.json";
    private static String text;
    public static void main(String[] args) throws IOException {
        System.out.println("\n\tExample how to work with generics in collection:\n");        
        ObjectMapper objMap = new ObjectMapper();
        
        List<ClassBforJackson> listW = getList(5);        
        
        objMap.writeValue(new File(PATH), listW); // write in file
        
        text = "%n\t 1st variant:%s%n%n";
        List<ClassBforJackson> listR =
                objMap.readValue(new File(PATH), new TypeReference<List<ClassBforJackson>>(){});        
        System.out.printf(text, listR);
        
        text = "%n\t 2nd variant:%s%n%n";
        CollectionType listType = 
                objMap.getTypeFactory().constructCollectionType(ArrayList.class, ClassBforJackson.class);
        listR = objMap.readValue(new File(PATH), listType);
        System.out.printf(text, listR);
        
        text = "%n\t 3rd variant:%s%n%n";
        JsonNode jsonNode = objMap.readTree(new File(PATH));
        listR = objMap.convertValue(jsonNode, new TypeReference<List<ClassBforJackson>>(){});
        System.out.printf(text, listR);
        
        text = "%n\t 4th variant:%s%n%n";
        jsonNode = objMap.readTree(new File(PATH));
        listR = objMap.convertValue(jsonNode, objMap.getTypeFactory().constructCollectionType(
                ArrayList.class, ClassBforJackson.class));
        System.out.printf(text, listR);
        
        text = "%n\t 5th variant:%s%n%n";
        String jsonString = OzoHelper.getTextFromFile(PATH);
        listR = jsonArrayToList(jsonString, ClassBforJackson.class);
        System.out.printf(text, listR);
        
        /* in article said:
        ClassCastException occurred!
        We've passed a TypeReference object to the readValue() method, and we've 
        previously seen that this way will solve the class casting problem. So, 
        why are we seeing the same exception in this case?
        It's because our method is generic. The type parameter T cannot be reified 
        at runtime, even if we pass a TypeReference instance with the type parameter T.
        
        But my code works... And I dont know why */
        text = "%n\t 6th variant:%s%n%n";
        jsonString = OzoHelper.getTextFromFile(PATH);
        listR = jsonArrayToList2(jsonString, ClassBforJackson.class);
        System.out.printf(text, listR);
        
    }
    
    static <T> List<T> jsonArrayToList(String json, Class<T> elementClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType listType = 
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
        return objectMapper.readValue(json, listType);
    }
    
    static <T> List<T> jsonArrayToList2(String json, Class<T> elementClass) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<List<T>>(){});
    }
    
    // just for initialization
    private static List<ClassBforJackson> getList(int n) {
        List<ClassBforJackson> list = new ArrayList<ClassBforJackson>();
        for (int i = 0; i < n; i++) {
            ClassBforJackson b = new ClassBforJackson();
            b.setParamInt(OzoHelper.getRandomFromZeroToN(1000));
            b.setParamIntArr(new int[]{
                OzoHelper.getRandomFromZeroToN(50),
                OzoHelper.getRandomFromZeroToN(50),
                OzoHelper.getRandomFromZeroToN(50)});
            list.add(b);
        }        
        return list;
    }
}
