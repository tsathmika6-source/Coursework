package com.example.malabespareparts;

import com.example.malabespareparts.model.Part;
import com.example.malabespareparts.service.InventoryService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {

    @Test
    void testAddPart() {

        InventoryService service = new InventoryService(
                "src/main/resources/com/example/malabespareparts/Data/inventory_legacy.txt"
        );

        int before = service.getTotalParts();

        Part part = new Part(
                "P999",
                "JUnit Part",
                "Toyota",
                1000.0,
                5,
                "Engine",
                "2026-07-01",
                "test.jpg"
        );

        service.addPart(part);

        assertEquals(before + 1, service.getTotalParts());
    }

    @Test
    void testFindPartByCode() {

        InventoryService service = new InventoryService(
                "src/main/resources/com/example/malabespareparts/Data/inventory_legacy.txt"
        );

        Part part = service.findPartByCode("P001");

        assertNotNull(part);
    }

    @Test
    void testDeletePart() {

        InventoryService service = new InventoryService(
                "src/main/resources/com/example/malabespareparts/Data/inventory_legacy.txt"
        );

        Part part = new Part(
                "P998",
                "Delete Test",
                "Honda",
                500,
                2,
                "Engine",
                "2026-07-01",
                "test.jpg"
        );

        service.addPart(part);

        assertTrue(service.deletePart("P998"));
    }
}