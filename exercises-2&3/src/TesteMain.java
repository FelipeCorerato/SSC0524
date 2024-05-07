import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TesteMain {
    @Nested
    @DisplayName("Testes calendário do mês atual")
    class TestesCalendarioDoMesAtual {
        @Test
        @DisplayName("Teste calendário do mês atual")
        public void testCalendarioMesAtual() {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            Cal.main(new String[]{});

            System.setOut(System.out);

            String output = outputStream.toString();
            assertTrue(output.contains(Cal.smon[java.time.LocalDate.now().getMonthValue() - 1]));
            assertTrue(output.contains(Cal.dayw));
        }

        @Test
        @DisplayName("Teste com argumento inválido")
        public void testCalendarioArgumentoInvalido() {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setErr(new PrintStream(outputStream));

            Cal.main(new String[]{"NotANumber"});

            System.setOut(System.out);

            String output = outputStream.toString();
            assertTrue(output.contains("Cal: NotANumber: ano invalido.\n"));
        }

        @Test
        @DisplayName("Teste com argumento inválido para mês")
        public void testCalendarioArgumentoInvalidoMes() {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setErr(new PrintStream(outputStream));

            Cal.main(new String[]{"15", "2024"});

            System.setOut(System.out);

            String output = outputStream.toString();
            assertTrue(output.contains("Cal: 15: mes invalido.\n"));
        }

        @Test
        @DisplayName("Teste argumento de mês inválido")
        public void testArgumentoMesInvalido() {
            // Redirecionar a saída de erro para capturar a mensagem de exceção
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setErr(new PrintStream(outputStream));

            // Executar a função main com um argumento de mês inválido ("NotANumber")
            Cal.main(new String[]{"NotANumber", "2024"});

            // Restaurar a saída padrão
            System.setOut(System.out);

            // Verificar se a saída contém a mensagem de exceção esperada
            String output = outputStream.toString();
            assertTrue(output.contains("Cal: NotANumber: mes invalido.\n"));
        }

        @Test
        @DisplayName("Teste com argumento inválido para ano")
        public void testCalendarioArgumentoInvalidoAno() {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setErr(new PrintStream(outputStream));

            Cal.main(new String[]{"12", "NotANumber"});

            System.setOut(System.out);

            String output = outputStream.toString();
            assertTrue(output.contains("Cal: NotANumber: ano invalido.\n"));
        }

        @Test
        @DisplayName("Teste calendário com ano inválido (muito grande)")
        public void testCalendarioAnoMuitoGrande() {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setErr(new PrintStream(outputStream));

            Cal.main(new String[]{"10000"});

            System.setOut(System.out);

            String output = outputStream.toString();
            assertTrue(output.contains("Cal: 10000: ano invalido.\n"));
        }

        @Test
        @DisplayName("Teste calendário com ano inválido (muito grande) - com mês")
        public void testCalendarioAnoMuitoGrandeComMes() {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setErr(new PrintStream(outputStream));

            Cal.main(new String[]{"1", "10000"});

            System.setOut(System.out);

            String output = outputStream.toString();
            assertTrue(output.contains("Cal: 10000: ano invalido.\n"));
        }

        @Test
        @DisplayName("Teste calendário com mês inválido")
        public void testCalendarioMesInvalido() {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setErr(new PrintStream(outputStream));

            Cal.main(new String[]{"-1", "2024"});

            System.setOut(System.out);

            String output = outputStream.toString();
            assertTrue(output.contains("Cal: -1: mes invalido.\n"));
        }
    }

    @Test
    @DisplayName("Teste calendário de janeiro de 2024")
    public void testCalendarioJaneiro2024() {
        // Redirecionar a saída padrão para capturar a impressão do calendário
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Executar a função main com os argumentos de janeiro de 2024
        Cal.main(new String[]{"1", "2024"});

        // Restaurar a saída padrão
        System.setOut(System.out);

        // Verificar se a saída contém o nome do mês e os dias da semana
        String output = outputStream.toString();
        assertTrue(output.contains("Janeiro"));
        assertTrue(output.contains(Cal.dayw));
    }

    @Test
    @DisplayName("Teste calendário de dezembro de 2024")
    public void testCalendarioDezembro2024() {
        // Redirecionar a saída padrão para capturar a impressão do calendário
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Executar a função main com os argumentos de dezembro de 2024
        Cal.main(new String[]{"12", "2024"});

        // Restaurar a saída padrão
        System.setOut(System.out);

        // Verificar se a saída contém o nome do mês e os dias da semana
        String output = outputStream.toString();
        assertTrue(output.contains("Dezembro"));
        assertTrue(output.contains(Cal.dayw));
    }

    @Test
    @DisplayName("Teste calendário de todos os meses do ano")
    public void testCalendarioTodosMesesAno() {
        // Redirecionar a saída padrão para capturar a impressão do calendário
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Executar a função main com os argumentos para um ano específico
        Cal.main(new String[]{"2024"});

        // Restaurar a saída padrão
        System.setOut(System.out);

        // Verificar se a saída contém o nome de todos os meses e os dias da semana
        String output = outputStream.toString();
        for (int zi = 1; zi < 13; zi++) {
            assertTrue(output.contains(Cal.smon[zi-1])); // Verificar se o nome do mês está presente
            assertTrue(output.contains(Cal.dayw)); // Verificar se os dias da semana estão presentes
        }
    }
}