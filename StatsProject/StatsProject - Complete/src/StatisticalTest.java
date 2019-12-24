import java.util.ArrayList;
import java.util.HashMap;

public abstract class StatisticalTest {
	public ArrayList<ArrayList<Integer>> data;
	public ArrayList<String> dataLabels;
	public HashMap<String, Double> statisticsCalculations = new HashMap<String, Double>();
	
	public StatisticalTest(ArrayList<ArrayList<Integer>> data, ArrayList<String> dataLabels) {
		this.data = data;
		this.dataLabels = dataLabels;
	}
	
	public abstract void calculateMean();
	public abstract void calculateSumOfData();
	public abstract void calculateSumOfSquaredData();
	public abstract void calculateSumOfDataSquared();
	public abstract void calculateSampleSize();
	public abstract void calculateEstimatedStandardDeviation();
	public abstract void calculateEtaSquared();
	public abstract void calculateSumOfSquares();
	public abstract void calculateDegreesOfFreedom();
	public abstract void calculateStatistics();
	public abstract String printMean();
	public abstract String printSumOfData();
	public abstract String printSumOfSquaredData();
	public abstract String printSumOfDataSquared();
	public abstract String printSampleSize();
	public abstract String printEstimatedStandardDeviation();
	public abstract String printEtaSquared();
	public abstract String printSumOfSquares();
	public abstract String printDegreesOfFreedom();
	public abstract String printStatistics();
	
}
