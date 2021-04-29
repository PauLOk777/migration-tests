package com.paulok777.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public static List<String> read(String path) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.trim().isEmpty()) {
                lines.add(line);
            }
        }

        return lines;
    }
}
