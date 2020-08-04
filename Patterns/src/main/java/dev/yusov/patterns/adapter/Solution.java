package dev.yusov.patterns.adapter;

/**
 *  Adapter(Wrapper) - адаптер(обёртка)
 *
 *
 *
 *
 *
 * */


public class Solution {
    public static void main(String[] args) {
        TotalTime totalTime = null; // Программа получает объект, который реализует интерфейс TotalTime
        Time time = new TotalTimeAdapter(totalTime);

    }
}
