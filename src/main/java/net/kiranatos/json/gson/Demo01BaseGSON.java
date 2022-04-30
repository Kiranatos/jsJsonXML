package net.kiranatos.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import net.kiranatos.json.model.ClassKforGson;
import net.kiranatos.json.model.ClassLforGson;

public class Demo01BaseGSON {
    public static void main(String[] args) {        
        Gson gson = new Gson();
        
        System.out.println("Marshallization:");
        ClassKforGson k1 = ClassKforGson.initializeMe();
        String str = gson.toJson(k1);        
        System.out.println("1) " + str);
        str = gson.toJson(k1, ClassKforGson.class);
        System.out.println("2) " + str);
        
        System.out.println("\nDemarshallization:");
        ClassKforGson k2 = gson.fromJson(str, ClassKforGson.class);
        System.out.println(k2);
        
        System.out.println("\nDemarshallization from file:");        
        /* try(FileReader reader = new FileReader("file.json")){
            ClassKforGson kn = gson.fromJson(reader, ClassKforGson.class);
        } catch(Exception e) { } */
        
        System.out.println("\nMarshallization and deserialization of simple and primitive types:");
        Gson gson2 = new Gson(); 
        System.out.println(gson2.toJson(123));
        System.out.println(gson2.toJson("hello"));
        System.out.println(gson2.toJson(Long.valueOf(10)));
        Integer integer = gson.fromJson("1", int.class); 
        String string = gson.fromJson("world", String.class); 
        Boolean bool = gson.fromJson("true", Boolean.class); 
        
        System.out.println("\nРабота с коллекциями");
        System.out.println("Маршализация коллекций, таких как ArrayList, LinkedList, HashSet, TreeMap :");
        System.out.println("метод toJson для Collection вернет массив объектов или примитивов;");
        System.out.println("метод toJson для Map вернет ассоциативный массив.");        
        Map<String, Integer> map = new LinkedHashMap<>(); 
        map.put("USD", 123); 
        map.put("EUR", 321); 
        String json = gson.toJson(map);
        System.out.println(json);
        Type type = new TypeToken<Map<String, Integer>>(){}.getType(); 
        // variant 1:
        Map<String, Integer> map2 = gson.fromJson(json, type);                 
        for (String strB: map2.keySet())
            System.out.println(map2.get(strB));
        // variant 2:
        Map<String, Integer> map3 = gson.fromJson(json, Map.class);        
        for (String strB: map2.keySet())
            System.out.println(map2.get(strB));
                
        System.out.println("\nMarshallization of Collection : ");
        Collection collection = new ArrayList(); 
        collection.add("string"); 
        collection.add(10); 
        collection.add(new File("")); 
        System.out.println(gson.toJson(collection)); // ["string",10,{"path":""}]
        
        System.out.println("\nAnnotation SerializedName in class ClassLforGson writes & reads specific "
                + "name(paramNameInJSONFile) in JSON file, that different from name of field in class:");
        ClassLforGson clfg = new ClassLforGson(new Date(), BigInteger.valueOf(66L)); 
        System.out.println(new Gson().toJson(clfg, ClassLforGson.class));        
        
        System.out.println("\nConfigurate Marshallization and Demarshallization : ");
        /* Gson позволяет определять свои правила для сериализации и десериализации
        объектов используя метод registerTypeAdapter().*/                
        GsonBuilder builder = new GsonBuilder(); 
        builder.registerTypeAdapter(ClassLforGson.class, new LConverter()); 
        Gson gson4 = builder.create();
        ClassLforGson sL = new ClassLforGson(new Date(), BigInteger.valueOf(100L));
        String serDes = gson4.toJson(sL, ClassLforGson.class);
        System.out.println(serDes);
        sL = gson4.fromJson(serDes, ClassLforGson.class);
        System.out.println(sL);
        
        System.out.println("\nTree-readable output of JSON:");
        Gson gson5 = new GsonBuilder().setPrettyPrinting().create();         
        System.out.println(gson5.toJson(k2, ClassKforGson.class));
        
        /* excludeFieldsWithModifiers - настройка для GsonBuilder, позволяет 
        изменить набор несериализуемых полей при конвертации java объектов в JSON. 
        По умолчанию игнорируются только поля с модификатором transient.*/
    }
}
