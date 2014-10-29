package ca.mcgill.cs.comp303.rummy.engine;

import ca.mcgill.cs.comp303.rummy.model.Card;

/**
 * Indicates any problem with the state of the hand.
 */
@SuppressWarnings("serial")
public class KnockException extends Exception
{
    private Card aDiscardedCard;

    /**
     * @param pDiscardedCard Card to discard
     */
    public KnockException(Card pDiscardedCard)
    {
        aDiscardedCard = pDiscardedCard;
    }

    /**
     * @return discarded card
     */
    public Card getDiscardedCard()
    {
        return aDiscardedCard;
    }

}
