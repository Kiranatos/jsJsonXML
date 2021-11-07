package net.kiranatos.json.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.Date;
import net.kiranatos.json.model.ClassLforGson;

/* Для кастомного маршаллизатора необходимо реализовать интерфейс JsonSerializer, 
а для демаршаллизатора — соответственно JsonDeserializer */

public class LConverter implements JsonSerializer<ClassLforGson>, JsonDeserializer<ClassLforGson> { 
    public JsonElement serialize(ClassLforGson src, Type type, JsonSerializationContext context) {
        JsonObject object = new JsonObject(); 
        object.addProperty("date", src.date.getTime()); 
        object.addProperty("integer", src.integer.toString()); 
        System.out.println("Marshallization was succesful!!!");
        return object; 
    } 
    
    public ClassLforGson deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException { 
        JsonObject object = json.getAsJsonObject(); 
        Date date = new Date(object.get("date").getAsLong()); 
        BigInteger integer = new BigInteger(object.get("integer").getAsString()); 
        System.out.println("Demarshallization was succesful!!!");
        return new ClassLforGson(date, integer); 
    }
} 