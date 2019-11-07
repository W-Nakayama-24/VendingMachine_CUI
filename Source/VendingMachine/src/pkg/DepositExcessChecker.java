package pkg;

public class DepositExcessChecker {

    // 上限チェック
    public boolean checkDepositExcess(final int requestMoney, final int tmpDeposit) {
        final int maxOfDeposit = 9990; // 投入金額の上限
        int sumOfRequestAndTmp; // チェック用変数
        sumOfRequestAndTmp = requestMoney + tmpDeposit; // 希望金額と現在の投入金額を足してみる

        if (sumOfRequestAndTmp <= maxOfDeposit) {
            System.out.println("上限チェック OK");
            return true;
        } else {
            System.out.println("上限チェック NG");
        }
        return false;
    }
}