/*
В классе Main была произведена операця по разархивации архива и десериализации файла сохраненной игры в Java класс.
 */

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        openZip("I://Games//savegames//zip.zip", "I://Games//savegames");

        GameProgress gameProgress = openProgress("I://Games//savegames//save2.dat");

        System.out.println(gameProgress.toString());

    }

    public static void openZip(String pathZip, String pathFolder) {
        try (ZipInputStream zin = new ZipInputStream(new
                FileInputStream(pathZip))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();

                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        GameProgress gameProgress = (GameProgress) objectInputStream.readObject();

        return gameProgress;
    }
}
