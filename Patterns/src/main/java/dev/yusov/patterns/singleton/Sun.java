package dev.yusov.patterns.singleton;

public class Sun {
    private static Sun instance;

    public static Sun getInstance() {
        if(instance == null)
            instance = new Sun();
        return instance;
    }

    private Sun() {}
}
