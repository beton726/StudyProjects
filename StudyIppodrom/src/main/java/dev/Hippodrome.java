package dev;

import java.util.List;

public class Hippodrome {

    private List<Horse> horses;

    public static void main(String[] args) {

    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }
}