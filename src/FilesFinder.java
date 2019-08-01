import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс поиска файла по его имени: полное совпадение, частичное совпадение
 * (имя файла содержит заданное сочетание символов).
 */
public class FilesFinder {

    /**
     * Процедура поиска файлов
     * @return список путей к файлам, равных или частично содержащих в имени (@param fileName),
     * включая вложенные директории (@param searchDirectory)
     */
    public static Collection<Path> find(String fileName, String searchDirectory) throws IOException {
        Pattern pattern = Pattern.compile(fileName, Pattern.CASE_INSENSITIVE);
        try (Stream<Path> files = Files.walk(Paths.get(searchDirectory))) {
            return files
                    .filter(f -> f.toFile().isFile())
                    .filter(f -> pattern.matcher(f.getFileName().toString()).find())
                    .collect(Collectors.toList());
        }
    }

    /**
     * Процедура поиска файлов
     * @param fileName - имя файла или его часть
     * @param extension - расширение искомого файла
     * @param searchDirectory - директория для поиска файлов
     * @return список путей к файлам с заданным расширением
     */
    public static Collection<Path> find(String fileName, String extension, String searchDirectory) throws IOException {
        Pattern extensionPattern = Pattern.compile(extension, Pattern.CASE_INSENSITIVE);
        Pattern pattern = Pattern.compile(fileName, Pattern.CASE_INSENSITIVE);
        try (Stream<Path> files = Files.walk(Paths.get(searchDirectory))) {
            return files
                    .filter(f -> extensionPattern.matcher(f.getFileName().toString()).find())
                    .filter(f -> pattern.matcher(f.getFileName().toString()).find())
                    .collect(Collectors.toList());
        }
    }
}
