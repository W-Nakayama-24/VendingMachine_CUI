package pkg;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class DepositExcessCheckerTest {

    @Test
    // 希望金額が1000円,投入金額が0円のとき,加算後に上限の9990円を超えないのでtrueを返す
    public void SuccessFromZero() {
        DepositExcessChecker dexchecker = new DepositExcessChecker();
        boolean checkResult = dexchecker.checkDepositExcess(1000, 0);
        assertThat(checkResult, is(true));
    }

    @Test
    // 希望金額が1000円,投入金額が1000円のとき,加算後に上限の9990円を超えないのでtrueを返す
    public void SuccessFromNotZero() {
        DepositExcessChecker dexchecker = new DepositExcessChecker();
        boolean checkResult = dexchecker.checkDepositExcess(1000, 1000);
        assertThat(checkResult, is(true));
    }

    @Test
    // 希望金額が1円,投入金額が9990円のとき,加算後に上限の9990円を超えるのでfalseを返す
    public void DepositExcess() {
        DepositExcessChecker dexchecker = new DepositExcessChecker();
        boolean checkResult = dexchecker.checkDepositExcess(1, 9990);
        assertThat(checkResult, is(false));
    }

    @Test
    // 希望金額が1円,投入金額が9989円のとき,加算後にちょうど上限の9990円となり,trueを返す
    public void ReachMaxDeposit() {
        DepositExcessChecker dexchecker = new DepositExcessChecker();
        boolean checkResult = dexchecker.checkDepositExcess(1, 9989);
        assertThat(checkResult, is(true));
    }

}
