package org.cron;

public class CronParseException extends Exception {
    public CronParseException(String errorMessage) {
        super(errorMessage);
    }
}
