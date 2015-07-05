/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author rb
 */
    public class IOUtils {
    public static void readFilteredLines(String file, Predicate<String> filter, Consumer<String> consumer) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File name is missing");
        }
 
        if(consumer == null) {
            throw new IllegalArgumentException("No consumer function found!");
        }
 
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Stream<String> lines= reader.lines();
            lines = filter != null ? lines.filter(filter) : lines;
 
            lines.forEach(consumer);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + file);
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + file, e);
        }
    }
 
    public static void readAllLines(String file, Consumer<String> consumer) {
        readFilteredLines(file, null, consumer);
    }
 
    public static void readNonEmptyLines(String file, Consumer<String> consumer) {
        readFilteredLines(file, (line -> !line.trim().isEmpty()), consumer);
    }
}

