package org.example;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class VaRCalculator {

    private static double confidenceLevel = 0.95;
    private static final double[] singleTradeHistoricalValues = {10.0, -5.0, 8.0, -2.0, 6.0}; // Example historical values for a single trade
    private static final double[][] portfolioHistoricalValues = {{10.0, -5.0, 8.0, -2.0, 6.0}, {6.0, 4.0, -3.0, 7.0, -1.0}}; // Example historical values for a portfolio of trades


    public static double calculateSingleTradeVaR(double[] historicalValues, double confidenceLevel) {
        Arrays.sort(historicalValues); // Sort the historical values in ascending order

        int index = (int) Math.ceil((1 - confidenceLevel) * historicalValues.length);
        if (index >= historicalValues.length) {
            index = historicalValues.length - 1;
        }

        return Math.abs(historicalValues[index]);
    }

    public static double calculatePortfolioVaR(double[][] historicalValues, double confidenceLevel) {
        double[] portfolioPnLs = new double[historicalValues.length];

        for (int i = 0; i < historicalValues.length; i++) {
            portfolioPnLs[i] = calculateSingleTradeVaR(historicalValues[i], confidenceLevel);
        }

        return Arrays.stream(portfolioPnLs).sum();
    }

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select the following\n");
        System.out.print("[1] Use default confidence level (0.95)\n");
        System.out.print("[2] Enter confidence level (1-0.95)\n");
        int input = scanner.nextInt();
        switch (input) {
            case 1: {
                confidenceLevel = 0.95;
                break;
            }
            case 2: {
                System.out.print("Enter confidence level (1-0.95)\n");
                confidenceLevel = scanner.nextDouble();
                break;
            }
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
//        showMenu();

        // Calculate VaR for a single trade
        double singleTradeVaR = calculateSingleTradeVaR(singleTradeHistoricalValues, confidenceLevel);
        System.out.println("VaR for a single trade: £" + singleTradeVaR + "m at " + (confidenceLevel * 100) + "% confidence level.");

        // Calculate VaR for the portfolio
        double portfolioVaR = calculatePortfolioVaR(portfolioHistoricalValues, confidenceLevel);
        System.out.println("VaR for the portfolio: £" + portfolioVaR + "m at " + (confidenceLevel * 100) + "% confidence level.");
    }

}