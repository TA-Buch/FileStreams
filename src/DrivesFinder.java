import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

/**
 * Класс поиска локальных дисков и вывод их на экран, без сетевых, но
 * включая флешки и CD/DVD.
 */
public class DrivesFinder {

    public static void readDrives () {
        File[] roots = File.listRoots();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        for (File file: roots) {
            if (!"Сетевой диск".equals(fsv.getSystemTypeDescription(file))) {
                System.out.println(file.getAbsolutePath());
            }
        }
    }


    /**
     * Запуск программы.
     * Примеры команд:
     * 1. DrivesFinder.jar ReadDrives - вывести список дисков
     * 2. DrivesFinder.jar FindFile C:\temp aaa - найти файлы
     * 3. DrivesFinder.jar AppendString C:\temp aaa - изменить текстовые файлы
     */
    public static void main( String[] args ) throws IOException{
        if (args.length == 0) {
            System.out.println("No arguments have been supplied");
            return;
        }
        switch (args[0].toLowerCase()) {
            case ("readdrives"): {
                readDrives ();
                break;
            }
            case ("findfile"): {
                Collection<Path> listFiles = FilesFinder.find(args[2], args[1]);
                listFiles.forEach(System.out::println);
                break;
            }
            case ("appendstring"): {
                FileAppender.appendStringToBeginning(args[2], args[1]);
                break;
            }
            default: {
                System.out.println("Unknown command");
            }
        }
    }
}
