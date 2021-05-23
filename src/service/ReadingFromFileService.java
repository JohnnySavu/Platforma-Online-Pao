package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ReadingFromFileService {

    private static ReadingFromFileService instance = null;

    private ReadingFromFileService() {}

    public static ReadingFromFileService getInstance() {

        if(instance == null) {
            instance = new ReadingFromFileService();
        }
        return instance;
    }
    public List<String> databaseContent(String info){
        List<String> row = List.of(info.split(", "));
        return row;
    }


    //generalising the reader service in order to work for every model
    public List<List<String>>  csvContent(String csvToRead) {
        List<List<String>> content = new ArrayList<>();

        Path p = Paths.get(csvToRead);
        try( BufferedReader reader = new BufferedReader(Files.newBufferedReader(p))) {
            String line = null;
            while ((line = reader.readLine()) != null){
                String[] row = line.split(", ");
                List<String> rowList = List.of(row);
                content.add(rowList);
            }
            return content;
        }catch (IOException e) {
            System.out.println(e.getMessage());

        }

        return Collections.emptyList();
    }

}