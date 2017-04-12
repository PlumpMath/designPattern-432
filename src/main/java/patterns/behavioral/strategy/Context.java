package patterns.behavioral.strategy;

/**
 * Created by Administrator on 2017/2/15.
 */
public class Context {

    Strategy strategy;

    public void method(){
        strategy.algorithm();
    }

    public void setStrategy(Strategy strategy){
        this.strategy =strategy;
    }

}
