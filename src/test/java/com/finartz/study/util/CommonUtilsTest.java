package com.finartz.study.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class CommonUtilsTest {

    @Test
    void whenGive16DigitNumber_thenReturnMaskedNumber() {
        String cardNumber = "0000111122223333";
        String maskedNumber = CommonUtils.maskCreditCardNumber(cardNumber);

        assertEquals("0000********3333", maskedNumber);
    }

    @Test
    void whenGive16DigitNumberWithDash_thenReturnMaskedNumber() {
        String cardNumber = "0000-1111-2222-3333";
        String maskedNumber = CommonUtils.maskCreditCardNumber(cardNumber);

        assertEquals("0000********3333", maskedNumber);
    }

    @Test
    void whenGive16DigitNumberWithRandomCharacters_thenReturnMaskedNumber() {
        String cardNumber = "0*,0?0!0-1%1+1'1-2222-3333";
        String maskedNumber = CommonUtils.maskCreditCardNumber(cardNumber);

        assertEquals("0000********3333", maskedNumber);
    }

    @Test
    void whenGiveMoreThan16_thenThrowsException() {
        String cardNumber = "0000-1111-2222-3333-4444";

        Assertions.assertThrows(InvalidParameterException.class, () -> {
            CommonUtils.maskCreditCardNumber(cardNumber);
        });
    }

    @Test
    void whenGiveLessThan16_thenThrowsException() {
        String cardNumber = "0000-1111";

        Assertions.assertThrows(InvalidParameterException.class, () -> {
            CommonUtils.maskCreditCardNumber(cardNumber);
        });
    }

    @Test
    void whenGiveEmptyInput_thenThrowsException() {
        String cardNumber = "";

        Assertions.assertThrows(InvalidParameterException.class, () -> {
            CommonUtils.maskCreditCardNumber(cardNumber);
        });
    }

    @Test
    void whenGiveNullInput_thenThrowsException() {
        String cardNumber = null;

        Assertions.assertThrows(InvalidParameterException.class, () -> {
            CommonUtils.maskCreditCardNumber(cardNumber);
        });
    }
}