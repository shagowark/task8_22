package ConsoleModule;

import Utils.ArrayUtils;
import com.Logic;

import java.io.PrintStream;


public class ConsoleMain {
    /*
    8 таск, вариант 22.

    Тесты находятся в файлах под именами input1.txt, input2.txt и т.д.
    Для каждого теста создана конфигурация с соответствующими параметрами командной строки (test1, test2...)
    Результаты для всех тестов выводятся в файл output.txt
    Пустые клетки в тестовых файлах и при создании рандомного массива в оконном приложении
    обозначаются как -1
    Ожидаемые результаты:
    1) 0 - ничья, никто не имеет выигрышную ситуацию
    2) -1 - победа нулей
    3) 1 - победа единиц
    4) 0 - ничья, оба игрока имеют выигрышную ситуацию
    5) 1 - победа единиц (весь файл состоит из них)
    6) 0 - ничья, никто не имеет выигрышную ситуацию (большой массив)
    7) ошибка пустого массива
    8) ошибка непрямоугольного массива
    9) ошибка несуществующего файла ввода

     */

    /**
     * Разбирает аргументы командной строки
     */
    public static InputArgs parseCmdArgs(String[] args) {
        InputArgs params = new InputArgs();

        if (args.length > 0) {
            if (args.length < 2) {
                params.error = true;
                return params;
            }
            if (!args[0].equals("-i") && !args[0].contains("--input-file=")
                    && !args[0].equals("-o") && !args[0].contains("--output-file=")) {
                params.error = true;
                return params;
            }
            if (args[0].equals("-i")) {
                params.inputFile = args[1];
                if (args[2].equals("-o")) {
                    params.outputFile = args[3];
                } else {
                    params.error = true;
                    return params;
                }
            }
            if (args[0].equals("-o")) {
                params.outputFile = args[1];
                if (args[2].equals("-i")) {
                    params.inputFile = args[3];
                } else {
                    params.error = true;
                    return params;
                }
            }
            if (args[0].contains("--input-file=")) {
                params.inputFile = args[0].replace("--input-file=", "");
                if (args[1].contains("--output-file=")) {
                    params.outputFile = args[1].replace("--output-file=", "");
                } else {
                    params.error = true;
                    return params;
                }
            }
            if (args[0].contains("--output-file=")) {
                params.outputFile = args[0].replace("--output-file=", "");
                if (args[1].contains("--input-file=")) {
                    params.inputFile = args[1].replace("--input-file=", "");
                } else {
                    params.error = true;
                    return params;
                }
            }
            if (params.inputFile.equals("") || params.outputFile.equals("")) {
                params.error = true;
                return params;
            }
        } else {
            params.error = true;
        }

        return params;
    }

    public static void main(String[] args) throws Exception {
        InputArgs params = parseCmdArgs(args);

        if (params.error) {
            PrintStream out = System.err;
            out.println("Неверные параметры командной строки, попробуйте еще раз");
            out.println("Параметры должны выглядеть следующим образом:");
            out.println("-i input.txt -o output.txt ИЛИ --input-file=input.txt –-output-file=output.txt");
            out.println("Файлы должны находиться в директории проекта");
            System.exit(1);
        } else {
            int[][] arr = ArrayUtils.readIntArray2FromFile(params.inputFile);
            try {
                Logic.checkIfArrayIsNull(arr);
            }catch (Exception e){
                e.printStackTrace();
                System.exit(2);
            }
            try {
                Logic.checkIfArrayIsEmpty(arr);
            }catch (Exception e){
                e.printStackTrace();
                System.exit(3);
            }
            try{
                Logic.checkIfArrayIsRectangle(arr);
            }catch (Exception e){
                e.printStackTrace();
                System.exit(4);
            }

            int result = Logic.resultOfTheMatch(arr);
            Logic.saveOutputIntoFile(params.outputFile, result);
        }
    }
}


