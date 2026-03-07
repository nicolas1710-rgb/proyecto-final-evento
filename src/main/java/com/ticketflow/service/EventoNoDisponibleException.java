package com.ticketflow.service;

public class EventoNoDisponibleException extends RuntimeException {
    public EventoNoDisponibleException(String message) { super(message); }
}
