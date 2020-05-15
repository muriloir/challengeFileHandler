package br.com.challengefilehandler.Factory;

import br.com.challengefilehandler.Entity.Client;

public class ClientFactory{
    public Client createClient(String line){
        String[] splittedList = line.split("ç");
        return new Client(splittedList[1], splittedList[2], splittedList[3]);
    }
}