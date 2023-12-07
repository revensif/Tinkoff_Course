package edu.hw7.Task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockPersonDatabase implements PersonDatabase {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Map<Integer, Person> personId = new HashMap<>();

    public int size() {
        return personId.size();
    }

    @Override
    public void add(Person person) {
        readWriteLock.writeLock().lock();
        try {
            personId.put(person.id(), person);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        readWriteLock.writeLock().lock();
        try {
            Person person = personId.get(id);
            if (person == null) {
                return;
            }
            personId.remove(person.id(), person);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        readWriteLock.readLock().lock();
        try {
            return personId.values()
                .stream()
                .parallel()
                .filter((person) -> person.name().equals(name))
                .toList();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        readWriteLock.readLock().lock();
        try {
            return personId.values()
                .stream()
                .parallel()
                .filter((person) -> person.address().equals(address))
                .toList();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        readWriteLock.readLock().lock();
        try {
            return personId.values()
                .stream()
                .parallel()
                .filter((person) -> person.phoneNumber().equals(phone))
                .toList();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
