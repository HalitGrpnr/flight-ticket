package com.finartz.study.util;

import lombok.experimental.UtilityClass;

import java.security.InvalidParameterException;

@UtilityClass
public class CommonUtils {
    public String maskCreditCardNumber(String cardNumber) throws InvalidParameterException {

        if (cardNumber == null || cardNumber.isEmpty()){
            throw new InvalidParameterException("Credit card number should be include 16 digit number");
        }

        cardNumber = cardNumber.replaceAll("\\D+","");

        if (cardNumber.length() != 16){
            throw new InvalidParameterException("Credit card number should be include 16 digit number");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cardNumber.substring(0, 4));
        for (int i=0; i<cardNumber.length()-8; i++){
            sb.append("*");
        }
        sb.append(cardNumber.substring(cardNumber.length()-4, cardNumber.length()));

        return sb.toString();
    }
}
