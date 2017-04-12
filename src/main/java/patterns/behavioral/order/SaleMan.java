package patterns.behavioral.order;


/**
 * Created by Administrator on 2017/3/28.
 */
public class SaleMan {


    private String name;

    private ProductManager productManager;

    public SaleMan(String name) {
        super();
        this.name = name;
    }

    public SaleMan(String name, ProductManager productManager) {
        super();
        this.name = name;
        this.productManager = productManager;
    }

    public void putDemand(){
        System.out.println( "业务员" + name + "提出新需求");
        productManager.receive(new Demand(productManager.chooseProgrammer()));
    }

    public void putBug(){
        System.out.println( "业务员" + name + "提出bug");
        productManager.receive(new Bug(productManager.chooseProgrammer()));
    }



}
