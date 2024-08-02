package com.bmc.exception;

public class MovieAlreadyPresentWithSameNameAndLanguage extends Exception{
    public MovieAlreadyPresentWithSameNameAndLanguage(String s){
        super(s);
    }
}
