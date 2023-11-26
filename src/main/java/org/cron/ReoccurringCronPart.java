package org.cron;

import java.util.List;
import java.util.Objects;

public class ReoccurringCronPart extends CronPart {
    private final List<Integer> occurrences;

    public ReoccurringCronPart(String name, List<Integer> occurrences) {
        super(name, String.join(" ", occurrences.stream().map(Object::toString).toArray(String[]::new)));
        this.occurrences = occurrences;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, occurrences);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReoccurringCronPart that)) return false;
        return this.name.equals(that.name) && this.value.equals(that.value) &&
                this.occurrences.containsAll(that.occurrences) && this.occurrences.size() == that.occurrences.size();
    }
}
