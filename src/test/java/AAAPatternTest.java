import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AAAPatternTest {

    private int value;

    @Before
    public void setup() {
        // Arrange: Prepare data before each test
        value = 10;
    }

    @Test
    public void testWithAAAPattern() {
        // Act: Perform the action
        int result = value + 5;

        // Assert: Check the result
        assertEquals(15, result);
    }

    @After
    public void teardown() {
        // Cleanup: Reset or clear resources
        value = 0;
    }
}