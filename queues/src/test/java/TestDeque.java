import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class TestDeque {
    @Test
    public void test1() {
        Deque<Integer> d = new Deque<>();

        assertTrue(d.isEmpty());
        assertEquals(d.size(), 0);

        d.addFirst(4);
        d.addFirst(5);
        d.addFirst(6);

        d.addLast(3);
        d.addLast(2);
        d.addLast(1);

        assertFalse(d.isEmpty());
        assertEquals(d.size(), 6);

        assertEquals(d.removeFirst().intValue(), 6);
        assertEquals(d.removeFirst().intValue(), 5);
        assertEquals(d.removeFirst().intValue(), 4);

        assertFalse(d.isEmpty());
        assertEquals(d.size(), 3);

        assertEquals(d.removeLast().intValue(), 1);
        assertEquals(d.removeLast().intValue(), 2);
        assertEquals(d.removeLast().intValue(), 3);

        assertTrue(d.isEmpty());
        assertEquals(d.size(), 0);

        d.addFirst(4);
        d.addFirst(5);
        d.addFirst(6);

        d.addLast(3);
        d.addLast(2);
        d.addLast(1);

        int start = 6;
        for (int i : d) {
            assertEquals(i, start--);
        }
    }
}
