package model;

public class Lab02ContaCorrente {
    private int numAge;
    private int numConta;
    private String nome;
    private double saldo;

    public void setDados(int agencia, int conta, String nomeCliente, double saldoInicial) {
        this.numAge = agencia;
        this.numConta = conta;
        this.nome = nomeCliente;
        this.saldo = saldoInicial;
    }

    public int saque(double pValor) {
        if (saldo >= pValor) {
            saldo -= pValor;
            return 1;
        }
        return 0;
    }

    public void deposito(double pValor) {
        saldo += pValor;
    }

    public void imprimir() {
        System.out.println("=== Dados da Conta Corrente ===");
        System.out.println("Número da Agência: " + numAge);
        System.out.println("Número da Conta : " + numConta);
        System.out.println("Nome do Cliente : " + nome);
        System.out.printf("Saldo           : %.2f\n", saldo);
    }
}
