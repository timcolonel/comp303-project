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
    private Queue<Player> aPlayers = new ArrayDeque<Player>();

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
        return new ArrayList<Player>(aPlayers);
    }

    /**
     *
     */
    public void newGame()
    {
        aDeck = new Deck();
        aDiscardPile = new Stack<Card>();
    }

    /**
     * Draw a card from the deck.
     *
     * @return card got in the deck
     */
    public Card draw()
    {
        return aDeck.draw();
    }

    /**
     * @return the last discarded card(Don't remove just peek)
     */
    public Card getLastDiscardedCard()
    {
        return aDiscardPile.peek();
    }

    /**
     * Discard the given card.
     *
     * @param pCard card to discard
     */
    public void discard(Card pCard)
    {
        aDiscardPile.push(pCard);
    }

    /**
     * Remove the card on top of the discard pile and return it.
     *
     * @return the top card in the discard pile
     */
    public Card recycle()
    {
        return aDiscardPile.pop();
    }

    /**
     *
     */
    public boolean isGameOver()
    {
        return false;
    }

    /**
     *
     */
    public Card topOfStack()
    {
        return null;
    }

    /**
     * @return Human player
     */
    public Player getHuman()
    {
        return null;
    }

    /**
     * @return Computer player
     */
    public Player getComputer()
    {
        return null;
    }

}
