package pkg;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    Map<Integer, Integer> stockMap;
    Product[] productInfo = new Product[3];

    Storage() {

        // 商品ごとにインスタンスを生成
        Product water = new Product(1, "おいしい水", 100);
        Product soda = new Product(2, "サイコソーダ", 150);
        Product mix = new Product(3, "ミックスオレ", 160);

        productInfo[0] = water;
        productInfo[1] = soda;
        productInfo[2] = mix;

        stockMap = new HashMap<Integer, Integer>();

        // 在庫情報もstockMapに追加
        stockMap.put(1, 12);
        stockMap.put(2, 5);
        stockMap.put(3, 1);
    }

    Product getProduct(int num) throws WrongProductNumberException {
        if (num <= productInfo.length) {
            Product pr = productInfo[num - 1];
            return pr;
        } else {
            throw new WrongProductNumberException("存在しない商品番号がリクエストされています");
        }

    }

    int getStock(int num) throws WrongProductNumberException {
        if (num <= stockMap.size()) {
            int stock = stockMap.get(num);
            return stock;
        } else {
            throw new WrongProductNumberException("存在しない商品番号がリクエストされています");
        }
    }

    void reduceStock(int num) throws WrongProductNumberException {
        stockMap.put(num, getStock(num) - 1);
    }

}
