package org.example;

import java.util.Collection;
import java.util.List;

public class NonPrimitiveType <T>{

        String objstr;
        public String getObjstr(Number s) {
            objstr=s.toString();
            return objstr;
        }
        public String getObjstr(char s) {
            objstr=Character.toString(s);
            return objstr;
        }
        public String getObjstr(String s) {
            return s;
        }




}
