package calcu;

/**
 * @author sunguiyong
 * @date 2022/4/20 5:35 下午
 */
public class MaxProfit {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price > minPrice) {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
            minPrice = Math.min(minPrice, price);
        }
        return maxProfit;
    }
}
