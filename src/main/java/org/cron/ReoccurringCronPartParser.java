package org.cron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ReoccurringCronPartParser {
    private final static Pattern allOccurrencesPatter = Pattern.compile("(\\*|\\?)");
    private final static Pattern singleNumberPattern = Pattern.compile("(\\d+)");
    private final static Pattern listPattern = Pattern.compile("(\\d+)(,(\\d+))+");
    private final static Pattern rangePattern = Pattern.compile("(\\d+-(\\d+))");
    private final static Pattern intervalPattern = Pattern.compile("((\\d+|\\*)/(\\d+))");

    private final Integer startIndex;
    private final Integer endIndex;
    private final String partName;

    public ReoccurringCronPartParser(Integer startIndex, Integer endIndex, String partName) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.partName = partName;
    }

    public ReoccurringCronPart parse(String expression) throws CronParseException {
        expression = expression.trim();
        var occurrences = new ArrayList<Integer>();

        if (allOccurrencesPatter.matcher(expression).matches()) {
            occurrences.addAll(getAllOccurrences());
        } else if (singleNumberPattern.matcher(expression).matches()) {
            occurrences.add(Integer.parseInt(expression));
        } else if (listPattern.matcher(expression).matches()) {
            occurrences.addAll(Arrays.stream(expression.split(","))
                    .map(Integer::parseInt)
                    .sorted()
                    .toList());
        } else if (rangePattern.matcher(expression).matches()) {
            var expressionParts = expression.split("-");
            var start = Integer.parseInt(expressionParts[0]);
            var end = Integer.parseInt(expressionParts[1]);

            if (start > end) {
                throw new CronParseException(
                        String.format("Start of range cannot be greater than end it's end for '%s'", partName));
            }

            occurrences.addAll(getOccurrencesBetween(start, end));
        } else if (intervalPattern.matcher(expression).matches()) {
            var expressionParts = expression.split("/");
            var start = expressionParts[0].equals("*") ? startIndex : Integer.parseInt(expressionParts[0]);
            var interval = Integer.parseInt(expressionParts[1]);

            if (interval > endIndex - startIndex) {
                throw new CronParseException(
                        String.format("Interval can not be greater than the whole range for '%s'", partName));
            }

            occurrences.addAll(getOccurrencesInInterval(start, interval));
        } else {
            throw new CronParseException(
                    String.format("Invalid expression for '%s'", partName));
        }

        validateOccurrences(occurrences);

        return new ReoccurringCronPart(partName, occurrences);
    }

    private List<Integer> getAllOccurrences() {
        return getOccurrencesBetween(startIndex, endIndex);
    }

    private List<Integer> getOccurrencesBetween(Integer start, Integer end) {
        var result = new ArrayList<Integer>();

        for (int i = start; i <= end; i++) {
            result.add(i);
        }

        return result;
    }

    private List<Integer> getOccurrencesInInterval(Integer start, Integer interval) {
        var result = new ArrayList<Integer>();

        for (int i = start; i <= endIndex; i += interval) {
            result.add(i);
        }

        return result;
    }

    private void validateOccurrences(List<Integer> occurrences) throws CronParseException {
        for (var occurrence : occurrences) {
            if (occurrence < startIndex || occurrence > endIndex) {
                throw new CronParseException(
                        String.format("'%s' accepts only values between %s and %s", partName, startIndex, endIndex));
            }
        }
    }
}
