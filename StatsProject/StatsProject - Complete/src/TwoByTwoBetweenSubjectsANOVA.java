import java.util.ArrayList;

public class TwoByTwoBetweenSubjectsANOVA extends StatisticalTest implements FTest, MeanSquares {

	public TwoByTwoBetweenSubjectsANOVA(ArrayList<ArrayList<Integer>> data, ArrayList<String> dataLabels) {
		super(data, dataLabels);
	}

	public void calculateMean() {
		double groupA1B1Sum = statisticsCalculations.get("Group A1 B1 Sum"); 
		double groupA1B2Sum = statisticsCalculations.get("Group A1 B2 Sum"); 
		double groupA2B1Sum = statisticsCalculations.get("Group A2 B1 Sum"); 
		double groupA2B2Sum = statisticsCalculations.get("Group A2 B2 Sum"); 
		double groupA1Sum = statisticsCalculations.get("Group A1 Sum"); 
		double groupA2Sum = statisticsCalculations.get("Group A2 Sum"); 
		double groupB1Sum = statisticsCalculations.get("Group B1 Sum"); 
		double groupB2Sum = statisticsCalculations.get("Group B2 Sum"); 
		double littleN = statisticsCalculations.get("Little N");
		
		double groupA1B1Mean = groupA1B1Sum / littleN;
		double groupA1B2Mean = groupA1B2Sum / littleN;
		double groupA2B1Mean = groupA2B1Sum / littleN;
		double groupA2B2Mean = groupA2B2Sum / littleN;
		double groupA1Mean = groupA1Sum / (littleN * 2);
		double groupA2Mean = groupA2Sum / (littleN * 2);
		double groupB1Mean = groupB1Sum / (littleN * 2);
		double groupB2Mean = groupB2Sum / (littleN * 2);
		
		statisticsCalculations.put("Group A1 B1 Mean", groupA1B1Mean);
		statisticsCalculations.put("Group A1 B2 Mean", groupA1B2Mean);
		statisticsCalculations.put("Group A2 B1 Mean", groupA2B1Mean);
		statisticsCalculations.put("Group A2 B2 Mean", groupA2B2Mean);
		statisticsCalculations.put("Group A1 Mean", groupA1Mean);
		statisticsCalculations.put("Group A2 Mean", groupA2Mean);
		statisticsCalculations.put("Group B1 Mean", groupB1Mean);
		statisticsCalculations.put("Group B2 Mean", groupB2Mean);
		
	}

	@Override
	public void calculateSumOfData() {
		ArrayList<Integer> groupA1B1Data = data.get(0);
		ArrayList<Integer> groupA1B2Data = data.get(1);
		ArrayList<Integer> groupA2B1Data = data.get(2);
		ArrayList<Integer> groupA2B2Data = data.get(3);
		
		double groupA1B1Sum = 0.0;
		double groupA1B2Sum = 0.0;
		double groupA2B1Sum = 0.0;
		double groupA2B2Sum = 0.0;
		double groupA1Sum = 0.0;
		double groupA2Sum = 0.0;
		double groupB1Sum = 0.0;
		double groupB2Sum = 0.0;
		double totalGroupSum = 0.0;
		
		
		for(int i = 0; i < groupA1B1Data.size(); i++) {
			groupA1B1Sum += groupA1B1Data.get(i);
		}
		
		for(int i = 0; i < groupA1B2Data.size(); i++) {
			groupA1B2Sum += groupA1B2Data.get(i);
		}
		
		for(int i = 0; i < groupA2B1Data.size(); i++) {
			groupA2B1Sum += groupA2B1Data.get(i);
		}
		
		for(int i = 0; i < groupA2B2Data.size(); i++) {
			groupA2B2Sum += groupA2B2Data.get(i);
		}
		
		groupA1Sum = groupA1B1Sum + groupA1B2Sum;
		groupA2Sum = groupA2B1Sum + groupA2B2Sum;
		groupB1Sum = groupA1B1Sum + groupA2B1Sum;
		groupB2Sum = groupA1B2Sum + groupA2B2Sum;
		totalGroupSum = groupA1Sum + groupA2Sum;
		
		statisticsCalculations.put("Group A1 B1 Sum", groupA1B1Sum);
		statisticsCalculations.put("Group A1 B2 Sum", groupA1B2Sum);
		statisticsCalculations.put("Group A2 B1 Sum", groupA2B1Sum);
		statisticsCalculations.put("Group A2 B2 Sum", groupA2B2Sum);
		statisticsCalculations.put("Group A1 Sum", groupA1Sum);
		statisticsCalculations.put("Group A2 Sum", groupA2Sum);
		statisticsCalculations.put("Group B1 Sum", groupB1Sum);
		statisticsCalculations.put("Group B2 Sum", groupB2Sum);
		statisticsCalculations.put("Total Group Sum", totalGroupSum);

	}

	@Override
	public void calculateSumOfSquaredData() {
		ArrayList<Integer> groupA1B1Data = data.get(0);
		ArrayList<Integer> groupA1B2Data = data.get(1);
		ArrayList<Integer> groupA2B1Data = data.get(2);
		ArrayList<Integer> groupA2B2Data = data.get(3);
		
		double groupA1B1DataSquaredSum = 0.0;
		double groupA1B2DataSquaredSum = 0.0;
		double groupA2B1DataSquaredSum = 0.0;
		double groupA2B2DataSquaredSum = 0.0;
		double groupA1DataSquaredSum = 0.0;
		double groupA2DataSquaredSum = 0.0;
		double groupB1DataSquaredSum = 0.0;
		double groupB2DataSquaredSum = 0.0;
		double totalGroupDataSquaredSum = 0.0;
		
		
		for(int i = 0; i < groupA1B1Data.size(); i++) {
			groupA1B1DataSquaredSum += Math.pow(groupA1B1Data.get(i), 2);
		}
		
		for(int i = 0; i < groupA1B2Data.size(); i++) {
			groupA1B2DataSquaredSum += Math.pow(groupA1B2Data.get(i), 2);
		}
		
		for(int i = 0; i < groupA2B1Data.size(); i++) {
			groupA2B1DataSquaredSum += Math.pow(groupA2B1Data.get(i), 2);
		}
		
		for(int i = 0; i < groupA2B2Data.size(); i++) {
			groupA2B2DataSquaredSum += Math.pow(groupA2B2Data.get(i), 2);
		}
		
		groupA1DataSquaredSum = groupA1B1DataSquaredSum + groupA1B2DataSquaredSum;
		groupA2DataSquaredSum = groupA2B1DataSquaredSum + groupA2B2DataSquaredSum;
		groupB1DataSquaredSum = groupA1B1DataSquaredSum + groupA2B1DataSquaredSum;
		groupB2DataSquaredSum = groupA1B2DataSquaredSum + groupA2B2DataSquaredSum;
		totalGroupDataSquaredSum = groupA1DataSquaredSum + groupA2DataSquaredSum;
		
		statisticsCalculations.put("Group A1 B1 Data Squared Sum", groupA1B1DataSquaredSum);
		statisticsCalculations.put("Group A1 B2 Data Squared Sum", groupA1B2DataSquaredSum);
		statisticsCalculations.put("Group A2 B1 Data Squared Sum", groupA2B1DataSquaredSum);
		statisticsCalculations.put("Group A2 B2 Data Squared Sum", groupA2B2DataSquaredSum);
		statisticsCalculations.put("Group A1 Data Squared Sum", groupA1DataSquaredSum);
		statisticsCalculations.put("Group A2 Data Squared Sum", groupA2DataSquaredSum);
		statisticsCalculations.put("Group B1 Data Squared Sum", groupB1DataSquaredSum);
		statisticsCalculations.put("Group B2 Data Squared Sum", groupB2DataSquaredSum);
		statisticsCalculations.put("Total Group Data Squared Sum", totalGroupDataSquaredSum);

	}

	private void calculateSumOfSquaredTotals() {
		double groupA1Sum = statisticsCalculations.get("Group A1 Sum");
		double groupA2Sum = statisticsCalculations.get("Group A2 Sum");
		double groupB1Sum = statisticsCalculations.get("Group B1 Sum");
		double groupB2Sum = statisticsCalculations.get("Group B2 Sum");
		double groupA1B1Sum = statisticsCalculations.get("Group A1 B1 Sum");
		double groupA1B2Sum = statisticsCalculations.get("Group A1 B2 Sum");
		double groupA2B1Sum = statisticsCalculations.get("Group A2 B1 Sum");
		double groupA2B2Sum = statisticsCalculations.get("Group A2 B2 Sum");
		
		double totalGroupASquaredSum = Math.pow(groupA1Sum, 2) + Math.pow(groupA2Sum, 2);
		double totalGroupBSquaredSum = Math.pow(groupB1Sum, 2) + Math.pow(groupB2Sum, 2);
		double totalGroupsSquaredSum = Math.pow(groupA1B1Sum, 2) + Math.pow(groupA1B2Sum, 2) + Math.pow(groupA2B1Sum, 2) + Math.pow(groupA2B2Sum, 2);
		
		statisticsCalculations.put("Total Group A Squared Sum", totalGroupASquaredSum);
		statisticsCalculations.put("Total Group B Squared Sum", totalGroupBSquaredSum);
		statisticsCalculations.put("Total Groups Squared Sum", totalGroupsSquaredSum);

	}
	
	@Override
	public void calculateSumOfDataSquared() {
		double totalGroupSum = statisticsCalculations.get("Total Group Sum");
		
		double totalGroupSumSquared = Math.pow(totalGroupSum, 2);
		
		statisticsCalculations.put("Total Group Sum Squared", totalGroupSumSquared);

	}

	@Override
	public void calculateSampleSize() {
		double groupA1B1SampleSize = data.get(0).size();
		double groupA1B2SampleSize = data.get(1).size();	
		double groupA2B1SampleSize = data.get(2).size();
		double groupA2B2SampleSize = data.get(3).size();
		
		double littleN = groupA1B1SampleSize;
		double bigN = groupA1B1SampleSize + groupA1B2SampleSize + groupA2B1SampleSize + groupA2B2SampleSize;
		
		statisticsCalculations.put("Little N", littleN);
		statisticsCalculations.put("Big N", bigN);

	}

	@Override
	public void calculateEstimatedStandardDeviation() {
		ArrayList<Integer> groupA1B1Data = data.get(0);
		ArrayList<Integer> groupA1B2Data = data.get(1);
		ArrayList<Integer> groupA2B1Data = data.get(2);
		ArrayList<Integer> groupA2B2Data = data.get(3);
		double groupA1B1Mean = statisticsCalculations.get("Group A1 B1 Mean");
		double groupA1B2Mean = statisticsCalculations.get("Group A1 B2 Mean");
		double groupA2B1Mean = statisticsCalculations.get("Group A2 B1 Mean");
		double groupA2B2Mean = statisticsCalculations.get("Group A2 B2 Mean");
		double groupA1Mean = statisticsCalculations.get("Group A1 Mean");
		double groupA2Mean = statisticsCalculations.get("Group A2 Mean");
		double groupB1Mean = statisticsCalculations.get("Group B1 Mean");
		double groupB2Mean = statisticsCalculations.get("Group B2 Mean");
		double littleN = statisticsCalculations.get("Little N");
		
		double sumOfModifiedGroupA1B1Data = 0.0;
		double sumOfModifiedGroupA1B2Data = 0.0;
		double sumOfModifiedGroupA2B1Data = 0.0;
		double sumOfModifiedGroupA2B2Data = 0.0;
		double sumOfModifiedGroupA1Data = 0.0;
		double sumOfModifiedGroupA2Data = 0.0;
		double sumOfModifiedGroupB1Data = 0.0;
		double sumOfModifiedGroupB2Data = 0.0;
		
		for(int i = 0; i < groupA1B1Data.size(); i++) {
			double modifiedData = Math.pow((groupA1B1Data.get(i) - groupA1B1Mean), 2);
			sumOfModifiedGroupA1B1Data += modifiedData;
		}
		
		for(int i = 0; i < groupA1B2Data.size(); i++) {
			double modifiedData = Math.pow((groupA1B2Data.get(i) - groupA1B2Mean), 2);
			sumOfModifiedGroupA1B2Data += modifiedData;
		}
		
		for(int i = 0; i < groupA2B1Data.size(); i++) {
			double modifiedData = Math.pow((groupA2B1Data.get(i) - groupA2B1Mean), 2);
			sumOfModifiedGroupA2B1Data += modifiedData;
		}
		
		for(int i = 0; i < groupA2B2Data.size(); i++) {
			double modifiedData = Math.pow((groupA2B2Data.get(i) - groupA2B2Mean), 2);
			sumOfModifiedGroupA2B2Data += modifiedData;
		}
		
		for(int i = 0; i < groupA1B1Data.size(); i++) {
			double modifiedData = Math.pow(groupA1B1Data.get(i) - groupA1Mean, 2) + Math.pow(groupA1B2Data.get(i) - groupA1Mean, 2);
			sumOfModifiedGroupA1Data += modifiedData;
		}
		
		for(int i = 0; i < groupA2B1Data.size(); i++) {
			double modifiedData = Math.pow(groupA2B1Data.get(i) - groupA2Mean, 2) + Math.pow(groupA2B2Data.get(i) - groupA2Mean, 2);
			sumOfModifiedGroupA2Data += modifiedData;
		}
		
		for(int i = 0; i < groupA1B1Data.size(); i++) {
			double modifiedData = Math.pow(groupA1B1Data.get(i) - groupB1Mean, 2) + Math.pow(groupA2B1Data.get(i) - groupB1Mean, 2);
			sumOfModifiedGroupB1Data += modifiedData;
		}
		
		for(int i = 0; i < groupA1B2Data.size(); i++) {
			double modifiedData = Math.pow(groupA1B2Data.get(i) - groupB2Mean, 2) + Math.pow(groupA2B2Data.get(i) - groupB2Mean, 2);
			sumOfModifiedGroupB2Data += modifiedData;
		}
		
		double groupA1B1EstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupA1B1Data / (littleN - 1));
		double groupA1B2EstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupA1B2Data / (littleN - 1));
		double groupA2B1EstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupA2B1Data / (littleN - 1));
		double groupA2B2EstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupA2B2Data / (littleN - 1));
		double groupA1EstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupA1Data / ((littleN * 2) - 1));
		double groupA2EstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupA2Data / ((littleN * 2) - 1));
		double groupB1EstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupB1Data / ((littleN * 2) - 1));
		double groupB2EstimatedStandardDeviation = Math.sqrt(sumOfModifiedGroupB2Data / ((littleN * 2) - 1));
		
		statisticsCalculations.put("Group A1 B1 Estimated Standard Deviation", groupA1B1EstimatedStandardDeviation);
		statisticsCalculations.put("Group A1 B2 Estimated Standard Deviation", groupA1B2EstimatedStandardDeviation);
		statisticsCalculations.put("Group A2 B1 Estimated Standard Deviation", groupA2B1EstimatedStandardDeviation);
		statisticsCalculations.put("Group A2 B2 Estimated Standard Deviation", groupA2B2EstimatedStandardDeviation);
		statisticsCalculations.put("Group A1 Estimated Standard Deviation", groupA1EstimatedStandardDeviation);
		statisticsCalculations.put("Group A2 Estimated Standard Deviation", groupA2EstimatedStandardDeviation);
		statisticsCalculations.put("Group B1 Estimated Standard Deviation", groupB1EstimatedStandardDeviation);
		statisticsCalculations.put("Group B2 Estimated Standard Deviation", groupB2EstimatedStandardDeviation);

	}

	@Override
	public void calculateEtaSquared() {
		double sumOfSquaresOfTotal = statisticsCalculations.get("Sum Of Squares Of Total");
		double sumOfSquaresOfA = statisticsCalculations.get("Sum Of Squares Of A");
		double sumOfSquaresOfB = statisticsCalculations.get("Sum Of Squares Of B");
		double sumOfSquaresOfAByB = statisticsCalculations.get("Sum Of Squares Of A X B");
		
		double etaSquaredOfA = sumOfSquaresOfA / sumOfSquaresOfTotal;
		double etaSquaredOfB = sumOfSquaresOfB / sumOfSquaresOfTotal;
		double etaSquaredOfAByB = sumOfSquaresOfAByB / sumOfSquaresOfTotal;
		
		statisticsCalculations.put("Eta Squared Of A", etaSquaredOfA);
		statisticsCalculations.put("Eta Squared Of B", etaSquaredOfB);
		statisticsCalculations.put("Eta Squared Of A X B", etaSquaredOfAByB);

	}

	@Override
	public void calculateSumOfSquares() {
		double totalGroupSumSquared = statisticsCalculations.get("Total Group Sum Squared");
		double totalGroupASquaredSum = statisticsCalculations.get("Total Group A Squared Sum");
		double totalGroupBSquaredSum = statisticsCalculations.get("Total Group B Squared Sum");
		double totalGroupsSquaredSum = statisticsCalculations.get("Total Groups Squared Sum");
		double totalGroupDataSquaredSum = statisticsCalculations.get("Total Group Data Squared Sum");
		double littleN = statisticsCalculations.get("Little N");
		double bigN = statisticsCalculations.get("Big N");
		double a = 2;
		double b = 2;
		
		double sumOfSquaresOfTotal = totalGroupDataSquaredSum - (totalGroupSumSquared / bigN);
		double sumOfSquaresOfA = (totalGroupASquaredSum / (littleN * b)) - (totalGroupSumSquared / bigN);
		double sumOfSquaresOfB = (totalGroupBSquaredSum / (littleN * a)) - (totalGroupSumSquared / bigN);
		double sumOfSquaresOfAByB = (totalGroupSumSquared / bigN) + (totalGroupsSquaredSum / littleN) - (totalGroupASquaredSum / (littleN * b)) - (totalGroupBSquaredSum / (littleN * a));
		double sumOfSquaresOfWithin = totalGroupDataSquaredSum - (totalGroupsSquaredSum / littleN);
		
		statisticsCalculations.put("Sum Of Squares Of Total", sumOfSquaresOfTotal);
		statisticsCalculations.put("Sum Of Squares Of A", sumOfSquaresOfA);
		statisticsCalculations.put("Sum Of Squares Of B", sumOfSquaresOfB);
		statisticsCalculations.put("Sum Of Squares Of A X B", sumOfSquaresOfAByB);
		statisticsCalculations.put("Sum Of Squares Of Within", sumOfSquaresOfWithin);

	}

	@Override
	public void calculateDegreesOfFreedom() {
		double bigN = statisticsCalculations.get("Big N");
		double littleN = statisticsCalculations.get("Little N");
		double a = 2;
		double b = 2;
		
		double degreesOfFreedomForTotal = bigN - 1;
		double degreesOfFreedomForA = a - 1;
		double degreesOfFreedomForB = b - 1;
		double degreesOfFreedomForAByB = degreesOfFreedomForA * degreesOfFreedomForB;
		double degreesOfFreedomForWithin = (a) * (b) * (littleN - 1);
		
		statisticsCalculations.put("Degrees Of Freedom For Total", degreesOfFreedomForTotal);
		statisticsCalculations.put("Degrees Of Freedom For A", degreesOfFreedomForA);
		statisticsCalculations.put("Degrees Of Freedom For B", degreesOfFreedomForB);
		statisticsCalculations.put("Degrees Of Freedom For A X B", degreesOfFreedomForAByB);
		statisticsCalculations.put("Degrees Of Freedom For Within", degreesOfFreedomForWithin);

	}

	@Override
	public void calculateMeanSquares() {
		double sumOfSquaresOfA = statisticsCalculations.get("Sum Of Squares Of A");
		double sumOfSquaresOfB = statisticsCalculations.get("Sum Of Squares Of B");
		double sumOfSquaresOfAByB = statisticsCalculations.get("Sum Of Squares Of A X B");
		double sumOfSquaresOfWithin = statisticsCalculations.get("Sum Of Squares Of Within");
		double degreesOfFreedomForA = statisticsCalculations.get("Degrees Of Freedom For A");
		double degreesOfFreedomForB = statisticsCalculations.get("Degrees Of Freedom For B");
		double degreesOfFreedomForAByB = statisticsCalculations.get("Degrees Of Freedom For A X B");
		double degreesOfFreedomForWithin = statisticsCalculations.get("Degrees Of Freedom For Within");
		
		double meanSquaresOfA = sumOfSquaresOfA / degreesOfFreedomForA;
		double meanSquaresOfB = sumOfSquaresOfB / degreesOfFreedomForB;
		double meanSquaresOfAByB = sumOfSquaresOfAByB / degreesOfFreedomForAByB;
		double meanSquaresOfWithin = sumOfSquaresOfWithin / degreesOfFreedomForWithin;
		
		statisticsCalculations.put("Mean Squares Of A", meanSquaresOfA);
		statisticsCalculations.put("Mean Squares Of B", meanSquaresOfB);
		statisticsCalculations.put("Mean Squares Of A X B", meanSquaresOfAByB);
		statisticsCalculations.put("Mean Squares Of Within", meanSquaresOfWithin);

	}

	@Override
	public void calculateFTest() {
		double meanSquaresOfA = statisticsCalculations.get("Mean Squares Of A");
		double meanSquaresOfB = statisticsCalculations.get("Mean Squares Of B");
		double meanSquaresOfAByB = statisticsCalculations.get("Mean Squares Of A X B");
		double meanSquaresOfWithin = statisticsCalculations.get("Mean Squares Of Within");
		
		double fTestOfA = meanSquaresOfA / meanSquaresOfWithin;
		double fTestOfB = meanSquaresOfB / meanSquaresOfWithin;
		double fTestOfAByB = meanSquaresOfAByB / meanSquaresOfWithin;
		
		statisticsCalculations.put("F Test Of A", fTestOfA);
		statisticsCalculations.put("F Test Of B", fTestOfB);
		statisticsCalculations.put("F Test Of A X B", fTestOfAByB);

	}

	@Override
	public void calculateStatistics() {
		calculateSampleSize();
		calculateSumOfData();
		calculateMean();
		calculateSumOfSquaredData();
		calculateSumOfDataSquared();
		calculateSumOfSquaredTotals();
		calculateEstimatedStandardDeviation();
		calculateDegreesOfFreedom();
		calculateSumOfSquares();
		calculateMeanSquares();
		calculateFTest();
		calculateEtaSquared();

	}

	@Override
	public String printMean() {
		double groupA1B1Mean = statisticsCalculations.get("Group A1 B1 Mean");
		double groupA1B2Mean = statisticsCalculations.get("Group A1 B2 Mean");
		double groupA2B1Mean = statisticsCalculations.get("Group A2 B1 Mean");
		double groupA2B2Mean = statisticsCalculations.get("Group A2 B2 Mean");
		double groupA1Mean = statisticsCalculations.get("Group A1 Mean");
		double groupA2Mean = statisticsCalculations.get("Group A2 Mean");
		double groupB1Mean = statisticsCalculations.get("Group B1 Mean");
		double groupB2Mean = statisticsCalculations.get("Group B2 Mean");
		
		String format = "Group A1 B1 Mean = " + groupA1B1Mean
				      + "\tGroup A1 B2 Mean = " + groupA1B2Mean
				      + "\nGroup A2 B1 Mean = " + groupA2B1Mean
				      + "\tGroup A2 B2 Mean = " + groupA2B2Mean
				      + "\nGroup A1 Mean = " + groupA1Mean
				      + "\tGroup A2 Mean = " + groupA2Mean
				      + "\nGroup B1 Mean = " + groupB1Mean
				      + "\tGroup B2 Mean = " + groupB2Mean + "\n******************************\n";
		
		return format;
	}

	@Override
	public String printSumOfData() {
		double groupA1B1Sum = statisticsCalculations.get("Group A1 B1 Sum");
		double groupA1B2Sum = statisticsCalculations.get("Group A1 B2 Sum");
		double groupA2B1Sum = statisticsCalculations.get("Group A2 B1 Sum");
		double groupA2B2Sum = statisticsCalculations.get("Group A2 B2 Sum");
		double groupA1Sum = statisticsCalculations.get("Group A1 Sum");
		double groupA2Sum = statisticsCalculations.get("Group A2 Sum");
		double groupB1Sum = statisticsCalculations.get("Group B1 Sum");
		double groupB2Sum = statisticsCalculations.get("Group B2 Sum");
		double totalGroupSum = statisticsCalculations.get("Total Group Sum");
		
		String format = "Group A1 B1 Sum = " + groupA1B1Sum
					  + "\tGroup A1 B2 Sum = " + groupA1B2Sum
					  + "\tGroup A2 B1 Sum = " + groupA2B1Sum
					  + "\tGroup A2 B2 Sum = " + groupA2B2Sum
					  + "\nGroup A1 Sum = " + groupA1Sum
					  + "\tGroup A2 Sum = " + groupA2Sum
					  + "\tGroup B1 Sum = " + groupB1Sum
					  + "\tGroup B2 Sum = " + groupB2Sum
					  + "\tTotal Group Sum = " + totalGroupSum + "\n";
		
		return format;
	}

	@Override
	public String printSumOfSquaredData() {
		double groupA1B1DataSquaredSum = statisticsCalculations.get("Group A1 B1 Data Squared Sum");
		double groupA1B2DataSquaredSum = statisticsCalculations.get("Group A1 B2 Data Squared Sum");
		double groupA2B1DataSquaredSum = statisticsCalculations.get("Group A2 B1 Data Squared Sum");
		double groupA2B2DataSquaredSum = statisticsCalculations.get("Group A2 B2 Data Squared Sum");
		double groupA1DataSquaredSum = statisticsCalculations.get("Group A1 Data Squared Sum");
		double groupA2DataSquaredSum = statisticsCalculations.get("Group A2 Data Squared Sum");
		double groupB1DataSquaredSum = statisticsCalculations.get("Group B1 Data Squared Sum");
		double groupB2DataSquaredSum = statisticsCalculations.get("Group B2 Data Squared Sum");
		double totalGroupDataSquaredSum = statisticsCalculations.get("Total Group Data Squared Sum");
		
		String format = "Group A1 B1 Data Squared Sum = " + groupA1B1DataSquaredSum
					  + "\tGroup A1 B2 Data Squared Sum = " + groupA1B2DataSquaredSum
					  + "\nGroup A2 B1 Data Squared Sum = " + groupA2B1DataSquaredSum
					  + "\tGroup A2 B2 Data Squared Sum = " + groupA2B2DataSquaredSum
					  + "\nGroup A1 Data Squared Sum = " + groupA1DataSquaredSum
					  + "\tGroup A2 Data Squared Sum = " + groupA2DataSquaredSum
					  + "\nGroup B1 Data Squared Sum = " + groupB1DataSquaredSum
					  + "\tGroup B2 Data Squared Sum = " + groupB2DataSquaredSum
					  + "\nTotal Group Data Squared Sum = " + totalGroupDataSquaredSum + "\n******************************\n";
		
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
		double groupA1B1EstimatedStandardDeviation = statisticsCalculations.get("Group A1 B1 Estimated Standard Deviation");
		double groupA1B2EstimatedStandardDeviation = statisticsCalculations.get("Group A1 B2 Estimated Standard Deviation");
		double groupA2B1EstimatedStandardDeviation = statisticsCalculations.get("Group A2 B1 Estimated Standard Deviation");
		double groupA2B2EstimatedStandardDeviation = statisticsCalculations.get("Group A2 B2 Estimated Standard Deviation");
		double groupA1EstimatedStandardDeviation = statisticsCalculations.get("Group A1 Estimated Standard Deviation");
		double groupA2EstimatedStandardDeviation = statisticsCalculations.get("Group A2 Estimated Standard Deviation");
		double groupB1EstimatedStandardDeviation = statisticsCalculations.get("Group B1 Estimated Standard Deviation");
		double groupB2EstimatedStandardDeviation = statisticsCalculations.get("Group B2 Estimated Standard Deviation");
		
		String format = "Group A1 B1 Estimated Standard Deviation = " + groupA1B1EstimatedStandardDeviation
					  + "\nGroup A1 B2 Estimated Standard Deviation = " + groupA1B2EstimatedStandardDeviation
					  + "\nGroup A2 B1 Estimated Standard Deviation = " + groupA2B1EstimatedStandardDeviation
					  + "\nGroup A2 B2 Estimated Standard Deviation = " + groupA2B2EstimatedStandardDeviation
					  + "\n\nGroup A1 Estimated Standard Deviation = " + groupA1EstimatedStandardDeviation
					  + "\nGroup A2 Estimated Standard Deviation = " + groupA2EstimatedStandardDeviation
					  + "\nGroup B1 Estimated Standard Deviation = " + groupB1EstimatedStandardDeviation
					  + "\nGroup B2 Estimated Standard Deviation = " + groupB2EstimatedStandardDeviation + "\n******************************\n";
		
		return format;
	}

	private String printSumOfSquaredTotals() {
		double totalGroupASquaredSum = statisticsCalculations.get("Total Group A Squared Sum");
		double totalGroupBSquaredSum = statisticsCalculations.get("Total Group B Squared Sum");
		double totalGroupsSquaredSum = statisticsCalculations.get("Total Groups Squared Sum");
		
		String format = "Total Group A Squared Sum = " + totalGroupASquaredSum
					  + "\tTotal Group B Squared Sum = " + totalGroupBSquaredSum
					  + "\nTotal Groups Squared Sum = " + totalGroupsSquaredSum;
		
		return format;
	}
	
	@Override
	public String printEtaSquared() {
		double etaSquaredOfA = statisticsCalculations.get("Eta Squared Of A");
		double etaSquaredOfB = statisticsCalculations.get("Eta Squared Of B");
		double etaSquaredOfAByB = statisticsCalculations.get("Eta Squared Of A X B");
		
		String format = "Eta Squared Of A = " + etaSquaredOfA
					  + "\tEta Squared Of B = " + etaSquaredOfB
					  + "\nEta Squared Of A X B = " + etaSquaredOfAByB;
		
		return format;
	}

	@Override
	public String printSumOfSquares() {
		double sumOfSquaresOfTotal = statisticsCalculations.get("Sum Of Squares Of Total");
		double sumOfSquaresOfA = statisticsCalculations.get("Sum Of Squares Of A");
		double sumOfSquaresOfB = statisticsCalculations.get("Sum Of Squares Of B");
		double sumOfSquaresOfAByB = statisticsCalculations.get("Sum Of Squares Of A X B");
		double sumOfSquaresOfWithin = statisticsCalculations.get("Sum Of Squares Of Within");
		
		String format = "Sum Of Squares Of Total = " + sumOfSquaresOfTotal
					  + "\tSum Of Squares Of A = " + sumOfSquaresOfA
					  + "\nSum Of Squares Of B = " + sumOfSquaresOfB
					  + "\nSum Of Squares Of A X B = " + sumOfSquaresOfAByB
					  + "\tSum Of Squares Of Within = " + sumOfSquaresOfWithin + "\n";
		
		return format;
	}

	@Override
	public String printDegreesOfFreedom() {
		double degreesOfFreedomForTotal = statisticsCalculations.get("Degrees Of Freedom For Total");
		double degreesOfFreedomForA = statisticsCalculations.get("Degrees Of Freedom For A");
		double degreesOfFreedomForB = statisticsCalculations.get("Degrees Of Freedom For B");
		double degreesOfFreedomForAByB = statisticsCalculations.get("Degrees Of Freedom For A X B");
		double degreesOfFreedomForWithin = statisticsCalculations.get("Degrees Of Freedom For Within");
		
		String format = "Degrees Of Freedom For Total = " + degreesOfFreedomForTotal
					  + "\nDegrees Of Freedom For A = " + degreesOfFreedomForA
					  + "\nDegrees Of Freedom For B = " + degreesOfFreedomForB
					  + "\nDegrees Of Freedom For A X B = " + degreesOfFreedomForAByB
					  + "\tDegrees Of Freedom For Within = " + degreesOfFreedomForWithin;
		
		return format;
	}
	
	@Override
	public String printMeanSquares() {
		double meanSquaresOfA = statisticsCalculations.get("Mean Squares Of A");
		double meanSquaresOfB = statisticsCalculations.get("Mean Squares Of B");
		double meanSquaresOfAByB = statisticsCalculations.get("Mean Squares Of A X B");
		double meanSquaresOfWithin = statisticsCalculations.get("Mean Squares Of Within");
		
		String format = "Mean Squares Of A = " + meanSquaresOfA
					  + "\tMean Squares Of B = " + meanSquaresOfB
					  + "\nMean Squares Of A X B = " + meanSquaresOfAByB
					  + "\tMean Squares Of Within = " + meanSquaresOfWithin;
		
		return format;
	}
	
	@Override
	public String printFTest() {
		double fTestOfA = statisticsCalculations.get("F Test Of A");
		double fTestOfB = statisticsCalculations.get("F Test Of B");
		double fTestOfAByB = statisticsCalculations.get("F Test Of A X B");
		
		String format = "F Test Of A = " + fTestOfA
					  + "\tF Test Of B = " + fTestOfB
					  + "\tF Test Of A X B = " + fTestOfAByB;
		
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
				      + printSumOfSquaredTotals() + "\n"
				      + printEtaSquared() + "\n"
				      + printSumOfSquares() + "\n"
				      + printDegreesOfFreedom() + "\n"
				      + printMeanSquares() + "\n"
				      + printFTest() + "\n";
		
		return format;
	}

}
