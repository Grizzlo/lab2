import java.util.Random;

/**
 * Created by grizzly on 3/14/15.
 */
public class control {
    CPU cpu1, cpu2;
    cpu_queue queue1, queue2;
    CPUProcess process1, process2;
    Object monitor = new Object();
    static Random rand = new Random(157);

    public static int getRandomNumber(int min, int max) {
        return min + (rand.nextInt() % (max - min + 1));
    }

    control() {
        cpu1 = new CPU("First");
        cpu2 = new CPU("Second");
        queue1 = new cpu_queue(25);
        queue2 = new cpu_queue(34);
        begin();
    }

    public void begin() {
        cpu1.start();
        cpu2.start();
        for (int i = 0; i <= 10; i++) {
            process1 = new CPUProcess(getRandomNumber(0, 10));
            process2 = new CPUProcess(getRandomNumber(0, 10));
            synchronized (monitor) {
                if (!cpu1.isBusy()) {
                    cpu1.loadProcess(process1);
                } else if (!queue1.isFull()) queue1.push(process1);
                else if (!queue2.isFull()) queue1.push(process1);

                if (!cpu2.isBusy()) {
                    cpu2.loadProcess(process1);
                } else if (!queue2.isFull()) queue1.push(process2);
                else if (!queue1.isFull()) queue1.push(process2);
                try {
                    Thread.sleep(1300);
                } catch (InterruptedException e) {
                }
            }
        }
    }
    private class TakeFromQueue implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (monitor) {
                    if (!queue1.isEmpty()) {
                        if (!cpu1.isBusy()) {
                            cpu1.loadProcess(queue1.pop());
                        }
                    }
                }
                synchronized (monitor) {
                    if (!queue2.isEmpty()) {
                        if (!cpu2.isBusy()) {
                            cpu2.loadProcess(queue2.pop());
                        }
                    }
                }
            }
        }

    }

}
    class  main {
        public static void main(String args[]) {
            control con = new control();
        }
    }
