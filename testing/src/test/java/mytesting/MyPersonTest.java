package mytesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MyPersonTest {

    @Test
    public void testAddition() {
        int result = 2 + 2;
        assertEquals(4, result, "2 + 2 should equal 4");
    }
}