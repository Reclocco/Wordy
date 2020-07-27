package com.example.wordy.logic;

import java.util.ArrayList;

public class MainLogic {
    public ArrayList<String> combs(ArrayList<String> letters, int length) {
        if (letters.size() == 1)
            return letters;
        else if (length == 1)
            return letters;

        ArrayList<String> results = new ArrayList<>();
        for(String letter: letters){
            ArrayList<String> copied = new ArrayList<>();
            copied.remove(letter);

            for(String word: combs(copied, length-1)){
                results.add(letter + word);
            }
        }

        return results;
    }


}
