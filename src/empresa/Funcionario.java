package empresa;

import formatadores.Formatador;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author diego
 */
public class Funcionario extends Pessoa {

    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate nascimento) {
        super(nome, nascimento);
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", getNome(),
                getNascimento().format(Formatador.dataFormatador),
                Formatador.numeroFormatador.format(getSalario()),
                getFuncao());
    }

}
