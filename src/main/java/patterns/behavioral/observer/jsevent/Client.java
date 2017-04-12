package patterns.behavioral.observer.jsevent;

/**
 * Created by Administrator on 2017/2/14.
 */
public class Client {

    public static void main(String[] args) {
        Button button = new Button();
        button.setId("1");
        button.setValue("2");

        button.setOnclick(new ClickListener() {
            public void click(ClickEvent clickEvent) {
                Button button = (Button)clickEvent.getSource();
                System.out.println("我点击了" + button.getId() + ",值为" + button.getValue());
            }
        });

        button.click();

    }

}
