package pkg;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VendingMachineTest {

    @Test
    // getterのテスト 最初の投入金額0円を取得する
    public void testGetDeposit() {
        VendingMachine vm = new VendingMachine();
        int getterTestResult = vm.getDeposit();
        assertThat(getterTestResult, is(0));
    }

    @Test
    // お金投入のテスト 希望金額1000円で上限チェックがOKのとき、trueを返す
    public void testInsertMoney1() {
        VendingMachine vm = new VendingMachine();
        boolean insertResult = vm.insertMoney(1000);
        int getterTestResult = vm.getDeposit();
        assertThat(insertResult, is(true));
        assertThat(getterTestResult, is(1000));
    }

    @Test
    // お金投入のテスト 希望金額9999円で上限チェックがNGのとき、falseを返す
    public void testInsertMoney2() {
        VendingMachine vm = new VendingMachine();
        boolean insertResult = vm.insertMoney(9999);
        assertThat(insertResult, is(false));
    }

}
