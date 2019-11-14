package pkg;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VendingMachineTest {

    @Test
    // getterのテスト 最初の投入金額0円を取得する
    public void getDeposit() {
        VendingMachine vm = new VendingMachine();
        int getterTestResult = vm.getDeposit();
        assertThat(getterTestResult, is(0));
    }

    @Test
    // お金投入のテスト 希望金額1000円で上限チェックがOKのとき、trueを返す
    // 投入金額が希望金額の分だけ増える
    public void insertSuccess() {
        VendingMachine vm = new VendingMachine();
        int getterTestResult = 0;
        int requestMoney = 1000;

        boolean insertResult = vm.insertMoney(requestMoney);
        assertThat(insertResult, is(true));

        getterTestResult = vm.getDeposit();
        assertThat(getterTestResult, is(requestMoney));
    }

    @Test
    // お金投入のテスト 希望金額9991円で上限チェックがNGのとき、falseを返す
    // 投入金額が変化していない
    public void insertFailure() {
        VendingMachine vm = new VendingMachine();
        int getResultBefore = 0;
        int getResultAfter = 0;
        int requestMoney = 9990 + 1;

        getResultBefore = vm.getDeposit();

        boolean insertResult = vm.insertMoney(requestMoney);
        assertThat(insertResult, is(false));

        getResultAfter = vm.getDeposit();

        assertThat(getResultAfter, is(getResultBefore));
    }

}
