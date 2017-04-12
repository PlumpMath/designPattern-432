package patterns.behavioral.strategy.price;

/**
 * 会员
 */
@TotalValidRegion(@ValidRegion(min=1000,max=2000,order=99))
public class Vip implements CalPrice{
    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.8;
    }
}
