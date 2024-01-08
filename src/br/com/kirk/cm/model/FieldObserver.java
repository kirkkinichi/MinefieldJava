package br.com.kirk.cm.model;

@FunctionalInterface
public interface FieldObserver {
    public void eventOccurred(Field field, FieldEvent event);
}
