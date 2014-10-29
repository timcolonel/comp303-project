package ca.mcgill.cs.comp303.rummy.engine;

import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.Hand;

/**
 * Player class
 * Created by tim on 14-10-20.
 */
public abstract class Player
{

    protected static final double KNOCK_MAX = 10;

    private Hand aHand;
    private GameEngine aEngine;

    /**
     *
     */
    public void knock()
    {

    }

    /**
     * Give card to the player.
     *
     * @param pCard Card to give
     */
    public void giveCard(Card pCard)
    {
        aHand.add(pCard);
    }

    /**
     * Remove the given card from the player.
     *
     * @param pCard card to discard
     */
    public void discard(Card pCard)
    {
        aHand.remove(pCard);
    }

    /**
     * @return Check if the player can drawFromDeck a new card
     */
    public boolean canDrawCard()
    {
        return !aHand.needToDiscard();
    }

    /**
     *
     */
    public abstract GameEngine.DrawAction draw();

    /**
     * @return the discarded card
     * @throws KnockException Throw a Knock exception if the player decide to knock.
     */
    public abstract Card play() throws KnockException;

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

    /**
     * Return current player score with the hand he has.
     *
     * @return score
     */
    public int getScore()
    {
        Hand clone = getHand();
        clone.autoMatch();
        return clone.score();
    }
}
