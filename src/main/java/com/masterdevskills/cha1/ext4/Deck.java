package com.masterdevskills.cha1.ext4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public interface Deck {
    List<Card> getCards();

    Deck deckFactory();

    int size();

    void addCard(Card card);

    void addCards(List<Card> cards);

    void addDeck(Deck deck);

    void shuffle();

    void sort();

    void sort(Comparator<Card> c);

    String deckToString();

    Map<Integer, Deck> deal(int players, int numberOfCards)
            throws IllegalArgumentException;
    default void sortByKing(){
        System.out.println("Sorted by King");
    };
    default void showTrumpCard(){
        List<String> cards= List.of("diamonds","spades","hearts","clubs");
        Random rand = new Random();
        System.out.println( cards.get(rand.nextInt(cards.size())));
    };
}
