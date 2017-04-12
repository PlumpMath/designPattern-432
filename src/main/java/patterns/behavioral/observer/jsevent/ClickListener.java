package patterns.behavioral.observer.jsevent;

import java.util.EventListener;

/**
 * Created by Administrator on 2017/2/14.
 */
public interface ClickListener extends EventListener {

    void click(ClickEvent clickEvent);

}
