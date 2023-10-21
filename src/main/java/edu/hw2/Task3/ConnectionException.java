package edu.hw2.Task3;

public class ConnectionException extends RuntimeException {

    public ConnectionException() {
    }

    public ConnectionException(String text, Throwable cause) {
       super(text, cause);
    }
}
