package q2.hw11;

public class HW11Q2 {
    public static void main(String[] args) {
        String sign1 = "sign1";
        String sign2 = "sign2";
        String sign3 = "sign3";

        MyThread Thread1 = new MyThread(sign1,sign3, "Thread-1");
        MyThread Thread2 = new MyThread(sign2,sign1, "Thread-2");
        MyThread Thread3 = new MyThread(sign3,sign2, "Thread-3");

        Thread1.start();
        Thread2.start();
        Thread3.start();

    }
}
