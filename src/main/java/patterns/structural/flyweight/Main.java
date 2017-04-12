package patterns.structural.flyweight;

/**
 * 作用：在一个系统中如果有多个相同(尤其是可以提取的内部状态)的对象，那么只共享一份就可以、
 * 好处：提高程序效率和性能的模式
 * 享元模式强调内部状态和外部状态
 *  --内部状态则是可以共享的状态
 *  --外部状态则是随外部环境而变化的状态，是无法共享的状态
 *  重点：将外部状态分离出来，只保留内部状态，这样的话对象的实例就可以共享
 *
 *  重点：
 *    1.用角色这个类去组合英雄的内部和外部状态 --只是一个组合
 *    2.需要一个提供共享功能的类提供 共享类 --其实就是个池对象（内部维护一个map等等）
 *  典型应用：
 *    String JAVA会确保一个字符串常量在常量池中只有一个拷贝
 *    数据库连接池，线程池
 */
public class Main {

    public static void main(String[] args) {

        Role role1 = new Role(HeroMgr.getInstance().getHero("影魔"));
        Role role2 = new Role(HeroMgr.getInstance().getHero("恶魔巫师"));


    }

}
