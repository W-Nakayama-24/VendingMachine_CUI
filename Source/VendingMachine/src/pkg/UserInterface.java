package pkg;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInterface {

    int requestMoney = 0; // 希望金額
    final int maxOfDeposit = 9990; // 投入金額の上限
    
    VendingMachine vm = new VendingMachine();
    Scanner sc = new Scanner(System.in);

    // 初期画面を表示する
    public void display() {
        System.out.println("商品番号 / 商品名 / 単価 / 在庫");
        System.out.println();
        System.out.println("===商品準備中===");
        System.out.println();
        System.out.println("投入金額 [ " + vm.getTmpDeposit() + " ]円");
        System.out.println();
        System.out.println("機能を選択してください");
        System.out.println("[1]お金を投入する");
        System.out.println("[2]商品を購入する");
        System.out.println("[3]返金を受け取る");
        System.out.println("[9]システムを終了する");
        System.out.println();
    }

    // 機能を呼び出す
    public void callFunction() {
        System.out.print("input 機能番号>>");

        try {
            switch (sc.nextInt()) {
            case 1:
                System.out.println("---[1]お金を投入する---");
                System.out.println("1000,500,100,50,10のいずれかを入力してください");
                inputRequestMoney();
                break;
            case 2:
                System.out.println("---[2]商品を購入する機能 は準備中です---");
                break;
            case 3:
                System.out.println("---[3]返金を受け取る機能 は準備中です---");
                System.out.println("(現在、簡易版の機能を実装しています 11/06)");
                vm.receiveChange();
                break;
            case 9:
                System.out.println("---[9]システムを終了する機能 は準備中です---");
                System.out.println("(現在、簡易版の機能を実装しています 11/06)");
                if (vm.getTmpDeposit() == 0) {
                    System.out.println("自販機システムを終了します ありがとうございました");
                    vm.quitSystem();
                    break;
                } else {
                    System.out.println("ERROR_08 システムに投入金額が残っています");
                    System.out.println("[3]を入力すると投入金額を返却します");
                    break;
                }
            default:
                System.out.println("ERROR_00 正しく入力してください");
                System.out.println("(1,2,3,9のいずれかを入力して機能を選択します)");
                break;
            }
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            System.out.println("ERROR_00 正しく入力してください");
            System.out.println("(1,2,3,9のいずれかを入力して機能を選択します)");
        }
        sc.nextLine(); // 入力バッファのクリアー
        System.out.println();
        System.out.println("========================");
        System.out.println();
    }

    // 投入したい金額を入力する
    public void inputRequestMoney() {
        while (true) {
            System.out.print("input 希望金額>>");
            try {
                requestMoney = sc.nextInt();
                if (requestMoney = 1000 || requestMoney = 500 || requestMoney = 100 || requestMoney = 50
                        || requestMoney = 10) {
                    if (vm.insertMoney(requestMoney) == true) {
                        System.out.println("[ " + requestMoney + " ]円を投入しました");
                        break;
                    } else {
                        System.out.println("ERROR_02 投入できる金額の上限は"+ maxOfDeposit +"円です");
                        System.out.println("[ " + requestMoney + " ]円を返却しました");
                        break;
                    }
                } else {
                    System.out.println("ERROR_01:入力が正しくありません");
                    System.out.println("1000,500,100,50,10のいずれかを入力してください");
                    System.out.println();
                }
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                System.out.println("ERROR_01:入力が正しくありません");
                System.out.println("1000,500,100,50,10のいずれかを入力してください");
                sc.nextLine(); // バッファに入ったままの不正入力をクリアーする
            }
        }
    }
}