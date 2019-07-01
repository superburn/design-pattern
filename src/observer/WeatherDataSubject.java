package observer;


import java.util.ArrayList;

public class WeatherDataSubject implements Subject {
    ArrayList<ObserverInterface> observers;

    private int temperature;

    public WeatherDataSubject(){
        observers = new ArrayList();
    }

    @Override
    public void registerObserver(ObserverInterface o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ObserverInterface o) {
        if(observers.indexOf(o) > 0){
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        for(ObserverInterface o : observers){
            o.update(temperature);
        }
    }

    public void setNewData(int temperature){
        this.temperature = temperature;
        notifyObservers();
    }
}
