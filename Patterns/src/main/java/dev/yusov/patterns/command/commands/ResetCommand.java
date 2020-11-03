package dev.yusov.patterns.command.commands;

import dev.yusov.patterns.command.Comp;

public class ResetCommand implements Command {

    Comp computer;

    public ResetCommand(Comp computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.reset();
    }
}