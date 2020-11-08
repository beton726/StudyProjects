package ru.command;

import ru.exception.InterruptOperationException;

public interface Command {

    void execute() throws InterruptOperationException;

}