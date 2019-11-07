package pkg;

public class MaxDepositChecker {

    // 上限チェック
    public boolean maxDepositCheck(int requestMoney, int deposit) {
        int variableForCheck; // チェック用変数
        variableForCheck = requestMoney + deposit; // 希望金額と現在の投入金額を足してみる

        if (variableForCheck <= 9990) {
            System.out.println("上限チェック OK");
            // チェックが済んだらチェック用変数を初期化
            variableForCheck = 0;
            return true;
        } else if (variableForCheck > 9990) {
            System.out.println("上限チェック NG");
            // チェックが済んだらチェック用変数を初期化
            variableForCheck = 0;
        }
        return false;
    }
}