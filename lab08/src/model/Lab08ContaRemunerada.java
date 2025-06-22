package model;

import util.HandlerClassException;

public class Lab08ContaRemunerada extends Lab08ContaCorrenteEspecial implements Lab08ContaCorrenteInterface {

    public Lab08ContaRemunerada(int numAge, int numConta, String nome, double saldo, double limite) throws HandlerClassException {
        super(numAge, numConta, nome, saldo, limite);
    }

    public Lab08ContaRemunerada(int numAge, int numConta) throws HandlerClassException {
        super(numAge, numConta);
    }

    public void calcularJuros() {
        double novoSaldo = getSaldo() + (getSaldo() * TAXA_JUROS / 100);
        setSaldo(novoSaldo);
    }
}
