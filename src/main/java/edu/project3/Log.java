package edu.project3;

import java.time.OffsetDateTime;

public record Log(OffsetDateTime timeLocal, String resource, String status, long bodyBytesSent) {
}
