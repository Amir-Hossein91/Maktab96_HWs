import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HW10_Q3 {

    public static void main(String[] args) {
        List<Integer> inputList = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < 10; i++){
            inputList.add(random.nextInt(1,11));
        }

        System.out.println(inputList);
        manageList(inputList);
        System.out.println(inputList);
    }

    public static void manageList (List<Integer> list){
        if(list != null){
            if(list.size() % 2 != 0)
                list.remove(list.size() - 1);

            int size = list.size();

            for(int i = size - 1; i >= 1; i -= 2){
                if(list.get(i) > list.get(i-1)){
                    list.remove(i);
                    list.remove(i-1);
                }
            }
        }
    }
}
