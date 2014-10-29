package ca.mcgill.cs.comp303.rummy.engine;

import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.Hand;

/**
 * Player class
 * Created by tim on 14-10-20.
 */
public abstract class Player
{
    private Hand aHand;
    private GameEngine aEngine;

    /**
     * Draw a card from the deck.
     *
     * @return if a card was draw
     */
    public boolean draw()
    {
        if (!canDrawCard())
        {
            return false;
        }
        aHand.add(aEngine.draw());
        return true;
    }

    /**
     * Recycle the card on top of the discard bin.
     *
     * @return card
     */
    public boolean recycle()
    {
        if (!canDrawCard())
        {
            return false;
        }
        aHand.add(aEngine.recycle());
        return true;
    }

    /**
     * Discard the extra card.
     */
    public void discard(Card pCard)
    {
        aEngine.discard(pCard);
        aHand.remove(pCard);
    }

    /**
     *
     */
    public void knock()
    {

    }

    /**
     * @return Check if the player can draw a new card
     */
    public boolean canDrawCard()
    {
        return !aHand.needToDiscard();
    }

    /**
     *
     */
    public abstract void play();

    /**
     * @return hand
     */
    protected Hand getHand()
    {
        return aHand;
    }

    /**
     * @param pHand hand to set
     */
    protected void setHand(Hand pHand)
    {
        this.aHand = pHand;
    }

    /**
     * @return engine
     */
    public GameEngine getEngine()
    {
        return aEngine;
    }

    /**
     * @param pEngine engine to set
     */
    public void setEngine(GameEngine pEngine)
    {
        this.aEngine = pEngine;
    }

}
