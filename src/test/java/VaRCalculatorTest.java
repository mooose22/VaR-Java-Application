import org.example.VaRCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VaRCalculatorTest {

    private static final double[] singleTradeHistoricalValues = {10.0, -5.0, 8.0, -2.0, 6.0};
    private static final double[][] portfolioHistoricalValues = {{10.0, -5.0, 8.0, -2.0, 6.0}, {6.0, 4.0, -3.0, 7.0, -1.0}};

    @Test
    public void testCalculateSingleTradeVaR() {
        double confidenceLevel = 0.95;
        double expectedVaR = 2.0;
        double actualVaR = VaRCalculator.calculateSingleTradeVaR(singleTradeHistoricalValues, confidenceLevel);
        Assertions.assertEquals(expectedVaR, actualVaR, 0.01);
    }

    @Test
    public void testCalculatePortfolioVaR() {
        double confidenceLevel = 0.95;
        double expectedVaR = 3.0;
        double actualVaR = VaRCalculator.calculatePortfolioVaR(portfolioHistoricalValues, confidenceLevel);
        Assertions.assertEquals(expectedVaR, actualVaR, 0.01);
    }
}