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
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void addProductInfoSuccess() throws WrongProductIdException {
        Storage storage = new Storage();
        Product productForTest = new Product(4, "テスト用商品 在庫なし", 400);
        storage.addProductInfo(productForTest.getProductID(), productForTest);

        Product productFromMap = storage.getProduct(productForTest.getProductID());

        assertThat(productFromMap.getProductID(), is(4));
        assertThat(productFromMap.getName(), is("テスト用商品 在庫なし"));
        assertThat(productFromMap.getPrice(), is(400));
        assertThat(storage.getStock(productFromMap.getProductID()), is(0));
    }

    /**
     * addProductIndoメソッドのテスト 第三引数で在庫数を指定し、商品情報とともにセットする
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void addProductInfoAndStock() throws WrongProductIdException {
        Storage storage = new Storage();
        Product productForTest = new Product(5, "テスト用商品 在庫9999", 500);
        storage.addProductInfo(productForTest.getProductID(), productForTest, 9999);

        Product productFromMap = storage.getProduct(productForTest.getProductID());

        assertThat(productFromMap.getProductID(), is(5));
        assertThat(productFromMap.getName(), is("テスト用商品 在庫9999"));
        assertThat(productFromMap.getPrice(), is(500));
        assertThat(storage.getStock(productFromMap.getProductID()), is(9999));
    }

    /**
     * getProductメソッドのテスト 「おいしい水」の商品情報取得に成功
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void getWaterSuccess() throws WrongProductIdException {
        Storage storage = new Storage();
        Product water = new Product(1, "おいしい水", 100);
        storage.addProductInfo(water.getProductID(), water);
        Product waterFromMap = storage.getProduct(water.getProductID());

        assertThat(waterFromMap.getProductID(), is(1));
        assertThat(waterFromMap.getName(), is("おいしい水"));
        assertThat(waterFromMap.getPrice(), is(100));
        assertThat(storage.getStock(waterFromMap.getProductID()), is(0));
    }

    /**
     * getProductメソッドのテスト 「サイコソーダ」の商品情報取得に成功
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void getSodaSuccess() throws WrongProductIdException {
        Storage storage = new Storage();
        Product soda = new Product(2, "サイコソーダ", 150);
        storage.addProductInfo(soda.getProductID(), soda);
        Product sodaFromMap = storage.getProduct(soda.getProductID());

        assertThat(sodaFromMap.getProductID(), is(2));
        assertThat(sodaFromMap.getName(), is("サイコソーダ"));
        assertThat(sodaFromMap.getPrice(), is(150));
        assertThat(storage.getStock(sodaFromMap.getProductID()), is(0));

    }

    /**
     * getProductメソッドのテスト 「ミックスオレ」の商品情報取得に成功
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void getMixSuccess() throws WrongProductIdException {
        Storage storage = new Storage();
        Product mix = new Product(3, "ミックスオレ", 160);
        storage.addProductInfo(mix.getProductID(), mix);
        Product mixFromMap = storage.getProduct(mix.getProductID());

        assertThat(mixFromMap.getProductID(), is(3));
        assertThat(mixFromMap.getName(), is("ミックスオレ"));
        assertThat(mixFromMap.getPrice(), is(160));
        assertThat(storage.getStock(mixFromMap.getProductID()), is(0));
    }

    /**
     * getProductメソッドのテスト 商品番号として存在していない整数を引数に渡すと、例外が発生し正常に商品情報を取得できない
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test(expected = WrongProductIdException.class)
    public void getProductFailure() throws WrongProductIdException {
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
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void getWaterStock() throws WrongProductIdException {
        Storage storage = new Storage();
        storage.chargeStock(1, 12);
        int testStock = storage.getStock(1);
        assertThat(testStock, is(12));
    }

    @Test
    public void getSodaStock() throws WrongProductIdException {
        Storage storage = new Storage();
        storage.chargeStock(2, 5);
        int testStock = storage.getStock(2);
        assertThat(testStock, is(5));
    }

    @Test
    public void getMixAulaitStock() throws WrongProductIdException {
        Storage storage = new Storage();
        storage.chargeStock(3, 1);
        int testStock = storage.getStock(3);
        assertThat(testStock, is(1));
    }

    /**
     * getStockメソッドのテスト productInfoMapに存在しない商品番号(キー)を指定した場合
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生.
     */
    @Test(expected = WrongProductIdException.class)
    public void getStockFailure() throws WrongProductIdException {
        Storage storage = new Storage();
        storage.getStock(9);
    }
    //

    /**
     * reduceStockメソッドのテスト 商品番号を指定して、在庫が1減らされることを確認
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void testReduceStock() throws WrongProductIdException {
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
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test(expected = WrongProductIdException.class)
    public void reduceStockFailure() throws WrongProductIdException {
        Storage storage = new Storage();
        storage.reduceStock(9);
    }

}
