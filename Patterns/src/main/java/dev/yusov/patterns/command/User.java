package dev.yusov.patterns.command;

import dev.yusov.patterns.command.commands.Command;

// Invoker
public class User {
    Command start;
    Command stop;
    Command reset;
    public User(Command start, Command stop, Command reset) {
        this.start = start;
        this.stop = stop;
        this.reset = reset;
    }
    void startComputer() {
        start.execute();
    }
    void stopComputer() {
        stop.execute();
    }
    void resetComputer() {
        reset.execute();
    }
}