package task_2;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class convertation_currencies {

    private static final HashMap<String,Double> currencies = new HashMap<>();

    //конвертация происходит на основе курса доллара
    private static void setCurrencies(){
        currencies.put("dollar", 1.0);
        currencies.put("euro", 0.85);
        currencies.put("rub", 83.74);
        currencies.put("tng", 543.31);
        currencies.put("gbp",0.75);
    }

    public static void main(String[] args) {
        setCurrencies();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Программа для конвертации валют");
        System.out.println("Доступные валюты для конвертации: " + currencies.keySet());

        while (true){
            System.out.println("Введите валюту (или exit для выхода): ");
            String currency = scanner.next().toLowerCase();

            if(currency.equals("exit")){
                System.out.println("Программа завершена. Хорошего дня!");
                break;
            }

            if(!currencies.containsKey(currency)){
                System.out.println("Данная валюта отсутсвует в списке!");
                continue;
            }

            System.out.println("Введите сумму конвертации: ");

            double amount;

            try {
                amount = scanner.nextDouble();
            }

            catch (InputMismatchException e){
                System.out.println("Введите число!");
                continue;
            }

            System.out.println("Введите валюту, в которую хотите конвертировать: ");
            String convertation_currency = scanner.next().toLowerCase();

            double amountInDOLLAR = amount / currencies.get(currency);

            double amount_after_convertation = amountInDOLLAR * currencies.get(convertation_currency);

            System.out.printf("Результат конвертации из %s в %s: %.4f%n", currency, convertation_currency, amount_after_convertation);

        }

        scanner.close();

    }
}
