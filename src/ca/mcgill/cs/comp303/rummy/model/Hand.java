package ca.mcgill.cs.comp303.rummy.model;

import java.util.*;

/**
 * Models a hand of 10 cards. The hand is not sorted. Not threadsafe. The hand is a set: adding the same card twice will
 * not add duplicates of the card.
 * <p/>
 * size() > 0 size() <= HAND_SIZE
 */
public class Hand
{
    private static final int HAND_MAX_SIZE = 11;
    private static final int MAX_CARD_VALUE = 10;
    private Set<Card> aCards = new HashSet<Card>();
    private Set<Card> aUnmatchedCards = new HashSet<Card>();
    private Set<ICardSet> aMatchedSets = new HashSet<ICardSet>();

    /**
     * Creates a new, empty hand.
     */
    public Hand()
    {

    }

    /**
     * Adds pCard to the list of unmatched cards. If the card is already in the hand, it is not added.
     *
     * @param pCard The card to add.
     * @throws HandException if the hand is complete
     *                       if the card is already in the hand.
     * @pre pCard != null
     */
    public void add(Card pCard)
    {
        if (pCard == null)
        {
            return;
        }

        if (isComplete())
        {
            throw new HandException("The hand is complete");
        }

        else if (!aCards.add(pCard))
        {
            aUnmatchedCards.add(pCard);
            throw new HandException("The card is already in the hand");
        }
    }

    /**
     * Remove pCard from the hand and break any matched set that the card is part of. Does nothing if pCard is not in
     * the hand.
     *
     * @param pCard The card to remove.
     * @pre pCard != null
     */
    public void remove(Card pCard)
    {
        if (pCard == null)
        {
            return;
        }
        aCards.remove(pCard);
        aUnmatchedCards.remove(pCard);
    }

    /**
     * @return True if the hand is complete.
     */
    public boolean isComplete()
    {
        return aCards.size() == HAND_MAX_SIZE - 1;
    }

    /**
     * @return true if the hand contains 1 extra card that need to be discarded
     */
    public boolean needToDiscard()
    {
        return aCards.size() == HAND_MAX_SIZE;
    }

    /**
     * Removes all the cards from the hand.
     */
    public void clear()
    {
        aCards.clear();
    }

    /**
     * @return A copy of the set of matched sets
     */
    public Set<ICardSet> getMatchedSets()
    {
        return new HashSet<ICardSet>(aMatchedSets);
    }

    /**
     * @return A copy of the set of unmatched cards.
     */
    public Set<Card> getUnmatchedCards()
    {
        return new HashSet<Card>(aUnmatchedCards);
    }

    /**
     * @return The number of cards in the hand.
     */
    public int size()
    {
        return aCards.size(); // TODO
    }

    /**
     * Determines if pCard is already in the hand, either as an unmatched card or as part of a set.
     *
     * @param pCard The card to check.
     * @return true if the card is already in the hand.
     * @pre pCard != null
     */
    public boolean contains(Card pCard)
    {
        return aCards.contains(pCard);
    }

    /**
     * @return The total point value of the unmatched cards in this hand.
     */
    public int score()
    {
        int score = 0;
        for (Card card : aUnmatchedCards)
        {
            score += Math.min(card.getRank().ordinal() + 1, MAX_CARD_VALUE);
        }
        return score;
    }

    /**
     * Creates a group of cards of the same rank.
     *
     * @param pCards The cards to groups
     * @throws HandException If the cards in pCard are not all unmatched cards of the hand or if the group is not a valid group.
     * @pre pCards != null
     */
    public void createGroup(Set<Card> pCards)
    {
        for (Card card : pCards)
        {
            if (!aUnmatchedCards.contains(card))
            {
                throw new HandException("Error card is not in unmatched cards");
            }
        }
        ICardSet aSet = new CardSet(pCards);

        if (!aSet.isGroup())
        {
            throw new HandException("Not a valid group");
        }
        else
        {
            aMatchedSets.add(aSet);
            for (Card card : pCards)
            {
                aUnmatchedCards.remove(card);
            }
        }
    }

    /**
     * Creates a run of cards of the same suit.
     *
     * @param pCards The cards to group in a run
     * @throws HandException If the cards in pCard are not all unmatched cards of the hand or if the group is not a valid group.
     * @pre pCards != null
     */
    public void createRun(Set<Card> pCards)
    {
        for (Card card : pCards)
        {
            if (!aUnmatchedCards.contains(card))
            {
                throw new HandException("Error card is not in unmatched cards");
            }
        }
        ICardSet aSet = new CardSet(pCards);

        if (!aSet.isRun())
        {
            throw new HandException("Not a valid group");
        }
        else
        {
            aMatchedSets.add(aSet);
            for (Card card : pCards)
            {
                aUnmatchedCards.remove(card);
            }
        }
    }

    /**
     * Calculates the matching of cards into groups and runs that results in the lowest amount of points for unmatched cards.
     */
    public void autoMatch()
    {
        List<Card> sortedCards = new ArrayList<Card>(aCards);
        Collections.sort(sortedCards, Collections.reverseOrder());
        List<Card> lastSet = new ArrayList<Card>();
        SetType currentSetType = null;
        boolean needRerun = true;

        Set<Card> usedCards = new HashSet<Card>();
        while (needRerun)
        {
            Iterator<Card> iterator = sortedCards.iterator();
            needRerun = false;
            while (iterator.hasNext())
            {
                Card card = iterator.next();
                if (usedCards.contains(card))
                {
                    continue;
                }
                // If the current serie has only 1 element we check what type of set we can make
                if (lastSet.size() == 1)
                {
                    Card lastCard = lastSet.get(lastSet.size() - 1);
                    if (lastCard.getRank() == card.getRank())
                    {
                        currentSetType = SetType.GROUP;
                    }
                    else if (lastCard.getSuit() == card.getSuit() && (lastCard.getRank().ordinal() - 1) == card
                            .getRank()
                            .ordinal())
                    {
                        currentSetType = SetType.RUN;
                    }
                    else
                    {
                        lastSet.clear();
                    }
                }

                if (lastSet.isEmpty())
                {
                    lastSet.add(card);
                }
                else
                {
                    boolean setEnded = false;
                    Card lastCard = lastSet.get(lastSet.size() - 1);
                    if (currentSetType == SetType.GROUP)
                    {
                        if (card.getRank() == lastCard.getRank())
                        {
                            lastSet.add(card);
                            usedCards.add(card);
                        }
                        else
                        {
                            setEnded = true;
                        }
                    }
                    else if (currentSetType == SetType.RUN)
                    {
                        //Potentially a group
                        if (card.getRank() == lastCard.getRank())
                        {
                            //Skip this value
                            needRerun = true;
                            continue;
                        }
                        else if (lastCard.getRank().ordinal() - 1 == card.getRank().ordinal()
                                && lastCard.getSuit() == card
                                .getSuit())
                        {
                            lastSet.add(card);
                            usedCards.add(card);
                        }
                        else
                        {
                            setEnded = true;
                        }
                    }
                    else
                    {
                        System.out.println("error should never be in here");
                    }

                    if (setEnded)
                    {
                        if (lastSet.size() >= 3)
                        {
                            System.out.println("Good set: " + lastSet);
                        }
                        else
                        {
                            System.out.println("Bad set: " + lastSet);
                        }
                        lastSet.clear();
                    }
                }
            }
        }

    }

    public ArrayList<Card> getCards()
    {
        return new ArrayList<Card>(aCards);

    }

    private enum SetType
    {
        RUN, GROUP
    }
}
