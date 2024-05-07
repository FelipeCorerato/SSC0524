import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TesteCal {
    private Cal cal;

    @BeforeEach
    public void setUp() {
        cal = new Cal();
    }

    @Test
    @DisplayName("Teste calendário com 19 dias (caso especial)")
    public void testCalendario19Dias() {
        assertEquals("       1  2 14 15 16\n17 18 19 20 21 22 23\n24 25 26 27 28 29 30", cal.cal(1, 19));
    }

    @Test
    @DisplayName("Teste calendário com 28 dias")
    public void testCalendario28Dias() {
        String expectedCalendar = "          1  2  3  4\n 5  6  7  8  9 10 11\n12 13 14 15 16 17 18\n19 20 21 22 23 24 25\n26 27 28 ";
        assertEquals(expectedCalendar, cal.cal(3, 28));
    }

    @Test
    @DisplayName("Teste calendário com 31 dias")
    public void testCalendario31Dias() {
        String expectedCalendar = "                1  2\n 3  4  5  6  7  8  9\n10 11 12 13 14 15 16\n17 18 19 20 21 22 23\n24 25 26 27 28 29 30\n31 ";
        assertEquals(expectedCalendar, cal.cal(5, 31));
    }
}
