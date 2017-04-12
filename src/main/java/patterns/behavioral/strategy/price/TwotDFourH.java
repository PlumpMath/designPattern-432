package patterns.behavioral.strategy.price;

/**
 *满2000返400
 */
@OnceValidRegion(@ValidRegion(min=2000,order=40))
class TwotDFourH implements CalPrice{

    public Double calPrice(Double originalPrice) {
        return originalPrice - 400;
    }

}
