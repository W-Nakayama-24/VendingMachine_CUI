package pkg;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author W-Nakayama
 *
 */
public class CheckerTest {

    /**
     * 希望金額が1000円,投入金額が0円のとき,加算後に上限の9990円を超えないのでtrueを返す
     */
    @Test
    public void successFromZero() {
        Checker dexchecker = new Checker();
        boolean checkResult = dexchecker.checkDepositExcess(1000, 0);
        assertThat(checkResult, is(true));
    }

    /**
     * 希望金額が1000円,投入金額が1000円のとき,加算後に上限の9990円を超えないのでtrueを返す
     */
    @Test
    public void successFromNotZero() {
        Checker dexchecker = new Checker();
        boolean checkResult = dexchecker.checkDepositExcess(1000, 1000);
        assertThat(checkResult, is(true));
    }

    /**
     * 希望金額が1円,投入金額が9989円のとき,加算後にちょうど上限の9990円となり,trueを返す
     */
    @Test
    public void depositExcess() {
        Checker dexchecker = new Checker();
        boolean checkResult = dexchecker.checkDepositExcess(1, 9990);
        assertThat(checkResult, is(false));
    }

    /**
     * 希望金額が1円,投入金額が9989円のとき,加算後にちょうど上限の9990円となり,trueを返す
     */
    @Test
    public void reachMaxDeposit() {
        Checker dexchecker = new Checker();
        boolean checkResult = dexchecker.checkDepositExcess(1, 9990 - 1);
        assertThat(checkResult, is(true));
    }

    /**
     * 指定した商品がおいしい水(water)で、投入金額が100円のとき 投入金額が単価と同じなので、チェッカーはtrueを返す
     */
    @Test

    public void isWaterAvairable() {
        Checker affchecker = new Checker();
        Storage storage = new Storage();
        Product product = storage.getProduct(1);
        boolean checkResult = affchecker.checkCanAfford(product, 100);
        assertThat(checkResult, is(true));
    }

    /**
     * 指定した商品がおいしい水(water)で、投入金額が99円のとき 投入金額が単価を下回るので、チェッカーはfalseを返す
     */
    @Test

    public void isNotWaterAvairable() {
        Checker affchecker = new Checker();
        Storage storage = new Storage();
        Product product = storage.getProduct(1);
        boolean checkResult = affchecker.checkCanAfford(product, 100 - 1);
        assertThat(checkResult, is(false));
    }

    @Test
    /**
     * 指定した商品がサイコソーダ(soda)で、投入金額が150円のとき 投入金額が単価と同じなので、チェッカーはtrueを返す
     */
    public void isSodaAvairable() {
        Checker affchecker = new Checker();
        Storage storage = new Storage();
        Product product = storage.getProduct(2);
        boolean checkResult = affchecker.checkCanAfford(product, 150);
        assertThat(checkResult, is(true));
    }

    @Test
    /**
     * 指定した商品がサイコソーダ(soda)で、投入金額が149円のとき 投入金額が単価を下回るので、チェッカーはfalseを返す
     */
    public void isNotSodaAvairable() {
        Checker affchecker = new Checker();
        Storage storage = new Storage();
        Product product = storage.getProduct(2);
        boolean checkResult = affchecker.checkCanAfford(product, 150 - 1);
        assertThat(checkResult, is(false));
    }

    /**
     * 指定した商品がミックスオレ(mix)で、投入金額が160円のとき 投入金額が単価と同じなので、チェッカーはtrueを返す
     */
    @Test
    public void isMixAulaitAvairable() {
        Checker affchecker = new Checker();
        Storage storage = new Storage();
        Product product = storage.getProduct(3);
        boolean checkResult = affchecker.checkCanAfford(product, 160);
        assertThat(checkResult, is(true));
    }

    /**
     * 指定した商品がミックスオレ(mix)で、投入金額が159円のとき 投入金額が単価を下回るので、チェッカーはfalseを返す
     */
    @Test
    public void isNotMixAulaitAvairable() {
        Checker affchecker = new Checker();
        Storage storage = new Storage();
        Product product = storage.getProduct(3);
        boolean checkResult = affchecker.checkCanAfford(product, 160 - 1);
        assertThat(checkResult, is(false));
    }

}
