package q3.hw111;

public class MyThreadQ3 extends Thread{

    final private Object lock;

    public MyThreadQ3 (String name , Object lock){
        super(name);
        this.lock = lock;
    }

    public void run (){
        for (int i = 1; i<=3; i++) {
            startTask(i);
            System.out.println("<<out of synch!>> starving threads attack to get the lock!");
            System.out.println();
        }
        System.out.println("\n" + this.getName() + " terminated!");
    }

    private void startTask(int taskNumber){
        synchronized (lock) {
            long start = System.currentTimeMillis();
            System.out.println(this.getName() + " with priority = " + this.getPriority()+ " started task " + taskNumber + "/3:");
            workOn();
            System.out.println();
            long end = System.currentTimeMillis();
            System.out.println(this.getName() + " finished task " + taskNumber + "/3 in: " + (end -start) + "ms");
        }
    }

    private void workOn() {
        for (int i = 1; i <= this.getPriority(); i++) {
            System.out.print("*");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
