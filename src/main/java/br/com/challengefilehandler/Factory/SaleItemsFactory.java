package br.com.challengefilehandler.Factory;
import br.com.challengefilehandler.Entity.SaleItems;

public class SaleItemsFactory {
    public SaleItems createSaleItems(String item){
        String[] splittedItem = item.split("-");
        return new SaleItems(Long.parseLong(splittedItem[0]),
                Integer.parseInt(splittedItem[1]),
                Double.parseDouble(splittedItem[2]));
    }
}
