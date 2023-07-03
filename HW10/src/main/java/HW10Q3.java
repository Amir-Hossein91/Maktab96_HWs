import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HW10Q3 {

    public void run() {
        List<Integer> integerList = createRandomIntegerList();
        System.out.println(integerList);
        manageList(integerList);
        System.out.println(integerList);
    }

    public static List<Integer> createRandomIntegerList() {
        List<Integer> integerList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            integerList.add(random.nextInt(1, 11));
        }
        return integerList;
    }

    public static void manageList(List<Integer> list) {
        if (list == null)
            return;
        if (list.size() % 2 != 0)
            list.remove(list.size() - 1);
        int size = list.size();
        for (int i = size - 1; i >= 1; i -= 2) {
            if (list.get(i) > list.get(i - 1)) {
                list.remove(i);
                list.remove(i - 1);
            }
        }
    }
}
