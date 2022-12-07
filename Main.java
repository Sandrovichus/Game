import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        String text = "";
        String currentText = "";

        File dir1 = new File("C://Games/src");
        if (dir1.mkdir()) {
            currentText = "Каталог " + dir1.getName() +  " создан ";
            text = text + currentText + "\r\n";
        }

        File dir2 = new File("C://Games/res");
        if (dir2.mkdir()) {
            currentText = "Каталог " + dir2.getName() +  " создан ";
            text = text + currentText + "\r\n";
        }

        File dir3 = new File("C://Games/savegames");
        if (dir3.mkdir()) {
            currentText = "Каталог " + dir3.getName() +  " создан ";
            text = text + currentText + "\r\n";
        }

        File dir4 = new File("C://Games/temp");
        if (dir4.mkdir()) {
            currentText = "Каталог " + dir4.getName() +  " создан ";
            text = text + currentText + "\r\n";
        }

        File dir1_1 = new File("C://Games/src/main");
        if (dir1_1.mkdir()) {
            currentText = "Каталог " + dir1_1.getName() +  " создан ";
            text = text + currentText + "\r\n";
        }

        File dir1_2 = new File("C://Games/src/test");
        if (dir1_2.mkdir()) {
            currentText = "Каталог " + dir1_2.getName() +  " создан ";
            text = text + currentText + "\r\n";
        }

        File file1_1_1 = new File("C://Games/src/main", "Main.java");
        try {
            if (file1_1_1.createNewFile()) {
                currentText = "Файл " + file1_1_1.getName() + " создан";
                text = text + currentText + "\r\n";
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        File file1_1_2 = new File("C://Games/src/main", "Utils.java");
        try {
            if (file1_1_2.createNewFile()) {
                currentText = "Файл " + file1_1_2.getName() + " создан";
                text = text + currentText + "\r\n";
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        File dir2_1 = new File("C://Games/res/drawables");
        if (dir2_1.mkdir()) {
            currentText = "Каталог " + dir2_1.getName() +  " создан ";
            text = text + currentText + "\r\n";
        }

        File dir2_2 = new File("C://Games/res/vectors");
        if (dir2_2.mkdir()) {
            currentText = "Каталог " + dir2_2.getName() +  " создан ";
            text = text + currentText + "\r\n";
        }

        File dir2_3 = new File("C://Games/res/icons");
        if (dir2_3.mkdir()) {
            currentText = "Каталог " + dir2_3.getName() +  " создан ";
            text = text + currentText + "\r\n";
        }

        File file4_1 = new File("C://Games/temp", "temp.txt");
        try {
            if (file4_1.createNewFile()) {
                currentText = "Файл " + file4_1.getName() + " создан";
                text = text + currentText + "\r\n";
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (FileWriter writer = new FileWriter("C://Games/temp/temp.txt", false)) {
            writer.write(text);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        GameProgress gameProgress1 = new GameProgress(5, 2, 3, 1.5);
        GameProgress gameProgress2 = new GameProgress(4, 3, 4, 3.5);
        GameProgress gameProgress3 = new GameProgress(10, 8, 5, 5.2);

        saveGame("C://Games/savegames/save1.dat", gameProgress1);
        saveGame("C://Games/savegames/save2.dat", gameProgress2);
        saveGame("C://Games/savegames/save3.dat", gameProgress3);

        List<String> paths = Arrays.asList("C://Games/savegames/save1.dat", "C://Games/savegames/save2.dat",
                "C://Games/savegames/save3.dat");
        zipFiles("C://Games/savegames/zip.zip", paths);

        File save1 = new File("C://Games/savegames/save1.dat");
        save1.delete();
        File save2 = new File("C://Games/savegames/save2.dat");
        save2.delete();
        File save3 = new File("C://Games/savegames/save3.dat");
        save3.delete();
    }

    public static void saveGame(String path, GameProgress gameprogress) {
        try (FileOutputStream fos = new FileOutputStream(path); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameprogress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String path, List<String> paths) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String s : paths) {
                try (FileInputStream fis = new FileInputStream(s)) {
                    ZipEntry entry = new ZipEntry(s.substring(s.lastIndexOf("/") + 1));
                    zout.putNextEntry(entry);
                    // считываем содержимое файла в массив byte
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    // добавляем содержимое к архиву
                    zout.write(buffer);
                    // закрываем текущую запись для новой записи
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
