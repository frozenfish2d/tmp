package com.tsystems.javaschool.tasks.pyramid;

import java.util.List;
import java.util.stream.Collectors;

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

    public int[][] buildPyramid(List<Integer> inputNumbers) {

        if (inputNumbers.contains(null) || inputNumbers.size() < 3) throw new CannotBuildPyramidException();

        List<Integer> checkList = inputNumbers.stream().distinct().collect(Collectors.toList());
        if(checkList.size()==1){
            throw new CannotBuildPyramidException();
        }

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
        int start = columns;
        int stop = -2;
        int i = rows - 1;
        while (i > -1) {
            start--;
            stop++;
            int j = start;
            while (j > stop) {
                pyramid[i][j] = inputNumbers.get(number - 1);
                number--;
                if (number == 0) break;
                j -= 2;
            }
            i--;
        }
        return pyramid;

    }


}
