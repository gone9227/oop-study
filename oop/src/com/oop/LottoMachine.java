package com.oop;

import java.util.Random;

public class LottoMachine {
    private final Random random = new Random();

    public LottoNumber generateNumber() {
        LottoNumber lottoNumber = new LottoNumber();

        while (lottoNumber.getNumbers() == null || lottoNumber.getNumbers().size() < 6) {
            lottoNumber.addNumber(generateRandomNumber());
        }
        return lottoNumber;
    }

    public int generateBonusNumber(LottoNumber winningNumber) {
        int number;
        while (true) {
            number = generateRandomNumber();
            if (!existNumber(winningNumber, number)) return number;
        }
    }

    private int generateRandomNumber() {
        return random.nextInt(45) + 1;
    }

    private boolean existNumber(LottoNumber lottoNumber, int number) {
        return lottoNumber.getNumbers().contains(number);
    }

    public int matchNumber(LottoNumber lottoNumber) {
        WinningNumber winningNumber = WinningNumber.getInstance();
        LottoNumber winningLottoNumber = winningNumber.getLottoNumber();
        int bonusNumber = winningNumber.getBonusNumber();

        int matchNumber = 0;
        for (Integer number : winningLottoNumber.getNumbers()) {
            if (existNumber(lottoNumber, number))
                matchNumber += 1;
        }

        if (matchNumber == 6) {
            return 1;
        }

        if (matchNumber == 5) {
            if (existNumber(lottoNumber, bonusNumber)) {
                return 2;
            }
            return 3;
        }

        if (matchNumber == 4) {
            return 4;
        }

        if (matchNumber == 3) {
            return 5;
        }

        return -1;
    }
}
