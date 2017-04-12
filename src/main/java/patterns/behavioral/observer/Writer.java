package patterns.behavioral.observer;

import java.util.Observable;

/**
 * 作者 被观察者  --实质就是被观察者内部维护了一个通知对象列表，一旦发生状态变更，就遍历通知每个观察者
 * Observable 无需关心具体的观察者是谁(继承了其所有方法，尤其是notifyObservers 批量通知方法 ，该方法会调用所有实现Observer对象的update方法)
 * Observer 会将 Observable对象 传递到update方法中
 */
public class Writer extends Observable {

    private String name;//作者的名称

    private String lastNovel;//记录作者最新发布的小说

    public Writer(String name) {
        super();
        this.name = name;
        WriterManager.getInstance().add(this);

    }

    public String getLastNovel() {
        return lastNovel;
    }

    public String getName() {
        return name;
    }

    //作者发布新小说了，要通知所有关注自己的读者 --被观察者 要通知的内容
    public void addNovel(String novel) {
        System.out.println(name + "发布了新书《" + novel + "》！");
        lastNovel = novel;
        setChanged();//开启变化
        notifyObservers();//开启通知
    }


}
