package net.kiranatos.json.model;

import java.util.Arrays;

public class ClassBforJackson {
    private int paramInt;
    private int[] paramIntArr;

    public int[] getParamIntArr() {
        return paramIntArr;
    }

    public void setParamIntArr(int[] paramIntArr) {
        this.paramIntArr = paramIntArr;
    }

    public int getParamInt() {
        return paramInt;
    }

    public void setParamInt(int paramInt) {
        this.paramInt = paramInt;
    }
    
    public static ClassBforJackson initializeMe() {
        ClassBforJackson a = new ClassBforJackson();
        a.setParamInt(3021);
        a.setParamIntArr(new int[]{7,8,4});        
        return a;
    }

    @Override
    public String toString() {
        return "\nclass ClassBforJackson{" + 
                "paramInt=" + paramInt + 
                ", paramIntArr=" + Arrays.toString(paramIntArr) + '}';
    }
    
    
}
