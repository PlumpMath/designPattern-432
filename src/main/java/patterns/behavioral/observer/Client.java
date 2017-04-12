package patterns.behavioral.observer;

/**
 *
 */
public class Client {

    public static void main(String[] args) {


        Reader reader1 = new Reader("kira");
        Reader reader2 = new Reader("sally");

        Writer writer1 = new Writer("writer1");
        Writer writer2 = new Writer("writer2");

        WriterManager.getInstance().add(writer1);
        WriterManager.getInstance().add(writer2);

        reader1.subscribe(writer1.getName());
        reader2.subscribe(writer1.getName());
        reader2.subscribe(writer2.getName());
        reader1.subscribe(writer2.getName());

        reader1.unsubscribe(writer1.getName());

        writer1.addNovel("邪气凌然");




    }

}
