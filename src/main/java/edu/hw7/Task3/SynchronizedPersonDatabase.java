package edu.hw7.Task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> personId = new HashMap<>();

    public int size() {
        return personId.size();
    }

    @Override
    public void add(Person person) {
        synchronized (personId) {
            personId.put(person.id(), person);
        }
    }

    @Override
    public void delete(int id) {
        synchronized (personId) {
            Person person = personId.get(id);
            if (person == null) {
                return;
            }
            personId.remove(person.id(), person);
        }
    }

    @Override
    public List<Person> findByName(String name) {
        synchronized (personId) {
            return personId.values()
                .stream()
                .parallel()
                .filter((person) -> person.name().equals(name))
                .toList();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        synchronized (personId) {
            return personId.values()
                .stream()
                .parallel()
                .filter((person) -> person.address().equals(address))
                .toList();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        synchronized (personId) {
            return personId.values()
                .stream()
                .parallel()
                .filter((person) -> person.phoneNumber().equals(phone))
                .toList();
        }
    }
}
