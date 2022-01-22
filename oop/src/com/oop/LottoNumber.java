package com.oop;

import java.util.HashSet;
import java.util.Set;

public class LottoNumber {
    private Set<Integer> numbers;

    public void addNumber(int number) {
        if (numbers == null) this.numbers = new HashSet<>();
        this.numbers.add(number);
    }

    public Set<Integer> getNumbers() {
        return this.numbers;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Integer number : numbers) {
            builder.append(number).append(" ");
        }
        return builder.toString();
    }
}
