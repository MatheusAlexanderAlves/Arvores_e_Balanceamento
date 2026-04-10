package ordenacao;

public class QuickSort {

	public static void sort(int[] arr, int ini, int fim) {

		if (ini < fim) {
			int p = particionar(arr, ini, fim);
			sort(arr, ini, p - 1);
			sort(arr, p + 1, fim);
		}
	}

	private static int particionar(int[] arr, int ini, int fim) {

		int pivo = arr[fim];
		int i = ini - 1;

		for (int j = ini; j < fim; j++) {
			if (arr[j] < pivo) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		int temp = arr[i + 1];
		arr[i + 1] = arr[fim];
		arr[fim] = temp;

		return i + 1;
	}
}