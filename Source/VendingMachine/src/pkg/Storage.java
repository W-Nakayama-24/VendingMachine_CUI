package pkg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {

    List<Product> productList = new ArrayList<Product>();
    Map<Integer, Integer> stockMap = new HashMap<Integer, Integer>();

    public void chargeProduct(Product product) {
        productList.add(product);
    }

    public void chargeStock(Integer num, Integer stock) {
        stockMap.put(num, stock);
    }

    Product getProduct(int num) throws WrongProductNumberException {
        Product pr = productList.get(num - 1);
        return pr;
    }

    int getStock(int num) throws WrongProductNumberException {
        int stock = stockMap.get(num);
        return stock;
    }

    int reduceStock(int num) throws WrongProductNumberException {
        int afterReduce = getStock(num) - 1;
        stockMap.put(num, afterReduce);

        return afterReduce;
    }

}