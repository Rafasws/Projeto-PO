package ggc.core;

import java.io.Serializable;

import ggc.app.exception.DuplicatePartnerKeyException;

public class Partner implements Serializable{

    private String _id;

    private String _name;

    private String _address;

    private int _points;

    private Status _status;

    private int _purchases;

    private int _totalSales;

    private int _paidSales;

    public Partner(String id, String name, String morada) throws DuplicatePartnerKeyException{
        _id = id;
        _name = name;
        _address = morada; 
        _points = 0;
        _status = Status.NORMAL;
        _purchases = 0;
        _totalSales = 0;
        _paidSales =  0;

    }

    public String getId() {
        return _id;
    }

    public String getName() {
        return _name; 
    }

    public String getAddress() {
        return _address;
    }

    public int getPoints() {
        return _points;
    }

    public void setpoints() {

        this.setStatus();
    }

    public Status getStatus(){
        return _status;
    }

    public void setStatus(){
        if (_points < 2000){
            _status = Status.NORMAL;
        }
        else if(2000 < _points  && _points< 2500){
            _status = Status.SELECTION;
        }
        else if(_points > 2500){
            _status = Status.ELITE;
        }
    }

    @Override
    public String toString() {
        return _id + "|" + _name + "|" + _address + "|" + _status + "|" + _points + "|" + _purchases + "|" + _totalSales + "|" + _paidSales + "\n";
    }
}
