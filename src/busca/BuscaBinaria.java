package busca;

public class BuscaBinaria {

	public static int buscar(int[] arr, int chave) {

		int ini = 0, fim = arr.length - 1;

		while (ini <= fim) {
			int meio = (ini + fim) / 2;

			if (arr[meio] == chave)
				return meio;

			if (arr[meio] < chave)
				ini = meio + 1;
			else
				fim = meio - 1;
		}

		return -1;
	}
}