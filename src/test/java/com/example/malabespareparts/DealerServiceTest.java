package com.example.malabespareparts;

import com.example.malabespareparts.model.Dealer;
import com.example.malabespareparts.service.DealerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DealerServiceTest {

    @Test
    void testGetDealerCount() {

        DealerService service = new DealerService(
                "src/main/resources/com/example/malabespareparts/Data/dealers_legacy.txt"
        );

        assertTrue(service.getDealerCount() > 0);
    }

    @Test
    void testFindDealerById() {

        DealerService service = new DealerService(
                "src/main/resources/com/example/malabespareparts/Data/dealers_legacy.txt"
        );

        Dealer dealer = service.findDealerById("D101");

        assertNotNull(dealer);
    }

    @Test
    void testGetAllDealers() {

        DealerService service = new DealerService(
                "src/main/resources/com/example/malabespareparts/Data/dealers_legacy.txt"
        );

        assertFalse(service.getAllDealers().isEmpty());
    }
}