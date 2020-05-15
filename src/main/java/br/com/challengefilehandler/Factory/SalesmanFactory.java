package br.com.challengefilehandler.Factory;

import br.com.challengefilehandler.Entity.Salesman;

public class SalesmanFactory {
    public Salesman createSalesman(String line) {
        String[] splittedList = line.split("ç");
        return new Salesman(splittedList[1],
                splittedList[2],
                Double.parseDouble(splittedList[3]));
    }
}
