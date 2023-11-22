package edu.hw7.Task3Test;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import edu.hw7.Task3.SynchronizedPersonDatabase;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SynchronizedPersonDatabaseTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("add() Test")
    void shouldAddPerson() {
        SynchronizedPersonDatabase database = new SynchronizedPersonDatabase();
        Person person1 = new Person(1, "Test1", "Test 12", "8888888888");
        database.add(person1);
        assertThat(database.size()).isEqualTo(1);
        Person person2 = new Person(2, "Test12", "Test 123123", "8888888888");
        database.add(person2);
        assertThat(database.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("delete() Test")
    void shouldDeletePerson() {
        SynchronizedPersonDatabase database = new SynchronizedPersonDatabase();
        Person person1 = new Person(1, "Test1", "Test 12", "8888888888");
        database.add(person1);
        assertThat(database.size()).isEqualTo(1);
        Person person2 = new Person(2, "Test12", "Test 123123", "8888888888");
        database.add(person2);
        assertThat(database.size()).isEqualTo(2);
        database.delete(2);
        assertThat(database.size()).isEqualTo(1);
        database.delete(3);
        assertThat(database.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("findByName() Test")
    void shouldFindByName() {
        SynchronizedPersonDatabase database = new SynchronizedPersonDatabase();
        Person person1 = new Person(1, "Test12", "Test 12", "8888888888");
        database.add(person1);
        assertThat(database.findByName("Test12")).contains(person1);
        Person person2 = new Person(2, "Test12", "Test 123123", "8888888888");
        database.add(person2);
        assertThat(database.findByName("Test12")).contains(person1, person2);
        assertThat(database.findByName("")).isEmpty();
    }

    @Test
    @DisplayName("findByAddress() Test")
    void shouldFindByAddress() {
        SynchronizedPersonDatabase database = new SynchronizedPersonDatabase();
        Person person1 = new Person(1, "Test1", "Test 12", "8888888888");
        database.add(person1);
        assertThat(database.findByAddress("Test 12")).contains(person1);
        Person person2 = new Person(2, "Test12", "Test 123123", "8888888888");
        database.add(person2);
        assertThat(database.findByAddress("Test 123123")).contains(person2);
        assertThat(database.findByAddress("")).isEmpty();
    }

    @Test
    @DisplayName("findByAddress() Test")
    void shouldFindByPhoneNumber() {
        SynchronizedPersonDatabase database = new SynchronizedPersonDatabase();
        Person person1 = new Person(1, "Test1", "Test 12", "8888888888");
        database.add(person1);
        assertThat(database.findByPhone("8888888888")).contains(person1);
        Person person2 = new Person(2, "Test12", "Test 123123", "8888888888");
        database.add(person2);
        assertThat(database.findByPhone("8888888888")).contains(person1, person2);
        assertThat(database.findByPhone("")).isEmpty();
    }

    @Test
    @DisplayName("MultiThreadFind Test")
    void shouldFindPersonWithMultipleThreads() {
        AtomicInteger countErrors = new AtomicInteger(0);
        PersonDatabase database = new SynchronizedPersonDatabase();
        for (int i = 0; i < 10000; i++) {
            Thread forAdd = new Thread(() -> {
                for (int k = 1; k <= 3; k++) {
                    String testString = "Test" + k;
                    database.add(new Person(k, testString, testString, testString));
                }
            });
            Thread forFind1 = new Thread(() -> checkByName(database, countErrors));
            Thread forFind2 = new Thread(() -> checkByAddress(database, countErrors));
            Thread forFind3 = new Thread(() -> checkByPhone(database, countErrors));
            forAdd.start();
            forFind1.start();
            forFind2.start();
            forFind3.start();
            try {
                forAdd.join();
                forFind1.join();
                forFind2.join();
                forFind3.join();
            } catch (InterruptedException e) {
                LOGGER.error("An error has occured: ", e);
            }
        }
        assertThat(countErrors.get()).isEqualTo(0);
    }

    private void checkByName(PersonDatabase database, AtomicInteger countErrors) {
        if (database.findByName("Test1") != null) {
            if ((database.findByAddress("Test1") == null) || (database.findByPhone("Test1") == null)) {
                countErrors.addAndGet(1);
            }
        } else {
            if ((database.findByAddress("Test1") != null) || (database.findByPhone("Test1") != null)) {
                countErrors.addAndGet(1);
            }
        }
    }

    private void checkByAddress(PersonDatabase database, AtomicInteger countErrors) {
        if (database.findByAddress("Test2") != null) {
            if ((database.findByName("Test2") == null) || (database.findByPhone("Test2") == null)) {
                countErrors.addAndGet(1);
            }
        } else {
            if ((database.findByName("Test2") != null) || (database.findByPhone("Test2") != null)) {
                countErrors.addAndGet(1);
            }
        }
    }

    private void checkByPhone(PersonDatabase database, AtomicInteger countErrors) {
        if (database.findByPhone("Test3") != null) {
            if ((database.findByName("Test3") == null) || (database.findByAddress("Test3") == null)) {
                countErrors.addAndGet(1);
            }
        } else {
            if ((database.findByName("Test3") != null) || (database.findByAddress("Test3") != null)) {
                countErrors.addAndGet(1);
            }
        }
    }
}
