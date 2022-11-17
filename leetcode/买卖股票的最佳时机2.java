
public class 买卖股票的最佳时机2 {
    public static void main(String[] args) {
        买卖股票的最佳时机2 s = new 买卖股票的最佳时机2();
        System.out.println(s.maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        } else if (prices.length == 2) {
            return prices[0] > prices[1] ? 0 : prices[1] - prices[0];
        }

        int result = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) {
                if (i == prices.length - 1) {
                    result += prices[i];
                }
            } else {
                if (i == prices.length - 1) {
                    result = result + prices[i - 1];
                } else
                    result = result + prices[i - 1] - prices[i];
            }
        }

        return result;
    }
}