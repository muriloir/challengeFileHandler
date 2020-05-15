package br.com.challengefilehandler;

import br.com.challengefilehandler.Exceptions.InvalidFile;
import br.com.challengefilehandler.Service.FileHandler;

import java.io.File;

public class challengeFileHandlerMain {
    public static void main(String args[]) {
        FileHandler fileHandler = new FileHandler();
        File file;
        String pathIn = System.getProperty("user.home").concat("/data/in/challenge.dat");

        try {
            fileHandler.reader(pathIn);
        } catch (InvalidFile invalidFile) {
            System.out.println(invalidFile.getMessage());
            System.out.println("Criando um novo arquivo.dat.");
            pathIn = System.getProperty("user.home").concat("/data/in");
            file = new File(pathIn);
            fileHandler.existingFileChecker(file, pathIn);
        }

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