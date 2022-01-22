package com.oop;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("몇 개를 구매하시겠습니까?");
            int numberOfLotto = lottoMachine.takeOrder(sc);
            if (!lottoMachine.isValid(numberOfLotto)) continue;

            System.out.println("로또 번호 생성중 ...");
            System.out.print("로또 당첨 번호입니다. ");
            System.out.println(WinningNumber.getInstance());
            lottoMachine.processLottoNumbers(numberOfLotto);
            break;
        }
    }
}
