package org.cron.models;

public class CronSchedule {

    private final ReoccurringCronPart minutes;
    private final ReoccurringCronPart hours;
    private final ReoccurringCronPart dayOfMonths;
    private final ReoccurringCronPart months;
    private final ReoccurringCronPart daysOfWeek;
    private final ReoccurringCronPart year;
    private final CronPart command;

    public CronSchedule(ReoccurringCronPart minutes,
                        ReoccurringCronPart hours,
                        ReoccurringCronPart dayOfMonths,
                        ReoccurringCronPart months,
                        ReoccurringCronPart daysOfWeek,
                        ReoccurringCronPart year,
                        CronPart command) {
        this.minutes = minutes;
        this.hours = hours;
        this.dayOfMonths = dayOfMonths;
        this.months = months;
        this.daysOfWeek = daysOfWeek;
        this.year = year;
        this.command = command;
    }

    @Override
    public String toString() {
        var output = new StringBuilder();

        output.append(minutes);
        output.append(hours);
        output.append(dayOfMonths);
        output.append(months);
        output.append(daysOfWeek);
        if (year != null) {
            output.append(year);
        }
        output.append(command);
        return output.toString();
    }
}
