package com.jenkins.apps;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
public class Anagram {
    private static final String REGEXP_SPACE = " ";
    private static final char JOIN_SPACE = ' ';
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        System.out.println("Enter text: ");
        String incomingText = reader.readLine();
        System.out.println("Incoming text is:	" + incomingText);
        String anagram = anagram(incomingText);
        System.out.println("Anagram text is:	" + anagram);
    }
    public static String anagram(String text) {
        String[] words = text.split(REGEXP_SPACE, -1);
        StringBuilder anagram = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String anagramOfWord = anagramWord(words[i]);
            anagram.append(anagramOfWord);
            anagram.append(JOIN_SPACE);
        }
        anagram.deleteCharAt(anagram.length() - 1);
        return anagram.toString();
    }
    private static String anagramWord(String word) {
        String anagramOfLetters = appendReversedLetters(word);
        String anagramOfWord = insertNonLettersToAnagram(anagramOfLetters, word);
        return anagramOfWord;
    }
    private static String appendReversedLetters(String word) {
        StringBuilder lettersBuilder = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
            if (Character.isLetter(word.charAt(i))) {
                lettersBuilder.append(word.charAt(i));
            }
        }
        return lettersBuilder.toString();
    }
    private static String insertNonLettersToAnagram(String letters, String word) {
        StringBuilder insertedSymbols = new StringBuilder(letters);
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isLetter(word.charAt(i))) {
                insertedSymbols.insert(i, word.charAt(i));
            }
        }
        return insertedSymbols.toString();
    }
}