package org.example;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private final List<Observer> observers = new ArrayList<>();
    private boolean changed = false;

    public void addObserver(Observer o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    public void deleteObserver(Observer o) {
        observers.remove(o);
    }

    protected void setChanged() {
        changed = true;
    }

    protected void clearChanged() {
        changed = false;
    }

    public boolean hasChanged() {
        return changed;
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    public void notifyObservers(Object arg) {
        Object[] arrLocal;
        synchronized (this) {
            if (!changed) {
                return;
            }
            arrLocal = observers.toArray();
            clearChanged();
        }
        for (int i = arrLocal.length - 1; i >= 0; i--) {
            ((Observer)arrLocal[i]).update(this, arg);
        }
    }
}
