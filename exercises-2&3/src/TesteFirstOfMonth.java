import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TesteFirstOfMonth {
    private Cal cal;

    @BeforeEach
    public void setUp() {
        cal = new Cal();
    }

    @Test
    @DisplayName("Teste primeiro dia do mês de fevereiro de 2024 (ano bissexto)")
    public void testPrimeiroDiaFevereiroAnoBissexto() {
        assertEquals(4, cal.firstOfMonth(2, 2024)); // Fevereiro de 2024 começa em uma quarta-feira
    }

    @Test
    @DisplayName("Teste primeiro dia do mês de setembro de 1752 (ajuste calendário gregoriano)")
    public void testPrimeiroDiaSetembro1752() {
        assertEquals(2, cal.firstOfMonth(9, 1752)); // Setembro de 1752 começa em uma terça-feira
    }

    @Test
    @DisplayName("Teste primeiro dia de outros meses e anos")
    public void testPrimeiroDiaOutrosMesesAnos() {
        assertEquals(1, cal.firstOfMonth(1, 2024)); // Janeiro de 2024 começa em uma segunda-feira
        assertEquals(5, cal.firstOfMonth(3, 2024)); // Março de 2024 começa em uma sexta-feira
    }

    @Test
    @DisplayName("Teste primeiro dia do mês de fevereiro de 1900 (ano bissexto exceção)")
    public void testPrimeiroDiaFevereiro1900() {
        assertEquals(4, cal.firstOfMonth(2, 1900)); // Fevereiro de 1900 começa em uma quinta-feira
    }

    @Test
    @DisplayName("Teste primeiro dia do mês de março de 2021 (ano não bissexto)")
    public void testPrimeiroDiaMarco2021() {
        assertEquals(1, cal.firstOfMonth(3, 2021)); // Março de 2021 começa em uma segunda-feira
    }

    @Test
    @DisplayName("Teste primeiro dia de meses após setembro de 1752")
    public void testPrimeiroDiaMesesAposSetembro1752() {
        assertEquals(0, cal.firstOfMonth(10, 1752)); // Outubro de 1752 começa em um domingo
        assertEquals(3, cal.firstOfMonth(11, 1752)); // Novembro de 1752 começa em uma quarta-feira
        assertEquals(5, cal.firstOfMonth(12, 1752)); // Dezembro de 1752 começa em uma sexta-feira
    }
}
