import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TesteIsLeap {
    private Cal cal;

    @BeforeEach
    public void setUp() {
        cal = new Cal();
    }

    @Nested
    @DisplayName("Testes para anos menores ou iguais a 1752")
    class TestesAnosMenoresOuIguaisA1752 {
        @Test
        @DisplayName("Ano bissexto antes da implementação do calendário gregoriano")
        public void testAnoBissextoAntesDe1752() {
            assertTrue(cal.isLeap(1700));
            assertTrue(cal.isLeap(1600));
            assertFalse(cal.isLeap(1701));
            assertFalse(cal.isLeap(1702));
        }
    }

    @Nested
    @DisplayName("Testes para anos maiores que 1752")
    class TestesAnosMaioresQue1752 {
        @Test
        @DisplayName("Ano bissexto divisível por 400")
        public void testAnoBissextoDivisivelPor400() {
            assertTrue(cal.isLeap(2000));
            assertTrue(cal.isLeap(2400));
            assertTrue(cal.isLeap(1600)); // Novo teste: ano divisível por 400
            assertFalse(cal.isLeap(1800));
        }

        @Test
        @DisplayName("Ano bissexto divisível por 4, mas não por 100")
        public void testAnoBissextoDivisivelPor4MasNaoPor100() {
            assertTrue(cal.isLeap(2004));
            assertTrue(cal.isLeap(2008));
            assertFalse(cal.isLeap(1900));
            assertFalse(cal.isLeap(2100));
        }

        @Test
        @DisplayName("Ano não bissexto")
        public void testAnoNaoBissexto() {
            assertFalse(cal.isLeap(2003));
            assertFalse(cal.isLeap(2101));
        }

        @Test
        @DisplayName("Ano bissexto divisível por 100 e 4")
        public void testAnoBissextoDivisivelPor100E4() {
            assertTrue(cal.isLeap(1600)); // Novo teste: ano divisível por 100 e 4
            assertTrue(cal.isLeap(2000));
            assertTrue(cal.isLeap(2400));
            assertTrue(cal.isLeap(1700));
        }
    }
}
