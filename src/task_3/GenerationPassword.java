package task_3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class PassGen {
    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String lower = "abcdefghijklmnopqrstuvwxyz";
    public static final String digits = "0123456789";
    public static final String special = "!@#$%^&*()-_=+<>?";
    private static final String all_chars = upper + lower + digits + special;

    private static final Random rand = new Random();

    public String generate(int pass_len){
        char[] password = new char[pass_len];
        int index = 0;
        Arrays.fill(password, '*');
        password[index++] = upper.charAt(rand.nextInt(upper.length()));
        password[index++] = lower.charAt(rand.nextInt(lower.length()));
        password[index++] = digits.charAt(rand.nextInt(digits.length()));
        password[index++] = special.charAt(rand.nextInt(special.length()));

        while (index < pass_len){
            password[index++] = all_chars.charAt(rand.nextInt(all_chars.length()));
        }

        for (int i = password.length - 1; i > 0; i--){
            int j = rand.nextInt(i + 1);
            char tmp = password[i];
            password[i] = password[j];
            password[j] = tmp;
        }

        return new String(password);
    }
}

public class GenerationPassword {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PassGen generator = new PassGen();

        System.out.println("Программа генерации пароля");
        System.out.println("Введите желаемую длину пароля: ");
        int pass_len = scanner.nextInt();

        if(pass_len < 8 || pass_len > 12){
            System.out.println("Пароль не должен превышать длину от 8 до 12 символов!");
            return;
        }

        String finish_pass = generator.generate(pass_len);

        System.out.println("Сгенерированный пароль: " + finish_pass);

        scanner.close();
    }
}