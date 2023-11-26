package org.cron;

public class CronSchedule {

    private final ReoccurringCronPart minutes;
    private final ReoccurringCronPart hours;
    private final ReoccurringCronPart dayOfMonths;
    private final ReoccurringCronPart months;
    private final ReoccurringCronPart daysOfWeek;
    private final CronPart command;

    public CronSchedule(ReoccurringCronPart minutes,
                        ReoccurringCronPart hours,
                        ReoccurringCronPart dayOfMonths,
                        ReoccurringCronPart months,
                        ReoccurringCronPart daysOfWeek,
                        CronPart command) {
        this.minutes = minutes;
        this.hours = hours;
        this.dayOfMonths = dayOfMonths;
        this.months = months;
        this.daysOfWeek = daysOfWeek;
        this.command = command;
    }

    @Override
    public String toString() {
        var output = new StringBuilder();

        output.append(minutes.toString());
        output.append(hours.toString());
        output.append(dayOfMonths.toString());
        output.append(months.toString());
        output.append(daysOfWeek.toString());
        output.append(command.toString());
        return output.toString();
    }
}
