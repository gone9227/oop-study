package com.oop;

import java.util.Random;

public class LottoMachine {
    private final Random random = new Random();

    public int takeOrder(String numberOfOrders) {
        int numberOfLotto;
        try {
            numberOfLotto = Integer.parseInt(numberOfOrders);
        } catch (NumberFormatException nfe) {
            System.out.println("숫자만 입력해주세요.");
            return -1;
        }

        if(numberOfLotto <= 0) {
            System.out.println(numberOfLotto + "개는 구매할 수 없습니다. 다시 입력해주세요.");
            return -1;
        }
        if(numberOfLotto > 20) {
            System.out.println("20개 초과로 구매할 수 없습니다. 다시 입력해주세요.");
            return -1;
        }
        return numberOfLotto;
    }

    public void processLottoNumbers(int numberOfLotto) {
        for (int i = 0; i < numberOfLotto; i++) {
            System.out.print(i + "번째 번호는 ?? ");
            LottoNumber lottoNumber = generateNumber();
            System.out.print(lottoNumber);
            printResult(matchNumber(lottoNumber));
        }
    }

    private void printResult(int rank) {
        if (rank == -1) {
            System.out.println("-> 당첨결과 꽝입니다. 다음을 기약해주세요.");
            return;
        }
        System.out.println("-> 당첨결과 " + rank + "등입니다. 축하드립니다.");
    }

    public boolean isValid(int numberOfLotto) {
        return numberOfLotto != -1;
    }

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
