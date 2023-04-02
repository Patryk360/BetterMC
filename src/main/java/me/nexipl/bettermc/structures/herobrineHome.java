package me.nexipl.bettermc.structures;

public class herobrineHome {
    private final int[][] array = {
            {0,0,0},{0,0,1},{0,0,2},{0,0,3},{0,0,4},{1,0,4},{2,0,4},{3,0,4},{3,0,3},{3,0,2},{3,0,1},{3,0,0},
            {0,1,0},{0,1,1},{0,1,2},{0,1,3},{0,1,4},{1,1,4},{2,1,4},{3,1,4},{3,1,3},{3,1,2},{3,1,1},{3,1,0},
    };
    public int[][] getHerobrineHome() {
        return array.clone();
    }
}