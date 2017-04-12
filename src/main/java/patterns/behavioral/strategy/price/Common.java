package patterns.behavioral.strategy.price;

/**
 * 普通客户
 */
@TotalValidRegion(@ValidRegion(max=1000,order=99))
public class Common implements CalPrice {
    public Double calPrice(Double originalPrice) {
        return originalPrice;
    }
}
