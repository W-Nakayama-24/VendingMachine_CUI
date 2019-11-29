package pkg;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {

    int requestMoney = 0; // 希望金額
    int requestProductID = 0; // 商品番号

    VendingMachine vm = new VendingMachine();

    Scanner sc = new Scanner(System.in);

    // 商品補充に使うメソッドを使い回して、サンプル商品と在庫数を用意する
    // 最初に、サンプル3商品の商品情報と在庫数をセット(ここは繰り返し実施しないので、コンストラクタを使用)
    UserInterface() {
        Product water = new Product(1, "おいしい水", 100);
        Product soda = new Product(2, "サイコソーダ", 150);
        Product mix = new Product(3, "ミックスオレ", 160);
        vm.addProductInfo(water.getProductID(), water);
        vm.addProductInfo(soda.getProductID(), soda);
        vm.addProductInfo(mix.getProductID(), mix);

        vm.chargeStock(water.getProductID(), 12);
        vm.chargeStock(soda.getProductID(), 5);
        vm.chargeStock(mix.getProductID(), 1);
    }

    // 初期画面を表示する
    public void display() throws WrongProductIdException {
        Product water = vm.getProduct(1);
        int waterStock = vm.getStock(1);

        Product soda = vm.getProduct(2);
        int sodaStock = vm.getStock(2);

        Product mix = vm.getProduct(3);
        int mixStock = vm.getStock(3);

        System.out.println("商品番号 / 商品名 / 単価 / 在庫");
        System.out.println();
        System.out.println(water.getProductID() + " / " + water.getName() + " / " + water.getPrice() + "円 / 残り"
                + waterStock + "本");
        System.out.println(
                soda.getProductID() + " / " + soda.getName() + " / " + soda.getPrice() + "円 / 残り" + sodaStock + "本");
        System.out.println(
                mix.getProductID() + " / " + mix.getName() + " / " + mix.getPrice() + "円 / 残り" + mixStock + "本");
        System.out.println();
        System.out.println("投入金額 [ " + vm.getDeposit() + " ]円");
        System.out.println();
        System.out.println("機能を選択してください");
        System.out.println("[1]お金を投入する");
        System.out.println("[2]商品を購入する");
        System.out.println("[3]返金を受け取る");
        System.out.println("[9]システムを終了する");
        System.out.println();

    }

    // 機能を呼び出す
    public void callFunction() throws WrongProductIdException {
        System.out.print("input 機能番号>>");

        try {
            switch (sc.nextInt()) {
            case 1:
                System.out.println("---[1]お金を投入する---");
                System.out.println("1000,500,100,50,10のいずれかを入力してください");
                inputRequestMoney();
                break;
            case 2:
                boolean checkAllStockResult = false;
                for (int stock : vm.storage.stockMap.values()) {
                    if (stock != 0) {
                        checkAllStockResult = true; // 在庫数が1以上の商品が1つでもあれば、trueに切り替える
                    }
                }
                if (checkAllStockResult == true) {
                    Product water = vm.getProduct(1);
                    Product soda = vm.getProduct(2);
                    Product mix = vm.getProduct(3);

                    System.out.println("---[2]商品を購入する---");
                    System.out.println("商品番号を入力してください");
                    System.out.println(water.getProductID() + " / " + water.getName() + " / " + water.getPrice() + "円");
                    System.out.println(soda.getProductID() + " / " + soda.getName() + " / " + soda.getPrice() + "円");
                    System.out.println(mix.getProductID() + " / " + mix.getName() + " / " + mix.getPrice() + "円");

                    inputProductNum();
                    break;
                } else {
                    System.out.println("ERROR_03:すべての商品が売り切れています");
                    break;
                }
            case 3:
                System.out.println("---[3]返金を受け取る機能 は準備中です---");
                System.out.println("(現在、簡易版の機能を実装しています 11/06)");
                int change = vm.receiveChange();
                if (change != 0) {
                    System.out.println("[" + change + " ]円を返却しました");
                } else {
                    System.out.println("ERROR_07:返却できるお金がありません");
                    System.out.println("お金を追加してください(1000, 500, 100, 50, 10)");
                }
                break;
            case 9:
                System.out.println("---[9]システムを終了する機能 は準備中です---");
                System.out.println("(現在、簡易版の機能を実装しています 11/06)");
                if (vm.getDeposit() == 0) {
                    System.out.println("自販機システムを終了します ありがとうございました");
                    vm.quitSystem();
                    break;
                } else {
                    System.out.println("ERROR_08:システムに投入金額が残っています");
                    System.out.println("[3]を入力すると投入金額を返却します");
                    break;
                }
            default:
                System.out.println("ERROR_00:正しく入力してください");
                System.out.println("(1,2,3,9のいずれかを入力して機能を選択します)");
                break;
            }
        } catch (InputMismatchException ex) {
            ex.printStackTrace();
            System.out.println("ERROR_00:正しく入力してください");
            System.out.println("(1,2,3,9のいずれかを入力して機能を選択します)");
            sc.nextLine(); // バッファに入ったままの不正入力をクリアーする
        }
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
                if (requestMoney == 1000 || requestMoney == 500 || requestMoney == 100 || requestMoney == 50
                        || requestMoney == 10) {
                    if (vm.insertMoney(requestMoney) == true) {
                        System.out.println("[ " + requestMoney + " ]円を投入しました");
                        break;
                    } else {
                        System.out.println("ERROR_02:投入できる金額の上限は" + Checker.MAX_DEPOSIT + "円です");
                        System.out.println("[ " + requestMoney + " ]円を返却しました");
                        break;
                    }
                } else {
                    System.out.println("ERROR_01:入力が正しくありません");
                    System.out.println("1000,500,100,50,10のいずれかを入力してください");
                    System.out.println();
                }
            } catch (InputMismatchException ex) {
                ex.printStackTrace();
                System.out.println("ERROR_01:入力が正しくありません");
                System.out.println("1000,500,100,50,10のいずれかを入力してください");
                sc.nextLine(); // バッファに入ったままの不正入力をクリアーする
            }
        }
    }

    // 購入したい商品の番号を入力する
    public void inputProductNum() throws WrongProductIdException {
        System.out.print("input 購入したい商品の番号>>");
        try {
            requestProductID = sc.nextInt();
            if (vm.storage.productInfoMap.containsKey(requestProductID) == true) {
                switch (vm.buyProduct(requestProductID)) {
                case SUCCESS:
                    System.out.println("---お買い上げありがとうございます---");
                    System.out.println(vm.storage.getProduct(requestProductID).getName() + " を購入しました");
                    break;
                case ERROR_NOT_ENOUGH_MONEY:
                    System.out.println("ERROR_05:投入金額が不足しています");
                    System.out.println("お金を投入してください");
                    break;
                case ERROR_ZERO_STOCK:
                    System.out.println("ERROR_06:ご指定の商品は売り切れています");
                    break;
                }
            } else {
                System.out.println("ERROR_04:存在しない商品番号です");
                System.out.println("正しく入力してください");
                System.out.println();
            }

        } catch (InputMismatchException ex) {
            ex.printStackTrace();
            System.out.println("ERROR_04:存在しない商品番号です");
            System.out.println("正しく入力してください");
            sc.nextLine(); // バッファに入ったままの不正入力をクリアーする
        }
    }

}