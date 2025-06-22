package model;

import java.io.*;

import util.HandlerClassException;

public class Lab08ContaCorrenteEspecial extends Lab08ContaCorrente {
    private double limite;

    public Lab08ContaCorrenteEspecial(int agencia, int conta, String nome, double saldo, double limite) throws HandlerClassException {
        super(agencia, conta, nome, saldo);
        this.limite = limite;
        gravaContaEspecial();
    }

    public Lab08ContaCorrenteEspecial(int agencia, int conta) throws HandlerClassException {
        super(agencia, conta);
        recuperaContaEspecial();
    }

    @Override
    public void saque(double valor) throws HandlerClassException {
        if (getSaldo() + limite >= valor) {
            setSaldo(getSaldo() - valor);
        } else {
            throw new HandlerClassException("Lab08ContaCorrente", "sacar", "Saldo insuficiente", "Valor: " + valor);
        }
    }

    @Override
    public boolean removerArquivo() {
        super.removerArquivo();
        File arq = new File(getNumAge() + "." + getNumConta() + ".esp");
        arq.delete();
        return true;
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Limite de Crédito: R$ " + String.format("%.2f", limite));
    }

    public void gravaContaEspecial() {
        try {
            FileWriter fw = new FileWriter(getNumAge() + "." + getNumConta() + ".esp");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(limite);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recuperaContaEspecial() {
        try {
            FileReader fr = new FileReader(getNumAge() + "." + getNumConta() + ".esp");
            BufferedReader br = new BufferedReader(fr);
            limite = Double.parseDouble(br.readLine());
            br.close();
        } catch (IOException e) {
            System.out.println("Conta especial sem limite gravado.");
        }
    }
}
