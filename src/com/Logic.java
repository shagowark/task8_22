package com;

import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Logic {

    /**
     * Возвращает результат матча
     *
     * @param arr массив, представляющий игровое поле
     * @return 1 - победа единиц, 0 - ничья, -1 - победа нулей
     */
    public static int resultOfTheMatch(int[][] arr) {
        if ((winInRow(0, arr) || winInColumn(0, arr) || winInDiagonal(0, arr)) &&
                (winInRow(1, arr) || winInColumn(1, arr) || winInDiagonal(1, arr))) {
            return 0;
        }
        if (winInRow(0, arr) || winInColumn(0, arr) || winInDiagonal(0, arr)) {
            return -1;
        }
        if (winInRow(1, arr) || winInColumn(1, arr) || winInDiagonal(1, arr)) {
            return 1;
        }
        return 0;
    }

    /**
     * Проверяет массив на победу "в ряд" для переданного аргумента (0/1)
     */
    public static boolean winInRow(int x, int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 1; j < arr[i].length; j++) {
                if (arr[i][j] == x && arr[i][j] == arr[i][j - 1]) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверяет массив на победу "в столбик" для переданного аргумента (0/1)
     */
    public static boolean winInColumn(int x, int[][] arr) {
        for (int i = 0; i < arr[0].length; i++) {
            int count = 0;
            int[] column = getColumn(i, arr);
            for (int j = 1; j < column.length; j++) {
                if (column[j] == x && column[j] == column[j - 1]) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверяет массив на победу "по диагонали" для переданного аргумента (0/1)
     */
    public static boolean winInDiagonal(int x, int[][] arr) {
        return checkLeftToppedDiagonal(x, arr) || checkRightToppedDiagonal(x, arr);
    }

    /**
     * Проверяет диагонали, наклоненные влево вверх, т.е. вида "\"
     */
    public static boolean checkLeftToppedDiagonal(int x, int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            int tempI = i;
            for (int j = 0; j < arr[0].length; j++) {
                int tempJ = j;
                if (arr[i][j] == x) {
                    count++;
                    while (count != 5 && tempJ < arr[0].length - 1 && tempI < arr.length - 1) {
                        tempJ++;
                        tempI++;
                        if (arr[tempI][tempJ] == x) {
                            count++;
                        } else {
                            count = 0;
                            break;
                        }
                    }
                    tempI = i;
                    if (count == 5) {
                        return true;
                    }
                }
                count = 0;
            }
        }
        return false;
    }

    /**
     * Проверяет диагонали, наклоненные вправо вверх, т.е. вида "/"
     */
    public static boolean checkRightToppedDiagonal(int x, int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            int tempI = i;
            for (int j = 0; j < arr[0].length; j++) {
                int tempJ = j;

                if (arr[i][j] == x) {
                    count++;
                    while (count != 5 && tempJ > 0 && tempI < arr.length - 1) {
                        tempI++;
                        tempJ--;
                        if (arr[tempI][tempJ] == x) {
                            count++;
                        } else {
                            count = 0;
                            break;
                        }
                    }
                    tempI = i;
                    if (count == 5) {
                        return true;
                    }
                }
                count = 0;
            }
        }
        return false;
    }

    /**
     * Возвращает колонку с переданным индексом
     */
    public static int[] getColumn(int index, int[][] arr) {
        int[] column = new int[arr.length];
        for (int i = 0; i < column.length; i++) {
            column[i] = arr[i][index];
        }
        return column;
    }


    /**
     * Сохраняет результат в файл
     */
    public static void saveOutputIntoFile(String fileName, int result) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(result);
        }
    }

    /**
     * Проверяет, был ли прочитан массив из файла
     * @throws Exception
     */
    public static void checkIfArrayIsNull(int[][] arr) throws Exception{
        if (arr == null){
            throw new Exception("Такой input-файл не существует");
        }
    }

    /**
     * Проверяет, является ли массив пустым
     * @throws Exception
     */
    public static void checkIfArrayIsEmpty(int[][] arr) throws Exception {
        if (arr.length == 0) {
            throw new Exception("Пустой массив");
        }
    }

    /**
     * Проверяет, является ли массив прямоугольным
     * @throws Exception
     */
    public static void checkIfArrayIsRectangle(int[][] arr) throws Exception {
        checkIfArrayIsEmpty(arr);
        int length = arr[0].length;
        for (int i = 1; i < arr.length; i++) {
            if (!(arr[i].length == length)) {
                throw new Exception("Массив не является прямоугольным");
            }
        }
    }
}
