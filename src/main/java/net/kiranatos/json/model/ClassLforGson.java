package net.kiranatos.json.model;

import com.google.gson.annotations.SerializedName;
import java.math.BigInteger;
import java.util.Date;

public class ClassLforGson {
    public Date date; 
    @SerializedName("paramNameInJSONFile")
    public BigInteger integer; 
    
    public ClassLforGson(Date date, BigInteger integer) { 
        this.date = date; 
        this.integer = integer; 
    } 

    @Override
    public String toString() {
        return "ClassLforGson { \n" + 
                "  date=" + date + 
                ",\n  integer=" + integer + '}';
    }
    
} 