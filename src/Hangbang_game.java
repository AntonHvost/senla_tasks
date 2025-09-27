import java.util.*;

public class Hangbang_game {

    private static final String[] WORDS = {
            "собака", "кошка", "лошадь", "корова", "заяц", "тигр", "медведь", "лиса", "волк", "петух",
            "яблоко", "груша", "слива", "арбуз", "дыня", "банан", "апельсин", "вишня", "виноград", "лимон",
            "стол", "стул", "диван", "кровать", "окно", "дверь", "крыша", "полка", "лампа", "зеркало",
            "лес", "гора", "река", "озеро", "море", "остров", "поляна", "пустыня", "трава", "цветок"
    };

    private static final Integer MAX_LIVES = 10;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random rnd = new Random();

        String word = WORDS[rnd.nextInt(WORDS.length)];
        char[] guessed_word = new char[word.length()];
        Arrays.fill(guessed_word, '_');

        int lives = MAX_LIVES;
        Set<Character> usedLetters = new HashSet<>();

        System.out.println("Игра 'Виселица'");
        System.out.println("Правила игры:\n Ваша задача угадать случайно выбранное рандомное слово, вводя каждую букву по очереди. " +
                "За неправильно угаданную букву снимается жизнь. Игра заканчивается либо при угадывании слова, либо при исчерпании жизней.");
        while(lives > 0 && new String(guessed_word).contains("_")){
            System.out.println("Загаданное слово: " + String.valueOf(guessed_word));
            System.out.println("Оставшиеся жизни: " + lives);

            String inp = scanner.nextLine().toLowerCase();

            if(inp.length() != 1 || !Character.isLetter(inp.charAt(0))){
                System.out.println("Введите одну букву!");
                continue;
            }

            char guess = inp.charAt(0);

            if(usedLetters.contains(guess)){
                System.out.println("Буква была использована!");
                continue;
            }

            usedLetters.add(guess);

            if(word.contains(String.valueOf(guess))){
                for(int i = 0; i < word.length(); i++){
                    if(word.charAt(i) == guess){
                        guessed_word[i] = guess;
                    }
                }
                System.out.println("Верно!");
            }
            else{
                lives--;
                System.out.println("Неверно!");
            }
        }

        if (lives == 0){
            System.out.println("Игра окончена! У вас не осталось жизней! Загаданное слово:" + word);
        }
        else{
            System.out.println("Молодец, ты угадал слово " + word);
        }

        scanner.close();
    }
}
