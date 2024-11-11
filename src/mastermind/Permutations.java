package mastermind;

import java.io.Console;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Permutations {
    public static void main(String[] args) {
        Set<String> uniqueSet = new HashSet<>();
        
        Console cons = System.console();
        String input = "";

        while(input.length() != 4)
        {
            input = cons.readLine("Please enter 4 alphabets/digits: ");
            if(input.length() != 4) 
                System.out.println("INVALID INPUT!!");
            else
                permutation(uniqueSet, input);
        }        

        System.out.println(uniqueSet.toString());
    }


    public static void permutation(Set<String> uniqueSet, String str) { 
        permutation(uniqueSet, "", str); 
    }

    // prefix   - accumulator for current permutation
    // str      - remaining chars to be permuted 
    public static void permutation(Set<String> uniqueSet, String prefix, String str) {
        int n = str.length();

        // Base case
        if (n == 0)     // if all chars used, print/add current permutation 
        {
            //System.out.println(prefix);
            uniqueSet.add(prefix);
        }
        else 
        {
            for (int i = 0; i < n; i++)
            {
                char initChar = str.charAt(i);
                permutation(uniqueSet, prefix + initChar, str.substring(0, i) + str.substring(i+1, n));
            }
        }
    }
}
