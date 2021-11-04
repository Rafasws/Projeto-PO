package ggc.core;

import java.io.Serializable;

import ggc.app.exception.InvalidDateException;

public class Date implements Serializable{

    private int _date;

    public Date(){
        _date = 0;
    }


    public int getDate(){
        return _date;
    }

    public void setDate (int days) throws InvalidDateException{
        _date += days;
    }
}
