package service;

import models.courses.Course;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class WritingInFileService {
    private static WritingInFileService instance = null;

    private WritingInFileService() {}

    public static WritingInFileService getInstance()
    {
        if(instance == null){
            instance = new WritingInFileService();
        }
        return instance;
    }

    public void csvWrite(String csvToWrite, List<String> rowToAdd) {
        Path p = Paths.get(csvToWrite);
        try(BufferedWriter writer = new BufferedWriter((Files.newBufferedWriter(p,CREATE,APPEND)))){
            for(String elem : rowToAdd) {
                writer.append(String.join(", ", elem));
                writer.append("\n");

            }

        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
