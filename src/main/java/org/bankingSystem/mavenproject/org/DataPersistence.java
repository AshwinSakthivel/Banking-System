package org.bankingSystem.mavenproject.org;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class DataPersistence {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void saveData(String filePath, Bank bank) throws IOException {
        objectMapper.writeValue(new File(filePath), bank);
    }

    public static Bank loadData(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), Bank.class);
    }
}
