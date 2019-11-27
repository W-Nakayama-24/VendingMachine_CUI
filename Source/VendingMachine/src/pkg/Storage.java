package pkg;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    Map<Integer, Product> productInfoMap = new HashMap<Integer, Product>();
    Map<Integer, Integer> stockMap = new HashMap<Integer, Integer>();

    public void addProductInfo(Integer num, Product product) {
        productInfoMap.put(num, product);
    }

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
