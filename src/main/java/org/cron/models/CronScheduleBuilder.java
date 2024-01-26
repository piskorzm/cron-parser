package org.cron.models;

public class CronScheduleBuilder {
    private ReoccurringCronPart minutes;
    private ReoccurringCronPart hours;
    private ReoccurringCronPart daysOfMonth;
    private ReoccurringCronPart months;
    private ReoccurringCronPart daysOfWeek;
    private ReoccurringCronPart year;
    private CronPart command;

    public CronScheduleBuilder withMinutes(ReoccurringCronPart minutes) {
        this.minutes = minutes;
        return this;
    }

    public CronScheduleBuilder withHours(ReoccurringCronPart hours) {
        this.hours = hours;
        return this;
    }

    public CronScheduleBuilder withDaysOfMonth(ReoccurringCronPart daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
        return this;
    }

    public CronScheduleBuilder withMonths(ReoccurringCronPart months) {
        this.months = months;
        return this;
    }

    public CronScheduleBuilder withDaysOfWeek(ReoccurringCronPart daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
        return this;
    }

    public CronScheduleBuilder withYear(ReoccurringCronPart year) {
        this.year = year;
        return this;
    }

    public CronScheduleBuilder withCommand(CronPart command) {
        this.command = command;
        return this;
    }

    public CronSchedule build() {
        return new CronSchedule(minutes, hours, daysOfMonth, months, daysOfWeek, year, command);
    }
}