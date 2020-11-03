package dev.yusov.patterns.command.commands;

import dev.yusov.patterns.command.Comp;

public class StopCommand implements Command {

    Comp computer;

    public StopCommand(Comp computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.stop();
    }
}