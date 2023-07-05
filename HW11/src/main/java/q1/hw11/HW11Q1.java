package q1.hw11;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class HW11Q1 {
    private static final List<Integer> total = new ArrayList<>();
    private static final List<Integer> even = new ArrayList<>();
    private static final List<Integer> odd = new ArrayList<>();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        CountDownLatch count = new CountDownLatch(2);
        MyThread evenNumbers;
        MyThread oddNumbers;

        System.out.println("Enter a number:");
        int number = input.nextInt();

        fillLists(number);

        evenNumbers = new MyThread(even, total, count);
        oddNumbers = new MyThread(odd, total, count);
        evenNumbers.start();
        oddNumbers.start();

        try {
            count.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(total);

    }

    public static void fillLists (int number){
        for(int i =0; i <= number; i++){
            if(i % 2 == 0)
                even.add(i);
            else
                odd.add(i);
        }
    }
}
