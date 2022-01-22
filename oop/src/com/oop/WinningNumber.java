package com.oop;

public class WinningNumber {
    private static LottoNumber winningNumber;
    private static int bonusNumber;

    private static final WinningNumber instance = new WinningNumber(new LottoMachine());
    public static WinningNumber getInstance() {
        return instance;
    }

    private WinningNumber(LottoMachine lottoMachine) {
        winningNumber = lottoMachine.generateNumber();
        bonusNumber = lottoMachine.generateBonusNumber(winningNumber);
    }

    public LottoNumber getLottoNumber() {
        return winningNumber;
    }
    public int getBonusNumber() { return bonusNumber; }

    @Override
    public String toString() {
        return winningNumber + "(보너스 " + bonusNumber + ")";
    }
}
