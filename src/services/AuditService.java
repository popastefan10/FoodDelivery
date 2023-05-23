package services;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class AuditService {
    private static AuditService instance = null;
    private static final String AUDIT_FILE = "src/resources/audit.csv";
    private final FileWriter fw;

    private AuditService() {
        try {
            File file = new File(AUDIT_FILE);
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (!created)
                    throw new RuntimeException("Could not create audit file");
            }
            fw = new FileWriter(AUDIT_FILE, true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not create audit file");
        }
    }

    public static AuditService getInstance() {
        if (instance == null)
            instance = new AuditService();
        return instance;
    }

    public void logAction(String action) {
        try {
            fw.write(action);
            fw.write(',');
            fw.write(LocalDateTime.now().toString());
            fw.write('\n');
            fw.close();
        } catch (Exception e) {
            throw new RuntimeException("Could not write to audit file");
        }
    }
}
