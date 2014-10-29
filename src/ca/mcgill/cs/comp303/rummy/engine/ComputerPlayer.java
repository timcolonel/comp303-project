package ca.mcgill.cs.comp303.rummy.engine;

import ca.mcgill.cs.comp303.rummy.model.Card;

/**
 * Computer player
 * Created by tim on 14-10-29.
 */
public abstract class ComputerPlayer extends Player
{
    @Override
    public GameEngine.DrawAction draw()
    {
        if (shouldRecycle())
        {
            return GameEngine.DrawAction.DISCARDED;
        }
        else
        {
            return GameEngine.DrawAction.DECK;
        }
    }

    @Override
    public Card play() throws KnockException
    {
        if (shouldKnock())
        {
            throw new KnockException(getBestDiscard());
        }
        return getBestDiscard();
    }

    /**
     * @return boolean if the computer player should get a card from the discard pile
     */
    protected abstract boolean shouldRecycle();

    /**
     * @return boolean if the computer player should knock
     */
    protected abstract boolean shouldKnock();

    /**
     * @return the best card to discard
     */
    protected abstract Card getBestDiscard();

}
