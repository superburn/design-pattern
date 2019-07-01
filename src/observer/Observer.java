package observer;

public class Observer implements ObserverInterface {
    private Subject subject;

    private int temperature;

    public Observer(Subject subject){
        this.subject = subject;
        subject.registerObserver(this);
    }

    public void register(){
        subject.registerObserver(this);
        System.out.println("订阅消息");
    }

    public void cancel(){
        subject.removeObserver(this);
        System.out.println("取消订阅");
    }

    @Override
    public void update(int temperature) {
        this.temperature = temperature;
        display();
    }

    public void display(){
        System.out.println("数据更新,温度:"+temperature);
    }
}
