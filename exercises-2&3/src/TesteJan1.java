import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TesteJan1 {
    private Cal cal;

    @BeforeEach
    public void setUp() {
        cal = new Cal();
    }

    @Nested
    @DisplayName("Testes para anos maiores que 1800")
    class TestesAnosMaioresQue1800 {
        @Test
        @DisplayName("Ano bissexto")
        public void testeJan2024() {
            assertEquals(1, cal.jan1(2024));
        }

        @Test
        @DisplayName("Ano bissexto divisível por 4, mas não por 100")
        public void testeJan2020() {
            assertEquals(3, cal.jan1(2020));
        }

        @Test
        @DisplayName("Ano não bissexto")
        public void testeJan2002() {
            assertEquals(2, cal.jan1(2002));
        }
    }

    @Nested
    @DisplayName("Testes para anos entre 1800 e 1752")
    class TestesAnosEntre1800E1752 {
        @Test
        @DisplayName("Ano bissexto antes da implementação do calendário gregoriano")
        public void testeJan1750() {
            assertEquals(1, cal.jan1(1750));
        }

        @Test
        @DisplayName("Ano bissexto antes do calendário gregoriano")
        public void testeJan1700() {
            assertEquals(1, cal.jan1(1700));
        }
    }

    @Nested
    @DisplayName("Testes para anos menores que 1752")
    class TestesAnosMenoresQue1752 {
        @Test
        @DisplayName("Ano não bissexto antes do calendário gregoriano")
        public void testeJan0001() {
            assertEquals(6, cal.jan1(1));
        }
    }
}
