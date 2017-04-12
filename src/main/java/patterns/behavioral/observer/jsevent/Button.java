package patterns.behavioral.observer.jsevent;

/**
 * 按钮对象  --事件本身其实是个 关联关系
 *          --这个例子中 按钮本身持有多个监听对象 button执行操作时,实际上是交给
 */
public class Button {

    private String id;//这相当于id属性
    private String value;//这相当于value属性
    private ClickListener onclick;//我们完全模拟原有的模型，这个其实相当于onclick属性
    private DbClickListener onDbClick;//同理，这个相当于双击属性

    //按钮的单击行为
    public void click(){
        //this表示的就是事件源  关联关系在于  Button -> ClickListener -> ClickEvent -> Button(对该对象执行了某些特定操作，操作定义在监听器实现类中)
        //事件源触发一个事件 -> 实际上是触发了监听器的方法 -> 监听器会传入事件 -> 事件本身会携带事件源 -> 这样事件源本身就被传递给监听器进行操作了
        onclick.click(new ClickEvent(this));
    }
    //按钮的双击行为
    public void dblClick(){
        onDbClick.dbClick(new DbClickEvent(this));
    }

    public void setOnclick(ClickListener click){
        onclick = click;
    }

    //同理
    public void setOnDblClick(DbClickListener onDblClick) {
        this.onDbClick = onDblClick;
    }

    //相当于给id赋值
    public void setId(String id) {
        this.id = id;
    }
    //类似
    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }


}
