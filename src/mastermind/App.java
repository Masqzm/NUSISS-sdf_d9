package mastermind;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class App {
    static final int TRIES_MAX = 12;
    static final int CODE_LENGTH = 4;
    static final int CODE_MIN = 1;
    static final int CODE_MAX = 6;
    static final char CP_CHAR = 'C';    
    public static void main(String[] args) {
        // Start game and keep repeating game until player choose not to
        boolean repeatGame = true;
        
        while(repeatGame)
            repeatGame = newGame();

        System.out.println("\nExiting game...");
    }

    private static boolean newGame() {        
        boolean hasWon = false;

        Console cons = System.console();
        String code = generateCode();

        // # DEBUG ONLY
        System.out.println("CODE: " + code);

        for(int gi = 0; gi < TRIES_MAX; gi++)
        {
            // Keep asking for input if its not valid
            String guess = "";
            while(!validateInput(guess)) 
                guess = cons.readLine("Attempt #"+ (gi+1) + ": ");
            
            // Check guess 
            int C = 0;      // to track correct digit, wrong pos guess
            int CP = 0;     // to track correct digit, right pos guess

            // Create map to keep track of code unmatched digit occurences (K: digit (char), V: occurences (int))
            // - to prevent repeat matches
            Map<Character, Integer> codeMap = new HashMap<>();
            for(int i = 0; i < CODE_LENGTH; i++)
            {
                char key = code.charAt(i);
                if(codeMap.containsKey(key))
                    codeMap.put(key, codeMap.get(key)+1);
                else
                    codeMap.put(key, 1);
            }
            //System.out.println(codeMap.toString());
            // Check each digit of guess
            for(int i = 0; i < CODE_LENGTH; i++)
            {
                // Check if digit is correct & at right pos
                if(guess.charAt(i) == code.charAt(i)) 
                {
                    ++CP;

                    // Reduce occurence count of digit in codeMap 
                    char key = guess.charAt(i);
                    codeMap.put(key, codeMap.get(key)-1);
                }
            }
            //System.out.println(codeMap.toString());
            
            for(int i = 0; i < CODE_LENGTH; i++)
            {
                // Check if digit occurs in other position
                if(code.contains(guess.charAt(i)+""))
                {
                    char key = guess.charAt(i);
                    
                    // If there is still more unmatched occurence in codeMap of this digit
                    if(codeMap.get(key) > 0) 
                    {
                        // Update values of guessed digit alrd matched 
                        codeMap.put(key, codeMap.get(key)-1);
                        ++C; 
                    }
                }
            }

            // if code entered is a match
            if(CP == CODE_LENGTH)
            {
                hasWon = true;
                break;
            }
            else
            {
                System.out.println("Total correct digits in right place: " + CP);
                System.out.println("Total correct digits in wrong place: " + C);
                System.out.println("Tries left: " + (TRIES_MAX-gi-1));
            }
        }

        // Game result
        if(hasWon)
            System.out.println("\nYou guessed the right code! You win!!!");
        else
        {
            System.out.println("\nYou ran out of tries! You lose!!!");
            System.out.println("CODE: " + code);
        }

        // Keep asking whether to replay (accepts 'y' or 'n' only)
        String input = "";
        while( !(input.equals("y") || input.equals("n")) ) {
            input = cons.readLine("\nReplay game? [y/n]\n> ").toLowerCase();
            
            if(input.equals("y"))
                return true;
        }

        return false;
    }

    private static String generateCode() {
        String code = "";
        Random rand = new Random();

        // Get random num 1-6 and append to code for CODE_LENGTH times
        for(int i = 0; i < CODE_LENGTH; i++)
            code += "" + (rand.nextInt(CODE_MAX) + 1);
        
        return code;
    }

    private static boolean validateInput(String input) {
        // Check if input is right length & is valid
        if (input.length() != 4 || !input.matches("[" +CODE_MIN+ "-" +CODE_MAX+ "]{"+CODE_LENGTH+"}")) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Please enter a 4-digit number between 1111 and 6666.");
            return false;
        }

        // Manual check without using regex (assuming length is correct)
        // for (int i = 0; i < CODE_LENGTH; i++) {
        //     if (Character.getNumericValue(input.charAt(i)) > 6 || Character.getNumericValue(input.charAt(i)) < 1) {
        //         System.out.println("Please enter a number between 1 and 6.");
        //         return false;
        //     }
        // }

        return true;
    }
}
