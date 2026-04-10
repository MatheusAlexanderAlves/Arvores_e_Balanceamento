package busca;

public class BuscaSequencial {

	public static int buscar(int[] arr, int chave) {
		for (int i = 0; i < arr.length; i++)
			if (arr[i] == chave)
				return i;
		return -1;
	}
}