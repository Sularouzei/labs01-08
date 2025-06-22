package model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

public class Lab07Historico extends Lab06Historico {

    public Lab07Historico(int numAge, int numConta) {
        super(numAge, numConta);
    }

    @Override
    public void imprimir() {
        recuperarHistorico();
        NumberFormat formatter = DecimalFormat.getCurrencyInstance(new Locale("pt", "BR"));
        for (Object obj : vetOperacoes) {
            String linha = (String) obj;
            String[] partes = linha.split(" ");
            String agencia = new DecimalFormat("0000").format(Integer.parseInt(partes[0]));
            String conta = new DecimalFormat("0000000").format(Integer.parseInt(partes[1]));
            String dataHora = partes[2] + "/" + partes[3] + "/" + partes[4] + " - " +
                              partes[5] + ":" + partes[6] + ":" + partes[7];
            int codigo = Integer.parseInt(partes[8]);
            double valor = Double.parseDouble(partes[9]);
            String operacao = switch (codigo) {
                case 1 -> "Saque caixa";
                case 2 -> "Deposito dinheiro";
                case 3 -> "Atualização do Saldo";
                default -> "Operação desconhecida";
            };
            System.out.println("");
            System.out.println(agencia + " " + conta + " " + dataHora + " - " + operacao + " " + formatter.format(valor));
        }
    }
}
