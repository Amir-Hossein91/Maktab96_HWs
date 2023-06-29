import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class HW10Q2 {
    public void run() {
        Set<Character> firstSet = new TreeSet<>();
        Set<Character> secondSet = new TreeSet<>();
        fillSets(firstSet, secondSet);
        System.out.println("first set: " + firstSet);
        System.out.println("second set: " + secondSet);
        System.out.println("union: " + union(firstSet,secondSet));
        System.out.println("intersection: " + intersetion(firstSet,secondSet));
    }

    public static void fillSets(Set<Character> a, Set<Character> b){
        Random random = new Random();
        while(a.size()<10 || b.size()<10){
            char ch = (char) random.nextInt(97,123);
            if(a.size() < 10)
                a.add(ch);
            else
                b.add(ch);
        }
    }

    public static Set<Character> union (Set<Character> a , Set<Character> b){
        Set<Character> result = new TreeSet<>();
        result.addAll(a);
        result.addAll(b);
        return result;
    }

    public static Set<Character> intersetion (Set<Character> a , Set<Character> b){
        Set<Character> result = new TreeSet<>();
        for(Character ch : a){
            if (b.contains(ch))
                    result.add(ch);
        }
        return result;
    }
}
