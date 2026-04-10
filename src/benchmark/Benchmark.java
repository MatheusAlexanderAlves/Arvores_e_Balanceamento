package benchmark;

import java.util.Random;

import ordenacao.*;
import busca.*;
import arvorebalanceamento.*;

public class Benchmark {

	static Random r = new Random();

	// 🔹 CASO MÉDIO (aleatório)
	static int[] casoMedio(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = r.nextInt(10000);
		return arr;
	}

	// 🔹 MELHOR CASO (ordenado)
	static int[] melhorCaso(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = i;
		return arr;
	}

	// 🔹 PIOR CASO (invertido)
	static int[] piorCaso(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = n - i;
		return arr;
	}

	// =========================================
	// 🔎 BUSCAS
	// =========================================
	public static void testarBuscas(int n) {

		int[] arr = casoMedio(n);
		int[] arrOrdenado = arr.clone();

		// 🔥 ORDENA UMA VEZ FORA DO TESTE
		java.util.Arrays.sort(arrOrdenado);

		int chave = arr[r.nextInt(n)];

		long[] seq = new long[30];
		long[] bin = new long[30];

		for (int i = 0; i < 30; i++) {

			// 🔹 BUSCA SEQUENCIAL
			long ini = System.nanoTime();
			BuscaSequencial.buscar(arr, chave);
			seq[i] = System.nanoTime() - ini;

			// 🔹 BUSCA BINÁRIA (SEM SORT AGORA)
			ini = System.nanoTime();
			BuscaBinaria.buscar(arrOrdenado, chave);
			bin[i] = System.nanoTime() - ini;
		}

		estatistica("Sequencial", seq);
		estatistica("Binaria", bin);
	}

	// =========================================
	// 🌳 ÁRVORES
	// =========================================
	public static void testarArvores(int n) {

		long[] bst = new long[30];
		long[] avl = new long[30];

		for (int i = 0; i < 30; i++) {

			BST bstTree = new BST();
			AVLTree avlTree = new AVLTree();

			long ini = System.nanoTime();
			for (int j = 0; j < n; j++)
				bstTree.insert(j);
			bst[i] = System.nanoTime() - ini;

			ini = System.nanoTime();
			for (int j = 0; j < n; j++)
				avlTree.insert(j);
			avl[i] = System.nanoTime() - ini;
		}

		estatistica("BST", bst);
		estatistica("AVL", avl);
	}

	// =========================================
	// ⚡ ORDENAÇÃO
	// =========================================
	public static void testarOrdenacao(int n) {

		testarCenario("MELHOR CASO", melhorCaso(n));
		testarCenario("CASO MÉDIO", casoMedio(n));
		testarCenario("PIOR CASO", piorCaso(n));
	}

	private static void testarCenario(String nome, int[] base) {

		long[] insertion = new long[30];
		long[] quick = new long[30];

		for (int i = 0; i < 30; i++) {

			int[] arr1 = base.clone();
			int[] arr2 = base.clone();

			long ini = System.nanoTime();
			InsertionSort.sort(arr1);
			insertion[i] = System.nanoTime() - ini;

			ini = System.nanoTime();
			QuickSort.sort(arr2, 0, arr2.length - 1);
			quick[i] = System.nanoTime() - ini;
		}

		System.out.println("\n=== " + nome + " ===");

		estatistica("Insertion", insertion);
		estatistica("QuickSort", quick);
	}

	// =========================================
	// 🚚 CAIXEIRO VIAJANTE (🔥 CORREÇÃO)
	// =========================================
	public static void testarCaixeiro(int n) {

		long[] tempos = new long[30];

		for (int i = 0; i < 30; i++) {

			int[][] matriz = Heuristica_Caixeiro.gerar(n);

			long ini = System.nanoTime();
			Heuristica_Caixeiro.calcular(matriz);
			tempos[i] = System.nanoTime() - ini;
		}

		estatistica("Caixeiro (" + n + " cidades)", tempos);
	}

	// =========================================
	// 📊 ESTATÍSTICA
	// =========================================
	static void estatistica(String nome, long[] dados) {

		double media = 0;

		for (long d : dados)
			media += d;
		media /= dados.length;

		double var = 0;
		for (long d : dados)
			var += Math.pow(d - media, 2);

		var /= dados.length;

		double desvio = Math.sqrt(var);

		System.out.println(nome + " -> Média: " + media + " | Desvio: " + desvio);
	}
}