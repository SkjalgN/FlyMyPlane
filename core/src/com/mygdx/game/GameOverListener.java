package com.mygdx.game;
import java.util.EventListener;

public interface GameOverListener extends EventListener {
    void onGameOver(GameOverEvent event);
}