package patterns.behavioral.strategy.price;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 策略工厂 --借用简单工厂模式(创建型)生成相同父类的子类对象
 */
public class CalPriceFactory {

    //这里不适用Spring的方式，直接使用JDK原生方式加载类
    private static final String CAL_PRICE_PACKAGE = "patterns.behavioral.strategy.price";//这里是一个常量，表示我们扫描策略的包，这是LZ的包名
    private ClassLoader classLoader = this.getClass().getClassLoader();//我们加载策略时的类加载器，我们任何类运行时信息必须来自该类加载器

    private List<Class<? extends CalPrice>> calPriceList;//策略列表


    //根据客户的总金额产生相应的策略
    public CalPrice createCalPrice(Customer customer){
        // TODO: 第二次优化 去掉万恶的ifelse 同时增强灵活度 -> 我们使用注解进行aop处理
//        if (customer.getTotalAmount() > 3000) {//3000则改为金牌会员计算方式
//            return new GoldVip();
//        }else if (customer.getTotalAmount() > 2000) {//类似
//            return new SuperVip();
//        }else if (customer.getTotalAmount() > 1000) {//类似
//            return new Vip();
//        }else {
//            return new Common();
//        }
        //变化点:为了支持优先级排序，我们采用可排序的MAP支持,这个Map是为了储存我们当前策略的运行时类信息 默认正序
        SortedMap<Integer, Class<? extends CalPrice>> clazzMap = new TreeMap<Integer, Class<? extends CalPrice>>();

        for (Class<? extends CalPrice> clazz : calPriceList){
            Annotation validRegion = handleAnnotation(clazz);//获取该策略的注解
            if(validRegion instanceof TotalValidRegion){
                TotalValidRegion totalValidRegion = (TotalValidRegion) validRegion;
                if (customer.getTotalAmount() > totalValidRegion.value().min() && customer.getTotalAmount() < totalValidRegion.value().max()){
                    clazzMap.put(totalValidRegion.value().order(), clazz);//将采用的策略放入MAP
                }
            }
            //// TODO: 第三次优化 针对 只能支持单一策略 (只针对总金额) 不支持多个策略 -> 使用嵌套策略 进行优化
            else if (validRegion instanceof OnceValidRegion) {
                OnceValidRegion onceValidRegion = (OnceValidRegion) validRegion;
                //判断单次金额是否在注解的区间，注意这次判断的是客户当次消费的金额
                if (customer.getAmount() > onceValidRegion.value().min() && customer.getAmount() < onceValidRegion.value().max()) {
                    clazzMap.put(onceValidRegion.value().order(), clazz);//将采用的策略放入MAP
                }
            }
        }
        //由于都是事先相同的接口 那用JDK动态代理就是OK的
        //我们采用动态代理处理策略重叠的问题，相信看过LZ的代理模式的同学应该都对代理模式的原理很熟悉了，那么下面出现的代理类LZ将不再解释，留给各位自己琢磨。
        return CalPriceProxy.getProxy(clazzMap);
    }

    //处理注解，我们传入一个策略类，返回它的注解
    private Annotation handleAnnotation(Class<? extends CalPrice> clazz){
        Annotation[] annotations = clazz.getAnnotations();
        if (annotations == null || annotations.length == 0){
            return null;
        }
        for (int i = 0; i < annotations.length ;i++){
            //支持多种策略
            if (annotations[i] instanceof TotalValidRegion || annotations[i] instanceof OnceValidRegion) {
                return annotations[i];
            }
        }
        return null;
    }

    private void init(){
        //获取物理文件
        File[] resources = this.getResources();
        if (resources == null || resources.length < 0){
            return;
        }
        calPriceList = new ArrayList<Class<? extends CalPrice>>();
        //获取策略基类 用作基类对比
        Class<CalPrice> calPriceClass = null;
        try {
            calPriceClass = (Class<CalPrice>) classLoader.loadClass(CalPrice.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("未找到策略接口");
        }
        //填充calPriceList
        for (int i = 0;i < resources.length ; i++){
            //加载包下的class对象
            try {
                Class<?> clz = classLoader.loadClass(CAL_PRICE_PACKAGE + "." + resources[i].getName().replace(".class",""));
                //判断是否是CalPrice的实现类并且不是CalPrice它本身，满足的话加入到策略列表
                if (CalPrice.class.isAssignableFrom(clz)  && clz != calPriceClass){
                    calPriceList.add((Class<? extends CalPrice>) clz);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("未找到策略接口");
            }
        }

    }

    private File[] getResources(){
        try {
            File file = new File(classLoader.getResource(CAL_PRICE_PACKAGE.replace(".","/")).toURI());
            return file.listFiles(new FileFilter() {//扫描策略
                public boolean accept(File pathname) {
                    if (pathname.getName().endsWith(".class")) {//我们只扫描class文件
                        return true;
                    }
                    return false;
                }
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException("未找到策略资源");
        }
    }

    //单例工厂
    public static CalPriceFactory getInstance(){
        return CalPriceFactoryInstance.instance;
    }

    private static class CalPriceFactoryInstance{
        private static CalPriceFactory instance = new CalPriceFactory();
    }

    private CalPriceFactory(){
        init();
    }


}
