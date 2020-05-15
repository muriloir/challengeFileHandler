package br.com.challengefilehandler.Exceptions;

public class InvalidFile extends Exception {
    @Override
    public String getMessage() {
        return "Arquivo inexistente.";
    }
}
