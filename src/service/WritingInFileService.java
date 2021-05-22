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
            for(int i = 0 ; i < rowToAdd.size(); ++i) {
                writer.append(rowToAdd.get(i));
                if (i == rowToAdd.size() - 1)
                    writer.append("\n");
                else
                    writer.append(", ");
            }
            writer.flush();

        }catch(IOException e) {

            System.out.println(e.getMessage());
        }
    }


}
