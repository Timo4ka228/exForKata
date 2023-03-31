import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа: ");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int a;
        int b;
        String operator;
        String result;
        boolean isRoman;
        String[] numbers = expression.split("[+\\-*/]");
        if (numbers.length != 2) throw new Exception("Должно быть два числа");
        operator = returnOperation(expression);
        if (operator == null) throw new Exception("Неподдерживаемая математическая операция");
        if (Roman.isRoman(numbers[0]) && Roman.isRoman(numbers[1])) {
            a = Roman.convertToArabian(numbers[0]);
            b = Roman.convertToArabian(numbers[1]);
            isRoman = true;
        }
        else if (!Roman.isRoman(numbers[0]) && !Roman.isRoman(numbers[1])) {
            a = Integer.parseInt(numbers[0]);
            b = Integer.parseInt(numbers[1]);
            isRoman = false;
        }
        else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if (a > 10 || b > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calculate(a, b, operator);
        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            result = Roman.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String returnOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calculate(int a, int b, String operator) {

        if (operator.equals("+")) return a + b;
        else if (operator.equals("-")) return a - b;
        else if (operator.equals("*")) return a * b;
        else return a / b;
    }

}
