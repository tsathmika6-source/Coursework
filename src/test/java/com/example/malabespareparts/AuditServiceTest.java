package com.example.malabespareparts;

import com.example.malabespareparts.model.AuditLogEntry;
import com.example.malabespareparts.service.AuditService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuditServiceTest {

    @Test
    void testAddLog() {

        AuditService service = new AuditService();

        AuditLogEntry log = new AuditLogEntry(
                "2026-07-27",
                "10:30:00",
                "CHECKOUT",
                "Admin"
        );

        service.addLog(log);

        assertEquals(1, service.getLogCount());
    }

    @Test
    void testGetAllLogs() {

        AuditService service = new AuditService();

        AuditLogEntry log = new AuditLogEntry(
                "2026-07-27",
                "10:30:00",
                "CHECKOUT",
                "Admin"
        );

        service.addLog(log);

        assertFalse(service.getAllLogs().isEmpty());
    }

    @Test
    void testClearLogs() {

        AuditService service = new AuditService();

        AuditLogEntry log = new AuditLogEntry(
                "2026-07-27",
                "10:30:00",
                "CHECKOUT",
                "Admin"
        );

        service.addLog(log);

        service.clearLogs();

        assertEquals(0, service.getLogCount());
    }
}