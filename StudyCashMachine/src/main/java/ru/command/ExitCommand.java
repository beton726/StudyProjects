package ru.command;

import ru.exception.InterruptOperationException;
import ru.output.ConsoleHelper;

class ExitCommand implements Command {
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Действительно ли вы хотите выйти? y - да, n - нет");
        String option = ConsoleHelper.readString();
        if(option.equals("y"))
            ConsoleHelper.writeMessage("Досвидания.");
        else if(option.equals("n")) {
        }
    }
}