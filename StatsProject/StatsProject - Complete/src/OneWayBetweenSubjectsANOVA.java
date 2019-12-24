import java.util.ArrayList;

public class OneWayBetweenSubjectsANOVA extends StatisticalTest implements MeanSquares, FTest, CriticalDifference {
	private double criticalValue;
	
	public OneWayBetweenSubjectsANOVA(ArrayList<ArrayList<Integer>> data, ArrayList<String> dataLabels, double criticalValue) {
		super(data, dataLabels);
		this.criticalValue = criticalValue;
	}

	@Override
	public void calculateMean() {
		double groupOneSum = statisticsCalculations.get("Group 1 Sum");
		double groupTwoSum = statisticsCalculations.get("Group 2 Sum");
		double groupThreeSum = statisticsCalculations.get("Group 3 Sum");
		double littleN = statisticsCalculations.get("Little N");
		
		double groupOneMean = groupOneSum / littleN;
		double groupTwoMean = groupTwoSum / littleN;
		double groupThreeMean = groupThreeSum / littleN;
		
		statisticsCalculations.put("Group 1 Mean", groupOneMean);
		statisticsCalculations.put("Group 2 Mean", groupTwoMean);
		statisticsCalculations.put("Group 3 Mean", groupThreeMean);

	}

	@Override
	public void calculateSumOfData() {
		ArrayList<Integer> groupOneData = data.get(0);
		ArrayList<Integer> groupTwoData = data.get(1);
		ArrayList<Integer> groupThreeData = data.get(2);
		
		double groupOneSum = 0.0;
		double groupTwoSum = 0.0;
		double groupThreeSum = 0.0;
		
		for(int i = 0; i < groupOneData.size(); i++) {
			groupOneSum += groupOneData.get(i);
		}
		
		for(int i = 0; i < groupTwoData.size(); i++) {
			groupTwoSum += groupTwoData.get(i);
		}
		
		for(int i = 0; i < groupThreeData.size(); i++) {
			groupThreeSum += groupThreeData.get(i);
		}
		
		double totalGroupSum = groupOneSum + groupTwoSum + groupThreeSum;
		
		statisticsCalculations.put("Group 1 Sum", groupOneSum);
		statisticsCalculations.put("Group 2 Sum", groupTwoSum);
		statisticsCalculations.put("Group 3 Sum", groupThreeSum);
		statisticsCalculations.put("Total Group Sum", totalGroupSum);

	}

	@Override
	public void calculateSumOfSquaredData() {
		ArrayList<Integer> groupOneData = data.get(0);
		ArrayList<Integer> groupTwoData = data.get(1);
		ArrayList<Integer> groupThreeData = data.get(2);
		double groupOneSum = statisticsCalculations.get("Group 1 Sum");
		double groupTwoSum = statisticsCalculations.get("Group 2 Sum");
		double groupThreeSum = statisticsCalculations.get("Group 3 Sum");
		
		double groupOneSumOfSquaredData = 0.0;
		double groupTwoSumOfSquaredData = 0.0;
		double groupThreeSumOfSquaredData = 0.0;
		
		for(int i = 0; i < groupOneData.size(); i++) {
			groupOneSumOfSquaredData += Math.pow(groupOneData.get(i), 2);
		}
		
		for(int i = 0; i < groupTwoData.size(); i++) {
			groupTwoSumOfSquaredData += Math.pow(groupTwoData.get(i), 2);
		}
		
		for(int i = 0; i < groupThreeData.size(); i++) {
			groupThreeSumOfSquaredData += Math.pow(groupThreeData.get(i), 2);
		}
		
		double totalSumOfSquaredData = groupOneSumOfSquaredData + groupTwoSumOfSquaredData + groupThreeSumOfSquaredData;
		double sumOfSquaredT = Math.pow(groupOneSum, 2) + Math.pow(groupTwoSum, 2) + Math.pow(groupThreeSum, 2);
		
		statisticsCalculations.put("Group 1 Sum Of Squared Data", groupOneSumOfSquaredData);
		statisticsCalculations.put("Group 2 Sum Of Squared Data", groupTwoSumOfSquaredData);
		statisticsCalculations.put("Group 3 Sum Of Squared Data", groupThreeSumOfSquaredData);
		statisticsCalculations.put("Total Sum Of Squared Data", totalSumOfSquaredData);
		statisticsCalculations.put("Sum Of Squared T", sumOfSquaredT);

	}

	@Override
	public void calculateSumOfDataSquared() {
		double totalGroupSum = statisticsCalculations.get("Total Group Sum");
		
		double totalGroupSumSquared = Math.pow(totalGroupSum, 2);
		
		statisticsCalculations.put("Total Group Sum Squared", totalGroupSumSquared);

	}

	@Override
	public void calculateSampleSize() {
		double groupOneSampleSize = data.get(0).size();
		double groupTwoSampleSize = data.get(1).size();	
		double groupThreeSampleSize = data.get(2).size();
		
		double littleN = groupOneSampleSize;
		double bigN = groupOneSampleSize + groupTwoSampleSize + groupThreeSampleSize;
		
		statisticsCalculations.put("Little N", littleN);
		statisticsCalculations.put("Big N", bigN);

	}

	@Override
	public void calculateEstimatedStandardDeviation() {
		ArrayList<Integer> groupOneData = data.get(0);
		ArrayList<Integer> groupTwoData = data.get(1);
		ArrayList<Integer> groupThreeData = data.get(2);
		double groupOneMean = statisticsCalculations.get("Group 1 Mean");
		double groupTwoMean = statisticsCalculations.get("Group 2 Mean");
		double groupThreeMean = statisticsCalculations.get("Group 3 Mean");
		double littleN = statisticsCalculations.get("Little N");
		
		double sumOfModifiedGroupOneData = 0.0;
		double sumOfModifiedGroupTwoData = 0.0;
		double sumOfModifiedGroupThreeData = 0.0;
		
		for(int i = 0; i < groupOneData.size(); i++) {
			double modifiedData = Math.pow((groupOneData.get(i) - groupOneMean), 2);
			sumOfModifiedGroupOneData += modifiedData;
		}
		
		for(int i = 0; i < groupTwoData.size(); i++) {
			double modifiedData = Math.pow((groupTwoData.get(i) - groupTwoMean), 2);
			sumOfModifiedGroupTwoData += modifiedData;
		}
		
		for(int i = 0; i < groupThreeData.size(); i++) {
			double modifiedData = Math.pow((groupThreeData.get(i) - groupThreeMean), 2);
			sumOfModifiedGroupThreeData += modifiedData;
		}
		
		double groupOneEstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupOneData / (littleN - 1));
		double groupTwoEstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupTwoData / (littleN - 1));
		double groupThreeEstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupThreeData / (littleN - 1));
		
		statisticsCalculations.put("Group 1 Estimated Standard Deviation", groupOneEstimatedStandardDeviation);
		statisticsCalculations.put("Group 2 Estimated Standard Deviation", groupTwoEstimatedStandardDeviation);
		statisticsCalculations.put("Group 3 Estimated Standard Deviation", groupThreeEstimatedStandardDeviation);

	}
	
	private void calculateTotalGroupSumSquaredOverBigN() {
		double totalGroupSumSquared = statisticsCalculations.get("Total Group Sum Squared");
		double bigN = statisticsCalculations.get("Big N");
		
		double totalGroupSumSquaredOverBigN = totalGroupSumSquared / bigN;
		
		statisticsCalculations.put("Total Group Sum Squared Over Big N", totalGroupSumSquaredOverBigN);
		
	}
	
	private void calculateSumOfSquaredTOverLittleN() {
		double sumOfSquaredT = statisticsCalculations.get("Sum Of Squared T");
		double littleN = statisticsCalculations.get("Little N");
		
		double sumOfSquaredTOverLittleN = sumOfSquaredT / littleN;
		
		statisticsCalculations.put("Sum Of Squared T Over Little N", sumOfSquaredTOverLittleN);
		
	}

	@Override
	public void calculateEtaSquared() {
		double sumOfSquaresOfBetween = statisticsCalculations.get("Sum Of Squares Of Between");
		double sumOfSquaresOfTotal = statisticsCalculations.get("Sum Of Squares Of Total");
		
		double etaSquared = sumOfSquaresOfBetween / sumOfSquaresOfTotal;
		
		statisticsCalculations.put("Eta Squared", etaSquared);

	}

	@Override
	public void calculateSumOfSquares() {
		double sumOfSquaredTOverLittleN = statisticsCalculations.get("Sum Of Squared T Over Little N");
		double totalGroupSumSquaredOverBigN = statisticsCalculations.get("Total Group Sum Squared Over Big N");
		double totalSumOfSquaredData = statisticsCalculations.get("Total Sum Of Squared Data");
		
		double sumOfSquaresOfTotal = totalSumOfSquaredData - totalGroupSumSquaredOverBigN;
		double sumOfSquaresOfBetween = sumOfSquaredTOverLittleN - totalGroupSumSquaredOverBigN;
		double sumOfSquaresOfWithin = totalSumOfSquaredData - sumOfSquaredTOverLittleN;
		
		statisticsCalculations.put("Sum Of Squares Of Total", sumOfSquaresOfTotal);
		statisticsCalculations.put("Sum Of Squares Of Between", sumOfSquaresOfBetween);
		statisticsCalculations.put("Sum Of Squares Of Within", sumOfSquaresOfWithin);

	}

	@Override
	public void calculateDegreesOfFreedom() {
		double bigN = statisticsCalculations.get("Big N");
		double k = data.size();
		
		double degreesOfFreedomForTotal = bigN - 1;
		double degreesOfFreedomForBetween = k - 1;
		double degreesOfFreedomForWithin = bigN - k;
		
		statisticsCalculations.put("Degrees Of Freedom For Total", degreesOfFreedomForTotal);
		statisticsCalculations.put("Degrees Of Freedom For Between", degreesOfFreedomForBetween);
		statisticsCalculations.put("Degrees Of Freedom For Within", degreesOfFreedomForWithin);
		

	}
	
	@Override
	public void calculateCriticalDifference() {
		double meanSquaresOfWithin = statisticsCalculations.get("Mean Squares Of Within");
		double littleN = statisticsCalculations.get("Little N");
		int k = data.size();
		double degreesOfFreedomForWithin = statisticsCalculations.get("Degrees Of Freedom For Within");
		
		double q = StudentizedRangeValueTable.calculateStudentizedRangeValue(degreesOfFreedomForWithin, criticalValue, k);
		
		double criticalDifference = q * Math.sqrt(meanSquaresOfWithin / littleN);
		
		statisticsCalculations.put("Critical Difference", criticalDifference);

	}

	@Override
	public void calculateFTest() {
		double meanSquaresOfBetween = statisticsCalculations.get("Mean Squares Of Between");
		double meanSquaresOfWithin = statisticsCalculations.get("Mean Squares Of Within");

		double fTest = meanSquaresOfBetween / meanSquaresOfWithin;
		
		statisticsCalculations.put("F Test", fTest);
		
	}

	@Override
	public void calculateMeanSquares() {
		double sumOfSquaresOfBetween = statisticsCalculations.get("Sum Of Squares Of Between");
		double sumOfSquaresOfWithin = statisticsCalculations.get("Sum Of Squares Of Within");
		double degreesOfFreedomForBetween = statisticsCalculations.get("Degrees Of Freedom For Between");
		double degreesOfFreedomForWithin = statisticsCalculations.get("Degrees Of Freedom For Within");
		
		double meanSquaresOfBetween = sumOfSquaresOfBetween / degreesOfFreedomForBetween;
		double meanSquaresOfWithin = sumOfSquaresOfWithin / degreesOfFreedomForWithin;
		
		statisticsCalculations.put("Mean Squares Of Between", meanSquaresOfBetween);
		statisticsCalculations.put("Mean Squares Of Within", meanSquaresOfWithin);

	}

	@Override
	public void calculateStatistics() {
		calculateSampleSize();
		calculateSumOfData();
		calculateMean();
		calculateSumOfSquaredData();
		calculateSumOfDataSquared();
		calculateEstimatedStandardDeviation();
		calculateTotalGroupSumSquaredOverBigN();
		calculateSumOfSquaredTOverLittleN();
		calculateSumOfSquares();
		calculateDegreesOfFreedom();
		calculateMeanSquares();
		calculateFTest();
		calculateEtaSquared();
		calculateCriticalDifference();

	}

	@Override
	public String printMean() {
		double groupOneMean = statisticsCalculations.get("Group 1 Mean");
		double groupTwoMean = statisticsCalculations.get("Group 2 Mean");
		double groupThreeMean = statisticsCalculations.get("Group 3 Mean");
		
		String format = "Group 1 Mean = " + groupOneMean
				      + "\tGroup 2 Mean = " + groupTwoMean
				      + "\tGroup 3 Mean = " + groupThreeMean;
		
		return format;
	}

	@Override
	public String printSumOfData() {
		double groupOneSum = statisticsCalculations.get("Group 1 Sum");
		double groupTwoSum = statisticsCalculations.get("Group 2 Sum");
		double groupThreeSum = statisticsCalculations.get("Group 3 Sum");
		double totalGroupSum = statisticsCalculations.get("Total Group Sum");
		
		String format = "Group 1 Sum = " + groupOneSum
				      + "\tGroup 2 Sum = " + groupTwoSum
				      + "\tGroup 3 Sum = " + groupThreeSum
				      + "\nTotal Group Sum = " + totalGroupSum;
		
		return format;
	}

	@Override
	public String printSumOfSquaredData() {
		double groupOneSumOfSquaredData = statisticsCalculations.get("Group 1 Sum Of Squared Data");
		double groupTwoSumOfSquaredData = statisticsCalculations.get("Group 2 Sum Of Squared Data");
		double groupThreeSumOfSquaredData = statisticsCalculations.get("Group 3 Sum Of Squared Data");
		double totalSumOfSquaredData = statisticsCalculations.get("Total Sum Of Squared Data");
		double sumOfSquaredT = statisticsCalculations.get("Sum Of Squared T");
		
		String format = "Group 1 Sum Of Squared Data = " + groupOneSumOfSquaredData
					  + "\nGroup 2 Sum Of Squared Data = " + groupTwoSumOfSquaredData
					  + "\nGroup 3 Sum Of Squared Data = " + groupThreeSumOfSquaredData
					  + "\nTotal Sum Of Squared Data = " + totalSumOfSquaredData
					  + "\tSum Of Squared T = " + sumOfSquaredT;
		
		return format;
	}

	@Override
	public String printSumOfDataSquared() {
		double totalGroupSumSquared = statisticsCalculations.get("Total Group Sum Squared");
		
		String format = "Total Group Sum Squared = " + totalGroupSumSquared;
		
		return format;
	}

	@Override
	public String printSampleSize() {
		double littleN = statisticsCalculations.get("Little N");
		double bigN = statisticsCalculations.get("Big N");
		
		String format = "n = " + littleN
					  + "\tN = " + bigN;
		
		return format;
	}

	@Override
	public String printEstimatedStandardDeviation() {
		double groupOneEstimatedStandardDeviation = statisticsCalculations.get("Group 1 Estimated Standard Deviation");
		double groupTwoEstimatedStandardDeviation = statisticsCalculations.get("Group 2 Estimated Standard Deviation");
		double groupThreeEstimatedStandardDeviation = statisticsCalculations.get("Group 3 Estimated Standard Deviation");
		
		String format = "Group 1 Estimated Standard Deviation = " + groupOneEstimatedStandardDeviation
					  + "\nGroup 2 Estimated Standard Deviation = " + groupTwoEstimatedStandardDeviation
					  + "\nGroup 3 Estimated Standard Deviation = " + groupThreeEstimatedStandardDeviation;
		
		return format;
	}

	@Override
	public String printEtaSquared() {
		double etaSquared = statisticsCalculations.get("Eta Squared");
		
		String format = "Eta Squared = " + etaSquared;
		
		return format;
	}

	@Override
	public String printSumOfSquares() {
		double sumOfSquaresOfTotal = statisticsCalculations.get("Sum Of Squares Of Total");
		double sumOfSquaresOfBetween = statisticsCalculations.get("Sum Of Squares Of Between");
		double sumOfSquaresOfWithin = statisticsCalculations.get("Sum Of Squares Of Within");
		
		String format = "\nSum Of Squares Of Total = " + sumOfSquaresOfTotal
				      + "\nSum Of Squares Of Between = " + sumOfSquaresOfBetween
				      + "\nSum Of Squares Of Within = " + sumOfSquaresOfWithin + "\n";
		
		return format;
	}

	@Override
	public String printDegreesOfFreedom() {
		double degreesOfFreedomForTotal = statisticsCalculations.get("Degrees Of Freedom For Total");
		double degreesOfFreedomForBetween = statisticsCalculations.get("Degrees Of Freedom For Between");
		double degreesOfFreedomForWithin = statisticsCalculations.get("Degrees Of Freedom For Within");
		
		String format = "Degrees Of Freedom For Total = " + degreesOfFreedomForTotal
				      + "\nDegrees Of Freedom For Between = " + degreesOfFreedomForBetween
				      + "\nDegrees Of Freedom For Within = " + degreesOfFreedomForWithin + "\n";
		
		return format;
	}
	
	@Override
	public String printCriticalDifference() {
		double criticalDifference = statisticsCalculations.get("Critical Difference");
		
		String format = "Critical Difference = " + criticalDifference;
		
		return format;
	}
	
	@Override
	public String printFTest() {
		double fTest = statisticsCalculations.get("F Test");
		
		String format = "F Test = " + fTest;
		
		return format;
	}
	
	@Override
	public String printMeanSquares() {
		double meanSquaresOfBetween = statisticsCalculations.get("Mean Squares Of Between");
		double meanSquaresOfWithin = statisticsCalculations.get("Mean Squares Of Within");
		
		String format = "Mean Squares Of Between = " + meanSquaresOfBetween
				      + "\tMean Squares Of Within = " + meanSquaresOfWithin;
		
		return format;
	}

	@Override
	public String printStatistics() {
		String format = printMean() + "\n"
				      + printSumOfData() + "\n"
				      + printSumOfSquaredData() + "\n"
				      + printSumOfDataSquared() + "\n"
				      + printSampleSize() + "\n"
				      + printEstimatedStandardDeviation() + "\n"
				      + printEtaSquared() + "\n"
				      + printSumOfSquares() + "\n"
				      + printDegreesOfFreedom() + "\n"
				      + printCriticalDifference() + "\n"
				      + printFTest() + "\n"
				      + printMeanSquares() + "\n";
				      
		return format;
	}

}
