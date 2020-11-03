package dev.yusov.patterns.command.commands;

import dev.yusov.patterns.command.Comp;

public class StartCommand implements Command {

    Comp computer;

    public StartCommand(Comp computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.start();
    }
}