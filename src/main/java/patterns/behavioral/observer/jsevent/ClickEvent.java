package patterns.behavioral.observer.jsevent;

/**
 * click事件
 */
public class ClickEvent extends ButtonEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ClickEvent(Object source) {
        super(source);
    }
}
