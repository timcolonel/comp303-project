package ca.mcgill.cs.comp303.rummy.engine;

import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Computer player that play random moves
 * Created by tim on 14-10-29.
 */
public class RandomComputerPlayer extends ComputerPlayer
{
    private static final double HALF = 0.5;

    private Random aRand = new Random();

    @Override
    protected boolean shouldRecycle()
    {
        return aRand.nextFloat() > HALF;
    }

    @Override
    protected boolean shouldKnock()
    {
        Hand workingHand = getHand().clone();
        workingHand.autoMatch();
        if (workingHand.score() < KNOCK_MAX)
        {
            return true;
        }
        return false;
    }

    @Override
    protected Card getBestDiscard()
    {
        // Get a random card to discard
        List<Card> cards = new ArrayList<Card>(getHand().getUnmatchedCards());
        return cards.get(aRand.nextInt(cards.size()));
    }
}
