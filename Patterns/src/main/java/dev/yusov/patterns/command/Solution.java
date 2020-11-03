package dev.yusov.patterns.command;

import dev.yusov.patterns.command.commands.ResetCommand;
import dev.yusov.patterns.command.commands.StartCommand;
import dev.yusov.patterns.command.commands.StopCommand;

public class Solution {
    public static void main(String[] args) {
        Comp comp = new Comp();
        User user = new User(new StartCommand(comp), new StopCommand(comp), new ResetCommand(comp));
        user.startComputer();
        user.stopComputer();
        user.resetComputer();

    }
}