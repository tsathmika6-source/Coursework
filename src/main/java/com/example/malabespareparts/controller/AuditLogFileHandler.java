package com.example.malabespareparts.controller;

import com.example.malabespareparts.model.AuditLogEntry;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AuditLogFileHandler {

    public static void writeLog(String filePath, AuditLogEntry logEntry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            writer.write(logEntry.toString());
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}