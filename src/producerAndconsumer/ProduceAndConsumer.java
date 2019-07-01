package producerAndconsumer;

public class ProduceAndConsumer {
    private static Integer count = 0;

    private static final Integer FULL = 10;

    private static String LOCK = "lock";

    public static void main(String[] args) {
        ProduceAndConsumer pc = new ProduceAndConsumer();
        new Thread(pc.new Producer()).start();
        new Thread(pc.new Consumer()).start();
        new Thread(pc.new Producer()).start();
        new Thread(pc.new Consumer()).start();
        new Thread(pc.new Producer()).start();
        new Thread(pc.new Consumer()).start();
        new Thread(pc.new Producer()).start();
        new Thread(pc.new Consumer()).start();
        new Thread(pc.new Producer()).start();
        new Thread(pc.new Consumer()).start();
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    while (count == FULL) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.getStackTrace();
                        }
                    }
                    count ++;
                    LOCK.notifyAll();
                }
            }
        }
    }

    class Consumer implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    while (count == 0) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.getStackTrace();
                        }
                    }
                    count --;
                    LOCK.notifyAll();
                }
            }
        }
    }
}
