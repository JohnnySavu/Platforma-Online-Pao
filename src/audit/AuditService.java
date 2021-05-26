package audit;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class AuditService {

    private static AuditService instance = null;
    private BufferedWriter writer;

    private AuditService() {

        Path p = Paths.get("src/resources/auditservice.csv");

        try {
            writer = new BufferedWriter(Files.newBufferedWriter(p, CREATE, APPEND));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static AuditService getInstance() {

        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    public void logToCSV(String actionName) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime ldt = LocalDateTime.now();
        String timestamp = dtf.format(ldt);
        try {

            writer.write(actionName + ", " + timestamp + '\n');
            writer.flush();

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }

    public void closeTheStream() {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



