package com.example.taskmanager.domain.exception;

import com.example.taskmanager.domain.Status;

public class InvalidStatusTransitionException extends RuntimeException {

    public InvalidStatusTransitionException(Status from, Status to) {
        super("invalid transition from " + from + " to " + to);
    }
}
