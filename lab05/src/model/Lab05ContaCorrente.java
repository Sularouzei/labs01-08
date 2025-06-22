package model;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Lab05ContaCorrente {
    private int numAge;
    private int numConta;
    private String nome;
    private double saldo;

    // Construtor 1: agencia e conta (lê do arquivo)
    public Lab05ContaCorrente(int agencia, int conta) {
        setNumAge(agencia);
        setNumConta(conta);
        recuperar();
    }

    // Construtor 2: todos os atributos
    public Lab05ContaCorrente(int agencia, int conta, String nome, double saldo) {
        setNumAge(agencia);
        setNumConta(conta);
        setNome(nome);
        setSaldo(saldo);
    }

    private void recuperar() {
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

    public int saque(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            return 1;
        }
        return 0;
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

    public void setNumAge(int numAge) {
        if (numAge >= 1 && numAge <= 9999) {
            this.numAge = numAge;
        } else {
            System.out.println("Erro: número de agência inválido!");
        }
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        if (numConta >= 1 && numConta <= 9999999) {
            this.numConta = numConta;
        } else {
            System.out.println("Erro: número de conta inválido!");
        }
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
