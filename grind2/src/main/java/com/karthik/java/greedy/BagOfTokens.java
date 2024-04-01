package com.karthik.java.greedy;

import java.util.Arrays;

public class BagOfTokens {
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int faceUpIndex = 0, faceDownIndex = tokens.length - 1;
        int score = 0;
        int maxScore = 0;
        while (faceUpIndex <= faceDownIndex) {
            if (power >= tokens[faceUpIndex]) {
                power -= tokens[faceUpIndex];
                score++;
                faceUpIndex++;
            } else if (score > 0) {
                score--;
                power += tokens[faceDownIndex];
                faceDownIndex--;
            } else
                break;
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }
}