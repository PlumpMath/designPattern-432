package patterns.behavioral.observer;

import java.util.*;

/**
 * 作者管理器  享元模式
 */
public class WriterManager {

    private Map<String, Writer> writerMap = new HashMap<String, Writer>();

    public void add(Writer writer){
        writerMap.put(writer.getName(),writer);
    }

    public Writer getWriter(String name){
        return writerMap.get(name);
    }

    private WriterManager(){

    }

    public static WriterManager getInstance(){
        return WriterManagerInstance.instance;
    }

    private static class WriterManagerInstance{
        private static WriterManager instance = new WriterManager();
    }

}
