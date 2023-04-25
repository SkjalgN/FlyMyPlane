package com.mygdx.game.Model;

public class Score implements Comparable<Score> {

    private int score;
    private String name;

    public Score(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public Score() {
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Score o) {
        if (score < o.score) {
            return -1;
        } else if (score > o.score) {
            return 1;
        } else {
            return name.compareTo(o.name);
        }
    }
}
