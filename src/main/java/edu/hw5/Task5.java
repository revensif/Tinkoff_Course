package edu.hw5;

public final class Task5 {
    private static final String LICENSE_PATTERN = "^([АВСЕКНОМРТХУ])(\\d{3})([АВСЕКНОМРТХУ]{2})(\\d{2,3})$";

    private Task5() {
    }

    public static boolean validateRusLicenseSigns(String sign) {
        return sign.matches(LICENSE_PATTERN);
    }
}
