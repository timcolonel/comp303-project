package ca.mcgill.cs.comp303.rummy.engine;

import ca.mcgill.cs.comp303.rummy.model.Card;

/**
 * Computer player
 * Created by tim on 14-10-29.
 */
public abstract class ComputerPlayer extends Player
{

    @Override
    public void play()
    {
        if (shouldRecycle())
        {
            recycle();
        }
        else
        {
            draw();
        }

        if (shouldKnock())
        {
            knock();
            discard(getBestDiscard());
        }
        else
        {
            discard(getBestDiscard());
        }
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
