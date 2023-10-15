package edu.hw2;

public final class Task4 {
    static final String MESSAGE = "Ошибка: Метод не вызывался ни одним классом";

    private Task4() {
    }

    public static CallingInfo callingInfo(String methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            if (element.getMethodName().equals(methodName)) {
                return new CallingInfo(element.getClassName(), methodName);
            }
        }
        return new CallingInfo(MESSAGE, methodName);

    }

    public record CallingInfo(String className, String methodName) {
    }

}
