package com.oop;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("몇 개를 구매하시겠습니까?");
            int numberOfLotto = takeOrder(sc);
            if (!isValid(numberOfLotto)) continue;

            System.out.println("로또 번호 생성중 ...");
            System.out.print("로또 당첨 번호입니다. ");
            System.out.println(WinningNumber.getInstance());
            generateLottoNumbers(numberOfLotto);
            break;
        }
    }

    private static void generateLottoNumbers(int numberOfLotto) {
        LottoMachine lottoMachine = new LottoMachine();

        for (int i = 0; i < numberOfLotto; i++) {
            System.out.print(i + "번째 번호는 ?? ");
            LottoNumber lottoNumber = lottoMachine.generateNumber();
            System.out.print(lottoNumber);
            printResult(lottoMachine.matchNumber(lottoNumber));
        }
    }

    private static void printResult(int rank) {
        if (rank == -1) {
            System.out.println("-> 당첨결과 꽝입니다. 다음을 기약해주세요.");
            return;
        }
        System.out.println("-> 당첨결과 " + rank + "등입니다. 축하드립니다.");
    }

    private static boolean isValid(int numberOfLotto) {
        return numberOfLotto != -1;
    }

    private static int takeOrder(Scanner sc) {
        int numberOfLotto;
        try {
            numberOfLotto = Integer.parseInt(sc.nextLine());
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
}
