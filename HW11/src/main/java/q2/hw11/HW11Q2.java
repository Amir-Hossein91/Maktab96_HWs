package q2.hw11;

public class HW11Q2 {
    public static void main(String[] args) {
        String sign1 = "sign1";
        String sign2 = "sign2";
        String sign3 = "sign3";

        MyThreadQ2 Thread1 = new MyThreadQ2(sign1,sign3, "Thread-1");
        MyThreadQ2 Thread2 = new MyThreadQ2(sign2,sign1, "Thread-2");
        MyThreadQ2 Thread3 = new MyThreadQ2(sign3,sign2, "Thread-3");

        Thread1.start();
        Thread2.start();
        Thread3.start();

    }
}
