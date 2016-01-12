package com.dmytrocherednyk;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by dmytro.cherednyk on 11/01/2016.
 */
public class Main {

    private static String path = "res/Сапсай_input.txt";

    public static void listAllCharsets() {
        SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
        int i = 1;
        for (String charsetName : availableCharsets.keySet()) {
            System.out.println(i + ").\t" + charsetName);
            i++;
        }
    }

    public static Map<String,String> readFileUsingFiles(String path) throws IOException {
        HashMap<String, String> results = new HashMap<String, String>();
      /*  Files.readAllLines(Paths.get(path));
        return "olol";*/
//        return new String(String.valueOf(Files.readAllLines(Paths.get(path))));
//        return new String(Files.readAllBytes(Paths.get(path)));
        SortedMap<String, Charset> allCharsets = Charset.availableCharsets();
        String result = null;
        String charsName = null;
        for (String charsetName : allCharsets.keySet()) {
            try {
                result = new String(String.valueOf(Files.readAllLines(Paths.get(path), Charset.forName(charsetName))));
                charsName = charsetName;
                results.put(charsetName,result);
            }
            catch (MalformedInputException ex) {
                System.out.println("Attempt to use " + charsetName + " charset failed: " + ex.toString());
            }
            catch (UnmappableCharacterException ex) {
                System.out.println("Attempt to use " + charsetName + " charset failed: " + ex.toString());
            }
        }

        /*charsName = "UTF-8";
        result = new String(String.valueOf(Files.readAllLines(Paths.get(path), Charset.forName(charsName))));*/
        System.out.println();
        System.out.println(charsName + " charset worked for the file!\n");

        return results;
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> results = readFileUsingFiles(path);
        for (String key : results.keySet()) {
            System.out.println("CHARSET: " + key);
            System.out.println(results.get(key));
            System.out.println();
        }
//        listAllCharsets();
    }
}
