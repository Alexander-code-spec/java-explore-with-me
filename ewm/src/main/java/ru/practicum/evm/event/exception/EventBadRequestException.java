package ru.practicum.evm.event.exception;

public class EventBadRequestException extends RuntimeException {
    public EventBadRequestException(String message) {
        super(message);
    }
}
