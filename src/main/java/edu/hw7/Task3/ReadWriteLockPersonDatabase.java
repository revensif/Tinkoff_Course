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
        readWriteLock.readLock().lock();
        personId.put(person.id(), person);
        readWriteLock.readLock().unlock();
    }

    @Override
    public void delete(int id) {
        readWriteLock.readLock().lock();
        Person person = personId.get(id);
        if (person == null) {
            return;
        }
        personId.remove(person.id(), person);
        readWriteLock.readLock().unlock();
    }

    @Override
    public List<Person> findByName(String name) {
        readWriteLock.writeLock().lock();
        List<Person> personList =
            personId.values()
                .stream()
                .parallel()
                .filter((person) -> person.name().equals(name))
                .toList();
        readWriteLock.writeLock().unlock();
        return personList;
    }

    @Override
    public List<Person> findByAddress(String address) {
        readWriteLock.writeLock().lock();
        List<Person> personList =
            personId.values()
                .stream()
                .parallel()
                .filter((person) -> person.address().equals(address))
                .toList();
        readWriteLock.writeLock().unlock();
        return personList;
    }

    @Override
    public List<Person> findByPhone(String phone) {
        readWriteLock.writeLock().lock();
        List<Person> personList =
            personId.values()
                .stream()
                .parallel()
                .filter((person) -> person.phoneNumber().equals(phone))
                .toList();
        readWriteLock.writeLock().unlock();
        return personList;
    }
}
