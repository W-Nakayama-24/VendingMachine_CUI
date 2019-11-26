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
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test

    public void isWaterAvairable() throws WrongProductNumberException {
        Checker affchecker = new Checker();
        Product water = new Product(1, "おいしい水", 100);
        boolean checkResult = affchecker.checkCanAfford(water, 100);
        assertThat(checkResult, is(true));
    }

    /**
     * 指定した商品がおいしい水(water)で、投入金額が99円のとき 投入金額が単価を下回るので、チェッカーはfalseを返す
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test

    public void isNotWaterAvairable() throws WrongProductNumberException {
        Checker affchecker = new Checker();
        Product water = new Product(1, "おいしい水", 100);
        boolean checkResult = affchecker.checkCanAfford(water, 100 - 1);
        assertThat(checkResult, is(false));
    }

    @Test
    /**
     * 指定した商品がサイコソーダ(soda)で、投入金額が150円のとき 投入金額が単価と同じなので、チェッカーはtrueを返す
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    public void isSodaAvairable() throws WrongProductNumberException {
        Checker affchecker = new Checker();
        Product soda = new Product(2, "サイコソーダ", 150);
        boolean checkResult = affchecker.checkCanAfford(soda, 150);
        assertThat(checkResult, is(true));
    }

    @Test
    /**
     * 指定した商品がサイコソーダ(soda)で、投入金額が149円のとき 投入金額が単価を下回るので、チェッカーはfalseを返す
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    public void isNotSodaAvairable() throws WrongProductNumberException {
        Checker affchecker = new Checker();
        Product soda = new Product(2, "サイコソーダ", 150);
        boolean checkResult = affchecker.checkCanAfford(soda, 150 - 1);
        assertThat(checkResult, is(false));
    }

    /**
     * 指定した商品がミックスオレ(mix)で、投入金額が160円のとき 投入金額が単価と同じなので、チェッカーはtrueを返す
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void isMixAulaitAvairable() throws WrongProductNumberException {
        Checker affchecker = new Checker();
        Product mix = new Product(3, "ミックスオレ", 160);
        boolean checkResult = affchecker.checkCanAfford(mix, 160);
        assertThat(checkResult, is(true));
    }

    /**
     * 指定した商品がミックスオレ(mix)で、投入金額が159円のとき 投入金額が単価を下回るので、チェッカーはfalseを返す
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void isNotMixAulaitAvairable() throws WrongProductNumberException {
        Checker affchecker = new Checker();
        Product mix = new Product(3, "ミックスオレ", 160);
        boolean checkResult = affchecker.checkCanAfford(mix, 160 - 1);
        assertThat(checkResult, is(false));
    }

}
