package pkg;

public class VendingMachine {

    int deposit = 0; // 投入金額

    Storage storage = new Storage();

    enum BuyResult {
        SUCCESS, ERROR_NOT_ENOUGH_MONEY, ERROR_ZERO_STOCK
    }

    // 投入金額のgetter
    public int getDeposit() {
        return deposit;
    }

    // 商品情報を追加する(商品情報のみ 在庫数はいったん0をセットする)
    public void addProductInfo(Integer productID, Product product) {
        storage.addProductInfo(productID, product);
    }

    // 商品情報を追加する(第三引数で在庫数も指定すると、商品情報とともにセットする)
    public void addProductInfo(Integer productID, Product product, Integer stock) {
        storage.addProductInfo(productID, product, stock);
    }

    // 在庫を補充する
    public void chargeStock(Integer productID, Integer stock) {
        storage.chargeStock(productID, stock);
    }

    // 自販機.get在庫数
    public int getStock(int productID) throws WrongProductIdException {
        int stock = storage.getStock(productID);
        return stock;
    }

    // 自販機.get商品情報
    public Product getProduct(int productID) throws WrongProductIdException {
        Product product = storage.getProduct(productID);
        return product;
    }

    // お金を投入する
    public boolean insertMoney(int requestMoney) {
        boolean checkResult;
        Checker dexcheck = new Checker();
        checkResult = dexcheck.checkDepositExcess(requestMoney, deposit);

        if (checkResult == true) {
            deposit += requestMoney;
            return true;
        } else {
            return false;
        }
    }

    // 商品を購入する
    public BuyResult buyProduct(int productID) throws WrongProductIdException {
        if (storage.getStock(productID) > 0) {
            Product reserveProduct = storage.getProduct(productID);
            Checker affcheck = new Checker();
            boolean checkResult = affcheck.checkCanAfford(reserveProduct, getDeposit());
            if (checkResult == true) {
                storage.reduceStock(productID);
                deposit -= reserveProduct.getPrice();
                return BuyResult.SUCCESS;
            } else {
                // ("投入金額が不足しています");
                return BuyResult.ERROR_NOT_ENOUGH_MONEY;
            }
        } else {
            // ("ご指定の商品は売り切れています");
            return BuyResult.ERROR_ZERO_STOCK;
        }
    }

    // 返金を受け取る (voidからintに変更 11/25)
    public int receiveChange() {
        int change = 0;
        if (deposit != 0) {
            change = deposit;
            deposit = 0;
        }
        return change;
    }

    // 自販機システムを終了する(簡易版実装 11/06)
    public void quitSystem() {
        // こちらからmainメソッドのflagを1にするには? → 戻り値が必要?
        // スキャナーのcloseも実施していないままである
        System.exit(0);
    }
}
