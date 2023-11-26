package org.cron.parsing;

import org.cron.models.CronPart;
import org.cron.models.CronPartName;
import org.cron.models.CronSchedule;
import org.cron.models.CronScheduleBuilder;

public class CronParser {
    private static final int EXPECTED_EXPRESSION_PART_COUNT = 6;
    private static final String EXPRESSION_PART_SEPARATOR = " ";
    private static final ReoccurringCronPartParser minuteParser =
            new ReoccurringCronPartParser(0, 59, CronPartName.MINUTE.toString());
    private static final ReoccurringCronPartParser hourParser =
            new ReoccurringCronPartParser(0, 23, CronPartName.HOUR.toString());
    private static final ReoccurringCronPartParser dayOfMonthParser =
            new ReoccurringCronPartParser(1, 31, CronPartName.DAY_OF_MONTH.toString());
    private static final ReoccurringCronPartParser monthParser =
            new ReoccurringCronPartParser(1, 12, CronPartName.MONTH.toString());
    private static final ReoccurringCronPartParser dayOfWeekParser =
            new ReoccurringCronPartParser(1, 7, CronPartName.DAY_OF_WEEK.toString());

    public static CronSchedule parse(String expression) throws CronParseException {
        expression = expression.trim().replaceAll(" +", " ");
        var expressionParts = expression.trim().split(EXPRESSION_PART_SEPARATOR);
        if (expressionParts.length != EXPECTED_EXPRESSION_PART_COUNT) {
            throw new CronParseException(
                    String.format("CRON expression accepts exactly %s parts " +
                            "(minute, hour, dayOfMonth, month, dayOfWeek, command) separated by '%s'",
                            EXPECTED_EXPRESSION_PART_COUNT, EXPRESSION_PART_SEPARATOR));
        }

        return new CronScheduleBuilder()
                .withMinutes(minuteParser.parse(expressionParts[0]))
                .withHours(hourParser.parse(expressionParts[1]))
                .withDaysOfMonth(dayOfMonthParser.parse(expressionParts[2]))
                .withMonths(monthParser.parse(expressionParts[3]))
                .withDaysOfWeek(dayOfWeekParser.parse(expressionParts[4]))
                .withCommand(new CronPart(CronPartName.COMMAND.toString(), expressionParts[5]))
                .build();
    }
}
