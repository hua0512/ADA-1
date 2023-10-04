import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * Programa de la parte 1 de la práctica
 *
 * @author weiweng
 * @author
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
    String prefixBuilder = "Vector size, " +
            "Comparisons, " +
            "Assignments," +
            "Media," +
            "Time(ns)\n";
    StringBuilder resultStringBuilder = new StringBuilder(prefixBuilder);
    StringBuilder resultStringBuilder2 = new StringBuilder(prefixBuilder);
    StringBuilder resultStringBuilder3 = new StringBuilder(prefixBuilder);


    /**
     * Tamaño del vector
     */
    int TAM = 10000;

    // vector de enteros
    int[] vector;

    // alg1
    DataConfig data1 = new DataConfig();
    // alg 2
    DataConfig data2 = new DataConfig();
//    alg 3
    DataConfig data3 = new DataConfig();

    while (TAM <= 100000) {

      vector = new int[TAM];

      for (int i = 0; i < REPETICIONES; i++) {
        // relleno del vector
        Algorithms.populate(vector);
        // descolacion de elementos
        Algorithms.randomize(vector, vector.length);

        Instant startTime = Instant.now();
        Algorithms.ordena1(vector, vector.length, data1);
        data1.incrementarTime(Duration.between(startTime, Instant.now()).toNanos());

        // relleno del vector
        Algorithms.populate(vector);
        // descolacion de elementos
        Algorithms.randomize(vector, vector.length);

        startTime = Instant.now();
        Algorithms.ordena2(vector, vector.length, data2);
        data2.incrementarTime(Duration.between(startTime, Instant.now()).toNanos());

        // relleno del vector
        Algorithms.populate(vector);
        // descolacion de elementos
        Algorithms.randomize(vector, vector.length);

        startTime = Instant.now();
        Algorithms.ordena3(vector, vector.length, data3);
        data3.incrementarTime(Duration.between(startTime, Instant.now()).toNanos());
      }

      long mediaComps1 = data1.getComps() / REPETICIONES;
      long mediaAsigs1 = data1.getAsigs() / REPETICIONES;
      long media1 = (mediaComps1 + mediaAsigs1) / 2;
      long mediaTime1 = data1.getTime() / REPETICIONES;

      long mediaComps2 = data2.getComps() / REPETICIONES;
      long mediaAsigs2 = data2.getAsigs() / REPETICIONES;
      long media2 = (mediaComps2 + mediaAsigs2) / 2;
      long mediaTime2 = data2.getTime() / REPETICIONES;

      long mediaComps3 = data3.getComps() / REPETICIONES;
      long mediaAsigs3 = data3.getAsigs() / REPETICIONES;
      long media3 = (mediaComps3 + mediaAsigs3) / 2;
      long mediaTime3 = data3.getTime() / REPETICIONES;

      resultStringBuilder.append(TAM).append(',')
              .append(mediaComps1).append(',')
              .append(mediaAsigs1).append(',')
              .append(media1).append(',')
              .append(mediaTime1)
              .append("\n");

      resultStringBuilder2.append(TAM).append(',')
              .append(mediaComps2).append(',')
              .append(mediaAsigs2).append(',')
              .append(media2).append(',')
              .append(mediaTime2)
              .append("\n");

      resultStringBuilder3.append(TAM).append(',')
              .append(mediaComps3).append(',')
              .append(mediaAsigs3).append(',')
              .append(media3).append(',')
              .append(mediaTime3)
              .append("\n");

      data1.setAsigs(0);
      data1.setComps(0);
      data1.setTime(0);
      data2.setAsigs(0);
      data2.setComps(0);
      data2.setTime(0);
      data3.setAsigs(0);
      data3.setComps(0);
      data3.setTime(0);

      TAM += 10000;
    }

    // escribimos los resultados
    outputResults(resultFile, resultStringBuilder + "\n" + resultStringBuilder2 + "\n" + resultStringBuilder3);
  }
}