package pkg;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    Map<Integer, Product> productInfoMap = new HashMap<Integer, Product>();
    Map<Integer, Integer> stockMap = new HashMap<Integer, Integer>();

    // 商品情報を追加する(商品情報のみ 在庫数はいったん0をセットする)
    public void addProductInfo(Integer num, Product product) {
        productInfoMap.put(num, product);
        stockMap.put(num, 0);
    }

    // 商品情報を追加する(第三引数で在庫数も指定すると、商品情報とともにセットする)
    public void addProductInfo(Integer num, Product product, Integer stock) {
        productInfoMap.put(num, product);
        stockMap.put(num, stock);
    }

    // 在庫を補充する
    public void chargeStock(Integer num, Integer stock) {
        stockMap.put(num, stock);
    }

    Product getProduct(int num) throws WrongProductNumberException {
        if (productInfoMap.containsKey(num) == true) {
            Product pr = productInfoMap.get(num);
            return pr;
        } else {
            throw new WrongProductNumberException("存在しない商品番号がリクエストされています");
        }

    }

    int getStock(int num) throws WrongProductNumberException {
        if (stockMap.containsKey(num) == true) {
            int stock = stockMap.get(num);
            return stock;
        } else {
            throw new WrongProductNumberException("存在しない商品番号がリクエストされています");
        }
    }

    // 在庫を1減らす
    int reduceStock(int num) throws WrongProductNumberException {
        if (stockMap.containsKey(num) == true) {
            int afterReduce = getStock(num) - 1;
            stockMap.put(num, afterReduce);
            return afterReduce;
        } else {
            throw new WrongProductNumberException("存在しない商品番号がリクエストされています");
        }
    }

}
