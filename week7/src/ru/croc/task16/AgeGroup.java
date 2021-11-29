package ru.croc.task16;

import java.util.ArrayList;

public class AgeGroup {
    private final int min, max;
    public ArrayList<Person> people = new ArrayList<>();

    public AgeGroup(int min, int max) {
        this.min = min;
        this.max = max;
    }
    public int getMin() {
        return this.min;
    }
    public int getMax() {
        return this.max;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.max == Integer.MAX_VALUE ? String.format("%d+", this.min) : String.format("%d-%d", this.min, this.max));
        for (int i = 0; i < this.people.size(); i++)
            stringBuilder.append(String.format(i == 0 ? ": %s (%d)" : ", %s (%d)", this.people.get(i).getName(), this.people.get(i).getAge()));

        return stringBuilder.toString();
    }
}
