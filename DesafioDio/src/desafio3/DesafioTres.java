package desafio3;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class DesafioTres {

	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		TreeMap<Integer, Integer> numeros = new TreeMap<>();
		int N = leitor.nextInt();
		for (int i = 0; i < N; i++) {
			int numero = leitor.nextInt();
			if (numeros.containsKey(numero))
				numeros.replace(numero, numeros.get(numero) + 1);
			else
				numeros.put(numero, 1);
		}
		for (Entry<Integer, Integer> entry : numeros.entrySet()) {
			System.out.println(entry.getKey() + " aparece " + entry.getValue() + " vez(es)");
		}
	}
	}


