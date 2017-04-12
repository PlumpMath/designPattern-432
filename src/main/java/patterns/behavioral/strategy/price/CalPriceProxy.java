package patterns.behavioral.strategy.price;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.SortedMap;

/**
 * jdk动态代理
 */
public class CalPriceProxy implements InvocationHandler {
    private SortedMap<Integer, Class<? extends CalPrice>> clazzMap;

    private CalPriceProxy(SortedMap<Integer, Class<? extends CalPrice>> clazzMap) {
        super();
        this.clazzMap = clazzMap;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Double result = 0D;
        if (method.getName().equals("calPrice")) {
            for (Class<? extends CalPrice> clazz : clazzMap.values()) {//Map遍历时是有序的 order越小越优先
                if (result == 0) {
                    result = (Double) method.invoke(clazz.newInstance(), args);
                }else {
                    result = (Double) method.invoke(clazz.newInstance(), result);//在原先基础之上继续干活
                }
            }
            return result;
        }
        return null;
    }

    public static CalPrice getProxy(SortedMap<Integer, Class<? extends CalPrice>> clazzMap){
        return (CalPrice) Proxy.newProxyInstance(CalPriceProxy.class.getClassLoader(), new Class<?>[]{CalPrice.class}, new CalPriceProxy(clazzMap));
    }
}
