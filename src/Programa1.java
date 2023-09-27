import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * Programa de la parte 1 de la práctica
 *
 * @author weiweng
 * @author robquir
 */
public class Programa1 extends BaseProgram {


  /**
   * Programa de la parte 1 de la práctica
   *
   * @param args Argumentos de la línea de comandos
   * @throws IOException Si hay algún error de apertura o leida de ficheros
   */
  public static void main(String[] args) throws IOException {

    // numero de resultado
    int current = getCurrentVersion(1);

    // Fichero en el que se escribe los resultados (directorio del proyecto/results/Result.csv)
    File resultFile = new File(System.getProperty("user.dir") + String.format("/results/1/Result%d.csv", current));

    // generamos fichero de salida
    createFile(resultFile);


    // Cabezera del fichero
    String prefixBuilder = "K size, " +
            "Comparisons, " +
            "Assignments," +
            "Media," +
            "Time(ns)\n";
    StringBuilder resultStringBuilder = new StringBuilder(prefixBuilder);


    /**
     * Tamaño del vector
     */
    int TAM = 100000;

    // vector de enteros
    int[] vector;

    // indice k
    int k = MIN_K;

    // dato quicksort modificado
    DataConfig data = new DataConfig();

    for (int i = 0; i < 2; i++) {

      // maximo k = 30
      while (k <= MAX_K) {

        vector = new int[TAM];


        // 20 repeticiones para cada k
        for (int z = 0; z < REPETICIONES; z++) {
          System.out.println("Starting TAM : " + i + " k = "  + k );
          // relleno del vector
          Algorithms.populate(vector);
          // descolacion de elementos
          Algorithms.randomize(vector, vector.length);

          Instant startTime = Instant.now();
          if (z == 0) {
            Algorithms.ordena1(vector, vector.length, data);
          } else if (z == 1) Algorithms.ordena2(vector, vector.length, data);
          else Algorithms.ordena3(vector, vector.length, data);
          data.incrementarTime(Duration.between(startTime, Instant.now()).toNanos());
        }


        long mediaComps = data.getComps() / REPETICIONES;
        long mediaAsigs = data.getAsigs() / REPETICIONES;
        long media = (mediaComps + mediaAsigs) / 2;
        long mediaTime = data.getTime() / REPETICIONES;

        resultStringBuilder.append(k).append(',')
                .append(mediaComps).append(',')
                .append(mediaAsigs).append(',')
                .append(media).append(',')
                .append(mediaTime)
                .append("\n");

        data.setAsigs(0);
        data.setComps(0);
        data.setTime(0);
        k++;
      }


    }


    // escribimos los resultados
    outputResults(resultFile, resultStringBuilder.toString());
  }
}