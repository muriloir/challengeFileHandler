package br.com.challengefilehandler;
import br.com.challengefilehandler.File.FileHandler;

import java.io.IOException;

public class challengeFileHandlerMain {
    public static void main(String args[]) throws IOException {
        FileHandler fh = new FileHandler();

        String pathIn = System.getProperty("user.home")+"/Desktop/br.com.desafio-backend-arquivo/src/main/resources/data/in/challenge.dat";
        fh.reader(pathIn);

        String pathOut = System.getProperty("user.home")+"/Desktop/br.com.desafio-backend-arquivo/src/main/resources/data/out/challenge.done.dat";
        fh.writer(pathOut);
    }
}