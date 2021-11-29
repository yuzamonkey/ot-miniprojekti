package ot.miniprojekti.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextUserInterface {

    private List<String> names;
    private Scanner reader;

    public TextUserInterface(Scanner reader) {
        this.names = new ArrayList<>();
        this.reader = reader;
    }

    public void start() {
        while (true) {
            System.out.println("Valitse toiminto");
            System.out.println("[1] Lisää vinkki");
            System.out.println("[2] Tulosta vinkit");
            System.out.println("[x] Sulje sovellus");

            System.out.print("> ");
            String answer = reader.nextLine();

            if (answer.equals("1")) {
                addReadingTip();
            } else if (answer.equals("2")) {
                printTips();
            } else if (answer.equals("x") || answer.equals("")) {
                break;
            }
        }
    }

    private void addReadingTip() {
        System.out.print("Nimi: ");
        String name = reader.nextLine();
        names.add(name);
    }

    private void printTips() {
        for (String tip : names) {
            System.out.println(tip);
        }
    }
}
