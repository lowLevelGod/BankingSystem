package pao;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class AuditService {
    FileWriter writer;

    private static AuditService instance = null;

    public static AuditService getInstance() {
        if (instance == null)
            instance = new AuditService();
        return instance;
    }

    public void logAction(String action) throws IOException {
        writer.append(action);
        writer.append(",");
        writer.append(new Timestamp(System.currentTimeMillis()).toString());
        writer.append("\n");
        writer.flush();
    }

    public AuditService() {
        try {
            this.writer = new FileWriter("audit.csv");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}