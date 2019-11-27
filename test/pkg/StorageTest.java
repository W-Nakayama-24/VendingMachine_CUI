package pkg;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class StorageTest {

    // getProductメソッドのテスト 商品番号「1」を指定した場合
    // コンストラクタで生成したインスタンスを取得し、
    // インスタンスが持つ情報もgetterで正しく取得できるかどうかでテストする
    @Test
    public void getWaterSuccess() {
        Storage storage = new Storage();
        Product testWater = storage.getProduct(1);
        assertThat(testWater.getNum(), is(1));
        assertThat(testWater.getName(), is("おいしい水"));
        assertThat(testWater.getPrice(), is(100));
    }

    // getProductメソッドのテスト 商品番号「2」を指定した場合
    @Test
    public void getSodaSuccess() {
        Storage storage = new Storage();
        Product testSoda = storage.getProduct(2);
        assertThat(testSoda.getNum(), is(2));
        assertThat(testSoda.getName(), is("サイコソーダ"));
        assertThat(testSoda.getPrice(), is(150));
    }

    // getProductメソッドのテスト 商品番号「3」を指定した場合
    @Test
    public void getMixAulaitSuccess() {
        Storage storage = new Storage();
        Product testMixAulait = storage.getProduct(3);
        assertThat(testMixAulait.getNum(), is(3));
        assertThat(testMixAulait.getName(), is("ミックスオレ"));
        assertThat(testMixAulait.getPrice(), is(160));
    }

    // getProductメソッドのテスト 存在しない商品番号を指定した場合、不正なインデックスを使って配列にアクセスしてしまう
    // 商品番号が正しくないことを伝えるため、自作例外WrongProductNumberExceptionが発生
    @Test(expected = WrongProductNumberException.class)
    public void getProductFailure() {
        Storage storage = new Storage();
        storage.getProduct(9);
    }

    // getStockメソッドのテスト
    // 商品番号を指定し、コンストラクタでstockMapにputしていた在庫数が正しく取得できるかどうかでテストする
    @Test
    public void getWaterStock() {
        Storage storage = new Storage();
        int testStock = storage.getStock(1);
        assertThat(testStock, is(12));
    }

    @Test
    public void getSodaStock() {
        Storage storage = new Storage();
        int testStock = storage.getStock(2);
        assertThat(testStock, is(5));
    }

    @Test
    public void getMixAulaitStock() {
        Storage storage = new Storage();
        int testStock = storage.getStock(3);
        assertThat(testStock, is(1));
    }

    // getStockメソッドのテスト
    // productInfoMapに存在しない商品番号(キー)を指定した場合
    // 対応する番号を持った商品が見つからなかった結果、nullを返却してしまう
    // 商品番号が正しくないことを伝えるため、自作例外WrongProductNumberExceptionが発生
    @Test(expected = WrongProductNumberException.class)
    public void getStockFailure() {
        Storage storage = new Storage();
        storage.getStock(9);
    }

    // reduceStockメソッドのテスト
    // 商品番号を指定して、在庫が1減らされることを確認
    @Test
    public void reduceWaterStock() {
        Storage storage = new Storage();
        int beforeTest = storage.getStock(1);
        assertThat(beforeTest, is(12));

        storage.reduceStock(1);

        int afterTest = storage.getStock(1);
        assertThat(afterTest, is(11));
    }

    @Test
    public void reduceSodaStock() {
        Storage storage = new Storage();
        int beforeTest = storage.getStock(2);
        assertThat(beforeTest, is(5));

        storage.reduceStock(2);

        int afterTest = storage.getStock(2);
        assertThat(afterTest, is(4));
    }

    @Test
    public void reduceMixAulaitStock() {
        Storage storage = new Storage();
        int beforeTest = storage.getStock(3);
        assertThat(beforeTest, is(1));

        storage.reduceStock(3);

        int afterTest = storage.getStock(3);
        assertThat(afterTest, is(0));
    }

    // reduceStockメソッドのテスト
    // 商品番号「9」を指定して、存在しない商品に対して在庫を減らす処理が出来ないことを確認
    // 自作例外WrongProductNumberExceptionが発生
    @Test(expected = WrongProductNumberException.class)
    public void reduceStockFailure() {
        Storage storage = new Storage();
        storage.reduceStock(9);
    }

}
