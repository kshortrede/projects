import java.util.ArrayList;

public class CorrelatedGroupsTTest extends StatisticalTest implements TTest {

	private ArrayList<Integer> differenceOfData = new ArrayList<Integer>();
	private double criticalValue;
	private String typeOfDirectionalTest;
	
	public CorrelatedGroupsTTest(ArrayList<ArrayList<Integer>> data, ArrayList<String> dataLabels, double criticalValue, String typeOfDirectionalTest) {
		super(data, dataLabels);
		this.criticalValue = criticalValue;
		this.typeOfDirectionalTest = typeOfDirectionalTest;
	}

	private void calculateDifferenceOfData() {
		double sampleSize = statisticsCalculations.get("Sample Size");
		ArrayList<Integer> groupOneData = data.get(0);
		ArrayList<Integer> groupTwoData = data.get(1);
		
		for(int i = 0; i < sampleSize; i++) {
			int difference = groupOneData.get(i) - groupTwoData.get(i);
			differenceOfData.add(difference);
		}
	}
	
	public void calculateMean() {
		double groupOneSum = statisticsCalculations.get("Group 1 Sum");
		double groupTwoSum = statisticsCalculations.get("Group 2 Sum");
		double differenceSum = statisticsCalculations.get("Sum Of The Difference");

		double sampleSize = statisticsCalculations.get("Sample Size");
		
		double groupOneMean = groupOneSum / sampleSize;
		double groupTwoMean = groupTwoSum / sampleSize;
		double differenceMean = differenceSum / sampleSize;
		
		statisticsCalculations.put("Group 1 Mean", groupOneMean);
		statisticsCalculations.put("Group 2 Mean", groupTwoMean);
		statisticsCalculations.put("Mean Of The Difference", differenceMean);
	}

	public void calculateSumOfData() {
		double groupOneSum = 0.0;
		double groupTwoSum = 0.0;
		double differenceSum = 0.0;
		
		ArrayList<Integer> groupOneData = data.get(0);
		ArrayList<Integer> groupTwoData = data.get(1);
		
		for(int i = 0; i < groupOneData.size(); i++) {
			groupOneSum += groupOneData.get(i);
		}
		
		for(int i = 0; i < groupTwoData.size(); i++) {
			groupTwoSum += groupTwoData.get(i);
		}
		
		for(int i = 0; i < differenceOfData.size(); i++) {
			differenceSum += differenceOfData.get(i);
		}

		statisticsCalculations.put("Group 1 Sum", groupOneSum);
		statisticsCalculations.put("Group 2 Sum", groupTwoSum);
		statisticsCalculations.put("Sum Of The Difference", differenceSum);
		
	}

	public void calculateSumOfSquaredData() {
		double sumOfSquaredDifferenceData = 0.0;
		
		for(int i = 0; i < differenceOfData.size(); i++) {
			sumOfSquaredDifferenceData += Math.pow(differenceOfData.get(i), 2);
		}

		statisticsCalculations.put("Sum Of Squared Difference Data", sumOfSquaredDifferenceData);
	}

	public void calculateSumOfDataSquared() {
		double sumOfDifferenceData = statisticsCalculations.get("Sum Of The Difference");
		
		double sumOfDifferenceDataSquared = Math.pow(sumOfDifferenceData, 2);
		
		statisticsCalculations.put("Sum Of Difference Data Squared", sumOfDifferenceDataSquared);

	}

	public void calculateSampleSize() {
		double sampleSize = data.get(0).size();

		statisticsCalculations.put("Sample Size", sampleSize);
	}

	public void calculateEstimatedStandardDeviation() {
		ArrayList<Integer> groupOneData = data.get(0);
		ArrayList<Integer> groupTwoData = data.get(1);
		double groupOneMean = statisticsCalculations.get("Group 1 Mean");
		double groupTwoMean = statisticsCalculations.get("Group 2 Mean");
		double sumOfSquaresOfTheDifference  = statisticsCalculations.get("Sum Of Squares Of The Difference");
		double sampleSize = statisticsCalculations.get("Sample Size");
		
		double sumOfModifiedGroupOneData = 0;
		double sumOfModifiedGroupTwoData = 0;
		
		for(int i = 0; i < groupOneData.size(); i++) {
			double modifiedData = Math.pow((groupOneData.get(i) - groupOneMean), 2);
			sumOfModifiedGroupOneData += modifiedData;
		}
		
		for(int i = 0; i < groupTwoData.size(); i++) {
			double modifiedData = Math.pow((groupTwoData.get(i) - groupTwoMean), 2);
			sumOfModifiedGroupTwoData += modifiedData;
		}
		
		double estimatedVarianceOfTheDifference = sumOfSquaresOfTheDifference / (sampleSize - 1);
		double estimatedStandardDeviationOfTheDifference = Math.sqrt(estimatedVarianceOfTheDifference);
		
		double groupOneEstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupOneData / (sampleSize - 1));
		double groupTwoEstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupTwoData / (sampleSize - 1));
		
		double estimatedStandardDeviationOfTheMeanDifference = estimatedStandardDeviationOfTheDifference / Math.sqrt(sampleSize);
		
		statisticsCalculations.put("Group 1 Estimated Standard Deviation", groupOneEstimatedStandardDeviation);
		statisticsCalculations.put("Group 2 Estimated Standard Deviation", groupTwoEstimatedStandardDeviation);
		statisticsCalculations.put("Estimated Variance Of The Difference", estimatedVarianceOfTheDifference);
		statisticsCalculations.put("Estimated Standard Deviation of the Difference", estimatedStandardDeviationOfTheDifference);
		statisticsCalculations.put("Estimated Standard Deviation Of The Mean Difference", estimatedStandardDeviationOfTheMeanDifference);

	}

	public void calculateEtaSquared() {
		double tTest = statisticsCalculations.get("T Test");	
		double degreesOfFreedom = statisticsCalculations.get("Degrees Of Freedom");
		
		double tTestSquared = Math.pow(tTest, 2);
		
		double etaSquared = tTestSquared / (tTestSquared + degreesOfFreedom);
		
		statisticsCalculations.put("Eta Squared", etaSquared);

	}

	public void calculateSumOfSquares() {
		double sampleSize = statisticsCalculations.get("Sample Size");
		double sumOfDifferenceDataSquared = statisticsCalculations.get("Sum Of Difference Data Squared");
		double sumOfSquaredDifferenceData = statisticsCalculations.get("Sum Of Squared Difference Data");
		
		double sumOfSquaresOfTheDifference = sumOfSquaredDifferenceData - (sumOfDifferenceDataSquared / sampleSize);
		
		statisticsCalculations.put("Sum Of Squares Of The Difference", sumOfSquaresOfTheDifference);

	}

	public void calculateDegreesOfFreedom() {
		double sampleSize = statisticsCalculations.get("Sample Size");
		
		double degreesOfFreedom = sampleSize - 1;
		
		statisticsCalculations.put("Degrees Of Freedom", degreesOfFreedom);

	}
	
	public void calculateTTest() {
		double differenceMean = statisticsCalculations.get("Mean Of The Difference");
		
		double estimatedStandardDeviationOfTheMeanDifference = statisticsCalculations.get("Estimated Standard Deviation Of The Mean Difference");
		
		double degreesOfFreedom = statisticsCalculations.get("Degrees Of Freedom");
		
		double tTest = differenceMean / estimatedStandardDeviationOfTheMeanDifference;
		double tTestWithCriticalValue = CriticalValueTDistributionTable.calculateCriticalValue(degreesOfFreedom, criticalValue, typeOfDirectionalTest);
		
		statisticsCalculations.put("T Test With Critical Value", tTestWithCriticalValue);
		statisticsCalculations.put("T Test", tTest);
	}

	public void calculateStatistics() {
		calculateSampleSize();
		calculateDifferenceOfData();
		calculateSumOfData();
		calculateMean();
		calculateSumOfSquaredData();
		calculateSumOfDataSquared();
		calculateDegreesOfFreedom();
		calculateSumOfSquares();
		calculateEstimatedStandardDeviation();
		calculateTTest();
		calculateEtaSquared();

	}

	public String printMean() {
		double groupOneMean = statisticsCalculations.get("Group 1 Mean");
		double groupTwoMean = statisticsCalculations.get("Group 2 Mean");
		double differenceMean = statisticsCalculations.get("Mean Of The Difference");
		
		String format = "Group 1 Mean = " + groupOneMean + "\tGroup 2 Mean = " + groupTwoMean + "\tMean Of The Difference = " + differenceMean;
		
		return format;
	}

	public String printSumOfData() {
		double groupOneSum = statisticsCalculations.get("Group 1 Sum");
		double groupTwoSum = statisticsCalculations.get("Group 2 Sum");
		double differenceSum = statisticsCalculations.get("Sum Of The Difference");
		
		String format = "Group 1 Sum = " + groupOneSum + "\tGroup 2 Sum = " + groupTwoSum + "\tSum Of The Difference = " + differenceSum;
		
		return format;
	}

	public String printSumOfSquaredData() {
		double sumOfSquaredDifferenceData = statisticsCalculations.get("Sum Of Squared Difference Data");
		
		String format = "Sum Of Squared Difference Data = " + sumOfSquaredDifferenceData;
		
		return format;
	}

	public String printSumOfDataSquared() {
		double sumOfDifferenceDataSquared = statisticsCalculations.get("Sum Of Difference Data Squared");
		
		String format = "Sum Of Difference Data Squared = " + sumOfDifferenceDataSquared;
		
		return format;
	}

	public String printSampleSize() {
		double sampleSize = statisticsCalculations.get("Sample Size");
		
		String format = "Sample Size = " + sampleSize;
		
		return format;
	}

	public String printEstimatedStandardDeviation() {
		double groupOneEstimatedStandardDeviation = statisticsCalculations.get("Group 1 Estimated Standard Deviation");
		double groupTwoEstimatedStandardDeviation = statisticsCalculations.get("Group 2 Estimated Standard Deviation");
		double estimatedVarianceOfTheDifference = statisticsCalculations.get("Estimated Variance Of The Difference");
		double estimatedStandardDeviationOfTheDifference = statisticsCalculations.get("Estimated Standard Deviation of the Difference");
		double estimatedStandardDeviationOfTheMeanDifference = statisticsCalculations.get("Estimated Standard Deviation Of The Mean Difference");
		
		String format = "Group 1 Estimated Standard Deviation = " + groupOneEstimatedStandardDeviation + "\nGroup 2 Estimated Standard Deviation = " + groupTwoEstimatedStandardDeviation
					  + "\nEstimated Variance Of The Difference = " + estimatedVarianceOfTheDifference + "\tEstimated Standard Deviation of the Difference = " + estimatedStandardDeviationOfTheDifference
					  + "\nEstimated Standard Deviation Of The Mean Difference = " + estimatedStandardDeviationOfTheMeanDifference;
		
		return format;
	}

	public String printEtaSquared() {
		double etaSquared = statisticsCalculations.get("Eta Squared");
		
		String format = "Eta Squared = " + etaSquared;
		
		return format;
	}

	public String printSumOfSquares() {
		double sumOfSquaresOfTheDifference = statisticsCalculations.get("Sum Of Squares Of The Difference");
		
		String format = "Sum Of Squares Of The Difference = " + sumOfSquaresOfTheDifference;
		
		return format;
	}

	public String printDegreesOfFreedom() {
		double degreesOfFreedom = statisticsCalculations.get("Degrees Of Freedom");
		
		String format = "Degrees of Freedom = " + degreesOfFreedom;
		
		return format;
	}

	public String printTTest() {
		double tTest = statisticsCalculations.get("T Test");
		double tTestWithCriticalValue = statisticsCalculations.get("T Test With Critical Value");
		
		String format = "T Test = " + tTest + "\tT Test with Critical Value = " + tTestWithCriticalValue;
		
		return format;
	}
	
	public String printStatistics() {
		String format = printMean() + "\n"
					  + printSumOfData() + "\n"
					  + printSumOfSquaredData() + "\n"
					  + printSumOfDataSquared() + "\n"
					  + printSampleSize() + "\n ****************************** \n"
					  + printEstimatedStandardDeviation() + "\n ****************************** \n"
					  + printEtaSquared() + "\n"
					  + printSumOfSquares() + "\n"
					  + printDegreesOfFreedom() + "\n"
					  + printTTest() + "\n";
		
		return format;
	}


}
