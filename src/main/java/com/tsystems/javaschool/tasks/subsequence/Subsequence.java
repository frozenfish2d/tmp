package com.tsystems.javaschool.tasks.subsequence;

import java.util.LinkedList;
import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        if (x == null || y == null) throw new IllegalArgumentException();

        try {
            boolean result = false;
            List<Object> firstList = new LinkedList(x);
            List<Object> secondList = new LinkedList(y);
            List<Object> bufferList = new LinkedList();
            int j = 0;

            for (int i = 0; i < firstList.size() && secondList.size() > 0; i++) {
                while (secondList.size() > 0) {
                    if (firstList.get(i) != secondList.get(j)) {
                        secondList.remove(j);
                        j = 0;
                    } else {
                        bufferList.add(secondList.get(j));
                        j++;
                        break;
                    }
                }
            }
            if (firstList.equals(bufferList)) result = true;


            return result;
        } catch (Exception e) {
            return false;
        }
    }
}
