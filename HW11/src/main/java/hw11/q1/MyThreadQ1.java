package hw11.q1;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MyThreadQ1 extends Thread{

    private final List<Integer> numbers;
    private final List<Integer> total;
    private final CountDownLatch count;

    public MyThreadQ1(List<Integer> numbers , List<Integer> total, CountDownLatch count){
        this.numbers = numbers;
        this.total = total;
        this.count = count;

    }

    public void run() {
        fillTotal(numbers , total);
    }


    public void fillTotal (List<Integer> numbers , List<Integer> total){

        synchronized (this.total){
            if(numbers.get(0) == 1 && total.size() == 0) {
                try {
                    this.total.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            for (Integer number : numbers) {
                total.add(number);
                this.total.notify();
                try {
                    this.total.wait(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            count.countDown();
        }
    }
}
