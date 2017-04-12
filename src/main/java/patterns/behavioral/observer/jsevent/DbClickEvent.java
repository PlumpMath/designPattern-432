package patterns.behavioral.observer.jsevent;

/**
 * DbClick事件
 */
public class DbClickEvent extends ButtonEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public DbClickEvent(Object source) {
        super(source);
    }
}
