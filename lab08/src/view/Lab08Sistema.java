package view;

import model.Lab08ContaCorrente;
import model.Lab08Historico;
import util.HandlerClassException;
import model.Lab08ContaCorrenteEspecial;
import model.Lab08ContaRemunerada;

import java.util.Locale;
import java.util.Scanner;

public class Lab08Sistema {

    public static void main(String[] args) throws HandlerClassException {
        Lab08Sistema sistema = new Lab08Sistema();
        sistema.exibirMenu();
    }

    public void exibirMenu() throws HandlerClassException {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastramento");
            System.out.println("2 - Saque");
            System.out.println("3 - Depósito");
            System.out.println("4 - Consulta");
            System.out.println("5 - Extrato");
            System.out.println("6 - Atualizar Saldo");
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
                case 6 -> execAtualizarSaldo();
                case 8 -> execRemoverContaCorrente();
                case 9 -> System.out.println("Encerrando o programa.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 9);
    }

    public void execCadastramento() throws HandlerClassException {
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
                Lab08ContaCorrenteEspecial contaEsp = new Lab08ContaCorrenteEspecial(agencia, conta, nome, saldo, limite);
                contaEsp.gravar();
            } else {
                Lab08ContaCorrente contaSimples = new Lab08ContaCorrente(agencia, conta, nome, saldo);
                contaSimples.gravar();
            }
            System.out.println("Cadastramento realizado.");
        } else {
            System.out.println("Cadastramento cancelado.");
        }
    }

    public void execSaque() throws HandlerClassException {
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
                Lab08ContaCorrenteEspecial contaEsp = new Lab08ContaCorrenteEspecial(agencia, conta);
                contaEsp.saque(valor);                
                contaEsp.gravar();
                contaEsp.gravaContaEspecial();
                new Lab08Historico(agencia, conta).gravar(1, valor);
                System.out.println("Saque realizado.");

            } else {
                Lab08ContaCorrente contaSimples = new Lab08ContaCorrente(agencia, conta);
                contaSimples.saque(valor);
                contaSimples.gravar();
                new Lab08Historico(agencia, conta).gravar(1, valor);
                System.out.println("Saque realizado.");
            }
        } else {
            System.out.println("Saque cancelado.");
        }
    }

    public void execDeposito() throws HandlerClassException {
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
                Lab08ContaCorrenteEspecial contaEsp = new Lab08ContaCorrenteEspecial(agencia, conta);
                contaEsp.deposito(valor);
                contaEsp.gravar();
                contaEsp.gravaContaEspecial();
                new Lab08Historico(agencia, conta).gravar(2, valor);
            } else {
                Lab08ContaCorrente contaSimples = new Lab08ContaCorrente(agencia, conta);
                contaSimples.deposito(valor);
                contaSimples.gravar();
                new Lab08Historico(agencia, conta).gravar(2, valor);
            }
            System.out.println("Depósito realizado.");
        } else {
            System.out.println("Depósito cancelado.");
        }
    }

    public void execConsulta() throws HandlerClassException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Número da Agência: ");
        int agencia = sc.nextInt();
        System.out.print("Número da Conta: ");
        int conta = sc.nextInt();

        if (agencia >= 5000) {
            new Lab08ContaCorrenteEspecial(agencia, conta).imprimir();
        } else {
            new Lab08ContaCorrente(agencia, conta).imprimir();
        }
    }

    public void execExtrato() throws HandlerClassException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Número da Agência: ");
        int agencia = sc.nextInt();
        System.out.print("Número da Conta: ");
        int conta = sc.nextInt();

        if (agencia >= 5000) {
            new Lab08ContaCorrenteEspecial(agencia, conta).imprimir();
        } else {
            new Lab08ContaCorrente(agencia, conta).imprimir();
        }

        new Lab08Historico(agencia, conta).imprimir();
    }

    public void execRemoverContaCorrente() throws HandlerClassException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Número da Agência: ");
        int agencia = sc.nextInt();
        System.out.print("Número da Conta: ");
        int conta = sc.nextInt();

        if (agencia >= 5000) {
            new Lab08ContaCorrenteEspecial(agencia, conta).removerArquivo();
        } else {
            new Lab08ContaCorrente(agencia, conta).removerArquivo();
        }

        System.out.println("Conta removida com sucesso.");
    }

    public void execAtualizarSaldo() throws HandlerClassException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Numero da Agencia: ");
        int numAge = sc.nextInt();
        System.out.print("Numero da Conta  : ");
        int numConta = sc.nextInt();

        if (numAge >= 5000) {
            Lab08ContaRemunerada conta = new Lab08ContaRemunerada(numAge, numConta);
            conta.calcularJuros();
            conta.gravar();
            conta.imprimir();

            Lab08Historico hist = new Lab08Historico(numAge, numConta);
            hist.gravar(3, conta.getSaldo());
        } else {
            System.out.println("Esta conta não é remunerada.");
        }
    }

}
