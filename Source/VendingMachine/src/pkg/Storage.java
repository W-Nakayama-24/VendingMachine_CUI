package pkg;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    Map<Integer, Product> productInfoMap = new HashMap<Integer, Product>();
    Map<Integer, Integer> stockMap = new HashMap<Integer, Integer>();

    public void addProductInfo(Integer productID, Product product) {
        productInfoMap.put(productID, product);
        stockMap.put(productID, 0);
    }

    public void addProductInfo(Integer productID, Product product, Integer stock) {
        productInfoMap.put(productID, product);
        stockMap.put(productID, stock);
    }

    public void chargeStock(Integer productID, Integer stock) {
        stockMap.put(productID, stock);
    }

    Product getProduct(int productID) throws WrongProductIdException {
        if (productInfoMap.containsKey(productID) == true) {
            return productInfoMap.get(productID);
        } else {
            throw new WrongProductIdException("存在しない商品番号がリクエストされています");
        }

    }

    int getStock(int productID) throws WrongProductIdException {
        if (stockMap.containsKey(productID) == true) {
            return stockMap.get(productID);
        } else {
            throw new WrongProductIdException("存在しない商品番号がリクエストされています");
        }
    }

    int reduceStock(int productID) throws WrongProductIdException {
        if (stockMap.containsKey(productID) == true) {
            int afterReduce = getStock(productID) - 1;
            stockMap.put(productID, afterReduce);
            return afterReduce;
        } else {
            throw new WrongProductIdException("存在しない商品番号がリクエストされています");
        }
    }

}
