package org.biblio.helper;

import java.lang.reflect.Field;

public class Printer {


    public   static <T> void  print(T message){
        System.out.println(message);
    }

    public static <T> void printModel(T model) {
        if (model == null) {
            System.out.println("Model is null.");
            return;
        }

        Field[] fields = model.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Allow access to private fields
            String fieldName = field.getName();

            try {
                Object fieldValue = field.get(model);
                System.out.println(fieldName + ": " + fieldValue);
            } catch (IllegalAccessException e) {
                System.out.println("Error accessing field: " + fieldName);
            }
        }
    }

}
