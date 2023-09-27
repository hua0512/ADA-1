import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase con utilidades de algoritmos de ordenación
 *
 * @author weiweng
 * @author
 */
public class Algorithms {


  /**
   * Rellena un vector de tamaño n con números desde 1 hasta tamaño del vector
   *
   * @param vector el vector a rellenar
   */
  public static void populate(int[] vector) {
    for (int i = 0; i < vector.length; i++) vector[i] = i + 1;
  }

  /**
   * Desordena un vector de tamaño n hasta la posición k con valores aleatorios.
   *
   * @param vector el vector a desordenar
   * @param k      la posición hasta la que desordena
   */
  public static void randomize(int[] vector, int k) {
    for (int i = 0; i < k; i++) {
      // numero aleatorio desde 0 hasta k (exclusive)
      int randomX = ThreadLocalRandom.current().nextInt(0, k);
      // numero aleatorio desde 0 hasta k (exclusive)
      int randomY = ThreadLocalRandom.current().nextInt(0, k);
      swap(vector, randomX, randomY);
    }
  }


  /**
   * Intercambio de elementos en un vector
   *
   * @param v el vector
   * @param i posición del primer elemento
   * @param j posición del segundo elemento
   */
  private static void swap(int[] v, int i, int j) {
    int tmp = v[i];
    v[i] = v[j];
    v[j] = tmp;
  }


  public static void ordena1(int v[], int tam, DataConfig data) {
    /* v con índices de 0 a tam-1 */
    int h, r, i, j, w;
    r = tam - 1;
    h = 1;
    while (h <= r / 9) {
      h = 3 * h + 1;
    }
    while (h > 0) {
      for (i = h; i <= r; i++) {
        j = i;
        w = v[i];
        data.incrementarAsigs();
        while ((j >= h) && (w < v[j - h])) {
          v[j] = v[j - h];
          data.incrementarAsigs();
          data.incrementarComps();
          j = j - h;
        }
        v[j] = w;
        data.incrementarAsigs();
      }
      h = h / 3;
    }
  }

  public static void ordena2(int v[], int tam, DataConfig data) {
    /* v con índices de 0 a tam-1 */
    boolean swapped = true;
    int start = 0;
    int end = tam;
    while (swapped == true) {
      swapped = false;
      for (int i = start; i < end - 1; ++i) {
        data.incrementarComps();
        if (v[i] > v[i + 1]) {
          int temp = v[i];
          v[i] = v[i + 1];
          v[i + 1] = temp;
          data.incrementarAsigs(3);
          swapped = true;
        }
      }
      if (swapped == false) {
        break;
      }
      swapped = false;
      end = end - 1;
      for (int i = end - 1; i >= start; i--) {
        data.incrementarComps();
        if (v[i] > v[i + 1]) {
          int temp = v[i];
          v[i] = v[i + 1];
          v[i + 1] = temp;
          data.incrementarAsigs(3);
          swapped = true;
        }
      }
      start = start + 1;
    }
  }


  public static  void ordena3(int[] v, int tam, DataConfig data) {
    /* v con índices de 0 a tam-1 */
    int w[] = new int[tam];
    ordena3rec(v, w, 0, tam - 1, data);
  }

  static void ordena3rec(int v[], int w[], int l, int r, DataConfig data) {
    if (l < r) {
      int m = (l + r) / 2;
      ordena3rec(v, w, l, m, data);
      ordena3rec(v, w, m + 1, r, data);
      int ia = l;
      int ib = m + 1;
      int ic = l;
      while ((ia <= m) && (ib <= r)) {
        data.incrementarComps();
        if (v[ia] < v[ib]) {
          w[ic] = v[ia];
          ia++;
          ic++;
          data.incrementarAsigs();
        } else {
          w[ic] = v[ib];
          ib++;
          ic++;
          data.incrementarAsigs();
        }
      }
      while (ia <= m) {
        w[ic] = v[ia];
        ia++;
        ic++;
        data.incrementarAsigs();
      }

      while (ib <= r) {
        w[ic] = v[ib];
        ib++;
        ic++;
        data.incrementarAsigs();
      }
      for (int i = l; i <= r; i++) {
        v[i] = w[i];
        data.incrementarAsigs();
      }
    }
  }


}
