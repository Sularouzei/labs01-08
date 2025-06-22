package model;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import util.HandlerClassException;

public class Lab08ContaCorrente {
    private int numAge;
    private int numConta;
    private String nome;
    private double saldo;

    // Construtor 1: agencia e conta (lê do arquivo)
    public Lab08ContaCorrente(int agencia, int conta) throws HandlerClassException {
        setNumAge(agencia);
        setNumConta(conta);
        recuperar();
    }

    // Construtor 2: todos os atributos
    public Lab08ContaCorrente(int agencia, int conta, String nome, double saldo) throws HandlerClassException {
        setNumAge(agencia);
        setNumConta(conta);
        setNome(nome);
        setSaldo(saldo);
    }

    private void recuperar() throws NumberFormatException, HandlerClassException {
        try {
            FileReader fr = new FileReader(getNumAge() + "." + getNumConta() + ".dat");
            BufferedReader br = new BufferedReader(fr);

            String[] dados = new String[4];
            for (int i = 0; i < 4; i++) {
                dados[i] = br.readLine();
            }
            br.close();

            setNumAge(Integer.parseInt(dados[0]));
            setNumConta(Integer.parseInt(dados[1]));
            setNome(dados[2]);
            setSaldo(Double.parseDouble(dados[3]));

        } catch (IOException e) {
            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
        }
    }

    public boolean gravar() {
        try {
            FileWriter fw = new FileWriter(getNumAge() + "." + getNumConta() + ".dat");
            PrintWriter pw = new PrintWriter(fw);

            pw.println(getNumAge());
            pw.println(getNumConta());
            pw.println(getNome());
            pw.println(getSaldo());

            pw.close();
            return true;

        } catch (IOException e) {
            System.out.println("Erro ao gravar os dados: " + e.getMessage());
            return false;
        }
    }

    public void saque(double valor) throws HandlerClassException {
        if (saldo >= valor) {
            saldo -= valor;
        } else {
            throw new HandlerClassException("Lab08ContaCorrente", "sacar", "Saldo insuficiente", "Valor: " + valor);
        }
    }

    public void deposito(double valor) {
        saldo += valor;
    }

    public void imprimir() {
        NumberFormat formatter = DecimalFormat.getCurrencyInstance(new Locale("pt", "BR"));
        formatter.setMinimumFractionDigits(2);
        System.out.println("=== Extrato da Conta ===");
        System.out.println("Agência: " + numAge);
        System.out.println("Conta  : " + numConta);
        System.out.println("Nome   : " + nome);
        System.out.println("Saldo  : " + formatter.format(saldo));
    }

    public boolean removerArquivo() {
        File arq1 = new File(numAge + "." + numConta + ".dat");
        arq1.delete();
        arq1 = new File(numAge + "." + numConta + ".hist");
        arq1.delete();
        return true;
    }
    

    // Getters e Setters com validação
    public int getNumAge() {
        return numAge;
    }

    public void setNumAge(int numAge) throws HandlerClassException{
        if (numAge < 1 || numAge > 9999) {
            throw new HandlerClassException("Lab08ContaCorrente", "setNumAge", "Agência inválida", "Valor: " + numAge);
        }
        this.numAge = numAge;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) throws HandlerClassException{
        if (numConta < 1 || numConta > 9999999) {
            throw new HandlerClassException("Lab08ContaCorrente", "setNumConta", "Conta inválida", "Valor: " + numConta);
        }
        this.numConta = numConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
