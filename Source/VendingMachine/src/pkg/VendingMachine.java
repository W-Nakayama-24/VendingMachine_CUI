package pkg;

public class VendingMachine {

    int deposit = 0; // 投入金額

    // 投入金額のgetter
    public int getDeposit() {
        return deposit;
    }

    // お金を投入する
    public boolean insertMoney(int requestMoney) {
        boolean checkResult;
        MaxDepositChecker mdc = new MaxDepositChecker();
        checkResult = mdc.maxDepositCheck(requestMoney, deposit);

        if (checkResult == true) {
            deposit += requestMoney;
            return true;
        } else {
            return false;
        }
    }

    // 返金を受け取る(簡易版実装 11/06)
    // 本来は戻り値がint
    // おつり用の変数が必要になる可能性??
    // メッセージの出力は本来UIの仕事になるか
    public void receiveChange() {
        if (deposit != 0) {
            System.out.println("[ " + deposit + " ]円を返却しました");
            deposit = 0;
        } else {
            System.out.println("ERROR_07 返却出来るお金がありません");
            System.out.println("お金を追加してください(1000, 500, 100, 50, 10)");
        }
    }

    // 自販機システムを終了する(簡易版実装 11/06)
    public void quitSystem() {
        // こちらからmainメソッドのflagを1にするには? → 戻り値が必要
        // スキャナーのcloseも実施していないままである
        System.exit(0);
    }
}