package com.umontreal.ift3913.h22.Javadocchecker;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Parser {
    public static void getAllFilesFromPath(String pathToRepo) {

        Path p = Paths.get(pathToRepo);
        FileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                // todo EXECUTE CODE HERE
                System.out.println(file);
                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(p, fv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
