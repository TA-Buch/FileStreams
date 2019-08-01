import java.io.*;
import java.nio.file.*;
import java.util.Collection;


/**
 * Класс внесение строки в начало текстового файла. Вывод на экран в
 * консоль найденных файлов.
 */
public class FileAppender {

    /**
     * Процедура вносит строку "T E S T" в начало каждого найденного файла с расширением txt
     * @param fileName - имя файла или его часть
     * @param searchDirectory - директория для поиска файлов
     */
    public static void appendStringToBeginning (String fileName, String searchDirectory) throws IOException {
        Collection<Path> listFiles = FilesFinder.find(fileName,"\\.txt$", searchDirectory);
        for (Path fileSrc: listFiles) {
            File tempFile = File.createTempFile("temp", ".tmp");
            Files.copy(fileSrc, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            try (BufferedWriter writer = Files.newBufferedWriter(fileSrc, StandardOpenOption.TRUNCATE_EXISTING);
            BufferedReader reader = Files.newBufferedReader(tempFile.toPath())) {
                writer.write("T E S T");
                String strCurrentLine;
                while ((strCurrentLine = reader.readLine()) != null) {
                    writer.write(strCurrentLine);
                }
            } finally {
                tempFile.delete();
            }
            System.out.println(fileSrc);
        }
    }
}