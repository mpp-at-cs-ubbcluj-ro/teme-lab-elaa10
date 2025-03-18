
import ro.mpp2025.model.ComputerRepairRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComputerRepairRequestTest {

    @Test
    @DisplayName("Test obiect ComputerRepairRequest - Constructor și Getteri")
    void testComputerRepairRequestCreation() {
        ComputerRepairRequest request = new ComputerRepairRequest(10, "Alice Brown", "789 Oak St", "1122334455", "HP Pavilion", "2025-04-10", "Keyboard not working");

        assertEquals(10, request.getID());
        assertEquals("Alice Brown", request.getOwnerName());
        assertEquals("789 Oak St", request.getOwnerAddress());
        assertEquals("1122334455", request.getPhoneNumber());
        assertEquals("HP Pavilion", request.getModel());
        assertEquals("2025-04-10", request.getDate());
        assertEquals("Keyboard not working", request.getProblemDescription());
    }

    @Test
    @DisplayName("Test modificare date - Setteri")
    void testSetters() {
        ComputerRepairRequest request = new ComputerRepairRequest();
        request.setID(20);
        request.setOwnerName("Bob White");
        request.setOwnerAddress("321 Pine St");
        request.setPhoneNumber("5566778899");
        request.setModel("Lenovo ThinkPad");
        request.setDate("2025-05-15");
        request.setProblemDescription("Overheating issue");

        assertEquals(20, request.getID());
        assertEquals("Bob White", request.getOwnerName());
        assertEquals("321 Pine St", request.getOwnerAddress());
        assertEquals("5566778899", request.getPhoneNumber());
        assertEquals("Lenovo ThinkPad", request.getModel());
        assertEquals("2025-05-15", request.getDate());
        assertEquals("Overheating issue", request.getProblemDescription());
    }
}
