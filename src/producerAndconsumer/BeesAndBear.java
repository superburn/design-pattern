package producerAndconsumer;

//有n只蜜蜂和一只饥饿的熊。他们共享一罐蜂蜜。这个罐子最初是空的;它的容量是蜜蜂的一半。
//熊睡到罐子里的蜂蜜满了，然后把所有的蜂蜜都吃了，然后再继续睡觉。
//每只蜜蜂都要反复地收集一份蜂蜜，并把它放进罐子里蜜蜂填满罐子，唤醒了熊。
public class BeesAndBear {
    public static class Pot{
        private int start = 0;
        private int Max;
        public Pot(int n){
            this.Max = n/2;
        }
    }

    public static class Bee extends Thread{
        private Pot pot;
        public Bee(Pot pot){
            this.pot = pot;
        }

        @Override
        public void run() {
            for(;;){
                synchronized (pot) {
                    while (pot.start == pot.Max) {
                        try {
                            pot.wait();
                        } catch (InterruptedException e) {
                            e.getStackTrace();
                        }
                    }
                    pot.start++;
                    pot.notify();
                }
            }
        }
    }

    public static class Bear extends Thread{
        private Pot pot;
        public Bear(Pot pot){
            this.pot = pot;
        }

        @Override
        public void run() {
            for(;;){
                synchronized (pot) {
                    while (pot.start < pot.Max) {
                        try {
                            pot.wait();
                        } catch (InterruptedException e) {
                            e.getStackTrace();
                        }
                    }
                    pot.start = 0;
                    pot.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 100;
        Pot pot = new Pot(n);
        for(int i = 0;i<n;i++){
            new Bee(pot).start();
        }
        new Bear(pot).start();
    }
}
