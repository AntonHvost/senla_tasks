package task_1;

import java.util.*;

class WordProvider{
    private static final String[] WORDS = {
            "собака", "кошка", "лошадь", "корова", "заяц", "тигр", "медведь", "лиса", "волк", "петух",
            "яблоко", "груша", "слива", "арбуз", "дыня", "банан", "апельсин", "вишня", "виноград", "лимон",
            "стол", "стул", "диван", "кровать", "окно", "дверь", "крыша", "полка", "лампа", "зеркало",
            "лес", "гора", "река", "озеро", "море", "остров", "поляна", "пустыня", "трава", "цветок"
    };

    public String getRandWord(){
        Random rand = new Random();
        return WORDS[rand.nextInt(WORDS.length)];
    }

}

class HangmanGameLogic {
    private static final Integer MAX_LIVES = 10;
    private final String word;
    private final char[] guessed_word;
    private int lives;
    private final Set<Character> usedLetters = new HashSet<>();

    public HangmanGameLogic(String word){
        this.word = word;
        this.guessed_word = new char[word.length()];
        Arrays.fill(guessed_word, '_');
        this.lives = MAX_LIVES;
    }

    public boolean isFinished(){
        return lives > 0 && new String(guessed_word).contains("_");
    }

    public void guessWork(char guess){
        if(usedLetters.contains(guess)){
            System.out.println("Буква была использована!");
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

    public void printState (){
        System.out.println("Загаданное слово: " + String.valueOf(guessed_word));
        System.out.println("Оставшиеся жизни: " + lives);
    }

    public void printRes(){
        if (lives == 0){
            System.out.println("Игра окончена! У вас не осталось жизней! Загаданное слово:" + word);
        }
        else{
            System.out.println("Молодец, ты угадал слово " + word);
        }
    }
}

public class HangmanGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        WordProvider provider = new WordProvider();

        String word = provider.getRandWord();
        HangmanGameLogic game = new HangmanGameLogic(word);

        System.out.println("Игра 'Виселица'");
        System.out.println("Правила игры:\n Ваша задача угадать случайно выбранное рандомное слово, вводя каждую букву по очереди. " +
                "За неправильно угаданную букву снимается жизнь. Игра заканчивается либо при угадывании слова, либо при исчерпании жизней.");
        while(game.isFinished()){
            game.printState();
            System.out.println("Введите букву: ");

            String inp = scanner.nextLine().toLowerCase();

            if(inp.length() != 1 || !Character.isLetter(inp.charAt(0))){
                System.out.println("Введите одну букву!");
                continue;
            }
            game.guessWork(inp.charAt(0));
        }

        game.printRes();
        scanner.close();
    }
}
