import static org.junit.jupiter.api.Assertions.*;

import com.eridaz.expenses.exceptions.InvalidEmployeeIdException;
import com.eridaz.expenses.utilities.EmployeeUtilities;
import org.junit.jupiter.api.Test;


class SampleTests {
    @Test
    public void testSomething() {
        int a = 10;
        int b = 5;
        int total = a + b;
        assertEquals(15, total);
    }

    @Test
    public void testEmployeeIdNumberIsConvertedCorrectly() throws InvalidEmployeeIdException {
        EmployeeUtilities employeeUtilities = new EmployeeUtilities();
        int result = employeeUtilities.validateEmployeeId("416");

        assertEquals(416, result);
    }

    @Test
    public void testThatExceptionIsThrownIfEmployeeIdIsNotAvalidNumber() {
        EmployeeUtilities employeeUtilities = new EmployeeUtilities();
        assertThrows(InvalidEmployeeIdException.class, () -> {
            int result = employeeUtilities.validateEmployeeId("hi");

        });
    }
}
