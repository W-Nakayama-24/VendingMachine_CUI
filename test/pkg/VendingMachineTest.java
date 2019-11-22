package pkg;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VendingMachineTest {

    @Test
    //getterのテスト 最初の投入金額0円を取得する
    public void getDeposit() {
        VendingMachine vm = new VendingMachine();
        int result = vm.getDeposit();
        assertThat(result,is(0));
    }

    @Test
    //get在庫数のテスト storageのstockMapから正しく在庫数を取得できるか
    public void getWaterStock() {
        VendingMachine vm = new VendingMachine();
        int result = vm.getStock(1);
        assertThat(result,is(12));
    }

    //サイコソーダ、ミックスオレも同様に実施
    @Test
    public void getSodaStock() {
        VendingMachine vm = new VendingMachine();
        int result = vm.getStock(2);
        assertThat(result,is(5));
    }

    @Test
    public void getMixAualitStock() {
        VendingMachine vm = new VendingMachine();
        int result = vm.getStock(3);
        assertThat(result,is(1));
    }

    //存在しない商品番号を指定した場合、
    //対応する番号を持った商品が見つからなかった結果nullを返却してしまう
    //商品番号が正しくないことを伝えるため、自作例外WrongProductNumberExceptionが発生
    @Test(expected = WrongProductNumberException.class)
    public void getStockFailure() {
        VendingMachine vm = new VendingMachine();;
        vm.getStock(9);
    }

    @Test
    //get商品情報のテスト storageのProduct型配列から正しく商品情報を取得できるか
    public void getWaterInfo() {
        VendingMachine vm = new VendingMachine();
        Product result = vm.getProduct(1);
        assertThat(result.getNum(),is(1));
        assertThat(result.getName(),is("おいしい水"));
        assertThat(result.getPrice(),is(100));
    }

    //サイコソーダ、ミックスオレも同様に実施
    public void getSodaInfo() {
        VendingMachine vm = new VendingMachine();
        Product result = vm.getProduct(2);
        assertThat(result.getNum(),is(2));
        assertThat(result.getName(),is("サイコソーダ"));
        assertThat(result.getPrice(),is(150));
    }

    public void getMixAulaitInfo() {
        VendingMachine vm = new VendingMachine();
        Product result = vm.getProduct(3);
        assertThat(result.getNum(),is(3));
        assertThat(result.getName(),is("ミックスオレ"));
        assertThat(result.getPrice(),is(160));
    }

    //存在しない商品番号を指定した場合、不正なインデックスを使って配列にアクセスしてしまう
    //商品番号が正しくないことを伝えるため、自作例外WrongProductNumberExceptionが発生
    @Test(expected = WrongProductNumberException.class)
    public void getProductFailure() {
        VendingMachine vm = new VendingMachine();
        vm.getProduct(9);
    }


    @Test
    //お金投入のテスト 希望金額1000円で上限チェックがOKのとき、trueを返す
    //投入金額が希望金額の分だけ増える
    public void insertSuccess() {
        VendingMachine vm = new VendingMachine();
        int getterTestResult = 0;
        int requestMoney = 1000;

        boolean insertResult = vm.insertMoney(requestMoney);
        assertThat(insertResult,is(true));

        getterTestResult = vm.getDeposit();
        assertThat(getterTestResult,is(requestMoney));
    }

    @Test
    //お金投入のテスト 希望金額9991円で上限チェックがNGのとき、falseを返す
    //投入金額が変化していない
    public void insertFailure() {
        VendingMachine vm = new VendingMachine();
        int getResultBefore = 0;
        int getResultAfter = 0;
        int requestMoney = 9990 + 1;

        getResultBefore = vm.getDeposit();

        boolean insertResult  = vm.insertMoney(requestMoney);
        assertThat(insertResult,is(false));

        getResultAfter = vm.getDeposit();
        assertThat(getResultAfter,is(getResultBefore));
    }


    @Test
    //商品購入に成功
    //指定した商品の在庫がある 投入金額も足りている trueを返す
    //投入金額が購入できた商品の単価分だけ減っている,在庫が1本減っている
    public void buySuccess() {
        VendingMachine vm = new VendingMachine();
        int depositBeforeBuy = vm.getProduct(1).getPrice();
        int stockBeforeBuy = vm.getStock(1);
        vm.deposit = depositBeforeBuy;

        boolean result = vm.buyProduct(1);

        assertThat(result,is(true));
        assertThat(vm.getDeposit(),is(depositBeforeBuy - vm.getProduct(1).getPrice()));
        assertThat(vm.getStock(1),is(stockBeforeBuy - 1));
    }

    @Test
    //商品購入に失敗
    //指定した商品の在庫はある 投入金額が足りていない falseを返す
    //投入金額と在庫が変化していないことも確認
    public void notEnoughMoney() {
        VendingMachine vm = new VendingMachine();
        int depositBeforeBuy = vm.getProduct(1).getPrice() - 1;
        int stockBeforeBuy = vm.getStock(1);
        vm.deposit = depositBeforeBuy;

        boolean result = vm.buyProduct(1);

        assertThat(result,is(false));
        assertThat(vm.getDeposit(), is(depositBeforeBuy));
        assertThat(vm.getStock(1),is(stockBeforeBuy));
    }

    @Test
    //商品購入に失敗
    //指定した商品の在庫がない  投入金額は足りている falseを返す
    //投入金額と在庫が変化していないことも確認
    public void notEnoughStock() {
        VendingMachine vm = new VendingMachine();
        vm.storage.stockMap.put(1, 0); //商品番号1:おいしい水の在庫を0に変更
        int depositBeforeBuy = vm.getProduct(1).getPrice();
        int stockBeforeBuy = vm.getStock(1);
        vm.deposit = depositBeforeBuy;

        boolean result = vm.buyProduct(1);

        assertThat(result,is(false));
        assertThat(vm.getDeposit(), is(depositBeforeBuy));
        assertThat(vm.getStock(1),is(stockBeforeBuy));
    }

    @Test
    //商品購入に失敗
    //指定した商品の在庫がない 投入金額も足りていない falseを返す
    //投入金額と在庫が変化していないことも確認
    public void notEnoughStockAndMoney() {
        VendingMachine vm = new VendingMachine();
        vm.storage.stockMap.put(1, 0); //商品番号1:おいしい水の在庫を0に変更
        int depositBeforeBuy = vm.getProduct(1).getPrice() - 1;
        int stockBeforeBuy = vm.getStock(1);
        vm.deposit = depositBeforeBuy;

        boolean result = vm.buyProduct(1);

        assertThat(result,is(false));
        assertThat(vm.getDeposit(), is(depositBeforeBuy));
        assertThat(vm.getStock(1),is(stockBeforeBuy));
    }


    //商品購入のテスト
    //失敗 存在する商品番号以外の整数を引数に渡したとき
    //    自作例外WrongProductNumberExceptionが発生
    @Test(expected = WrongProductNumberException.class)
    public void requestWrongNumber() {
        VendingMachine vm = new VendingMachine();
        vm.buyProduct(9);
    }
}
