package view;

import java.util.Locale;
import java.util.Scanner;

public class Lab01Sistema {

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("Menu:");
            System.out.println("1 - Cadastramento");
            System.out.println("2 - Saque");
            System.out.println("3 - Depósito");
            System.out.println("9 - Fim");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    execCadastramento();
                    break;
                case 2:
                    execSaque();
                    break;
                case 3:
                    execDeposito();
                    break;
                case 9:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 9);
        
        scanner.close();
    }

    public void execCadastramento() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        
        System.out.println("Layout método cadastramento");
        System.out.print("Numero da Agencia: ");
        int numAgencia = scanner.nextInt();
        System.out.print("Numero da Conta: ");
        int numConta = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha pendente
        System.out.print("Nome do Cliente: ");
        String nomeCliente = scanner.nextLine();
        System.out.print("Saldo: ");
        double saldo = scanner.nextDouble();
        System.out.print("Confirma cadastramento (S/N): ");
        char confirma = scanner.next().charAt(0);
        
        if (confirma == 'S' || confirma == 's') {
            System.out.println("Cadastramento realizado.");
        } else {
            System.out.println("Cadastramento cancelado.");
        }
        
        scanner.close();
    }

    public void execSaque() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        
        System.out.println("Layout método saque");
        System.out.print("Numero da Agencia: ");
        int numAgencia = scanner.nextInt();
        System.out.print("Numero da Conta: ");
        int numConta = scanner.nextInt();
        System.out.print("Valor do Saque: ");
        double valorSaque = scanner.nextDouble();
        System.out.print("Confirma saque (S/N): ");
        char confirma = scanner.next().charAt(0);
        
        if (confirma == 'S' || confirma == 's') {
            System.out.println("Saque realizado.");
        } else {
            System.out.println("Saque cancelado.");
        }
        
        scanner.close();
    }

    public void execDeposito() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        
        System.out.println("Layout método depósito");
        System.out.print("Numero da Agencia: ");
        int numAgencia = scanner.nextInt();
        System.out.print("Numero da Conta: ");
        int numConta = scanner.nextInt();
        System.out.print("Valor do Deposito: ");
        double valorDeposito = scanner.nextDouble();
        System.out.print("Confirma deposito (S/N): ");
        char confirma = scanner.next().charAt(0);
        
        if (confirma == 'S' || confirma == 's') {
            System.out.println("Deposito realizado.");
        } else {
            System.out.println("Deposito cancelado.");
        }
        
        scanner.close();
    }
}
