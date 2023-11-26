package org.cron;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CronParserTest {

    @Test
    void parse_invalidMinute_CronParseExceptionThrown() {
        // given

        // when
        var exception = assertThrows(CronParseException.class, () ->
                CronParser.parse("invalid * ? * ? test-command"));

        // then
        assertEquals("Invalid expression for 'minute'", exception.getMessage());
    }

    @Test
    void parse_monthOutOfBounds_CronParseExceptionThrown() {
        // given

        // when
        var exception = assertThrows(CronParseException.class, () ->
                CronParser.parse("* * ? 15 ? test-command"));

        // then
        assertEquals("'month' accepts only values between 1 and 12", exception.getMessage());
    }

    @Test
    void parse_hourStartIndexGreaterThanEnd_CronParseExceptionThrown() {
        // given

        // when
        var exception = assertThrows(CronParseException.class, () ->
                CronParser.parse("* 15-5 ? * ? test-command"));

        // then
        assertEquals("Start of range cannot be greater than end it's end for 'hour'", exception.getMessage());
    }

    @Test
    void parse_dayOfMonthIntervalGreaterThanWholeRange_CronParseExceptionThrown() {
        // given

        // when
        var exception = assertThrows(CronParseException.class, () ->
                CronParser.parse("* * */32 * ? test-command"));

        // then
        assertEquals("Interval can not be greater than the whole range for 'day of month'", exception.getMessage());
    }

    @Test
    void parse_asteriskOrQuestionMarkTokenForAll_allRangesForEachPart() throws IOException, CronParseException {
        // given
        var expectedResult = new String(Files.readAllBytes(
                Paths.get("src/test/resources/allValuesResult.txt")));

        // when
        var actualResult = CronParser.parse("* * ? * ? test-command").toString();

        // then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void parse_asteriskWithRedundantSpaces_allRangesForEachPart() throws IOException, CronParseException {
        // given
        var expectedResult = new String(Files.readAllBytes(
                Paths.get("src/test/resources/allValuesResult.txt")));

        // when
        var actualResult = CronParser.parse("  *  *  *     * *    test-command  ").toString();

        // then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void parse_singleNumbersForAll_singleOccurrenceForEachPart() throws IOException, CronParseException {
        // given
        var expectedResult = new String(Files.readAllBytes(
                Paths.get("src/test/resources/singleNumbersResult.txt")));

        // when
        var actualResult = CronParser.parse("1 2 3 4 5 test-command").toString();

        // then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void parse_dashTokenForAll_subsetRangesForEachPart() throws IOException, CronParseException {
        // given
        var expectedResult = new String(Files.readAllBytes(
                Paths.get("src/test/resources/rangesResult.txt")));

        // when
        var actualResult = CronParser.parse("1-4 5-23 4-7 5-9 5-7 test-command").toString();

        // then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void parse_slashTokenForAll_intervalsForEachPart() throws IOException, CronParseException {
        // given
        var expectedResult = new String(Files.readAllBytes(
                Paths.get("src/test/resources/intervalsResult.txt")));

        // when
        var actualResult = CronParser.parse("0/4 4/20 2/3 */4 1/2 test-command").toString();

        // then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void parse_mixedInputsForAllParts_correctResult() throws IOException, CronParseException {
        // given
        var expectedResult = new String(Files.readAllBytes(
                Paths.get("src/test/resources/mixedResult.txt")));

        // when
        var actualResult = CronParser.parse("*/4 4-20 2/3 4 * test-command").toString();

        // then
        assertEquals(expectedResult, actualResult);
    }
}