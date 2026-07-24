package com.example.malabespareparts.service;

import com.example.malabespareparts.model.AuditLogEntry;

import java.util.ArrayList;

public class AuditService {

    private final  ArrayList<AuditLogEntry> auditLogs;

    public AuditService() {
        auditLogs=new ArrayList<>();
    }

    public void addLog(AuditLogEntry log){
        auditLogs.add(log);
    }

    public ArrayList<AuditLogEntry> getAllLogs(){
        return auditLogs;
    }

    public int getLogCount(){
        return auditLogs.size();
    }

    public void clearLogs(){
        auditLogs.clear();
    }

}
