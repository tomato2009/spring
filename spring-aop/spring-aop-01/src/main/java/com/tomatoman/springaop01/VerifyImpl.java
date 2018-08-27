package com.tomatoman.springaop01;

public class VerifyImpl implements Verify{

    @Override
    public boolean verify(int b) {
        return b != 0;
    }
}
