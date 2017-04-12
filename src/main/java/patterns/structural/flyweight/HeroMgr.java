package patterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 提供共享功能，单例
 * 享元模式最重要的类，就是提供共享功能的类
 */
public class HeroMgr {

    //饿汉式单例模式
    private static HeroMgr heroManager = new HeroMgr();

    private Map<String, AbstractHero> heroMap;//提供共享功能

    //线程池 数据库连接池 各种池
    private HeroMgr(){
        heroMap = new HashMap<String, AbstractHero>();
    }

    public static HeroMgr getInstance(){
        return heroManager;
    }

    //该方法提供共享功能
    public AbstractHero getHero(String name){
        AbstractHero hero = heroMap.get(name);
        if (hero == null) {
            if (name.equals("恶魔巫师")) {
                hero = new Lion();
            }else if (name.equals("影魔")) {
                hero = new SF();
            }
            heroMap.put(name, hero);
        }
        return hero;
    }


}
