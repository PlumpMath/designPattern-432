package patterns.behavioral.strategy.price;

/**
 * 黄金会员
 */
@TotalValidRegion(@ValidRegion(min=3000,order=99))
public class GoldVip implements CalPrice{
    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.5;
    }
}
