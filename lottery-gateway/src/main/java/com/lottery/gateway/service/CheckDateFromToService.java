package com.lottery.gateway.service;

import com.lottery.gateway.exceptions.InvalidDateException;

import java.text.ParseException;

/**
 * Dátum validáló interfész
 */
public interface CheckDateFromToService {

    /**
     * @param from         dátum intervallum kezdete
     * @param to           dátum intervallum vége
     * @param access_token JWT access token
     * @return boolean érték
     */
    boolean checkFromTo(String from, String to, String access_token) throws ParseException, InvalidDateException;
}
