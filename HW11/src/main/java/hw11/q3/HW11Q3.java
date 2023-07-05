package hw11.q3;

public class HW11Q3 {
    public static void main(String[] args) {
        Object lock = new Object();
        MyThreadQ3 thread_1 = new MyThreadQ3("thread_1", lock);
        thread_1.setPriority(10);
        MyThreadQ3 thread_2 = new MyThreadQ3("thread_2", lock);
        thread_2.setPriority(3);
        MyThreadQ3 thread_3 = new MyThreadQ3("thread_3", lock);
        thread_3.setPriority(8);
        MyThreadQ3 thread_4 = new MyThreadQ3("thread_4", lock);
        thread_4.setPriority(9);
        MyThreadQ3 thread_5 = new MyThreadQ3("thread_5", lock);
        thread_5.setPriority(1);

        thread_1.start();
        thread_2.start();
        thread_3.start();
        thread_4.start();
        thread_5.start();
    }
}
