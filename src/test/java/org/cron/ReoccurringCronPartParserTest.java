package org.cron;

import org.cron.models.ReoccurringCronPart;
import org.cron.parsing.CronParseException;
import org.cron.parsing.ReoccurringCronPartParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReoccurringCronPartParserTest {
    private static final String PARSER_NAME = "test part";
    private final ReoccurringCronPartParser testParser =
            new ReoccurringCronPartParser(1, 5, PARSER_NAME);

    @Test
    void parse_invalidExpression_CronParseExceptionThrown() {
        // given

        // when
        var exception = assertThrows(CronParseException.class, () -> testParser.parse("invalid"));

        // then
        assertEquals("Invalid expression for 'test part'", exception.getMessage());
    }

    @Test
    void parse_numberOutsideBounds_CronParseExceptionThrown() {
        // given

        // when
        var exception = assertThrows(CronParseException.class, () -> testParser.parse("15"));

        // then
        assertEquals("'test part' accepts only values between 1 and 5", exception.getMessage());
    }

    @Test
    void parse_intervalGreaterThanWholeRange_CronParseExceptionThrown() {
        // given

        // when
        var exception = assertThrows(CronParseException.class, () -> testParser.parse("*/6"));

        // then
        assertEquals("Interval can not be greater than the whole range for 'test part'", exception.getMessage());
    }

    @Test
    void parse_asteriskToken_wholeRangeOccurrences() throws CronParseException {
        // given
        var expectedCronPart = new ReoccurringCronPart(PARSER_NAME, List.of(1,2,3,4,5));

        // when
        var actualCronPart = testParser.parse("*");

        // then
        assertEquals(expectedCronPart, actualCronPart);
    }

    @Test
    void parse_questionMarkToken_wholeRangeOccurrences() throws CronParseException {
        // given
        var expectedCronPart = new ReoccurringCronPart(PARSER_NAME, List.of(1,2,3,4,5));

        // when
        var actualCronPart = testParser.parse("?");

        // then
        assertEquals(expectedCronPart, actualCronPart);
    }

    @Test
    void parse_asteriskTokenWithTrailingSpaces_wholeRangeOccurrences() throws CronParseException {
        // given
        var expectedCronPart = new ReoccurringCronPart(PARSER_NAME, List.of(1,2,3,4,5));

        // when
        var actualCronPart = testParser.parse("  * ");

        // then
        assertEquals(expectedCronPart, actualCronPart);
    }

    @Test
    void parse_multipleNumbers_specifiedOccurrences() throws CronParseException {
        // given
        var expectedCronPart = new ReoccurringCronPart(PARSER_NAME, List.of(1,2,5));

        // when
        var actualCronPart = testParser.parse("1,2,5");

        // then
        assertEquals(expectedCronPart, actualCronPart);
    }


    @Test
    void parse_singleNumberToken_oneOccurrence() throws CronParseException {
        // given
        var expectedCronPart = new ReoccurringCronPart(PARSER_NAME, List.of(4));

        // when
        var actualCronPart = testParser.parse("4");

        // then
        assertEquals(expectedCronPart, actualCronPart);
    }


    @Test
    void parse_dashToken_subsetOccurrences() throws CronParseException {
        // given
        var expectedCronPart = new ReoccurringCronPart(PARSER_NAME, List.of(2,3,4));

        // when
        var actualCronPart = testParser.parse("2-4");

        // then
        assertEquals(expectedCronPart, actualCronPart);
    }

    @Test
    void parse_slashTokenWithAsterisk_oddOccurrences() throws CronParseException {
        // given
        var expectedCronPart = new ReoccurringCronPart(PARSER_NAME, List.of(1,3,5));

        // when
        var actualCronPart = testParser.parse("*/2");

        // then
        assertEquals(expectedCronPart, actualCronPart);
    }

    @Test
    void parse_slashTokenWithNumber_evenOccurrences() throws CronParseException {
        // given
        var expectedCronPart = new ReoccurringCronPart(PARSER_NAME, List.of(2,4));

        // when
        var actualCronPart = testParser.parse("2/2");

        // then
        assertEquals(expectedCronPart, actualCronPart);
    }
}