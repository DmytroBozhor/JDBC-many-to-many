package org.example;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class Environment {
    public static Optional<String> getProperty(String propertyName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src\\main\\resources\\dataBase.properties"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            String propertyLine = scanner.nextLine();
            if (propertyLine.substring(0, propertyLine.indexOf("=")).equals(propertyName)) {
                return Optional.of(propertyLine.substring(propertyLine.indexOf("=") + 1));
            }
        }
        return Optional.empty();
    }
}
