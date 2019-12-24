import java.util.ArrayList;
import java.lang.Math;

public class IndependentGroupsTTest extends StatisticalTest implements TTest, EstimatedStandardErrorOfTheDifference {
	
	private double criticalValue;
	private String typeOfDirectionalTest;
	
	public IndependentGroupsTTest(ArrayList<ArrayList<Integer>> data, ArrayList<String> dataLabels, double criticalValue, String typeOfDirectionalTest) {
		super(data, dataLabels);
		this.criticalValue = criticalValue;
		this.typeOfDirectionalTest = typeOfDirectionalTest;
	}

	public void calculateMean() {
		double groupOneSum = statisticsCalculations.get("Group 1 Sum");
		double groupTwoSum = statisticsCalculations.get("Group 2 Sum");
		
		double groupOneSampleSize = statisticsCalculations.get("Group 1 Sample Size");
		double groupTwoSampleSize = statisticsCalculations.get("Group 2 Sample Size");
		
		double groupOneMean = groupOneSum / groupOneSampleSize;
		double groupTwoMean = groupTwoSum / groupTwoSampleSize;
		
		statisticsCalculations.put("Group 1 Mean", groupOneMean);
		statisticsCalculations.put("Group 2 Mean", groupTwoMean);
		
	}
	
	public void calculateSumOfData() {
		double groupOneSum = 0.0;
		double groupTwoSum = 0.0;
		
		for(int i = 0; i < data.size(); i++) {
			for(int j = 0; j < data.get(i).size(); j++) {
				if(i == 0) {
					groupOneSum += data.get(i).get(j);
				} else {
					groupTwoSum += data.get(i).get(j);
				}
			}
		}
		
		statisticsCalculations.put("Group 1 Sum", groupOneSum);
		statisticsCalculations.put("Group 2 Sum", groupTwoSum);
		
	}

	public void calculateSumOfSquaredData() {
		ArrayList<Integer> groupOneData = data.get(0);
		ArrayList<Integer> groupTwoData = data.get(1);
		
		double groupOneSumOfSquaredData = 0;
		double groupTwoSumOfSquaredData = 0;
		
		for(int i = 0; i < groupOneData.size(); i++) {
			double squaredData = Math.pow(groupOneData.get(i), 2);
			groupOneSumOfSquaredData += squaredData;
		}
		
		for(int i = 0; i < groupTwoData.size(); i++) {
			double squaredData = Math.pow(groupTwoData.get(i), 2);
			groupTwoSumOfSquaredData += squaredData;
		}
		
		statisticsCalculations.put("Group 1 Sum Of Squared Data", groupOneSumOfSquaredData);
		statisticsCalculations.put("Group 2 Sum Of Squared Data", groupTwoSumOfSquaredData);
		
	}

	public void calculateSumOfDataSquared() {
		double groupOneSum = statisticsCalculations.get("Group 1 Sum");
		double groupTwoSum = statisticsCalculations.get("Group 2 Sum");
		
		double groupOneSumSquared = Math.pow(groupOneSum, 2);
		double groupTwoSumSquared = Math.pow(groupTwoSum, 2);
		
		statisticsCalculations.put("Group 1 Sum Squared", groupOneSumSquared);
		statisticsCalculations.put("Group 2 Sum Squared", groupTwoSumSquared);
		
	}

	public void calculateSampleSize() {
		double groupOneSampleSize = data.get(0).size();
		double groupTwoSampleSize = data.get(1).size();
		
		double bigN = groupOneSampleSize + groupTwoSampleSize;
		
		statisticsCalculations.put("Group 1 Sample Size", groupOneSampleSize);
		statisticsCalculations.put("Group 2 Sample Size", groupTwoSampleSize);
		statisticsCalculations.put("N", bigN);
		
	}

	public void calculateEstimatedStandardDeviation() {
		double groupOneSumOfSquares = statisticsCalculations.get("Group 1 Sum Of Squares");
		double groupTwoSumOfSquares = statisticsCalculations.get("Group 2 Sum Of Squares");
		double groupOneSampleSize = statisticsCalculations.get("Group 1 Sample Size");
		double groupTwoSampleSize = statisticsCalculations.get("Group 2 Sample Size");
		
		double groupOneEstimatedStandardDeviation = Math.sqrt(groupOneSumOfSquares / (groupOneSampleSize - 1));
		double groupTwoEstimatedStandardDeviation = Math.sqrt(groupTwoSumOfSquares / (groupTwoSampleSize - 1));
		
		statisticsCalculations.put("Group 1 Estimated Standard Deviation", groupOneEstimatedStandardDeviation);
		statisticsCalculations.put("Group 2 Estimated Standard Deviation", groupTwoEstimatedStandardDeviation);
		
	}

	public void calculateEtaSquared() {
		double tTest = statisticsCalculations.get("T Test");	
		double degreesOfFreedom = statisticsCalculations.get("Degrees Of Freedom");
		
		double tTestSquared = Math.pow(tTest, 2);
		
		double etaSquared = tTestSquared / (tTestSquared + degreesOfFreedom);
		
		statisticsCalculations.put("Eta Squared", etaSquared);
	}

	public void calculateSumOfSquares() {
		double groupOneSumOfSquaredData = statisticsCalculations.get("Group 1 Sum Of Squared Data");
		double groupTwoSumOfSquaredData = statisticsCalculations.get("Group 2 Sum Of Squared Data");
		
		double groupOneSumSquared = statisticsCalculations.get("Group 1 Sum Squared");
		double groupTwoSumSquared = statisticsCalculations.get("Group 2 Sum Squared");
		
		double groupOneSampleSize = statisticsCalculations.get("Group 1 Sample Size");
		double groupTwoSampleSize = statisticsCalculations.get("Group 2 Sample Size");
		
		double groupOneSumOfSquares = groupOneSumOfSquaredData - (groupOneSumSquared / groupOneSampleSize);
		double groupTwoSumOfSquares = groupTwoSumOfSquaredData - (groupTwoSumSquared / groupTwoSampleSize);
		
		statisticsCalculations.put("Group 1 Sum Of Squares", groupOneSumOfSquares);
		statisticsCalculations.put("Group 2 Sum Of Squares", groupTwoSumOfSquares);
		
	}

	public void calculateDegreesOfFreedom() {
		double bigN = statisticsCalculations.get("N");
		
		double degreesOfFreedom = bigN - 2;
		
		statisticsCalculations.put("Degrees Of Freedom", degreesOfFreedom);

	}
	
	public void calculateTTest() {
		double groupOneMean = statisticsCalculations.get("Group 1 Mean");
		double groupTwoMean = statisticsCalculations.get("Group 2 Mean");
		
		double estimatedStandardErrorOfTheDifference = statisticsCalculations.get("Estimated Standard Error Of The Difference");
		
		double degreesOfFreedom = statisticsCalculations.get("Degrees Of Freedom");
		
		double tTest = (groupOneMean - groupTwoMean) / estimatedStandardErrorOfTheDifference;
		double tTestWithCriticalValue = CriticalValueTDistributionTable.calculateCriticalValue(degreesOfFreedom, criticalValue, typeOfDirectionalTest);
		
		statisticsCalculations.put("T Test With Critical Value", tTestWithCriticalValue);
		statisticsCalculations.put("T Test", tTest);
	}
	
	public void calculateEstimatedStandardErrorOfTheDifference() {
		double groupOneSumOfSquares = statisticsCalculations.get("Group 1 Sum Of Squares");
		double groupTwoSumOfSquares = statisticsCalculations.get("Group 2 Sum Of Squares");
		
		double groupOneSampleSize = statisticsCalculations.get("Group 1 Sample Size");
		double groupTwoSampleSize = statisticsCalculations.get("Group 2 Sample Size");
		
		double estimatedStandardErrorOfTheDifference = Math.sqrt(((groupOneSumOfSquares + groupTwoSumOfSquares) / (groupOneSampleSize + groupTwoSampleSize - 2)) * ((1 / groupOneSampleSize) + (1 / groupTwoSampleSize)));
		
		statisticsCalculations.put("Estimated Standard Error Of The Difference", estimatedStandardErrorOfTheDifference);
		
	}

	public void calculateStatistics() {
		calculateSumOfData();
		calculateSampleSize();
		calculateMean();
		calculateSumOfDataSquared();
		calculateSumOfSquaredData();
		calculateDegreesOfFreedom();
		calculateSumOfSquares();
		calculateEstimatedStandardDeviation();
		calculateEstimatedStandardErrorOfTheDifference();
		calculateTTest();
		calculateEtaSquared();
		
	}

	public String printMean() {
		double groupOneMean = statisticsCalculations.get("Group 1 Mean");
		double groupTwoMean = statisticsCalculations.get("Group 2 Mean");
		
		String format = "Group 1 Mean = " + groupOneMean + "\tGroup 2 Mean = " + groupTwoMean;
		
		return format;

	}

	public String printSumOfData() {
		double groupOneSum = statisticsCalculations.get("Group 1 Sum");
		double groupTwoSum = statisticsCalculations.get("Group 2 Sum");
		
		String format = "Group 1 Sum = " + groupOneSum + "\tGroup 2 Sum = " + groupTwoSum;
		
		return format;

	}

	public String printSumOfSquaredData() {
		double groupOneSumOfSquaredData = statisticsCalculations.get("Group 1 Sum Of Squared Data");
		double groupTwoSumOfSquaredData = statisticsCalculations.get("Group 2 Sum Of Squared Data");
		
		String format = "Group 1 Sum Of Squared Data = " + groupOneSumOfSquaredData + "\tGroup 2 Sum Of Squared Data = " + groupTwoSumOfSquaredData;
		
		return format;

	}

	public String printSumOfDataSquared() {
		double groupOneSumSquared = statisticsCalculations.get("Group 1 Sum Squared");
		double groupTwoSumSquared = statisticsCalculations.get("Group 2 Sum Squared");
		
		String format = "Group 1 Sum Squared = " + groupOneSumSquared + "\tGroup 2 Sum Squared = " + groupTwoSumSquared;
		
		return format;

	}

	public String printSampleSize() {
		double groupOneSampleSize = statisticsCalculations.get("Group 1 Sample Size");
		double groupTwoSampleSize = statisticsCalculations.get("Group 2 Sample Size");
		double bigN = statisticsCalculations.get("N");
		
		String format = "Group 1 Sample Size = " + groupOneSampleSize + "\tGroup 2 Sample Size = " + groupTwoSampleSize + "\tN = " + bigN;
		
		return format;

	}

	public String printEstimatedStandardDeviation() {
		double groupOneEstimatedStandardDeviation = statisticsCalculations.get("Group 1 Estimated Standard Deviation");
		double groupTwoEstimatedStandardDeviation = statisticsCalculations.get("Group 2 Estimated Standard Deviation");
		
		String format = "Group 1 Estimated Standard Deviation = " + groupOneEstimatedStandardDeviation + "\nGroup 2 Estimated Standard Deviation = " + groupTwoEstimatedStandardDeviation;
				
		return format;

	}

	public String printEtaSquared() {
		double etaSquared = statisticsCalculations.get("Eta Squared");
		
		String format = "Eta Squared = " + etaSquared;
		
		return format;

	}

	public String printSumOfSquares() {
		double groupOneSumOfSquares = statisticsCalculations.get("Group 1 Sum Of Squares");
		double groupTwoSumOfSquares = statisticsCalculations.get("Group 2 Sum Of Squares");
		
		String format = "Group 1 Sum Of Squares = " + groupOneSumOfSquares + "\tGroup 2 Sum Of Squares = " + groupTwoSumOfSquares;
		
		return format;

	}

	public String printDegreesOfFreedom() {
		double degreesOfFreedom = statisticsCalculations.get("Degrees Of Freedom");
		
		String format = "Degrees Of Freedom = " + degreesOfFreedom;
		
		return format;

	}

	public String printTTest() {
		double tTest = statisticsCalculations.get("T Test");
		double tTestWithCriticalValue = statisticsCalculations.get("T Test With Critical Value");
		double degreesOfFreedom = statisticsCalculations.get("Degrees Of Freedom");
		
		String format = "t(" + degreesOfFreedom + ") = " + tTest + "\tt(" + degreesOfFreedom + ") " + criticalValue + " = " + tTestWithCriticalValue;
		
		return format;
		
	}
	
	public String printEstimatedStandardErrorOfTheDifference() {
		double estimatedStandardErrorOfTheDifference = statisticsCalculations.get("Estimated Standard Error Of The Difference");
		
		String format = "Estimated Standard Error Of The Difference = " + estimatedStandardErrorOfTheDifference;
		
		return format;
		
	}
	
	public String printStatistics() {
		String format = printMean() + "\n"
					  + printSumOfData() + "\n"
					  + printSumOfSquaredData() + "\n"
					  + printSumOfDataSquared() + "\n"
					  + printSampleSize() + "\n"
					  + printEstimatedStandardErrorOfTheDifference() + "\n"
					  + printEtaSquared() + "\n"
					  + printSumOfSquares() + "\n"
					  + printDegreesOfFreedom() + "\n"
					  + printTTest() + "\n"
					  + printEstimatedStandardDeviation();
		
		return format;

	}
}
