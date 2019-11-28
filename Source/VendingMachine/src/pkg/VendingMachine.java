package pkg;

public class VendingMachine {

    int deposit = 0; // 投入金額

    Storage storage = new Storage();

    enum BuyResult {
        SUCCESS, NOT_ENOUGH_MONEY, ZERO_STOCK
    }

    // 投入金額のgetter
    public int getDeposit() {
        return deposit;
    }

    // 商品情報を追加する(商品情報のみ 在庫数はいったん0をセットする)
    public void addProductInfo(Integer num, Product product) {
        storage.addProductInfo(num, product);
    }

    // 商品情報を追加する(第三引数で在庫数も指定すると、商品情報とともにセットする)
    public void addProductInfo(Integer num, Product product, Integer stock) {
        storage.addProductInfo(num, product, stock);
    }

    // 在庫を補充する
    public void chargeStock(Integer num, Integer stock) {
        storage.chargeStock(num, stock);
    }

    // 自販機.get在庫数
    public int getStock(int num) throws WrongProductNumberException {
        int stock = storage.getStock(num);
        return stock;
    }

    // 自販機.get商品情報
    public Product getProduct(int num) throws WrongProductNumberException {
        Product product = storage.getProduct(num);
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
    public BuyResult buyProduct(int num) throws WrongProductNumberException {
        if (storage.getStock(num) > 0) {
            Product reserveProduct = storage.getProduct(num);
            Checker affcheck = new Checker();
            boolean checkResult = affcheck.checkCanAfford(reserveProduct, getDeposit());
            if (checkResult == true) {
                storage.reduceStock(num);
                deposit -= reserveProduct.getPrice();
                return BuyResult.SUCCESS;
            } else {
                // ("投入金額が不足しています");
                return BuyResult.NOT_ENOUGH_MONEY;
            }
        } else {
            // ("ご指定の商品は売り切れています");
            return BuyResult.ZERO_STOCK;
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
