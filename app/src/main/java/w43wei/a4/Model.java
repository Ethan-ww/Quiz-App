package w43wei.a4;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * MVC2 Model
 * <p>
 * Created by J. J. Hartmann on 11/19/2017.
 * Email: j3hartma@uwaterloo.ca
 * Copyright 2017
 */

class Model extends Observable
{
    // Create static instance of this mModel
    private static final Model ourInstance = new Model();
    static Model getInstance()
    {
        return ourInstance;
    }

    // Private Variables
    private int qNum;
    String name;
    char[][] answers;
    char[][] selections;

    /**
     * Model Constructor:
     * - Init member variables
     */
    Model() {
        qNum = 1;

        name="anonymous";

        answers=new char[5][2];
        answers[0][0]='a';
        answers[0][1]='x';
        answers[1][0]='a';
        answers[1][1]='c';
        answers[2][0]='c';
        answers[2][1]='x';
        answers[3][0]='d';
        answers[3][1]='x';
        answers[4][0]='c';
        answers[4][1]='d';

        selections=new char[5][2];
        for(int x=0;x<5;x++){
            selections[x][0]='x';
            selections[x][1]='x';
        }
    }

    public int calScore(){
        int score=0;
        for(int x=0;x<5;x++){
            if(selections[x][0]==answers[x][0]&&
                    selections[x][1]==answers[x][1]){
                score++;
            }
        }
        return score;
    }

    public void reset(){
        qNum=1;
        for(int x=0;x<5;x++){
            selections[x][0]='x';
            selections[x][1]='x';
        }
        name="anonymous";

        notifyObservers();

    }

    /**
     * Get mCounter Values
     * @return Current value mCounter
     */
    public int getQuestionNum()
    {
        return qNum;
    }

    /**
     * Set mCounter Value
     * @param i
     * -- Value to set Counter
     */
    public void setQuestionNum(int i)
    {
        Log.d("DEMO", "Model: set qNum to " + i);
        this.qNum = i;
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Observable Methods
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Helper method to make it easier to initialize all observers
     */
    public void initObservers()
    {
        setChanged();
        notifyObservers();
    }

    /**
     * Deletes an observer from the set of observers of this object.
     * Passing <CODE>null</CODE> to this method will have no effect.
     *
     * @param o the observer to be deleted.
     */
    @Override
    public synchronized void deleteObserver(Observer o)
    {
        super.deleteObserver(o);
    }

    /**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     * The order in which notifications will be delivered to multiple
     * observers is not specified. See the class comment.
     *
     * @param o an observer to be added.
     * @throws NullPointerException if the parameter o is null.
     */
    @Override
    public synchronized void addObserver(Observer o)
    {
        super.addObserver(o);
    }

    /**
     * Clears the observer list so that this object no longer has any observers.
     */
    @Override
    public synchronized void deleteObservers()
    {
        super.deleteObservers();
    }

    /**
     * If this object has changed, as indicated by the
     * <code>hasChanged</code> method, then notify all of its observers
     * and then call the <code>clearChanged</code> method to
     * indicate that this object has no longer changed.
     * <p>
     * Each observer has its <code>update</code> method called with two
     * arguments: this observable object and <code>null</code>. In other
     * words, this method is equivalent to:
     * <blockquote><tt>
     * notifyObservers(null)</tt></blockquote>
     *
     * @see Observable#clearChanged()
     * @see Observable#hasChanged()
     * @see Observer#update(Observable, Object)
     */
    @Override
    public void notifyObservers()
    {
        super.notifyObservers();
    }
}