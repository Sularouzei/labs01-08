package model;

public class Lab07ContaRemunerada extends Lab07ContaCorrenteEspecial implements Lab07ContaCorrenteInterface {

    public Lab07ContaRemunerada(int numAge, int numConta, String nome, double saldo, double limite) {
        super(numAge, numConta, nome, saldo, limite);
    }

    public Lab07ContaRemunerada(int numAge, int numConta) {
        super(numAge, numConta);
    }

    public void calcularJuros() {
        double novoSaldo = getSaldo() + (getSaldo() * TAXA_JUROS / 100);
        setSaldo(novoSaldo);
    }
}
