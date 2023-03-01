package com.mygdx.game.View;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;
    
    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public State getCurrentState(){
        return states.peek();
    }
}
