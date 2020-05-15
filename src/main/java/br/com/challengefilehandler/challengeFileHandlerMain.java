package br.com.challengefilehandler;
import br.com.challengefilehandler.File.FileHandler;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class challengeFileHandlerMain {
    public static void main(String args[]) throws IOException {
        FileHandler fh = new FileHandler();
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
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(pathIn));
            buffWrite.append("001ç1234567891234çPedroç50000\n" +
                    "001ç3245678865434çPauloç40000.99\n" +
                    "002ç2345675434544345çJose da SilvaçRural\n" +
                    "002ç2345675433444345çEduardo PereiraçRural\n" +
                    "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro\n" +
                    "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");
            buffWrite.close();
        }

        fh.reader(pathIn);

        String pathOut = System.getProperty("user.home").concat("/data/out");
        file = new File(pathOut);
        if (!file.exists()) {
            file.mkdirs();
        }
        file.mkdir();

        pathOut = pathOut.concat("/challenge.done.dat");
        fh.writer(pathOut);
    }
}