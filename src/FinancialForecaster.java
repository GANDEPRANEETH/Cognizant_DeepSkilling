public class FinancialForecaster {

    public static double predictFutureValue(double currentValue, double growthRate, int periods) {

        if (periods <= 0) {
            return currentValue;
        }

        return (1 + growthRate) * predictFutureValue(currentValue, growthRate, periods - 1);
    }

    public static void main(String[] args) {
        double pv = 1000.0;
        double rate = 0.05;
        int periods = 10;

        double futureValue = predictFutureValue(pv, rate, periods);
        System.out.println("Predicted Future Value: " + futureValue);
    }
}