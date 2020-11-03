package dev.yusov.patterns.command;

// Receiver
public class Comp {
    public void start(){
        System.out.println("Start");
    }
    public void stop(){
        System.out.println("Stop");
    }
    public void reset(){
        System.out.println("Reset");
    }
}