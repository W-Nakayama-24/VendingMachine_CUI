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
     * chargeProductメソッドのテスト UserInterfaceクラスで用意するサンプルとは異なる商品を追加する.
     * 
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void chargeProductSuccess() throws WrongProductNumberException {
        Storage storage = new Storage();
        Product productForTest = new Product(4, "テスト用商品", 500);
        storage.chargeProduct(productForTest);
    }

    /**
     * getProductメソッドのテスト 「おいしい水」の商品情報取得に成功.
     * 
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void getWaterSuccess() throws WrongProductNumberException {
        Storage storage = new Storage();
        Product water = new Product(1, "おいしい水", 100);
        storage.chargeProduct(water);
        Product waterFromList = storage.getProduct(1);

        assertThat(waterFromList.getNum(), is(1));
        assertThat(waterFromList.getName(), is("おいしい水"));
        assertThat(waterFromList.getPrice(), is(100));
    }

    /**
     * getProductメソッドのテスト 「サイコソーダ」の商品情報取得に成功.
     * 
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void getSodaSuccess() throws WrongProductNumberException {
        Storage storage = new Storage();
        Product soda = new Product(2, "サイコソーダ", 150);
        storage.chargeProduct(soda);
        Product sodaFromList = storage.getProduct(1);

        assertThat(sodaFromList.getNum(), is(2));
        assertThat(sodaFromList.getName(), is("サイコソーダ"));
        assertThat(sodaFromList.getPrice(), is(150));

    }

    /**
     * getProductメソッドのテスト 「ミックスオレ」の商品情報取得に成功.
     * 
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void getMixSuccess() throws WrongProductNumberException {
        Storage storage = new Storage();
        Product mix = new Product(3, "ミックスオレ", 160);
        storage.chargeProduct(mix);
        Product mixFromList = storage.getProduct(1);

        assertThat(mixFromList.getNum(), is(3));
        assertThat(mixFromList.getName(), is("ミックスオレ"));
        assertThat(mixFromList.getPrice(), is(160));
    }

    /**
     * getProductメソッドのテスト 商品番号として存在していない整数を引数に渡すと、例外が発生し正常に商品情報を取得できない.
     * 
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test(expected = WrongProductNumberException.class)
    public void getProductFailure() throws WrongProductNumberException {
        Storage storage = new Storage();
        storage.getProduct(9);
    }

    /**
     * chargeStockメソッドのテスト サンプルに無い商品の在庫を追加する.
     */
    @Test
    public void chargeStockSuccess() {
        Storage storage = new Storage();
        storage.chargeStock(4, 999);
    }

    /**
     * getStockメソッドのテスト
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生.
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
     * getStockメソッドのテスト productInfoMapに存在しない商品番号(キー)を指定した場合.
     * 
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test(expected = WrongProductNumberException.class)
    public void getStockFailure() throws WrongProductNumberException {
        Storage storage = new Storage();
        storage.getStock(9);
    }
    //

    /**
     * reduceStockメソッドのテスト 商品番号を指定して、在庫が1減らされることを確認.
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