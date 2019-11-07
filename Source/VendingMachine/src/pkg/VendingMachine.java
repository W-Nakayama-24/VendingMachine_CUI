package pkg;

public class VendingMachine {

    int tmpDeposit = 0; // 投入金額

    // 投入金額のgetter
    public int getTmpDeposit() {
        return tmpDeposit;
    }

    // お金を投入する
    public boolean insertMoney(int requestMoney) {
        boolean checkResult;
        DepositExcessChecker dexcheck = new DepositExcessChecker();
        checkResult = dexcheck.checkDepositExcess(requestMoney, tmpDeposit);

        if (checkResult == true) {
            tmpDeposit += requestMoney;
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
        if (tmpDeposit != 0) {
            System.out.println("[ " + tmpDeposit + " ]円を返却しました");
            tmpDeposit = 0;
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