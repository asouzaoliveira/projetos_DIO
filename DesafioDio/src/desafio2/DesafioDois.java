package desafio2;

import java.util.Scanner;

public class DesafioDois {

	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
        int cont = 0;
        double soma=0;
      int n=1;
        while (n >= 1) {
            n = leitor.nextInt();
            
            if (n >= 0) { 
               cont++;
               soma+=n;
            }
        }
        double media = soma / cont;
        System.out.println(String.format("%.2f", media));
    }

	}


