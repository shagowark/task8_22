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
        boolean winOne = false;
        boolean winZero = false;
        
        int[][] directionArgs = {
                {0, 1}, // горизонтально
                {1, 0}, // вертикально
                {1, 1}, // диагонали
                {1, -1}
        };

        for (int[] args : directionArgs){
            if (checkIfWin(args[0], args[1], 0, arr)){
                winZero = true;
            }
            if (checkIfWin(args[0], args[1], 1, arr)){
                winOne = true;
            }
        }

        if (winOne && winZero){
            return 0;
        }
        if (winOne){
            return 1;
        }
        if (winZero){
            return -1;
        }
        return 0;
    }

    /***
     * Проверяет переданный массив на победу для переданного числа (0/1) по заданному
     * направлению
     * @param growthI прирост i (вертикаль) -1/0/1
     * @param growthJ прирост j (горизонталь) -1/0/1
     * @param x число 0/1, для которого проверяется массив
     * @param arr массив, представляющий игровое поле
     * @return true/false
     */
    public static boolean checkIfWin (int growthI, int growthJ, int x, int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                int tempI = i;
                int tempJ = j;
                if (arr[i][j] == x) {
                    int count = 1;
                    while (count != 5 && tempI >= 0 && tempI < arr.length && tempJ >= 0
                            && tempJ < arr[0].length) {
                        tempI += growthI;
                        tempJ += growthJ;
                        if (tempI >= 0 && tempI < arr.length && tempJ >= 0 && tempJ < arr[0].length) {
                            if (arr[tempI][tempJ] == x) {
                                count++;
                            } else {
                                count = 0;
                                break;
                            }
                        }
                    }
                    if (count == 5) {
                        return true;
                    }
                }
            }
        }
        return false;
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
