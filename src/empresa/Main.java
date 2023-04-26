package empresa;

import formatadores.Formatador;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @author diego
 */
public class Main {

    public static void main(String[] args) {

        Funcionario maria, joao, caio, miguel, alice, heitor, arthur, laura, heloisa, helena;
        List<Funcionario> funcionarios;

        Map<String, List<Funcionario>> mapaFuncionarios = new HashMap<>();

        maria = new Funcionario("Maria", LocalDate.of(2000, Month.OCTOBER, 18));
        defineSalarioEFuncao(maria, new BigDecimal(2009.44), "Operador");

        joao = new Funcionario("João", LocalDate.of(1990, Month.MAY, 12));
        defineSalarioEFuncao(joao, new BigDecimal(2284.38), "Operador");

        caio = new Funcionario("Caio", LocalDate.of(1961, Month.MAY, 2));
        defineSalarioEFuncao(caio, new BigDecimal(9836.14), "Coordenador");

        miguel = new Funcionario("Miguel", LocalDate.of(1998, Month.OCTOBER, 14));
        defineSalarioEFuncao(miguel, new BigDecimal(19119.88), "Diretor");

        alice = new Funcionario("Alice", LocalDate.of(1995, Month.JANUARY, 5));
        defineSalarioEFuncao(alice, new BigDecimal(2234.68), "Recepcionista");

        heitor = new Funcionario("Heitor", LocalDate.of(1999, Month.NOVEMBER, 19));
        defineSalarioEFuncao(heitor, new BigDecimal(1582.72), "Operador");

        arthur = new Funcionario("Arthur", LocalDate.of(1993, Month.MARCH, 31));
        defineSalarioEFuncao(arthur, new BigDecimal(4071.84), "Contador");

        laura = new Funcionario("Laura", LocalDate.of(1994, Month.JULY, 8));
        defineSalarioEFuncao(laura, new BigDecimal(3017.45), "Gerente");

        heloisa = new Funcionario("Heloísa", LocalDate.of(2003, Month.MAY, 24));
        defineSalarioEFuncao(heloisa, new BigDecimal(1606.85), "Eletricista");

        helena = new Funcionario("Helena", LocalDate.of(1996, Month.SEPTEMBER, 2));
        defineSalarioEFuncao(helena, new BigDecimal(2799.93), "Gerente");

        funcionarios = new ArrayList<>(List.of(maria, joao, caio, miguel,
                alice, heitor, arthur, laura, heloisa, helena));

        funcionarios.remove(joao);

        imprimirFuncionarios(funcionarios);

        atualizarSalarios(funcionarios, new BigDecimal(0.1));

        imprimirFuncionarios(funcionarios);

        mapearFuncionarios(mapaFuncionarios, funcionarios);

        imprimirPorFuncao(mapaFuncionarios);

        imprimirPorMesNacimento(funcionarios, 10);

        imprimirPorMesNacimento(funcionarios, 12);

        imprimirFuncionarioMaisVelho(funcionarios);

        imprimirPorOrdemAlfabetica(funcionarios);

        imprimirTotalSalario(funcionarios);
        
        imprimirQuantidadeDeSalariosMinimos(funcionarios);

    }

    private static void defineSalarioEFuncao(Funcionario funcionario, BigDecimal salario, String funcao) {
        funcionario.setSalario(salario);
        funcionario.setFuncao(funcao);
    }

    private static void atualizarSalarios(List<Funcionario> funcionarios, BigDecimal ajuste) {

        Consumer<Funcionario> ajustarSalario = fun -> {
            fun.setSalario(fun.getSalario().multiply(ajuste).add(fun.getSalario()));
        };

        funcionarios.stream().forEach(ajustarSalario);
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        funcionarios.stream().forEach(System.out::println);
        System.out.println();
    }

    private static void imprimirPorFuncao(Map<String, List<Funcionario>> mapaFuncionarios) {
        mapaFuncionarios.forEach((funcao, funcionarios) -> System.out.println(funcao + ": " + funcionarios));
        System.out.println();
    }

    private static void imprimirPorMesNacimento(List<Funcionario> funcionarios, int mes) {
        System.out.format("Funcionarios que fazem aniversário no mes %d:%n", mes);
        funcionarios.stream()
                .filter(fun -> fun.getNascimento().getMonthValue() == mes)
                .forEach(System.out::println);

        System.out.println();
    }

    private static void imprimirFuncionarioMaisVelho(List<Funcionario> funcionarios) {

        Funcionario maisVelho = funcionarios.get(0);

        for (Funcionario funcionario : funcionarios) {
            if (maisVelho.getNascimento().isAfter(funcionario.getNascimento())) {
                maisVelho = funcionario;
            }
        }

        String nascimento = maisVelho.getNascimento().format(Formatador.dataFormatador);
        System.out.format("Funcionario com maior idade: %s, %s.%n%n", maisVelho.getNome(), nascimento);
    }

    private static void imprimirPorOrdemAlfabetica(List<Funcionario> funcionarios) {

        funcionarios.stream()
                .map(fun -> fun.getNome())
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);

        System.out.println();
    }

    private static void imprimirTotalSalario(List<Funcionario> funcionarios) {

        BigDecimal total = BigDecimal.ZERO;

        for (Funcionario funcionario : funcionarios) {
            total = total.add(funcionario.getSalario());
        }

        System.out.format("Valor total dos salário: %s.%n%n", Formatador.numeroFormatador.format(total));
    }

    private static void imprimirQuantidadeDeSalariosMinimos(List<Funcionario> funcionarios) {
        
        BigDecimal salarioMinimo = new BigDecimal(1212);
        
        funcionarios.stream().forEach(fun -> {
            String quantidade = Formatador.numeroFormatador.format(fun.getSalario().divide(salarioMinimo, RoundingMode.HALF_UP));
            System.out.format("%s recebe %s salários mínimos.%n", fun.getNome(), quantidade);
        });
        
        System.out.println();
    }

    private static void mapearFuncionarios(Map<String, List<Funcionario>> mapaFuncionarios, List<Funcionario> funcionarios) {

        for (Funcionario funcionario : funcionarios) {

            if (mapaFuncionarios.containsKey(funcionario.getFuncao())) {

                mapaFuncionarios.get(funcionario.getFuncao()).add(funcionario);

            } else {

                List<Funcionario> funcionariosPorFuncao = new ArrayList<>();
                funcionariosPorFuncao.add(funcionario);

                mapaFuncionarios.put(funcionario.getFuncao(), funcionariosPorFuncao);
            }

        }
    }

}
