package com.ticketflow.service;

public class CompraNoModificableException extends RuntimeException {
    public CompraNoModificableException(String message) { super(message); }
}
