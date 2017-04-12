package patterns.behavioral.observer.jsevent;

import java.util.EventObject;

/**
 * 事件基类
 */
public abstract class ButtonEvent extends EventObject{


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred. 事件源
     * @throws IllegalArgumentException if source is null.
     */
    public ButtonEvent(Object source) {
        super(source);
    }
}
