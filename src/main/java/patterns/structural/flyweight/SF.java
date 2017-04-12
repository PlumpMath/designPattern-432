package patterns.structural.flyweight;

/**
 * Created by Administrator on 2017/2/13.
 */
public class SF extends AbstractHero{

    public String getName() {
        return "影魔";
    }

    public void initSkills() {
        skills[0] = "毁灭阴影";
        skills[1] = "支配死灵";
        skills[2] = "魔王降临";
        skills[3] = "魂之挽歌";
    }
}
