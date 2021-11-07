package net.kiranatos.json.model;

import java.util.Arrays;

public class ClassAforJackson { 
    private String paramStr;
    
    private boolean paramBoo;    
    private String[] paramStrArr;
    private ClassBforJackson paramNestClass;

    public String getParamStr() {
        return paramStr;
    }

    public void setParamStr(String paramStr) {
        this.paramStr = paramStr;
    }

    public boolean isParamBoo() {
        return paramBoo;
    }

    public void setParamBoo(boolean paramBoo) {
        this.paramBoo = paramBoo;
    }

    public String[] getParamStrArr() {
        return paramStrArr;
    }

    public void setParamStrArr(String[] paramStrArr) {
        this.paramStrArr = paramStrArr;
    }

    public ClassBforJackson getParamNestClass() {
        return paramNestClass;
    }

    public void setParamNestClass(ClassBforJackson paramNestClass) {
        this.paramNestClass = paramNestClass;
    }
    
    public static ClassAforJackson initializeMe() {
        ClassAforJackson to1 = new ClassAforJackson();
        to1.setParamBoo(true);
        to1.setParamStr("some string");
        to1.setParamStrArr(new String[]{"it","is","a","string","array"});
        
        ClassBforJackson to2 = new ClassBforJackson();
        to2.setParamInt(33);
        to2.setParamIntArr(new int[]{1,2,3,4,5});
        
        to1.setParamNestClass(to2);
        return to1;
    }

    @Override
    public String toString() {
        return "class A {\n" + 
                "paramStr=" + paramStr + 
                ", \nparamBoo=" + paramBoo + 
                ", \nparamStrArr=" + Arrays.toString(paramStrArr) + 
                ", \nparamNestClass=" + paramNestClass + '}';
    }
}