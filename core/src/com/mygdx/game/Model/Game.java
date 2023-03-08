package com.mygdx.game.Model;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private Map map;
    private List<Package> packages = new ArrayList<Package>();
    private Plane plane;

    public Game(Map map, List<Package> packages, Plane plane) {
        this.map = map;
        this.packages = packages;
        this.plane = plane;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void addPackage(Package pkg) {
        this.packages.add(pkg);
    }
}
