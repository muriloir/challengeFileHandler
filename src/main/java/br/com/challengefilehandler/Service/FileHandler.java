package br.com.challengefilehandler.Service;

import br.com.challengefilehandler.Entity.Client;
import br.com.challengefilehandler.Entity.Sale;
import br.com.challengefilehandler.Entity.SaleItems;
import br.com.challengefilehandler.Entity.Salesman;
import br.com.challengefilehandler.Exceptions.InvalidFile;
import br.com.challengefilehandler.Factory.ClientFactory;
import br.com.challengefilehandler.Factory.SaleFactory;
import br.com.challengefilehandler.Factory.SalesmanFactory;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileHandler {
    List<Salesman> salesmanList = new ArrayList<>();
    List<Client> clientList = new ArrayList<>();
    List<Sale> saleList = new ArrayList<>();
    double expensiveSale = 0.0;
    double sumOfPurchase = 0.0;
    double cheapSale = 0.0;
    String salesmanName = "";
    String line = "";

    public void reader(String path) throws InvalidFile {
        BufferedReader buffRead;
        try {
            buffRead = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new InvalidFile();
        }
        while (true) {
            if (line != null) {
                createFactories(line);
            } else {
                break;
            }
            try {
                line = buffRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            buffRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFactories(String line) {
        if (line.contains("001")) {
            SalesmanFactory salesmanFactory = new SalesmanFactory();
            salesmanList.add(salesmanFactory.createSalesman(line));

        } else if (line.contains("002")) {
            ClientFactory clientFactory = new ClientFactory();
            clientList.add(clientFactory.createClient(line));

        } else if (line.contains("003")) {
            SaleFactory saleFactory = new SaleFactory();
            saleList.add(saleFactory.createSale(line));
        }

    }

    public String expensiveSaleId(List<Sale> saleList) {
        expensiveSale = 0.0;
        sumOfPurchase = 0.0;
        cheapSale = expensiveSale;
        String saleId = "";
        for (Sale sale : saleList) {
            sumOfPurchase = sumOfPurchase(sale);
            if (sumOfPurchase > expensiveSale) {
                expensiveSale = sumOfPurchase;
                cheapSale = expensiveSale;
                saleId = sale.getSaleId();
                sumOfPurchase = 0.0;
            }
        }
        return saleId;
    }

    public double sumOfPurchase(Sale sale) {
        double sumOfPurchase = 0.0;
        for (SaleItems saleItems : sale.getSalesItems()) {
            sumOfPurchase += (saleItems.getPrice() * saleItems.getQuantity());
        }
        return sumOfPurchase;
    }

    public String worstSalesman(List<Sale> saleList) {
        for (Sale sale : saleList) {
            cheapSale = expensiveSale;
            sumOfPurchase = 0.0;
            sumOfPurchase = sumOfPurchase(sale);

            if (sumOfPurchase < cheapSale) {
                cheapSale = sumOfPurchase;
                salesmanName = sale.getSalesmanName();
            }
        }
        return salesmanName;
    }

    public void writer(String path) {
        BufferedWriter buffWrite = null;
        try {
            buffWrite = new BufferedWriter(new FileWriter(path));
            buffWrite.append(String.format("%dç%dç%sç%s",
                    clientList.size(),
                    salesmanList.size(),
                    expensiveSaleId(saleList),
                    worstSalesman(saleList)) + "\n");
            buffWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDefaultFile(String pathIn) {
        BufferedWriter buffWrite = null;
        try {
            buffWrite = new BufferedWriter(new FileWriter(pathIn));
            buffWrite.append("001ç1234567891234çPedroç50000\n" +
                    "001ç3245678865434çPauloç40000.99\n" +
                    "002ç2345675434544345çJose da SilvaçRural\n" +
                    "002ç2345675433444345çEduardo PereiraçRural\n" +
                    "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro\n" +
                    "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");
            buffWrite.close();
        } catch (IOException e) {}
    }

    public void existingFileChecker(File file, String pathIn){
        if (!file.exists()) {
            file.mkdirs();
        }
        file.mkdir();

        pathIn = pathIn.concat("/challenge.dat");
        file = new File(pathIn);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {}
            createDefaultFile(pathIn);
        }
    }
}