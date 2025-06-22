package model;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class Lab04Historico {
    private int numAge, numConta;
    private int dia, mes, ano, hora, min, seg;
    private int codHist;
    private double valor;
    private Vector<String> vetOperacoes = new Vector<>();

    public Lab04Historico(int numAge, int numConta) {
        this.numAge = numAge;
        this.numConta = numConta;
    }

    public boolean gravar(int p_hist, double p_valor) {
        try {
            FileWriter fw = new FileWriter(numAge + "." + numConta + ".hist", true);
            PrintWriter pw = new PrintWriter(fw);

            Date hoje = new Date();
            Calendar cal = new GregorianCalendar();
            cal.setTime(hoje);

            dia = cal.get(Calendar.DAY_OF_MONTH);
            mes = cal.get(Calendar.MONTH) + 1;
            ano = cal.get(Calendar.YEAR);
            hora = cal.get(Calendar.HOUR_OF_DAY);
            min = cal.get(Calendar.MINUTE);
            seg = cal.get(Calendar.SECOND);

            pw.print(formatar(numAge, "0000") + " ");
            pw.print(formatar(numConta, "0000000") + " ");
            pw.print(String.format("%02d %02d %04d ", dia, mes, ano));
            pw.print(String.format("%02d %02d %02d ", hora, min, seg));
            pw.print(p_hist + " ");
            pw.println(p_valor);

            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void imprimir() {
        recuperarHistorico();
        if (vetOperacoes.isEmpty()) {
            System.out.println("Conta sem movimentação.");
            return;
        }

        System.out.println("------------------------------------------");
        for (String linha : vetOperacoes) {
            String[] tSplit = linha.split(" ");
            int cod = Integer.parseInt(tSplit[8]);
            double valor = Double.parseDouble(tSplit[9]);

            String tipo = cod == 1 ? "Saque caixa" : "Deposito dinheiro";

            NumberFormat fmtValor = DecimalFormat.getCurrencyInstance(new Locale("pt", "BR"));
            StringBuffer sb = new StringBuffer();
            sb.append(formatar(numAge, "0000")).append(" ");
            sb.append(formatar(numConta, "0000000")).append(" ");
            sb.append(tSplit[2]).append("/").append(tSplit[3]).append("/").append(tSplit[4]).append(" - ");
            sb.append(tSplit[5]).append(":").append(tSplit[6]).append(":").append(tSplit[7]).append(" - ");
            sb.append(tipo).append(" ");
            sb.append(fmtValor.format(valor));

            System.out.println(sb.toString());
        }
        System.out.println("------------------------------------------");
    }

    public void recuperarHistorico() {
        try {
            FileReader fr = new FileReader(numAge + "." + numConta + ".hist");
            BufferedReader br = new BufferedReader(fr);
            String linha;
            while ((linha = br.readLine()) != null) {
                vetOperacoes.add(linha);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n Conta sem movimento \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatar(int valor, String mascara) {
        NumberFormat formatter = new DecimalFormat(mascara);
        return formatter.format(valor);
    }
}
