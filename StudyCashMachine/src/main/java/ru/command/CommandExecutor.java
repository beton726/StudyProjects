package ru.command;

import ru.Operation;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {

    private static final Map<Operation, Command> allKnownCommandsMap = new HashMap<Operation, Command>();

    static {
        allKnownCommandsMap.put(Operation.DEPOSIT, new DepositCommand());
        allKnownCommandsMap.put(Operation.INFO, new InfoCommand());
        allKnownCommandsMap.put(Operation.WITHDRAW, new WithdrawCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
    }

    private CommandExecutor() {}

    public static final void execute(Operation operation) {
        allKnownCommandsMap.get(operation).execute();
    }
}