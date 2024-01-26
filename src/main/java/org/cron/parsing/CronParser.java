package org.cron.parsing;

import org.cron.models.*;

public class CronParser {
    private static final int EXPRESSION_PART_COUNT = 6;
    private static final int EXPRESSION_PART_COUNT_WITH_YEAR = 7;
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
    private static final ReoccurringCronPartParser yearParser =
            new ReoccurringCronPartParser(1970, 2050, CronPartName.YEAR.toString());

    public static CronSchedule parse(String expression) throws CronParseException {
        var yearParserOptions = new CronParserOptions();
        yearParserOptions.setUseAllOccurrencesPattern(false);
        yearParserOptions.setUseSingleNumberPattern(false);
        yearParserOptions.setUseListPattern(false);
        yearParserOptions.setUseIntervalPattern(false);
        yearParser.setOptions(yearParserOptions);

        expression = expression.trim().replaceAll(" +", " ");
        var expressionParts = expression.trim().split(EXPRESSION_PART_SEPARATOR);
        if (expressionParts.length == EXPRESSION_PART_COUNT) {
            return new CronScheduleBuilder()
                    .withMinutes(minuteParser.parse(expressionParts[0]))
                    .withHours(hourParser.parse(expressionParts[1]))
                    .withDaysOfMonth(dayOfMonthParser.parse(expressionParts[2]))
                    .withMonths(monthParser.parse(expressionParts[3]))
                    .withDaysOfWeek(dayOfWeekParser.parse(expressionParts[4]))
                    .withCommand(new CronPart(CronPartName.COMMAND.toString(), expressionParts[5]))
                    .build();
        } else if (expressionParts.length == EXPRESSION_PART_COUNT_WITH_YEAR) {
            return new CronScheduleBuilder()
                    .withMinutes(minuteParser.parse(expressionParts[0]))
                    .withHours(hourParser.parse(expressionParts[1]))
                    .withDaysOfMonth(dayOfMonthParser.parse(expressionParts[2]))
                    .withMonths(monthParser.parse(expressionParts[3]))
                    .withDaysOfWeek(dayOfWeekParser.parse(expressionParts[4]))
                    .withYear(yearParser.parse(expressionParts[5]))
                    .withCommand(new CronPart(CronPartName.COMMAND.toString(), expressionParts[6]))
                    .build();
        }

        throw new CronParseException(
                String.format("CRON expression accepts %s parts " +
                                "(minute, hour, dayOfMonth, month, dayOfWeek, year, command) separated by '%s' " +
                                "(year is optional).",
                        EXPRESSION_PART_COUNT, EXPRESSION_PART_SEPARATOR));

    }
}
