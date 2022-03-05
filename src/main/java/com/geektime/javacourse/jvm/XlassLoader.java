package com.geektime.javacourse.jvm;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class XlassLoader extends ClassLoader{

    public static void main(String[] args) throws Exception{
        String className = "Hello";
        String methodName = "hello";
        ClassLoader classLoader = new XlassLoader();

        Class<?> xlass = classLoader.loadClass(className);

        for(Method m: xlass.getDeclaredMethods()){
            System.out.println(xlass.getSimpleName() + "." + m.getName());
        }

        Object instance = xlass.getDeclaredConstructor().newInstance();
        Method method = xlass.getMethod(methodName);
        method.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        String resourcePath = name.replace(".","/");
        final String suffix = ".xlass";
        InputStream inputstream = this.getClass().getClassLoader().getResourceAsStream(resourcePath + suffix);
        try{
            int length = inputstream.available();
            byte[] byteArray = new byte[length];
            inputstream.read(byteArray);

            byte[] newByte = decode(byteArray);

            return defineClass(name, newByte,0,newByte.length);
        }catch(IOException e){
            throw new ClassNotFoundException(name,e);
        }finally{
            close(inputstream);
        }
    }

    private static byte[] decode(byte[] byteArray){
        byte[] targetArray = new byte[byteArray.length];
        for(int i=0;i<byteArray.length;i++){
            targetArray[i] = (byte) (255-byteArray[i]);
        }
        return targetArray;
    }

    private static void close(Closeable res){
        if(null != res){
            try{
                res.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
