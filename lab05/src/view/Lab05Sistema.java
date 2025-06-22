package view;

import model.Lab05ContaCorrente;
import model.Lab05Historico;
import model.Lab05ContaCorrenteEspecial;

import java.util.Locale;
import java.util.Scanner;

public class Lab05Sistema {

    public static void main(String[] args) {
        Lab05Sistema sistema = new Lab05Sistema();
        sistema.exibirMenu();
    }

    public void exibirMenu() {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastramento");
            System.out.println("2 - Saque");
            System.out.println("3 - Depósito");
            System.out.println("4 - Consulta");
            System.out.println("5 - Extrato");
            System.out.println("8 - Remover Conta Corrente");
            System.out.println("9 - Fim");
            System.out.print("Opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> execCadastramento();
                case 2 -> execSaque();
                case 3 -> execDeposito();
                case 4 -> execConsulta();
                case 5 -> execExtrato();
                case 8 -> execRemoverContaCorrente();
                case 9 -> System.out.println("Encerrando o programa.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 9);
    }

    public void execCadastramento() {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        System.out.print("Número da Agência: ");
        int agencia = sc.nextInt();
        System.out.print("Número da Conta: ");
        int conta = sc.nextInt();
        sc.nextLine();
        System.out.print("Nome do Cliente: ");
        String nome = sc.nextLine();
        double saldo;
        do {
            System.out.print("Saldo inicial: ");
            saldo = sc.nextDouble();
        } while (saldo <= 0.0);

        char conf;
        System.out.print("Confirma cadastramento (S/N)? ");
        conf = sc.next().charAt(0);

        if (conf == 'S' || conf == 's') {
            if (agencia >= 5000) {
                System.out.print("Limite de crédito: ");
                double limite = sc.nextDouble();
                Lab05ContaCorrenteEspecial contaEsp = new Lab05ContaCorrenteEspecial(agencia, conta, nome, saldo, limite);
                contaEsp.gravar();
            } else {
                Lab05ContaCorrente contaSimples = new Lab05ContaCorrente(agencia, conta, nome, saldo);
                contaSimples.gravar();
            }
            System.out.println("Cadastramento realizado.");
        } else {
            System.out.println("Cadastramento cancelado.");
        }
    }

    public void execSaque() {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        System.out.print("Número da Agência: ");
        int agencia = sc.nextInt();
        System.out.print("Número da Conta: ");
        int conta = sc.nextInt();
        double valor;
        do {
            System.out.print("Valor do saque: ");
            valor = sc.nextDouble();
        } while (valor <= 0.0);

        System.out.print("Confirma saque (S/N)? ");
        char conf = sc.next().charAt(0);
        if (conf == 'S' || conf == 's') {
            int resultado;
            if (agencia >= 5000) {
                Lab05ContaCorrenteEspecial contaEsp = new Lab05ContaCorrenteEspecial(agencia, conta);
                resultado = contaEsp.saque(valor);
                if (resultado == 1) {
                    contaEsp.gravar();
                    contaEsp.gravaContaEspecial();
                    new Lab05Historico(agencia, conta).gravar(1, valor);
                    System.out.println("Saque realizado.");
                } else {
                    System.out.println("Saldo insuficiente.");
                }
            } else {
                Lab05ContaCorrente contaSimples = new Lab05ContaCorrente(agencia, conta);
                resultado = contaSimples.saque(valor);
                if (resultado == 1) {
                    contaSimples.gravar();
                    new Lab05Historico(agencia, conta).gravar(1, valor);
                    System.out.println("Saque realizado.");
                } else {
                    System.out.println("Saldo insuficiente.");
                }
            }
        } else {
            System.out.println("Saque cancelado.");
        }
    }

    public void execDeposito() {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        System.out.print("Número da Agência: ");
        int agencia = sc.nextInt();
        System.out.print("Número da Conta: ");
        int conta = sc.nextInt();
        double valor;
        do {
            System.out.print("Valor do depósito: ");
            valor = sc.nextDouble();
        } while (valor <= 0.0);

        System.out.print("Confirma depósito (S/N)? ");
        char conf = sc.next().charAt(0);

        if (conf == 'S' || conf == 's') {
            if (agencia >= 5000) {
                Lab05ContaCorrenteEspecial contaEsp = new Lab05ContaCorrenteEspecial(agencia, conta);
                contaEsp.deposito(valor);
                contaEsp.gravar();
                contaEsp.gravaContaEspecial();
                new Lab05Historico(agencia, conta).gravar(2, valor);
            } else {
                Lab05ContaCorrente contaSimples = new Lab05ContaCorrente(agencia, conta);
                contaSimples.deposito(valor);
                contaSimples.gravar();
                new Lab05Historico(agencia, conta).gravar(2, valor);
            }
            System.out.println("Depósito realizado.");
        } else {
            System.out.println("Depósito cancelado.");
        }
    }

    public void execConsulta() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Número da Agência: ");
        int agencia = sc.nextInt();
        System.out.print("Número da Conta: ");
        int conta = sc.nextInt();

        if (agencia >= 5000) {
            new Lab05ContaCorrenteEspecial(agencia, conta).imprimir();
        } else {
            new Lab05ContaCorrente(agencia, conta).imprimir();
        }
    }

    public void execExtrato() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Número da Agência: ");
        int agencia = sc.nextInt();
        System.out.print("Número da Conta: ");
        int conta = sc.nextInt();

        if (agencia >= 5000) {
            new Lab05ContaCorrenteEspecial(agencia, conta).imprimir();
        } else {
            new Lab05ContaCorrente(agencia, conta).imprimir();
        }

        new Lab05Historico(agencia, conta).imprimir();
    }

    public void execRemoverContaCorrente() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Número da Agência: ");
        int agencia = sc.nextInt();
        System.out.print("Número da Conta: ");
        int conta = sc.nextInt();

        if (agencia >= 5000) {
            new Lab05ContaCorrenteEspecial(agencia, conta).removerArquivo();
        } else {
            new Lab05ContaCorrente(agencia, conta).removerArquivo();
        }

        System.out.println("Conta removida com sucesso.");
    }
}
