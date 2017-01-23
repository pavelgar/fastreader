/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pavelgarmuyev.fastreader;

import java.util.Scanner;

/**
 *
 * @author pavelgarmuyev
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Copy and paste text below");
        String input = scanner.nextLine();
        
        for (String s : input.split(" ")) {
            System.out.println(s);
        }
    }
}
