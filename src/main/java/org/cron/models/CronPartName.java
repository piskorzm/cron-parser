package org.cron.models;

public enum CronPartName {
    MINUTE("minute"),
    HOUR("hour"),
    DAY_OF_MONTH("day of month"),
    MONTH("month"),
    DAY_OF_WEEK("day of week"),
    YEAR("year"),
    COMMAND("command");

    private final String text;
    
    CronPartName(final String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return text;
    }
}
