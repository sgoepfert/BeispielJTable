package com.sabel.beispieljtable;

import java.util.*;

/**
 * Created by Steve on 08.05.2017.
 */
public class Datenbank {

    private List<Person> personen;

    public Datenbank() {
        this.personen = new ArrayList<>();
    }

    public void hinzufuegen(Person person) {
        if (person != null) {
            personen.add(person);
        }
    }

    public Person findePerson(int index) {
        return personen.get(index);
    }

    public Person findePerson(String name) {
        Iterator<Person> iterator = personen.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    public Person loeschePerson(String uuid) {
        Iterator<Person> iterator = personen.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getUuid().toString().equals(uuid)) {
                iterator.remove();
                return person;
            }
        }
        return null;
    }

    public List<Person> getPersonen() {
        return personen;
    }

    public void setPersonen(List<Person> personen) {
        this.personen = personen;
    }

    public int size() {
        return personen.size();
    }

    public static Datenbank erzeugeTestdatenbank() {
        Datenbank db = new Datenbank();
        db.hinzufuegen(new Person("Göpfert", 31));
        db.hinzufuegen(new Person("Müller", 23));
        return db;
    }
}
