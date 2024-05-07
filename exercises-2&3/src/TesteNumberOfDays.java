import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TesteNumberOfDays {
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
            assertEquals(29, cal.numberOfDays(2, 1700)); // Fevereiro em 1700 tem 29 dias (ano bissexto)
            assertEquals(28, cal.numberOfDays(2, 1701)); // Fevereiro em 1701 tem 28 dias (ano não bissexto)
        }

        @Test
        @DisplayName("Teste número de dias para setembro em 1752")
        public void testNumeroDiasSetembro1752() {
            assertEquals(19, cal.numberOfDays(9, 1752)); // Setembro em 1752 tem 19 dias
        }

        @Test
        @DisplayName("Teste número de dias para meses diferentes de setembro em 1752")
        public void testNumeroDiasMesesDiferentesDeSetembro1752() {
            assertEquals(31, cal.numberOfDays(1, 1752)); // Janeiro em 1752 tem 31 dias
            assertEquals(31, cal.numberOfDays(3, 1752)); // Março em 1752 tem 31 dias
        }
    }

    @Nested
    @DisplayName("Testes para anos maiores que 1752")
    class TestesAnosMaioresQue1752 {
        @Test
        @DisplayName("Ano bissexto divisível por 400")
        public void testAnoBissextoDivisivelPor400() {
            assertEquals(29, cal.numberOfDays(2, 2000)); // Fevereiro em 2000 tem 29 dias (ano bissexto)
            assertEquals(29, cal.numberOfDays(2, 2400)); // Fevereiro em 2400 tem 29 dias (ano bissexto)
        }

        @Test
        @DisplayName("Ano bissexto divisível por 4, mas não por 100")
        public void testAnoBissextoDivisivelPor4MasNaoPor100() {
            assertEquals(29, cal.numberOfDays(2, 2004)); // Fevereiro em 2004 tem 29 dias (ano bissexto)
            assertEquals(28, cal.numberOfDays(2, 1900)); // Fevereiro em 1900 tem 28 dias (ano não bissexto)
        }

        @Test
        @DisplayName("Ano não bissexto")
        public void testAnoNaoBissexto() {
            assertEquals(28, cal.numberOfDays(2, 2003)); // Fevereiro em 2003 tem 28 dias (ano não bissexto)
            assertEquals(28, cal.numberOfDays(2, 2101)); // Fevereiro em 2101 tem 28 dias (ano não bissexto)
        }
    }
}