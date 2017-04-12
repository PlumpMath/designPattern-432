package patterns.structural.flyweight;

/**
 * 英雄 -- 英雄内部状态
 */
public abstract class AbstractHero{

    protected final String name;//英雄名

    protected final String[] skills = new String[4];

    public AbstractHero(){
        super();
        this.name = getName();
        initSkills();
        checkSkills();
    }

    private void checkSkills(){
        for (int i = 0; i < skills.length; i++) {
            if (skills[i] == null) {
                throw new NullPointerException();
            }
        }
    }

    //释放技能
    public void release(int index){
        if (index < 0) {
            index = 0;
        }else if (index > 3) {
            index = 3;
        }
        System.out.println(name + "释放" + skills[index]);
    }

    //物理攻击
    public void commonAttack(){
        System.out.println(name + "进行物理攻击");
    }

    //英雄名
    public abstract String getName();
    //英雄技能
    public abstract void initSkills();

}
