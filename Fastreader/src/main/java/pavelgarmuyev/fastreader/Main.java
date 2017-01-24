
package pavelgarmuyev.fastreader;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pavelgarmuyev
 */

public class Main {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input text below");
        String input = scanner.nextLine();
        
        List words = new LinkedList<>();
        
        words.addAll(Arrays.asList(input.split(" ")));
        
        words.forEach(System.out::println);
    }
}
