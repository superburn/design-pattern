package observer;

public interface Subject {
    public void registerObserver(ObserverInterface o);
    public void removeObserver(ObserverInterface o);
    public void notifyObservers();
}
