package patterns.behavioral.chain;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/10.
 */
public class McSubbranch implements Subbranch {

    private final static int MIN_DISTANCE = 500;//假设是500米以内送餐

    private static int count;//类计数

    private final int number;//分店号

    private int x;//分店的横坐标，用于判断距离

    private int y;//分店的纵坐标，用于判断距离

    private Map<String, Integer> menu;//菜单

    private Subbranch nextSubbranch;//下一家分店 --重点

    public McSubbranch(int x, int y, Map<String, Integer> menu) {
        super();
        this.x = x;
        this.y = y;
        this.menu = menu;
        number = ++count;
    }

    public void setSuccessor(Subbranch subbranch) {
        this.nextSubbranch = subbranch;
    }

    public boolean handleOrder(Order order) {
        if (CommonUtils.getDistance(order.getX(), order.getY(), this.x, this.y) < MIN_DISTANCE && !CommonUtils.outOfStock(menu, order.getOrder())) {
            for (String name : order.getOrder().keySet()) {
                menu.put(name, menu.get(name) - order.getOrder().get(name));
            }
            System.out.println("订餐成功，接受订单的分店是：" + this);
            return true;
        }
        if (nextSubbranch == null) {
            return false;
        }
        return nextSubbranch.handleOrder(order);
    }


    public Map<String, Integer> getMenu() {
        return Collections.unmodifiableMap(menu);
    }

    public Subbranch getNextSubbranch() {
        return nextSubbranch;
    }

    public String toString() {
        return "麦当劳分店第" + number + "个";
    }

}
