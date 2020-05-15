package br.com.challengefilehandler.File;
import br.com.challengefilehandler.Entity.Client;
import br.com.challengefilehandler.Entity.Sale;
import br.com.challengefilehandler.Entity.SaleItems;
import br.com.challengefilehandler.Entity.Salesman;
import br.com.challengefilehandler.Factory.ClientFactory;
import br.com.challengefilehandler.Factory.SaleFactory;
import br.com.challengefilehandler.Factory.SalesmanFactory;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileHandler {
    List<Salesman> salesmanList = new ArrayList<>();
    List<Client> clientList = new ArrayList<>();
    List<Sale> saleList = new ArrayList<>();
    String salesmanName = "";
    String line = "";

    public void reader(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        while (true) {
            if (line != null) {
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
            } else {
                break;
            }
            line = buffRead.readLine();
        }
        buffRead.close();
    }

    public String expensiveSaleId(List<Sale> saleList) {
        double expensiveSale = 0.0;
        double sumOfPurchase = 0.0;
        double cheapSale = expensiveSale;
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
        worstSalesman(saleList, cheapSale, expensiveSale, sumOfPurchase);
        return saleId;
    }

    public double sumOfPurchase(Sale sale) {
        double sumOfPurchase = 0.0;
        for (SaleItems saleItems : sale.getSalesItems()) {
            double mult = (saleItems.getPrice() * saleItems.getQuantity());
            sumOfPurchase += mult;
        }
        return sumOfPurchase;
    }

    public void worstSalesman(List<Sale> salelist, double cheapSale, double expensiveSale, double sumOfPurchase) {
        for (Sale sale : saleList) {
            cheapSale = expensiveSale;
            sumOfPurchase = 0.0;
            for (SaleItems saleItems : sale.getSalesItems()) {
                double mult = (saleItems.getPrice() * saleItems.getQuantity());
                sumOfPurchase += mult;
            }

            if (sumOfPurchase < cheapSale) {
                cheapSale = sumOfPurchase;
                salesmanName = sale.getSalesmanName();
            }
        }
    }

    public void writer(String path) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        buffWrite.append(String.format("%dç%dç%sç%s",
                clientList.size(),
                salesmanList.size(),
                expensiveSaleId(saleList),
                salesmanName) + "\n");
        buffWrite.close();
    }
}