package pkg;

public class Checker {

    public static final int maxOfDeposit = 9990; // 投入金額の上限
    // 上限チェック

    public boolean checkDepositExcess(final int requestMoney, final int deposit) {
        int sumOfReqAndDeposit; // チェック用変数
        sumOfReqAndDeposit = requestMoney + deposit; // 希望金額と現在の投入金額を足してみる

        if (sumOfReqAndDeposit <= maxOfDeposit) {
            System.out.println("上限チェック OK");
            return true;
        } else {
            System.out.println("上限チェック NG");
        }
        return false;
    }

    public boolean checkCanAfford(final Product product, final int deposit) {
        if (product.getPrice() <= deposit) {
            System.out.println("checkCanAfford : 購入可能です");
            return true;
        } else {
            System.out.println("checkCanAfford : 投入金額が足りていません");
        }
        return false;
    }
}