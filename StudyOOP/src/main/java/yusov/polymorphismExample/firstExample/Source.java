package yusov.polymorphismExample.firstExample;

import java.util.Arrays;
import java.util.List;

public class Source {
    public static void main(String[] args) {
        Dancer dancer = new Dancer("Антон", 25);

        // Восходящее преобразование к базовому типу
        Dancer breakDanceDancer = new BreakDankDancer("Алексей",19);
        Dancer electricBoogieDancer = new ElectricBoogieDancer("Игорь",20);

        List<Dancer> allDancers = Arrays.asList(dancer, breakDanceDancer, electricBoogieDancer);

        for (Dancer name : allDancers) {
            name.dance();
        }
    }
}