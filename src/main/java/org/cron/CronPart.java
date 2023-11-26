package org.cron;

import java.util.Objects;

public class CronPart {
    protected String name;

    protected String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public CronPart(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%-14s %s\n", name, value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReoccurringCronPart that)) return false;
        return this.name.equals(that.name) && this.value.equals(that.value);
    }
}
