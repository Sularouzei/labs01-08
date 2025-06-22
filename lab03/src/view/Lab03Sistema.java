package view;

import model.Lab03ContaCorrente;

import java.util.Locale;
import java.util.Scanner;

public class Lab03Sistema {

    public void exibirMenu() {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastramento");
            System.out.println("2 - Saque");
            System.out.println("3 - Depósito");
            System.out.println("4 - Consulta");
            System.out.println("9 - Fim");
            System.out.print("Opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1: execCadastramento(); break;
                case 2: execSaque(); break;
                case 3: execDeposito(); break;
                case 4: execConsulta(); break;
                case 9: System.out.println("Encerrando o programa."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 9);

        sc.close();
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

        System.out.print("Confirma cadastramento (S/N)? ");
        char conf = sc.next().charAt(0);

        if (conf == 'S' || conf == 's') {
            Lab03ContaCorrente novaConta = new Lab03ContaCorrente(agencia, conta, nome, saldo);
            novaConta.gravar();
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
            Lab03ContaCorrente contaObj = new Lab03ContaCorrente(agencia, conta);
            int resultado = contaObj.saque(valor);
            if (resultado == 1) {
                contaObj.gravar();
                System.out.println("Saque realizado.");
            } else {
                System.out.println("Saldo insuficiente.");
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
            Lab03ContaCorrente contaObj = new Lab03ContaCorrente(agencia, conta);
            contaObj.deposito(valor);
            contaObj.gravar();
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

        Lab03ContaCorrente contaObj = new Lab03ContaCorrente(agencia, conta);
        contaObj.imprimir();
    }
}
