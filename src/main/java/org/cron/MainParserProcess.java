package org.cron;

import org.cron.parsing.CronParseException;
import org.cron.parsing.CronParser;

public class MainParserProcess {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.print("cron-parser accepts exactly one argument in the following format: " +
                    "'minute hour deyOfMonth month dayOfWeek command'");
            return;
        }

        try {
            System.out.printf(CronParser.parse(args[0]).toString());
        } catch (CronParseException e) {
            System.out.printf(e.getMessage());
        }
    }
}