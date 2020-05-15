package br.com.challengefilehandler.Service;
import br.com.challengefilehandler.Entity.Salesman;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileHandlerTest {
    @Test
    public void givenFileWithSellerAndExpectedListWithThat() {
        FileHandler fileHandler = new FileHandler();
        List<Salesman> salesmanList = new ArrayList<>();
        salesmanList.add(new Salesman("1234567891234", "Pedro", 50000.0));
        String line = "001ç1234567891234çPedroç50000";
        fileHandler.createFactories(line);
        assertEquals(salesmanList, fileHandler.getSalesmanList());
    }
    @Test
    public void givenSaleListAndExpectedTheMostExpensiveSaleId() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.createFactories("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");
        fileHandler.createFactories("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
        assertEquals("10", fileHandler.expensiveSaleId(fileHandler.getSaleList()));
    }
    @Test
    public void givenSaleAndExpectedSumOfAllItems() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.createFactories("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
        assertEquals(1199.0, fileHandler.sumOfPurchase(fileHandler.getSaleList().get(0)), 0.00);
    }
    @Test
    public void givenListSalesAndExpectedNameOfWorstSeller() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.createFactories("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
        fileHandler.createFactories("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");
        fileHandler.expensiveSaleId(fileHandler.getSaleList());
        assertEquals("Paulo", fileHandler.worstSalesman(fileHandler.getSaleList()));
    }
}