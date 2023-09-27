import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Programa base de la práctica, con funciones comunes.
 *
 * @author weiweng
 * @author robquir
 */
public abstract class BaseProgram {

  /**
   * Repeticiones para cada K
   */
  protected static final int REPETICIONES = 20;
  /**
   * Comienzo del K
   */
  protected static final int MAX_K = 30;
  /**
   * Máximo del K
   */
  protected static final int MIN_K = 3;


  /**
   * Obtiene la version actual del resultado del programa
   *
   * @param program Numero del programa
   * @return la version actual del resultado del programa
   * @throws IOException si ocurre un error al crear o leer el archivo
   */
  protected static int getCurrentVersion(int program) throws IOException {
    char separator = File.separatorChar;

    String currentFilePath = String.format("results%c%d%ccurrent", separator, program, separator);
    // fichero numero de resultado
    File currentFile = new File(System.getProperty("user.dir"), currentFilePath);

    // numero de resultado
    int current;

    if (!currentFile.exists()) {
      current = 0;
      // creamos fichero version actual
      createFile(currentFile);
      try (FileWriter fileWriter = new FileWriter(currentFile)) {
        fileWriter.write(String.valueOf(current));
      } catch (IOException e) {
        e.printStackTrace();
        throw new IOException("Error al escribir el archivo current");
      }
    } else {
      //read number from file
      try (Scanner scanner = new Scanner(currentFile)) {
        current = scanner.nextInt();
      } catch (IOException e) {
        e.printStackTrace();
        throw new IOException("Error al leer el archivo current");
      }
    }
    return current;
  }


  /**
   * Genera un fichero.
   * Si existe, lo sobreescribe con un nuevo fichero vacio.
   * Si no existe, lo crea.
   *
   * @param resultFile fichero de a crear
   * @throws IOException si ocurre un error al crear o eliminar el fichero
   */
  protected static void createFile(File resultFile) throws IOException {
    boolean deleteStatus = false;
    boolean isFileExists = resultFile.exists();
    // Si existe el fichero le eliminamos
    if (isFileExists) deleteStatus = resultFile.delete();
    else {
      resultFile.getParentFile().mkdirs();
    }
    // Salimos del programa si no conseguimos eliminar el archivo
    if (isFileExists && !deleteStatus) {
      System.out.println("Se ha producido un error al intentar eliminar el archivo!");
      throw new IOException("Se ha producido un error al intentar eliminar el archivo!");
    }
    // Intentamos crear el archivo, si falla salimos
    if (!resultFile.createNewFile()) {
      System.out.println("Se ha producido un error al intentar crear el archivo!");
      throw new IOException("Se ha producido un error al intentar crear el archivo!");
    }
  }


  /**
   * Escribe los resultados en el fichero de resultados
   *
   * @param resultFile   fichero de resultados
   * @param resultString resultados
   * @throws IOException si no se puede escribir en el fichero
   */
  protected static void outputResults(File resultFile, String resultString) throws IOException {
    // Creaamos el FileWriter y escribimos los resultados
    FileWriter fileWriter = new FileWriter(resultFile);
    fileWriter.write(resultString);
    fileWriter.flush();
    fileWriter.close();
  }

}
