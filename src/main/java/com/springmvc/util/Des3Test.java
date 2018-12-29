package com.springmvc.util;

import com.springmvc.util.DES3;

public class Des3Test {
    public static void main(String[] args) {
        try {
            String str = DES3.encode("Hello world!");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
