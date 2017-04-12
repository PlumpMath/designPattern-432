package patterns.behavioral.observer.event;

import java.util.EventListener;

/**
 * 自定义事件监听器  一定要继承EventListener 作为Java事件监听器的标识
 */
public interface WriterListener extends EventListener {

    /**
     * 自定义监听事件 -- 监听作者发布新的小说
     * @param writerEvent 作者事件
     */
    void addNoval(WriterEvent writerEvent);

}
