package task_2;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

class ConvertationProcess{
    private static final HashMap<String,Double> currencies = new HashMap<>();

    public ConvertationProcess(){
        currencies.put("dollar", 1.0);
        currencies.put("euro", 0.85);
        currencies.put("rub", 83.74);
        currencies.put("tng", 543.31);
        currencies.put("gbp",0.75);
    }

    public HashMap<String, Double> getCurrencies() {
        return currencies;
    }

    public boolean isValidCurrency(String currency){
        return !currencies.containsKey(currency);
    }

    public double convertation(double amount,String currency, String convertation_currency){
        double amountInDOLLAR = amount / currencies.get(currency);
        return amountInDOLLAR * currencies.get(convertation_currency);
    }

    public void printRes(String currency, String convertation_currency, double amount_after_convertation){
        System.out.printf("Результат конвертации из %s в %s: %.4f%n", currency, convertation_currency, amount_after_convertation);
    }


}

public class ConvertationCurrencies {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ConvertationProcess process = new ConvertationProcess();

        System.out.println("Программа для конвертации валют");
        System.out.println("Доступные валюты для конвертации: " + process.getCurrencies().keySet());

        while (true){
            System.out.println("Введите валюту (или exit для выхода): ");
            String currency = scanner.next().toLowerCase();

            if(currency.equals("exit")){
                System.out.println("Программа завершена. Хорошего дня!");
                break;
            }

            if(process.isValidCurrency(currency)){
                System.out.println("Данная валюта отсутсвует в списке!");
                continue;
            }

            System.out.println("Введите сумму конвертации: ");

            double amount;

            try {
                amount = scanner.nextDouble();
            } catch (InputMismatchException e){
                System.out.println("Введите число!");
                scanner.nextLine();
                continue;
            }

            System.out.println("Введите валюту, в которую хотите конвертировать: ");
            String convertation_currency = scanner.next().toLowerCase();

            if(process.isValidCurrency(convertation_currency)){
                System.out.println("Данная валюта отсутсвует в списке!");
                continue;
            }

            double res = process.convertation(amount,currency,convertation_currency);

            process.printRes(currency, convertation_currency, res);
        }
        scanner.close();
    }
}
