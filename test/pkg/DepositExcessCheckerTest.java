package pkg;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class DepositExcessCheckerTest {

    @Test
    // 希望金額が1000円,投入金額が0円のとき,加算後に上限を超えないのでtrueを返す
    public void testCheckDepositExcess1() {
        DepositExcessChecker dexchecker = new DepositExcessChecker();
        boolean checkResult = dexchecker.checkDepositExcess(1000, 0);
        assertThat(checkResult, is(true));
    }

    @Test
    // 希望金額が1000円,投入金額が9000円のとき,加算後に上限を超えるのでfalseを返す
    public void testCheckDepositExcess2() {
        DepositExcessChecker dexchecker = new DepositExcessChecker();
        boolean checkResult = dexchecker.checkDepositExcess(1000, 9000);
        assertThat(checkResult, is(false));
    }

}
