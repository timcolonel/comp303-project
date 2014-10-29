package ca.mcgill.cs.comp303.rummy.engine;

import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.Deck;

import java.util.ArrayDeque;
import java.util.Observable;
import java.util.Queue;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/**
 * Game engine
 * Created by tim on 14-10-20.
 */
public class GameEngine extends Observable
{
    private static final int MAX_PLAYER = 2;

    private Deck aDeck;
    private Stack<Card> aDiscardPile;
    private Queue<Player> aPlayers = new ArrayDeque<>();
    private boolean aRunning = false;

    /**
     * Add a player to the list. The number of players cannot overflow the MAX_PLAYER constant
     *
     * @param pPlayer Player to add
     * @return boolean if the player was added.
     */
    public boolean addPlayer(Player pPlayer)
    {
        if (aPlayers.size() < MAX_PLAYER)
        {
            aPlayers.add(pPlayer);
            return true;
        }
        return false;
    }

    /**
     * Move to the next player.
     *
     * @return the next player
     */
    public Player nextPlayer()
    {
        aPlayers.poll();
        return currentPlayer();
    }

    /**
     * @return current player
     */
    public Player currentPlayer()
    {
        return aPlayers.peek();
    }

    /**
     * @return a list of all the players.
     */
    public List<Player> getPlayers()
    {
        return new ArrayList<>(aPlayers);
    }

    /**
     * Return the winner.
     *
     * @return player winner
     */
    public Player newGame()
    {
        aDeck = new Deck();
        aDiscardPile = new Stack<>();
        aRunning = true;
        while (aRunning)
        {
            if (aDeck.size() == 2)
            {
                return null; //No winner
            }
            Player player = nextPlayer();
            player.giveCard(getCardByAction(player.draw()));
            Card discardedCard;
            try
            {
                discardedCard = player.play();
            }
            catch (KnockException e) //Current player knocked
            {
                discardedCard = e.getDiscardedCard();
                aRunning = false;
            }
            discard(discardedCard);
            player.discard(discardedCard);
        }
        Player bestPlayer = null;
        int bestScore = Integer.MAX_VALUE;
        boolean draw = false;
        for (Player player : aPlayers)
        {
            int score = player.getScore();
            if (score < bestScore)
            {
                bestPlayer = player;
                bestScore = score;
                draw = false;
            }
            else if (score == bestScore)
            {
                draw = true;
            }
        }
        if (draw)
        {
            return null;
        }
        else
        {
            return bestPlayer;

        }

    }

    /**
     * Get the card corresponding to the action
     *
     * @param pAction Action(Deck or discarded pile)
     * @return card draw
     */
    private Card getCardByAction(DrawAction pAction)
    {
        if (pAction == DrawAction.DECK)
        {
            return draw();
        }
        else
        {
            return recycle();
        }
    }

    /**
     * Draw a card from the deck.
     *
     * @return card got in the deck
     */
    private Card draw()
    {
        return aDeck.draw();
    }

    /**
     * @return the last discarded card(Don't remove just peek)
     */
    private Card getLastDiscardedCard()
    {
        return aDiscardPile.peek();
    }

    /**
     * Discard the given card.
     *
     * @param pCard card to discard
     */
    private void discard(Card pCard)
    {
        aDiscardPile.push(pCard);
    }

    /**
     * Remove the card on top of the discard pile and return it.
     *
     * @return the top card in the discard pile
     */
    private Card recycle()
    {
        return aDiscardPile.pop();
    }

    /**
     * Terminate the game.
     */
    public void knock()
    {
        aRunning = false;
    }

    public enum DrawAction
    {
        DECK, DISCARDED
    }

}
