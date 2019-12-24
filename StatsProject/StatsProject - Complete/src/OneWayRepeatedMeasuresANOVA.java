import java.util.ArrayList;
import java.lang.Math;

public class OneWayRepeatedMeasuresANOVA extends StatisticalTest implements MeanSquares, FTest, CriticalDifference {
	
	private static ArrayList<Integer> valuesOfS = new ArrayList<Integer>();
	public double criticalValue;
	
	public OneWayRepeatedMeasuresANOVA(ArrayList<ArrayList<Integer>> data, ArrayList<String> dataLabels, double criticalValue) {
		super(data, dataLabels);
		this.criticalValue = criticalValue;
	}

	private void calculateValuesOfS() {
		ArrayList<Integer> groupOneData = data.get(0);
		ArrayList<Integer> groupTwoData = data.get(1);
		ArrayList<Integer> groupThreeData = data.get(2);
		double sampleSize = statisticsCalculations.get("Sample Size");
		
		for(int i = 0; i < sampleSize; i++) {
			int s = groupOneData.get(i) + groupTwoData.get(i) + groupThreeData.get(i);
			valuesOfS.add(s);
		}
	}
	
	private void calculateSumOfSquaredValuesOfS() {
		double sumOfSquaredValuesOfS = 0.0;
		
		for(int i = 0; i < valuesOfS.size(); i++) {
			sumOfSquaredValuesOfS += Math.pow(valuesOfS.get(i), 2);
		}
		
		statisticsCalculations.put("Sum Of Squared Values Of S", sumOfSquaredValuesOfS);
	}
	
	public void calculateMean() {
		double sampleSize = statisticsCalculations.get("Sample Size");
		double groupOneSum = statisticsCalculations.get("Group 1 Sum");
		double groupTwoSum = statisticsCalculations.get("Group 2 Sum");
		double groupThreeSum = statisticsCalculations.get("Group 3 Sum");

		double groupOneMean = groupOneSum / sampleSize;
		double groupTwoMean = groupTwoSum / sampleSize;
		double groupThreeMean = groupThreeSum / sampleSize;
		
		statisticsCalculations.put("Group 1 Mean", groupOneMean);
		statisticsCalculations.put("Group 2 Mean", groupTwoMean);
		statisticsCalculations.put("Group 3 Mean", groupThreeMean);
		
	}

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
		
		double totalSumOfGroups = groupOneSum + groupTwoSum + groupThreeSum;
		
		statisticsCalculations.put("Group 1 Sum", groupOneSum);
		statisticsCalculations.put("Group 2 Sum", groupTwoSum);
		statisticsCalculations.put("Group 3 Sum", groupThreeSum);
		statisticsCalculations.put("Total Sum Of Groups", totalSumOfGroups);

	}

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

	public void calculateSumOfDataSquared() {
		double totalSumOfGroups = statisticsCalculations.get("Total Sum Of Groups");
		
		double totalSumOfGroupsSquared = Math.pow(totalSumOfGroups, 2);
		
		statisticsCalculations.put("Total Sum Of Groups Squared", totalSumOfGroupsSquared);

	}

	public void calculateSampleSize() {
		double sampleSize = data.get(0).size();
		
		statisticsCalculations.put("Sample Size", sampleSize);

	}

	public void calculateEstimatedStandardDeviation() {
		ArrayList<Integer> groupOneData = data.get(0);
		ArrayList<Integer> groupTwoData = data.get(1);
		ArrayList<Integer> groupThreeData = data.get(2);
		double groupOneMean = statisticsCalculations.get("Group 1 Mean");
		double groupTwoMean = statisticsCalculations.get("Group 2 Mean");
		double groupThreeMean = statisticsCalculations.get("Group 3 Mean");
		double sampleSize = statisticsCalculations.get("Sample Size");
		
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
		
		double groupOneEstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupOneData / (sampleSize - 1));
		double groupTwoEstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupTwoData / (sampleSize - 1));
		double groupThreeEstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupThreeData / (sampleSize - 1));
		
		statisticsCalculations.put("Group 1 Estimated Standard Deviation", groupOneEstimatedStandardDeviation);
		statisticsCalculations.put("Group 2 Estimated Standard Deviation", groupTwoEstimatedStandardDeviation);
		statisticsCalculations.put("Group 3 Estimated Standard Deviation", groupThreeEstimatedStandardDeviation);

	}
	
	private void calculateSumOfDataSquaredOverKTimesN() {
		double totalSumOfGroupsSquared = statisticsCalculations.get("Total Sum Of Groups Squared");
		double sampleSize = statisticsCalculations.get("Sample Size");
		double k = data.size();
		
		double sumOfDataSquaredOverKTimesN = totalSumOfGroupsSquared / (k * sampleSize);
		
		statisticsCalculations.put("Sum Of Data Squared Over K Times N", sumOfDataSquaredOverKTimesN);
		
	}
	
	private void calculateSumOfSquaredTOverN() {
		double sumOfSquaredT = statisticsCalculations.get("Sum Of Squared T");
		double sampleSize = statisticsCalculations.get("Sample Size");
		
		double sumOfSquaredTOverN = sumOfSquaredT / sampleSize;
		
		statisticsCalculations.put("Sum Of Squared T Over N", sumOfSquaredTOverN);
		
	}
	
	public void calculateSumOfSquaredValuesOfSOverK() {
		double sumOfSquaredValuesOfS = statisticsCalculations.get("Sum Of Squared Values Of S");
		double k = data.size();
		
		double sumOfSquaredValuesOfSOverK = sumOfSquaredValuesOfS / k;
		
		statisticsCalculations.put("Sum Of Squared Values Of S Over K", sumOfSquaredValuesOfSOverK);
		
	}
	
	public void calculateEtaSquared() {
		double sumOfSquaresOfIV = statisticsCalculations.get("Sum Of Squares Of IV");
		double sumOfSquaresOfError = statisticsCalculations.get("Sum Of Squares Of Error");
		
		double etaSquared = sumOfSquaresOfIV / (sumOfSquaresOfIV + sumOfSquaresOfError);
		
		statisticsCalculations.put("Eta Squared", etaSquared);

	}

	public void calculateSumOfSquares() {
		double sumOfSquaredValuesOfSOverK = statisticsCalculations.get("Sum Of Squared Values Of S Over K");
		double sumOfSquaredTOverN = statisticsCalculations.get("Sum Of Squared T Over N");
		double sumOfDataSquaredOverKTimesN = statisticsCalculations.get("Sum Of Data Squared Over K Times N");
		double totalSumOfSquaredData = statisticsCalculations.get("Total Sum Of Squared Data");

		double sumOfSquaresOfTotal = totalSumOfSquaredData - sumOfDataSquaredOverKTimesN;
		double sumOfSquaresOfAcrossSubjects = sumOfSquaredValuesOfSOverK - sumOfDataSquaredOverKTimesN;
		double sumOfSquaresOfIV = sumOfSquaredTOverN - sumOfDataSquaredOverKTimesN;
		double sumOfSquaresOfError = sumOfSquaresOfTotal - sumOfSquaresOfIV - sumOfSquaresOfAcrossSubjects;
		
		statisticsCalculations.put("Sum Of Squares Of Total", sumOfSquaresOfTotal);
		statisticsCalculations.put("Sum Of Squares Of Across Subjects", sumOfSquaresOfAcrossSubjects);
		statisticsCalculations.put("Sum Of Squares Of IV", sumOfSquaresOfIV);
		statisticsCalculations.put("Sum Of Squares Of Error", sumOfSquaresOfError);
		
	}

	public void calculateDegreesOfFreedom() {
		double k = data.size();
		double sampleSize = statisticsCalculations.get("Sample Size");
		
		double degreesOfFreedomForTotal = (k * sampleSize) - 1;
		double degreesOfFreedomForIV = k - 1;
		double degreesOfFreedomForAcrossSubjects = sampleSize - 1;
		double degreesOfFreedomForError = degreesOfFreedomForIV * degreesOfFreedomForAcrossSubjects;
		
		statisticsCalculations.put("Degrees Of Freedom For Total", degreesOfFreedomForTotal);
		statisticsCalculations.put("Degrees Of Freedom For IV", degreesOfFreedomForIV);
		statisticsCalculations.put("Degrees Of Freedom For Across Subjects", degreesOfFreedomForAcrossSubjects);
		statisticsCalculations.put("Degrees Of Freedom For Error", degreesOfFreedomForError);

	}
	
	public void calculateCriticalDifference() {
		double meanSquaresOfError = statisticsCalculations.get("Mean Squares Of Error");
		double sampleSize = statisticsCalculations.get("Sample Size");
		int k = data.size();
		double degreesOfFreedomForError = statisticsCalculations.get("Degrees Of Freedom For Error");
		
		double q = StudentizedRangeValueTable.calculateStudentizedRangeValue(degreesOfFreedomForError, criticalValue, k);
		
		double criticalDifference = q * Math.sqrt(meanSquaresOfError / sampleSize);
		
		statisticsCalculations.put("Critical Difference", criticalDifference);
		
	}

	public void calculateFTest() {
		double meanSquaresOfIV = statisticsCalculations.get("Mean Squares Of IV");
		double meanSquaresOfError = statisticsCalculations.get("Mean Squares Of Error");
		
		double fTest = meanSquaresOfIV / meanSquaresOfError;
		
		statisticsCalculations.put("F Test", fTest);

	}

	public void calculateMeanSquares() {
		double sumOfSquaresOfIV = statisticsCalculations.get("Sum Of Squares Of IV");
		double degreesOfFreedomForIV = statisticsCalculations.get("Degrees Of Freedom For IV");
		double sumOfSquaresOfError = statisticsCalculations.get("Sum Of Squares Of Error");
		double degreesOfFreedomForError = statisticsCalculations.get("Degrees Of Freedom For Error");
		
		double meanSquaresOfIV = sumOfSquaresOfIV / degreesOfFreedomForIV;
		double meanSquaresOfError = sumOfSquaresOfError / degreesOfFreedomForError;
		
		statisticsCalculations.put("Mean Squares Of IV", meanSquaresOfIV);
		statisticsCalculations.put("Mean Squares Of Error", meanSquaresOfError);

	}

	public void calculateStatistics() {
		calculateSampleSize();
		calculateSumOfData();
		calculateMean();
		calculateValuesOfS();
		calculateSumOfSquaredValuesOfS();
		calculateSumOfSquaredData();
		calculateSumOfDataSquared();
		calculateEstimatedStandardDeviation();
		calculateSumOfDataSquaredOverKTimesN();
		calculateSumOfSquaredTOverN();
		calculateSumOfSquaredValuesOfSOverK();
		calculateDegreesOfFreedom();
		calculateSumOfSquares();
		calculateMeanSquares();
		calculateFTest();
		calculateEtaSquared();
		calculateCriticalDifference();
		
	}

	private String printSumOfSquaredValuesOfS() {
		double sumOfSquaredValuesOfS = statisticsCalculations.get("Sum Of Squared Values Of S");
		
		String format = "Sum Of Squared Values Of S = " + sumOfSquaredValuesOfS;
		
		return format;
	}
	
	public String printMean() {
		double groupOneMean = statisticsCalculations.get("Group 1 Mean");
		double groupTwoMean = statisticsCalculations.get("Group 2 Mean");
		double groupThreeMean = statisticsCalculations.get("Group 3 Mean");
		
		String format = "Group 1 Mean = " + groupOneMean
				      + "\tGroup 2 Mean = " + groupTwoMean
				      + "\tGroup 3 Mean = " + groupThreeMean;
		
		return format;
	}

	public String printSumOfData() {
		double groupOneSum = statisticsCalculations.get("Group 1 Sum");
		double groupTwoSum = statisticsCalculations.get("Group 2 Sum");
		double groupThreeSum = statisticsCalculations.get("Group 3 Sum");
		double totalSumOfGroups = statisticsCalculations.get("Total Sum Of Groups");
		
		String format = "Group 1 Sum = " + groupOneSum
				      + "\tGroup 2 Sum =" + groupTwoSum
				      + "\tGroup 3 Sum = " + groupThreeSum
				      + "\tT = " + totalSumOfGroups;
				      
		return format;
	}

	public String printSumOfSquaredData() {
		double groupOneSumOfSquaredData = statisticsCalculations.get("Group 1 Sum Of Squared Data");
		double groupTwoSumOfSquaredData = statisticsCalculations.get("Group 2 Sum Of Squared Data");
		double groupThreeSumOfSquaredData = statisticsCalculations.get("Group 3 Sum Of Squared Data");
		double totalSumOfSquaredData = statisticsCalculations.get("Total Sum Of Squared Data");
		double sumOfSquaredT = statisticsCalculations.get("Sum Of Squared T");
		
		String format = "\n******************************\nGroup 1 Sum Of Squared Data = " + groupOneSumOfSquaredData
				      + "\nGroup 2 Sum Of Squared Data = " + groupTwoSumOfSquaredData
				      + "\nGroup 3 Sum Of Squared Data = " + groupThreeSumOfSquaredData
				      + "\nTotal Sum Of Squared Data = " + totalSumOfSquaredData
				      + "\nSum Of Squared T = " + sumOfSquaredT + "\n******************************\n";
		
		return format;
	}

	public String printSumOfDataSquared() {
		double totalSumOfGroupsSquared = statisticsCalculations.get("Total Sum Of Groups Squared");
		
		String format = "Total Sum Of Groups Squared = " + totalSumOfGroupsSquared;
		
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
		double groupThreeEstimatedStandardDeviation = statisticsCalculations.get("Group 3 Estimated Standard Deviation");

		String format = "\n******************************\nGroup 1 Estimated Standard Deviation = " + groupOneEstimatedStandardDeviation
				      + "\nGroup 2 Estimated Standard Deviation = " + groupTwoEstimatedStandardDeviation
				      + "\nGroup 3 Estimated Standard Deviation = " + groupThreeEstimatedStandardDeviation+"\n******************************\n";
		
		return format;
	}
	
	private String printSumOfDataSquaredOverKTimesN() {
		double sumOfDataSquaredOverKTimesN = statisticsCalculations.get("Sum Of Data Squared Over K Times N");
		
		String format = "Sum Of Data Squared Over K Times N = " + sumOfDataSquaredOverKTimesN;
		
		return format;
	}
	
	private String printSumOfSquaredTOverN() {
		double sumOfSquaredTOverN = statisticsCalculations.get("Sum Of Squared T Over N");
		
		String format = "Sum Of Squared T Over N = " + sumOfSquaredTOverN;
		
		return format;
	}
	
	private String printSumOfSquaredValuesOfSOverK() {
		double sumOfSquaredValuesOfSOverK = statisticsCalculations.get("Sum Of Squared Values Of S Over K");
		
		String format = "Sum Of Squared Values Of S Over K = " + sumOfSquaredValuesOfSOverK;
		
		return format;
	}

	public String printEtaSquared() {
		double etaSquared = statisticsCalculations.get("Eta Squared");
		
		String format = "Eta Squared = " + etaSquared;
		
		return format;
	}

	public String printSumOfSquares() {
		double sumOfSquaresOfTotal = statisticsCalculations.get("Sum Of Squares Of Total");
		double sumOfSquaresOfAcrossSubjects = statisticsCalculations.get("Sum Of Squares Of Across Subjects");
		double sumOfSquaresOfIV = statisticsCalculations.get("Sum Of Squares Of IV");
		double sumOfSquaresOfError = statisticsCalculations.get("Sum Of Squares Of Error");
		
		String format = "Sum Of Squares Of Total = " + sumOfSquaresOfTotal
				      + "\tSum Of Squares of Across Subjects = " + sumOfSquaresOfAcrossSubjects
				      + "\nSum Of Squares Of IV = " + sumOfSquaresOfIV
				      + "\tSum Of Squares Of Error = " + sumOfSquaresOfError;
		
		return format;
	}

	public String printDegreesOfFreedom() {
		double degreesOfFreedomForTotal = statisticsCalculations.get("Degrees Of Freedom For Total");
		double degreesOfFreedomForIV = statisticsCalculations.get("Degrees Of Freedom For IV");
		double degreesOfFreedomForAcrossSubjects = statisticsCalculations.get("Degrees Of Freedom For Across Subjects");
		double degreesOfFreedomForError = statisticsCalculations.get("Degrees Of Freedom For Error");
		
		String format = "Degrees Of Freedom For Total = " + degreesOfFreedomForTotal
				      + "\tDegrees Of Freedom For IV = " + degreesOfFreedomForIV
				      + "\tDegrees Of Freedom For Across Subjects = " + degreesOfFreedomForAcrossSubjects
				      + "\tDegrees Of Freedom For Error = " + degreesOfFreedomForError;
		
		return format;
	}

	public String printCriticalDifference() {
		double criticalDifference = statisticsCalculations.get("Critical Difference");
		
		String format = "Critical Difference = " + criticalDifference;
		
		return format;
	}
	
	public String printFTest() {
		double fTest = statisticsCalculations.get("F Test");
		
		String format = "F Test = " + fTest;
		
		return format;
	}
	
	public String printMeanSquares() {
		double meanSquaresOfIV = statisticsCalculations.get("Mean Squares Of IV");
		double meanSquaresOfError = statisticsCalculations.get("Mean Squares Of Error");
		
		String format = "Mean Squares Of IV = " + meanSquaresOfIV
				      + "\tMean Squares Of Error = " + meanSquaresOfError;
		
		return format;
	}
	
	public String printStatistics() {
		String format = printMean() + "\n"
					  + printSumOfData() + "\n"
					  + printSumOfSquaredValuesOfS() + "\n"
					  + printSumOfSquaredData() + "\n"
					  + printSumOfDataSquared() + "\n"
					  + printSampleSize() + "\n"
					  + printEstimatedStandardDeviation() + "\n"
					  + printSumOfDataSquaredOverKTimesN() + "\n"
					  + printSumOfSquaredTOverN() + "\n"
					  + printSumOfSquaredValuesOfSOverK() + "\n"
					  + printEtaSquared() + "\n"
					  + printSumOfSquares() + "\n"
					  + printDegreesOfFreedom() + "\n"
					  + printCriticalDifference() + "\n"
					  + printFTest() + "\n"
					  + printMeanSquares() + "\n";
		
		return format;
	}

}
