package patterns.behavioral.strategy.price;

/**
 * 一个商店有普通顾客，会员，超级会员以及金牌会员的区别，针对各个顾客，有不同的打折方式，并且一个顾客每在商店消费1000就增加一个级别
 * case1 ：　分别采用原价，八折，七折和半价的收钱方式
 */
public interface CalPrice {
    //根据原价返回一个最终的价格
    Double calPrice(Double originalPrice);
}
