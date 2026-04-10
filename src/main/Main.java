package main;

import benchmark.Benchmark;

public class Main {

	public static void main(String[] args) {

		int[] tamanhos = { 1000, 5000, 10000 };

		for (int n : tamanhos) {

			System.out.println("\n=======================================");
			System.out.println("EXECUÇÃO PARA TAMANHO: " + n);
			System.out.println("=======================================");

			// 🌳 PROJETO 1 - ÁRVORES
			System.out.println("\n>>> TESTE DE ÁRVORES (BST vs AVL)");
			Benchmark.testarArvores(n);

			// 🔎 PROJETO 2 - BUSCAS
			System.out.println("\n>>> TESTE DE BUSCAS (Sequencial vs Binária)");
			Benchmark.testarBuscas(n);

			// ⚡ PROJETO 3 - ORDENAÇÃO
			System.out.println("\n>>> TESTE DE ORDENAÇÃO");
			Benchmark.testarOrdenacao(n);
		}

		// 🚚 CAIXEIRO VIAJANTE (separado pois usa matriz)
		System.out.println("\n=======================================");
		System.out.println(">>> TESTE CAIXEIRO VIAJANTE");
		System.out.println("=======================================");

		int[] cidades = { 5, 10, 15 };

		for (int c : cidades) {
			System.out.println("\nNúmero de cidades: " + c);
			Benchmark.testarCaixeiro(c);
		}
	}
}