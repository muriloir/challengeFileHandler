package br.com.challengefilehandler.Factory;

import br.com.challengefilehandler.Entity.Sale;
import br.com.challengefilehandler.Entity.SaleItems;

import java.util.ArrayList;
import java.util.List;

public class SaleFactory {
    public Sale createSale(String line) {
        List<SaleItems> saleItemsList = new ArrayList<>();
        if (line.contains("[")) {
            line = line.replace("[", "").replaceAll("]", "");
        }
        String[] splittedList = line.split("ç");
        String[] splittedItems = splittedList[2].split(",");

        for (String item : splittedItems) {
            SaleItemsFactory saleItemsFactory = new SaleItemsFactory();
            saleItemsList.add(saleItemsFactory.createSaleItems(item));
        }

        return new Sale(splittedList[1],
                new ArrayList<>(saleItemsList),
                splittedList[3]);
    }
}
