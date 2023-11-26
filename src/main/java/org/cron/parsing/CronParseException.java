package org.cron.parsing;

public class CronParseException extends Exception {
    public CronParseException(String errorMessage) {
        super(errorMessage);
    }
}
