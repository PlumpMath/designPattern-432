package patterns.behavioral.observer.event;

/**
 * Created by Administrator on 2017/2/14.
 */
public class Client {

    public static void main(String[] args) {
        Writer writer = new Writer("kira");

        Reader reader = new Reader("sally");

        reader.subscribe(writer.getName());

        writer.addNovel("邪气凌然");

        reader.unsubscribe(writer.getName());

        writer.addNovel("苍穹之剑");
    }
}
