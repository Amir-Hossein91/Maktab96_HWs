package hw12.question2;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HW12Part1 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Amir", "Hossein", "Ali", "Amin", "Eli", "Mahtab", "Maryam", "Omid");
        Map<Integer, List<String>> names = words.stream()
                .collect(Collectors
                        .groupingBy(String::length));
        System.out.println(names.values());
    }


}
