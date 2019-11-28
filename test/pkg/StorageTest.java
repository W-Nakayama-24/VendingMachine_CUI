package pkg;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author W-Nakayama
 *
 */
public class StorageTest {

    /**
     * addProductInfoメソッドのテスト UserInterfaceクラスで用意するサンプルとは異なる商品を追加する
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void addProductInfoSuccess() throws WrongProductNumberException {
        Storage storage = new Storage();
        Product productForTest = new Product(4, "テスト用商品", 500);
        storage.addProductInfo(productForTest.getNum(), productForTest);
    }

    /**
     * getProductメソッドのテスト 「おいしい水」の商品情報取得に成功
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void getWaterSuccess() throws WrongProductNumberException {
        Storage storage = new Storage();
        Product water = new Product(1, "おいしい水", 100);
        storage.addProductInfo(water.getNum(), water);
        Product waterFromMap = storage.getProduct(water.getNum());

        assertThat(waterFromMap.getNum(), is(1));
        assertThat(waterFromMap.getName(), is("おいしい水"));
        assertThat(waterFromMap.getPrice(), is(100));
    }

    /**
     * getProductメソッドのテスト 「サイコソーダ」の商品情報取得に成功
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void getSodaSuccess() throws WrongProductNumberException {
        Storage storage = new Storage();
        Product soda = new Product(2, "サイコソーダ", 150);
        storage.addProductInfo(soda.getNum(), soda);
        Product sodaFromMap = storage.getProduct(soda.getNum());

        assertThat(sodaFromMap.getNum(), is(2));
        assertThat(sodaFromMap.getName(), is("サイコソーダ"));
        assertThat(sodaFromMap.getPrice(), is(150));

    }

    /**
     * getProductメソッドのテスト 「ミックスオレ」の商品情報取得に成功
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void getMixSuccess() throws WrongProductNumberException {
        Storage storage = new Storage();
        Product mix = new Product(3, "ミックスオレ", 160);
        storage.addProductInfo(mix.getNum(), mix);
        Product mixFromMap = storage.getProduct(mix.getNum());

        assertThat(mixFromMap.getNum(), is(3));
        assertThat(mixFromMap.getName(), is("ミックスオレ"));
        assertThat(mixFromMap.getPrice(), is(160));
    }

    /**
     * getProductメソッドのテスト 商品番号として存在していない整数を引数に渡すと、例外が発生し正常に商品情報を取得できない
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test(expected = WrongProductNumberException.class)
    public void getProductFailure() throws WrongProductNumberException {
        Storage storage = new Storage();
        storage.getProduct(9);
    }

    /**
     * chargeStockメソッドのテスト サンプルに無い商品の在庫を追加する
     */
    @Test
    public void chargeStockSuccess() {
        Storage storage = new Storage();
        storage.chargeStock(4, 999);
    }

    /**
     * getStockメソッドのテスト
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void getWaterStock() throws WrongProductNumberException {
        Storage storage = new Storage();
        storage.chargeStock(1, 12);
        int testStock = storage.getStock(1);
        assertThat(testStock, is(12));
    }

    @Test
    public void getSodaStock() throws WrongProductNumberException {
        Storage storage = new Storage();
        storage.chargeStock(2, 5);
        int testStock = storage.getStock(2);
        assertThat(testStock, is(5));
    }

    @Test
    public void getMixAulaitStock() throws WrongProductNumberException {
        Storage storage = new Storage();
        storage.chargeStock(3, 1);
        int testStock = storage.getStock(3);
        assertThat(testStock, is(1));
    }

    /**
     * getStockメソッドのテスト productInfoMapに存在しない商品番号(キー)を指定した場合
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生.
     */
    @Test(expected = WrongProductNumberException.class)
    public void getStockFailure() throws WrongProductNumberException {
        Storage storage = new Storage();
        storage.getStock(9);
    }
    //

    /**
     * reduceStockメソッドのテスト 商品番号を指定して、在庫が1減らされることを確認
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void testReduceStock() throws WrongProductNumberException {
        Storage storage = new Storage();
        storage.chargeStock(1, 100);
        int beforeTest = storage.getStock(1);
        assertThat(beforeTest, is(100));

        storage.reduceStock(1);

        int afterTest = storage.getStock(1);
        assertThat(afterTest, is(100 - 1));
    }

    /**
     * reduceStockメソッドのテスト 商品番号「9」を指定して、存在しない商品に対して在庫を減らす処理が出来ないことを確認.
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test(expected = WrongProductNumberException.class)
    public void reduceStockFailure() throws WrongProductNumberException {
        Storage storage = new Storage();
        storage.reduceStock(9);
    }

}
