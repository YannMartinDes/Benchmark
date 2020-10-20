package test_time;

import java.io.*;
import java.util.zip.*;

public class TestOutputInput implements ITest {

    File file = new File("./text");
    File zipFile = new File("./text.zip");
    int size;

    @Override
    public void setup(int size) {
        this.size = size;

        if(!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void test1() {
        try {
            FileWriter writer = new FileWriter(file);
            for(int i = 0;i<size;i++){
                writer.write(i);
            }
            writer.close();

            FileReader reader =new FileReader(file);
            while(reader.read() != -1) ;
            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void test2() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(int i = 0;i<size;i++){
                writer.write(i);
            }
            writer.close();

            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(reader.read() != -1) ;
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void test3() {
        try {
            createZipFile();

            ZipInputStream reader = new ZipInputStream(new FileInputStream(zipFile));

            while (reader.getNextEntry() != null) ;
            reader.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void createZipFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(zipFile);
        CheckedOutputStream cos = new CheckedOutputStream(fos, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(cos);
        FileWriter writer = new FileWriter(file);

        for (int i = 0; i < size; i++) {
            writer.write(i);
        }
        writer.close();

        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(String.valueOf(file));
        zos.putNextEntry(zipEntry);

        int length;
        byte[] buffer = new byte[1024];
        while ((length = fis.read(buffer)) > 0) {
            zos.write(buffer, 0, length);
        }

        zos.closeEntry();
        zos.finish();
        fis.close();
        zos.close();
    }
}
