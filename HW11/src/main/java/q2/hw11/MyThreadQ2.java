package q2.hw11;

public class MyThreadQ2 extends Thread{

    private final Object waitOn;
    private final Object notifyOn;

    public MyThreadQ2(Object waitOn, Object notifyOn, String name){
        super(name);
        this.waitOn = waitOn;
        this.notifyOn = notifyOn;
    }

    public void run(){

//        if(Thread.currentThread().getName().equals("Thread-1")){
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            synchronized (this.notifyOn){
//                this.notifyOn.notify();
//                System.out.println(Thread.currentThread().getName() + " has notified on " + notifyOn);
//            }
//        }
        synchronized (this.waitOn){
            System.out.println(Thread.currentThread().getName() + " is waiting on " + waitOn);

            try {
                this.waitOn.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + " is notified by " + waitOn);

            synchronized (this.notifyOn){
                this.notifyOn.notify();
                System.out.println(Thread.currentThread().getName() + " has notified on " + notifyOn);
            }




        }


    }
}
