package br.com.challengefilehandler;
import br.com.challengefilehandler.Service.FileHandler;
import java.io.File;
import java.io.IOException;

public class challengeFileHandlerMain {
    public static void main(String args[]) throws IOException {
        FileHandler fileHandler = new FileHandler();
        File file;

        String pathIn = System.getProperty("user.home").concat("/data/in");
        file = new File(pathIn);
        if (!file.exists()) {
            file.mkdirs();
        }
        file.mkdir();

        pathIn = pathIn.concat("/challenge.dat");
        file = new File(pathIn);
        if (!file.exists()) {
            file.createNewFile();
            fileHandler.createDefaultFile(pathIn);
        }
        fileHandler.reader(pathIn);

        String pathOut = System.getProperty("user.home").concat("/data/out");
        file = new File(pathOut);
        if (!file.exists()) {
            file.mkdirs();
        }
        file.mkdir();

        pathOut = pathOut.concat("/challenge.done.dat");
        fileHandler.writer(pathOut);
    }
}