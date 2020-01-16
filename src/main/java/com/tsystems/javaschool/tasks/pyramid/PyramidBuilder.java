package com.tsystems.javaschool.tasks.pyramid;

import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    private int rows;
    private int columns;
    private int max;

    public int[][] buildPyramid(List<Integer> inputNumbers) throws CannotBuildPyramidException {
        if (inputNumbers.contains(null)) throw new CannotBuildPyramidException();
        if (inputNumbers.size() < 3) throw new CannotBuildPyramidException();
        boolean canBuild = false;
        double x = -1;
        int n = 1;
        while (inputNumbers.size() - x > -1) {
            x = (n * n + 3 * n + 2) / 2;
            if ((x - inputNumbers.size()) == 0) {
                rows = n + 1;
                columns = n * 2 + 1;
                max = inputNumbers.size();
                canBuild = true;
                break;
            }
            n++;
        }
        if (!canBuild) throw new CannotBuildPyramidException();
        inputNumbers.sort(Integer::compareTo);
        int[][] pyramid = new int[rows][columns];
        int number = max;
        int start = columns, stop = -2;
        outer:
        for (int i = rows - 1; i > -1; i--) {
            start--;
            stop++;
            for (int j = start; j > stop; j -= 2) {
                pyramid[i][j] = inputNumbers.get(number - 1);
                number--;
                if (number == 0) break outer;
            }
        }
        return pyramid;
    }
}
