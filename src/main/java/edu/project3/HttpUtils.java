package edu.project3;

import java.util.Map;
import static java.net.HttpURLConnection.HTTP_BAD_GATEWAY;
import static java.net.HttpURLConnection.HTTP_FORBIDDEN;
import static java.net.HttpURLConnection.HTTP_GATEWAY_TIMEOUT;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_MOVED_PERM;
import static java.net.HttpURLConnection.HTTP_MOVED_TEMP;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_NOT_MODIFIED;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAVAILABLE;
import static java.util.Map.entry;

public final class HttpUtils {

    private HttpUtils() {
    }

    public static final Map<Integer, String> STATUS = Map.ofEntries(
        entry(HTTP_OK, "OK"),
        entry(HTTP_MOVED_PERM, "Moved Permanently"),
        entry(HTTP_MOVED_TEMP, "Temporary Redirect"),
        entry(HTTP_NOT_MODIFIED, "Not Modified"),
        entry(HTTP_FORBIDDEN, "Forbidden"),
        entry(HTTP_NOT_FOUND, "Not Found"),
        entry(HTTP_INTERNAL_ERROR, "Internal Server Error"),
        entry(HTTP_BAD_GATEWAY, "Bad Gateway"),
        entry(HTTP_UNAVAILABLE, "Service Unavailable"),
        entry(HTTP_GATEWAY_TIMEOUT, "Gateway Timeout")
    );
}
