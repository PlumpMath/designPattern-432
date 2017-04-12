package patterns.behavioral.chain;

/**
 * Created by Administrator on 2017/3/10.
 */
public interface Subbranch {

    //下一个处理者
    void setSuccessor(Subbranch subbranch);

    //业务逻辑
    boolean handleOrder(Order order);

}
