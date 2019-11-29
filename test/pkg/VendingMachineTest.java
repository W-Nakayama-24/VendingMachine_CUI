package pkg;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import pkg.VendingMachine.BuyResult;

/**
 * @author W-Nakayama
 */
public class VendingMachineTest {

    /**
     * getterのテスト 最初の投入金額0円を取得する
     */
    @Test
    public void getDeposit() {
        VendingMachine vm = new VendingMachine();
        int result = vm.getDeposit();
        assertThat(result, is(0));
    }

    /**
     * addProductInfoメソッドのテスト UserInterfaceクラスで用意するサンプルとは異なる商品を追加する
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void addProductInfoSuccess() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();
        Product productForTest = new Product(4, "テスト用商品 在庫なし", 400);
        vm.addProductInfo(productForTest.getProductID(), productForTest);

        Product productFromMap = vm.getProduct(productForTest.getProductID());

        assertThat(productFromMap.getProductID(), is(4));
        assertThat(productFromMap.getName(), is("テスト用商品 在庫なし"));
        assertThat(productFromMap.getPrice(), is(400));
        assertThat(vm.getStock(productFromMap.getProductID()), is(0));
    }

    /**
     * addProductIndoメソッドのテスト 第三引数で在庫数を指定し、商品情報とともにセットする
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void addProductInfoAndStock() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();
        Product productForTest = new Product(5, "テスト用商品 在庫9999", 500);
        vm.addProductInfo(productForTest.getProductID(), productForTest, 9999);

        Product productFromMap = vm.getProduct(productForTest.getProductID());

        assertThat(productFromMap.getProductID(), is(5));
        assertThat(productFromMap.getName(), is("テスト用商品 在庫9999"));
        assertThat(productFromMap.getPrice(), is(500));
        assertThat(vm.getStock(productFromMap.getProductID()), is(9999));
    }

    /**
     * chargeStockメソッドのテスト サンプルに無い商品の在庫を追加する
     */
    @Test
    public void chargeStockSuccess() {
        VendingMachine vm = new VendingMachine();
        vm.chargeStock(4, 999);
    }

    @Test
    /**
     * get在庫数のテスト storageのstockMapから正しく在庫数を取得できるか確認する
     *
     * @throws WrongProductNumberException 商品番号として存在していない整数を引数に渡すと発生
     */
    public void getWaterStock() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();
        vm.chargeStock(1, 12);
        int result = vm.getStock(1);
        assertThat(result, is(12));
    }

    @Test
    public void getSodaStock() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();
        vm.chargeStock(2, 5);
        int result = vm.getStock(2);
        assertThat(result, is(5));
    }

    @Test
    public void getMixAualitStock() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();
        vm.chargeStock(3, 1);
        int result = vm.getStock(3);
        assertThat(result, is(1));
    }

    /**
     * get在庫数のテスト 存在しない商品番号を指定した場合、例外が発生し在庫数を正常に取得できない
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test(expected = WrongProductIdException.class)
    public void getStockFailure() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();
        vm.getStock(9);
    }

    /**
     * getProductメソッドのテスト 「おいしい水」の商品情報取得に成功
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void getWaterSuccess() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();
        Product water = new Product(1, "おいしい水", 100);
        vm.addProductInfo(water.getProductID(), water);
        Product waterFromMap = vm.getProduct(water.getProductID());

        assertThat(waterFromMap.getProductID(), is(1));
        assertThat(waterFromMap.getName(), is("おいしい水"));
        assertThat(waterFromMap.getPrice(), is(100));
    }

    /**
     * getProductメソッドのテスト 「サイコソーダ」の商品情報取得に成功
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void getSodaSuccess() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();
        Product soda = new Product(2, "サイコソーダ", 150);
        vm.addProductInfo(soda.getProductID(), soda);
        Product sodaFromMap = vm.getProduct(soda.getProductID());

        assertThat(sodaFromMap.getProductID(), is(2));
        assertThat(sodaFromMap.getName(), is("サイコソーダ"));
        assertThat(sodaFromMap.getPrice(), is(150));

    }

    /**
     * getProductメソッドのテスト 「ミックスオレ」の商品情報取得に成功
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void getMixSuccess() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();
        Product mix = new Product(3, "ミックスオレ", 160);
        vm.addProductInfo(mix.getProductID(), mix);
        Product mixFromMap = vm.getProduct(mix.getProductID());

        assertThat(mixFromMap.getProductID(), is(3));
        assertThat(mixFromMap.getName(), is("ミックスオレ"));
        assertThat(mixFromMap.getPrice(), is(160));
    }

    /**
     * getProductメソッドのテスト 商品番号として存在していない整数を引数に渡すと、例外が発生し正常に商品情報を取得できない
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test(expected = WrongProductIdException.class)
    public void getProductFailure() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();
        vm.getProduct(9);
    }

    /**
     * お金投入のテスト 希望金額1000円で上限チェックがOKのとき、trueを返す 投入金額が希望金額の分だけ増える
     */
    @Test
    public void insertSuccess() {
        VendingMachine vm = new VendingMachine();
        int getterTestResult = 0;
        int requestMoney = 1000;

        boolean insertResult = vm.insertMoney(requestMoney);
        assertThat(insertResult, is(true));

        getterTestResult = vm.getDeposit();
        assertThat(getterTestResult, is(requestMoney));
    }

    /**
     * お金投入のテスト 希望金額9991円で上限チェックがNGのとき、 falseを返す投入金額が変化していない
     */
    @Test
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

    /**
     * 商品購入に成功 指定した商品の在庫がある・投入金額も足りている場合、trueを返す 投入金額が購入できた商品の単価分だけ減っている,在庫が1本減っている
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void buySuccess() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();

        Product water = new Product(1, "おいしい水", 100);
        vm.addProductInfo(water.getProductID(), water);
        vm.chargeStock(water.getProductID(), 12);

        int depositBeforeBuy = vm.getProduct(1).getPrice();
        int stockBeforeBuy = vm.getStock(1);
        vm.deposit = depositBeforeBuy;

        BuyResult result = vm.buyProduct(1);

        assertThat(result, is(BuyResult.SUCCESS));
        assertThat(vm.getDeposit(), is(depositBeforeBuy - vm.getProduct(1).getPrice()));
        assertThat(vm.getStock(1), is(stockBeforeBuy - 1));
    }

    /**
     * 商品購入に失敗 指定した商品の在庫はある・投入金額が足りていない場合 falseを返す 投入金額と在庫が変化していないことも確認
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void notEnoughMoney() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();
        Product water = new Product(1, "おいしい水", 100);
        vm.addProductInfo(water.getProductID(), water);
        vm.chargeStock(water.getProductID(), 12);

        int depositBeforeBuy = vm.getProduct(1).getPrice() - 1;
        int stockBeforeBuy = vm.getStock(1);
        vm.deposit = depositBeforeBuy;

        BuyResult result = vm.buyProduct(1);

        assertThat(result, is(BuyResult.ERROR_NOT_ENOUGH_MONEY));
        assertThat(vm.getDeposit(), is(depositBeforeBuy));
        assertThat(vm.getStock(1), is(stockBeforeBuy));
    }

    /**
     * 商品購入に失敗 指定した商品の在庫がない・投入金額は足りている場合 falseを返す 投入金額と在庫が変化していないことも確認
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void notEnoughStock() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();
        Product water = new Product(1, "おいしい水", 100);
        vm.addProductInfo(water.getProductID(), water);
        vm.chargeStock(water.getProductID(), 0);

        int depositBeforeBuy = vm.getProduct(1).getPrice();
        int stockBeforeBuy = vm.getStock(1);
        vm.deposit = depositBeforeBuy;

        BuyResult result = vm.buyProduct(1);

        assertThat(result, is(BuyResult.ERROR_ZERO_STOCK));
        assertThat(vm.getDeposit(), is(depositBeforeBuy));
        assertThat(vm.getStock(1), is(stockBeforeBuy));
    }

    /**
     * 商品購入に失敗 指定した商品の在庫はない・投入金額も足りていない場合 falseを返す 投入金額と在庫が変化していないことも確認
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test
    public void notEnoughStockAndMoney() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();

        Product water = new Product(1, "おいしい水", 100);
        vm.addProductInfo(water.getProductID(), water);
        vm.chargeStock(water.getProductID(), 0);

        int depositBeforeBuy = vm.getProduct(1).getPrice() - 1;
        int stockBeforeBuy = vm.getStock(1);
        vm.deposit = depositBeforeBuy;

        BuyResult result = vm.buyProduct(1);

        assertThat(result, is(BuyResult.ERROR_ZERO_STOCK));
        assertThat(vm.getDeposit(), is(depositBeforeBuy));
        assertThat(vm.getStock(1), is(stockBeforeBuy));
    }

    /**
     * 商品購入に失敗 存在する商品番号以外の整数を引数に渡した場合 例外が発生する
     *
     * @throws WrongProductIdException 商品番号として存在していない整数を引数に渡すと発生
     */
    @Test(expected = WrongProductIdException.class)
    public void requestWrongNumber() throws WrongProductIdException {
        VendingMachine vm = new VendingMachine();
        vm.buyProduct(9);
    }
}
