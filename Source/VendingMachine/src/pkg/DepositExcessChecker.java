package pkg;

public class DepositExcessChecker {

    public static final int maxOfDeposit = 9990; // 投入金額の上限
    // 上限チェック
    public boolean checkDepositExcess(final int requestMoney, final int Deposit) {
        int sumOfReqAndDeposit; // チェック用変数
        sumOfReqAndDeposit = requestMoney + Deposit; // 希望金額と現在の投入金額を足してみる

        if (sumOfReqAndDeposit <= maxOfDeposit) {
            System.out.println("上限チェック OK");
            return true;
        } else {
            System.out.println("上限チェック NG");
        }
        return false;
    }
}