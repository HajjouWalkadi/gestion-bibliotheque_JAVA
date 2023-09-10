package org.biblio.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LogicHelper {
    private  static final Scanner scanner = new Scanner(System.in);
    private static final String DATE_FORMAT = "yyyy-MM-dd";


    public  static  String scan(){
        return  scanner.nextLine();
    }


    public static Date stringToDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        try {
            // Parse the input string into a Date object
            Date date = dateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            // Handle parsing errors here (e.g., invalid date format)
            e.printStackTrace();
            return null; // Return null to indicate a parsing error
        }
    }
}
