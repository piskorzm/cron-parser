package org.cron.models;

public class CronParserOptions {
    private boolean allOccurrencesEnabled = true;
    private boolean singleNumberEnabled = true;
    private boolean listEnabled = true;
    private boolean rangeEnabled = true;
    private boolean intervalEnabled = true;

    public CronParserOptions() {

    }

    public boolean isUseAllOccurrencesPattern() {
        return allOccurrencesEnabled;
    }

    public void setUseAllOccurrencesPattern(boolean useAllOccurrencesPattern) {
        this.allOccurrencesEnabled = useAllOccurrencesPattern;
    }

    public boolean isUseSingleNumberPattern() {
        return singleNumberEnabled;
    }

    public void setUseSingleNumberPattern(boolean useSingleNumberPattern) {
        this.singleNumberEnabled = useSingleNumberPattern;
    }

    public boolean isUseListPattern() {
        return listEnabled;
    }

    public void setUseListPattern(boolean useListPattern) {
        this.listEnabled = useListPattern;
    }

    public boolean isUseRangePattern() {
        return rangeEnabled;
    }

    public void setUseRangePattern(boolean useRangePattern) {
        this.rangeEnabled = useRangePattern;
    }

    public boolean isUseIntervalPattern() {
        return intervalEnabled;
    }

    public void setUseIntervalPattern(boolean useIntervalPattern) {
        this.intervalEnabled = useIntervalPattern;
    }
}
