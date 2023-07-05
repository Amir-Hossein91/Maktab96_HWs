import java.util.*;

public class HW10Q1 {

    private static final List<String> possibleWords;
    private static final Scanner input;
    private static final Map<String, List<String>> map;

    static {
        possibleWords = new ArrayList<>();
        input = new Scanner(System.in);
        map = new HashMap<>();
    }

    public void run() {
        while (true){
            System.out.println("Enter the word (0: exit, all: all mapped values ): ");
            String word = input.nextLine();
            if(word.equals("0"))
                break;
            if(word.equals("all")){
                Iterator<String> iterator = map.keySet().iterator();
                System.out.println("***********************************************");
                while (iterator.hasNext()){
                    String key = iterator.next();
                    System.out.println(key +": " + map.get(key));
                }
                System.out.println("***********************************************");
                continue;
            }
            char [] charArray = word.toCharArray();
            List<Character> list = new ArrayList<>();

            for (char c : charArray) list.add(c);
            permutation("", list);
            map.put(word, new ArrayList<>(possibleWords));
            possibleWords.clear();
            System.out.println(map.get(word));
            System.out.println("Enter the matching word: ");
            String match = input.nextLine();
            if(map.get(word).contains(match))
                System.out.println("Pass!");
            else
                System.out.println("Fail!");
        }
    }

    public static void permutation (String result , List<Character> chars){
        if(chars.size() == 0){
            if(!possibleWords.contains(result)){
               possibleWords.add(result);
            }
            return;
        }
        for(int i =0; i<chars.size(); i++){
            String next = result.concat(chars.get(i).toString());
            List<Character> nextList = new ArrayList<>(chars);
            nextList.remove(i);
            permutation(next,nextList);
        }
    }
}
