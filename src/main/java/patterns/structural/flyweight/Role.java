package patterns.structural.flyweight;

/**
 * 用于组合英雄内部状态和外部状态的类，假设称为角色
 */
public class Role {

    private AbstractHero hero;//角色(用户)选择的英雄

    //我们把血量和魔法量这两个外部状态从英雄里剥离出来，放到外部的角色类中  英雄外部状态
    private long hp;

    private long mp;

    public Role(AbstractHero hero){
        this.hero = hero;
    }

    //释放技能
    public void release(int index){
        hero.release(index);
    }

    //物理攻击
    public void commonAttack(){
        hero.commonAttack();
    }

    public long getHp() {
        return hp;
    }

    public void setHp(long hp) {
        this.hp = hp;
    }

    public long getMp() {
        return mp;
    }

    public void setMp(long mp) {
        this.mp = mp;
    }
}
