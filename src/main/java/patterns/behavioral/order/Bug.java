package patterns.behavioral.order;

/**
 * Created by Administrator on 2017/3/28.
 */
public class Bug implements Task {
    private Programmer programmer;

    public Bug(Programmer programmer) {
        super();
        this.programmer = programmer;
    }

    public void handle() {
        programmer.handleBug();
    }

    public String toString() {
        return "Bug [programmer=" + programmer.getName() + "]";
    }
}
