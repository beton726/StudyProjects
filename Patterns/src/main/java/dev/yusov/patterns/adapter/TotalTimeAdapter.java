package dev.yusov.patterns.adapter;

public class TotalTimeAdapter implements Time{

    private TotalTime totalTime;

    public TotalTimeAdapter(TotalTime totalTime) {
        this.totalTime = totalTime;
    }

    public int getSeconds() {
        return totalTime.getTotalSeconds() % 60;
    }

    public int getMinutes() {
        return totalTime.getTotalSeconds() / 60;
    }

    public int getHours() {
        return totalTime.getTotalSeconds() / (60*60);
    }
}
