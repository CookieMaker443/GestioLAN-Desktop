package it.cookie.utils.interfaces.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private final List<Observer> observers = new ArrayList<>();

    public void Attach(Observer o) {
        observers.add(o);
    }

    public void Detach(Observer o) {
        observers.remove(o);
    }

    public void DetachAll() {
        observers.clear();
    }

    public void Notify(Object state) {
        for (Observer o : observers) {
            o.Update(this, state);
        }
    }
}
