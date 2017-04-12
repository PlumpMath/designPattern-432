package patterns.behavioral.chain;

//订餐管理部门，它并不是职责链模式中的角色
//但是在职责链模式中，通常情况下会有一个类专门负责维护职责链
//在本例中，这个类称为订餐管理部门更合适
//一般使用动态代理实现 ProxyManager创建具体的执行者
public class OrderManager {

    private static OrderManager orderManager = new OrderManager();

    private Subbranch head;

    private Subbranch last;

    private OrderManager(){}

    public static OrderManager getOrderManager(){
        return orderManager;
    }

    //注册分店
    public void registerSubbranch(Subbranch... subbranchs){
        for (Subbranch subbranch : subbranchs) {
            registerSubbranch(subbranch);
        }
    }
    public void registerSubbranch(Subbranch subbranch){
        //核心注册
        if (head == null) {
            last = head = subbranch;
        }else {
            last.setSuccessor(subbranch);
            last = subbranch;
        }
    }

    public boolean handleOrder(Order order){
        return head.handleOrder(order);
    }


}
