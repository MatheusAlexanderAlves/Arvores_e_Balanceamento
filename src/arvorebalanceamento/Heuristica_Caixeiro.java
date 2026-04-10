package arvorebalanceamento;

import java.util.Random;

public class Heuristica_Caixeiro {

	public static int[][] gerar(int n) {
		Random r = new Random();
		int[][] m = new int[n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i != j)
					m[i][j] = r.nextInt(100) + 1;

		return m;
	}

	public static int calcular(int[][] dist) {

		int n = dist.length;
		boolean[] visitado = new boolean[n];

		int atual = 0;
		int custo = 0;

		visitado[0] = true;

		for (int i = 1; i < n; i++) {

			int prox = -1;
			int menor = Integer.MAX_VALUE;

			for (int j = 0; j < n; j++) {
				if (!visitado[j] && dist[atual][j] < menor) {
					menor = dist[atual][j];
					prox = j;
				}
			}

			visitado[prox] = true;
			custo += menor;
			atual = prox;
		}

		return custo + dist[atual][0];
	}
}