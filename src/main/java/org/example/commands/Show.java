package org.example.commands;

import org.example.usualClasses.MusicBand;

import java.util.HashSet;

public class Show extends Command {
    public Show() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении",0);
    }

    @Override
    public void execute() {
        super.execute();
        long amount = 0;
        HashSet<MusicBand> musicBands = cm.getMusicBands();
        for (MusicBand mb : musicBands) {
            System.out.println(mb);
            amount++;
        }
        System.out.println("Коллекция содержит " + amount + " элементов");
    }
}
