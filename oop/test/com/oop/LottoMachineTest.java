package com.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class LottoMachineTest {

    private final LottoMachine lottoMachine = new LottoMachine();

    @Test
    void tooManyOrders() {
        int result = lottoMachine.takeOrder("50");
        assertSame(-1, result);
    }

    @Test
    void invalidOrders_notANumber() {
        int result = lottoMachine.takeOrder("aa");
        assertSame(-1, result);
    }

    @Test
    void invalidOrders_minusNumber() {
        int result = lottoMachine.takeOrder("-10");
        assertSame(-1, result);
    }

    @Test
    void invalidOrders_zero() {
        int result = lottoMachine.takeOrder("0");
        assertSame(-1, result);
    }

    @Test
    void normalOrders() {
        int result = lottoMachine.takeOrder("20");
        assertSame(20, result);
    }

    @Test
    void generateBaseNumbers() {
        LottoNumber lottoNumber = lottoMachine.generateNumber();
        System.out.println("lottoNumber.getNumbers() = " + lottoNumber.getNumbers());
        assertSame(6, lottoNumber.getNumbers().size());
    }

    @Test
    void generateBonusNumber() {
        LottoNumber baseNumbers = lottoMachine.generateNumber();
        int bonusNumber = lottoMachine.generateBonusNumber(baseNumbers);
        System.out.println("baseNumbers = " + baseNumbers.getNumbers());
        System.out.println("bonusNumber = " + bonusNumber);
        assertFalse(baseNumbers.getNumbers().contains(bonusNumber));
    }

    @Test
    void isFirstPlace() {
        LottoNumber winningBaseNumber = WinningNumber.getInstance().getLottoNumber();
        System.out.println("winningBaseNumber = " + winningBaseNumber.getNumbers());
        int result = lottoMachine.matchNumber(winningBaseNumber);
        assertSame(1, result);
    }

    @Test
    void isSecondPlace() {
        WinningNumber winningNumber = WinningNumber.getInstance();
        System.out.println("winningNumber = " + winningNumber);

        LottoNumber lottoNumber = new LottoNumber();
        for (Integer number : winningNumber.getLottoNumber().getNumbers()) {
            lottoNumber.addNumber(number);
            if (lottoNumber.getNumbers().size() == 5) break;
        }
        lottoNumber.addNumber(winningNumber.getBonusNumber());
        System.out.println("lottoNumber = " + lottoNumber);

        int result = lottoMachine.matchNumber(lottoNumber);
        assertSame(2, result);
    }

    @Test
    void isThirdPlace() {
        WinningNumber winningNumber = WinningNumber.getInstance();
        System.out.println("winningNumber = " + winningNumber);

        LottoNumber lottoNumber = new LottoNumber();
        for (Integer number : winningNumber.getLottoNumber().getNumbers()) {
            lottoNumber.addNumber(number);
            if (lottoNumber.getNumbers().size() == 5) break;
        }
        lottoNumber.addNumber(winningNumber.getBonusNumber() + 1);
        System.out.println("lottoNumber = " + lottoNumber);

        int result = lottoMachine.matchNumber(lottoNumber);
        assertSame(3, result);
    }

    @Test
    void isFourthPlace() {
        WinningNumber winningNumber = WinningNumber.getInstance();
        System.out.println("winningNumber = " + winningNumber);

        LottoNumber lottoNumber = new LottoNumber();
        for (Integer number : winningNumber.getLottoNumber().getNumbers()) {
            if (lottoNumber.getNumbers() == null || lottoNumber.getNumbers().size() < 4) {
                lottoNumber.addNumber(number);
            } else {
                lottoNumber.addNumber(number + 45);
            }
        }
        System.out.println("lottoNumber = " + lottoNumber);

        int result = lottoMachine.matchNumber(lottoNumber);
        assertSame(4, result);
    }

    @Test
    void isFifthPlace() {
        WinningNumber winningNumber = WinningNumber.getInstance();
        System.out.println("winningNumber = " + winningNumber);

        LottoNumber lottoNumber = new LottoNumber();
        for (Integer number : winningNumber.getLottoNumber().getNumbers()) {
            if (lottoNumber.getNumbers() == null || lottoNumber.getNumbers().size() < 3) {
                lottoNumber.addNumber(number);
            } else {
                lottoNumber.addNumber(number + 45);
            }
        }
        System.out.println("lottoNumber = " + lottoNumber);

        int result = lottoMachine.matchNumber(lottoNumber);
        assertSame(5, result);
    }

    @Test
    void notWin() {
        WinningNumber winningNumber = WinningNumber.getInstance();
        System.out.println("winningNumber = " + winningNumber);

        LottoNumber lottoNumber = new LottoNumber();
        for (Integer number : winningNumber.getLottoNumber().getNumbers()) {
            lottoNumber.addNumber(number + 45);
        }
        System.out.println("lottoNumber = " + lottoNumber);

        int result = lottoMachine.matchNumber(lottoNumber);
        assertSame(-1, result);
    }

}