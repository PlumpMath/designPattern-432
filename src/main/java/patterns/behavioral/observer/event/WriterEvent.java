package patterns.behavioral.observer.event;

import java.util.EventObject;

/**
 * 作者事件
 */
public class WriterEvent extends EventObject{

    private static final long serialVersionUID = 8546459078247503692L;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public WriterEvent(Object source) {
        super(source);
    }

    public Writer getWriter(){
        return (Writer) super.getSource();
    }

}
