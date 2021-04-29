package com.paulok777.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {

    public static void write(String data, String path) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path))){
            bos.write(data.getBytes());
        }
    }
}
