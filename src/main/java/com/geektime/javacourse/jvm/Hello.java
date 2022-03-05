package com.geektime.javacourse.jvm;

public class Hello {
    public static void main(String[] args){
        int param1 = 1;
        long param2 = 2L;
        double param3 = 3D;
        byte param4 = 4;

        String str = "test";
        if(str.length() > param1){
            System.out.println(param2 + param3);
            System.out.println(str + param1);
        }

        for(int i = 0;i<param1;i++){
            System.out.print("计算结果:");
            System.out.println(param1 * param4);
        }
    }
}
