import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {
	public static void main(String[] args) {

		String userHomeFolder = System.getProperty("user.home");
		File textFile = new File(userHomeFolder, "myText.txt");
		/*try {

			BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
			out.write(test1.printStatistics());
			out.close();
			System.out.println("Did it!");
		} catch (IOException x) {
			
		}*/
		
		ArrayList<Integer> groupAData = new ArrayList<Integer>();
		String groupADataLabel = "Smiling";
		groupAData.add(30);
		groupAData.add(60);
		groupAData.add(35);
		groupAData.add(27);
		groupAData.add(53);
		groupAData.add(48);
		
		ArrayList<Integer> groupBData = new ArrayList<Integer>();
		String groupBDataLabel = "Frowning";
		groupBData.add(15);
		groupBData.add(18);
		groupBData.add(25);
		groupBData.add(29);
		groupBData.add(36);
		groupBData.add(25);
		groupBData.add(25);
		
		ArrayList<ArrayList<Integer>> groups1 = new ArrayList<ArrayList<Integer>>();
		groups1.add(groupAData);
		groups1.add(groupBData);
		
		ArrayList<String> groups1Labels = new ArrayList<String>();
		groups1Labels.add(groupADataLabel);
		groups1Labels.add(groupBDataLabel);
		
		double criticalValue = 0.05;
		String typeOfDirectionalTest = "D";
		
		System.out.println(groups1.toString());
		
		IndependentGroupsTTest test1 = new IndependentGroupsTTest(groups1, groups1Labels, criticalValue, typeOfDirectionalTest);
		
		test1.calculateStatistics();
		System.out.println(test1.printStatistics());
		try {

			BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
			out.write(test1.printStatistics());
			out.close();
			System.out.println("Did it!");
		} catch (IOException x) {
			
		}
			
		
		
		ArrayList<Integer> groupCData = new ArrayList<Integer>();
		String groupCDataLabel = "60 Degrees";
		groupCData.add(81);
		groupCData.add(43);
		groupCData.add(80);
		groupCData.add(108);
		groupCData.add(90);
		groupCData.add(44);
		groupCData.add(86);
		groupCData.add(70);
		groupCData.add(58);
		groupCData.add(72);
		groupCData.add(96);
		groupCData.add(86);
		
		ArrayList<Integer> groupDData = new ArrayList<Integer>();
		String groupDDataLabel = "120 Degrees";
		groupDData.add(63);
		groupDData.add(53);
		groupDData.add(56);
		groupDData.add(69);
		groupDData.add(72);
		groupDData.add(34);
		groupDData.add(52);
		groupDData.add(33);
		groupDData.add(36);
		groupDData.add(48);
		groupDData.add(60);
		groupDData.add(65);
		
		ArrayList<ArrayList<Integer>> groups2 = new ArrayList<ArrayList<Integer>>();
		groups2.add(groupCData);
		groups2.add(groupDData);
		
		ArrayList<String> groups2Labels = new ArrayList<String>();
		groups2Labels.add(groupCDataLabel);
		groups2Labels.add(groupDDataLabel);
		
		criticalValue = 0.05;
		typeOfDirectionalTest = "ND";
		
		System.out.println(groups2.toString());
		
		CorrelatedGroupsTTest test2 = new CorrelatedGroupsTTest(groups2, groups2Labels, criticalValue, typeOfDirectionalTest);
		
		test2.calculateStatistics();
		System.out.println(test2.printStatistics());
		
		
		ArrayList<Integer> groupEData = new ArrayList<Integer>();
		String groupEDataLabel = "Buckley";
		groupEData.add(5);
		groupEData.add(7);
		groupEData.add(7);
		groupEData.add(5);
		groupEData.add(5);
		groupEData.add(2);
		groupEData.add(7);
		groupEData.add(4);
		
		ArrayList<Integer> groupFData = new ArrayList<Integer>();
		String groupFDataLabel = "Cohen";
		groupFData.add(1);
		groupFData.add(1);
		groupFData.add(2);
		groupFData.add(1);
		groupFData.add(2);
		groupFData.add(1);
		groupFData.add(3);
		groupFData.add(7);
		
		ArrayList<Integer> groupGData = new ArrayList<Integer>();
		String groupGDataLabel = "Wainwright";
		groupGData.add(4);
		groupGData.add(6);
		groupGData.add(5);
		groupGData.add(3);
		groupGData.add(4);
		groupGData.add(7);
		groupGData.add(6);
		groupGData.add(5);
		
		ArrayList<ArrayList<Integer>> groups3 = new ArrayList<ArrayList<Integer>>();
		groups3.add(groupEData);
		groups3.add(groupFData);
		groups3.add(groupGData);
		
		ArrayList<String> groups3Labels = new ArrayList<String>();
		groups3Labels.add(groupEDataLabel);
		groups3Labels.add(groupFDataLabel);
		groups3Labels.add(groupGDataLabel);
		
		criticalValue = 0.05;
		
		System.out.println(groups3.toString());
		
		OneWayRepeatedMeasuresANOVA test3 = new OneWayRepeatedMeasuresANOVA(groups3, groups3Labels, criticalValue);
		
		test3.calculateStatistics();
		System.out.println(test3.printStatistics());
		
		
		ArrayList<Integer> groupHData = new ArrayList<Integer>();
		String groupHDataLabel = "1";
		groupHData.add(11);
		groupHData.add(14);
		groupHData.add(13);
		groupHData.add(12);
		groupHData.add(13);
		groupHData.add(9);
		
		ArrayList<Integer> groupIData = new ArrayList<Integer>();
		String groupIDataLabel = "2";
		groupIData.add(6);
		groupIData.add(4);
		groupIData.add(11);
		groupIData.add(9);
		groupIData.add(5);
		groupIData.add(8);
		
		ArrayList<Integer> groupJData = new ArrayList<Integer>();
		String groupJDataLabel = "3";
		groupJData.add(3);
		groupJData.add(6);
		groupJData.add(3);
		groupJData.add(3);
		groupJData.add(3);
		groupJData.add(2);
		
		ArrayList<ArrayList<Integer>> groups4 = new ArrayList<ArrayList<Integer>>();
		groups4.add(groupHData);
		groups4.add(groupIData);
		groups4.add(groupJData);
		
		ArrayList<String> groups4Labels = new ArrayList<String>();
		groups4Labels.add(groupHDataLabel);
		groups4Labels.add(groupIDataLabel);
		groups4Labels.add(groupJDataLabel);
		
		criticalValue = 0.05;
		
		System.out.println(groups4.toString());
		
		OneWayBetweenSubjectsANOVA test4 = new OneWayBetweenSubjectsANOVA(groups4, groups4Labels, criticalValue);
		
		test4.calculateStatistics();
		System.out.println(test4.printStatistics());
		
		ArrayList<Integer> groupKData = new ArrayList<Integer>();
		String groupKDataLabel = "Placebo/Morning";
		groupKData.add(4);
		groupKData.add(3);
		groupKData.add(4);
		groupKData.add(5);
		groupKData.add(4);
		groupKData.add(5);
		
		ArrayList<Integer> groupLData = new ArrayList<Integer>();
		String groupLDataLabel = "300 mg/Morning";
		groupLData.add(7);
		groupLData.add(7);
		groupLData.add(8);
		groupLData.add(9);
		groupLData.add(9);
		groupLData.add(10);
		
		ArrayList<Integer> groupMData = new ArrayList<Integer>();
		String groupMDataLabel = "Placebo/Evening";
		groupMData.add(5);
		groupMData.add(9);
		groupMData.add(7);
		groupMData.add(6);
		groupMData.add(5);
		groupMData.add(4);
		
		ArrayList<Integer> groupNData = new ArrayList<Integer>();
		String groupNDataLabel = "300 mg/Evening";
		groupNData.add(6);
		groupNData.add(2);
		groupNData.add(4);
		groupNData.add(3);
		groupNData.add(5);
		groupNData.add(3);
		
		ArrayList<ArrayList<Integer>> groups5 = new ArrayList<ArrayList<Integer>>();
		groups5.add(groupKData);
		groups5.add(groupLData);
		groups5.add(groupMData);
		groups5.add(groupNData);
		
		ArrayList<String> groups5Labels = new ArrayList<String>();
		groups5Labels.add(groupKDataLabel);
		groups5Labels.add(groupLDataLabel);
		groups5Labels.add(groupMDataLabel);
		groups5Labels.add(groupNDataLabel);
		
		System.out.println(groups5.toString());
		
		TwoByTwoBetweenSubjectsANOVA test5 = new TwoByTwoBetweenSubjectsANOVA(groups5, groups5Labels);
		
		test5.calculateStatistics();
		System.out.println(test5.printStatistics());
		
		/*
		ArrayList<Integer> groupAData = new ArrayList<Integer>();
		String groupADataLabel = "Tylenol";
		groupAData.add(8);
		groupAData.add(8);
		groupAData.add(6);
		groupAData.add(5);
		groupAData.add(7);
		
		ArrayList<Integer> groupBData = new ArrayList<Integer>();
		String groupBDataLabel = "Placebo";
		groupBData.add(3);
		groupBData.add(4);
		groupBData.add(5);
		groupBData.add(5);
		groupBData.add(4);
		
		ArrayList<ArrayList<Integer>> groups1 = new ArrayList<ArrayList<Integer>>();
		groups1.add(groupAData);
		groups1.add(groupBData);
		
		ArrayList<String> groups1Labels = new ArrayList<String>();
		groups1Labels.add(groupADataLabel);
		groups1Labels.add(groupBDataLabel);
		
		double criticalValue = 0.05;
		String typeOfDirectionalTest = "D";
		
		System.out.println(groups1.toString());
		
		IndependentGroupsTTest test1 = new IndependentGroupsTTest(groups1, groups1Labels, criticalValue, typeOfDirectionalTest);
		
		test1.calculateStatistics();
		System.out.println(test1.printStatistics());
		
		
		ArrayList<Integer> groupCData = new ArrayList<Integer>();
		String groupCDataLabel = "Self";
		groupCData.add(100);
		groupCData.add(105);
		groupCData.add(110);
		groupCData.add(110);
		groupCData.add(115);
		groupCData.add(115);
		groupCData.add(115);
		groupCData.add(115);
		groupCData.add(120);
		groupCData.add(130);
		
		ArrayList<Integer> groupDData = new ArrayList<Integer>();
		String groupDDataLabel = "Peer";
		groupDData.add(100);
		groupDData.add(100);
		groupDData.add(105);
		groupDData.add(100);
		groupDData.add(100);
		groupDData.add(90);
		groupDData.add(105);
		groupDData.add(105);
		groupDData.add(105);
		groupDData.add(105);
		
		ArrayList<ArrayList<Integer>> groups2 = new ArrayList<ArrayList<Integer>>();
		groups2.add(groupCData);
		groups2.add(groupDData);
		
		ArrayList<String> groups2Labels = new ArrayList<String>();
		groups2Labels.add(groupCDataLabel);
		groups2Labels.add(groupDDataLabel);
		
		criticalValue = 0.05;
		typeOfDirectionalTest = "ND";
		
		System.out.println(groups2.toString());
		
		CorrelatedGroupsTTest test2 = new CorrelatedGroupsTTest(groups2, groups2Labels, criticalValue, typeOfDirectionalTest);
		
		test2.calculateStatistics();
		System.out.println(test2.printStatistics());
		

		ArrayList<Integer> groupEData = new ArrayList<Integer>();
		String groupEDataLabel = "Frowning";
		groupEData.add(5);
		groupEData.add(8);
		groupEData.add(5);
		groupEData.add(4);
		groupEData.add(6);
		groupEData.add(3);
		groupEData.add(5);
		groupEData.add(7);
		groupEData.add(2);
		groupEData.add(4);
		groupEData.add(3);
		groupEData.add(4);
		
		ArrayList<Integer> groupFData = new ArrayList<Integer>();
		String groupFDataLabel = "Mask";
		groupFData.add(5);
		groupFData.add(6);
		groupFData.add(8);
		groupFData.add(4);
		groupFData.add(11);
		groupFData.add(10);
		groupFData.add(5);
		groupFData.add(4);
		groupFData.add(8);
		groupFData.add(8);
		groupFData.add(5);
		groupFData.add(9);
		
		ArrayList<Integer> groupGData = new ArrayList<Integer>();
		String groupGDataLabel = "Smiling";
		groupGData.add(10);
		groupGData.add(12);
		groupGData.add(11);
		groupGData.add(8);
		groupGData.add(13);
		groupGData.add(10);
		groupGData.add(9);
		groupGData.add(7);
		groupGData.add(13);
		groupGData.add(10);
		groupGData.add(9);
		groupGData.add(9);
		
		ArrayList<ArrayList<Integer>> groups3 = new ArrayList<ArrayList<Integer>>();
		groups3.add(groupEData);
		groups3.add(groupFData);
		groups3.add(groupGData);
		
		ArrayList<String> groups3Labels = new ArrayList<String>();
		groups3Labels.add(groupEDataLabel);
		groups3Labels.add(groupFDataLabel);
		groups3Labels.add(groupGDataLabel);
		
		criticalValue = 0.05;
		
		System.out.println(groups3.toString());
		
		OneWayBetweenSubjectsANOVA test3 = new OneWayBetweenSubjectsANOVA(groups3, groups3Labels, criticalValue);
		
		test3.calculateStatistics();
		System.out.println(test3.printStatistics());
		
		/*
		ArrayList<Integer> groupHData = new ArrayList<Integer>();
		String groupHDataLabel = "1";
		groupHData.add(11);
		groupHData.add(14);
		groupHData.add(13);
		groupHData.add(12);
		groupHData.add(13);
		groupHData.add(9);
		
		ArrayList<Integer> groupIData = new ArrayList<Integer>();
		String groupIDataLabel = "2";
		groupIData.add(6);
		groupIData.add(4);
		groupIData.add(11);
		groupIData.add(9);
		groupIData.add(5);
		groupIData.add(8);
		
		ArrayList<Integer> groupJData = new ArrayList<Integer>();
		String groupJDataLabel = "3";
		groupJData.add(3);
		groupJData.add(6);
		groupJData.add(3);
		groupJData.add(3);
		groupJData.add(3);
		groupJData.add(2);
		
		ArrayList<ArrayList<Integer>> groups4 = new ArrayList<ArrayList<Integer>>();
		groups4.add(groupHData);
		groups4.add(groupIData);
		groups4.add(groupJData);
		
		ArrayList<String> groups4Labels = new ArrayList<String>();
		groups4Labels.add(groupHDataLabel);
		groups4Labels.add(groupIDataLabel);
		groups4Labels.add(groupJDataLabel);
		
		criticalValue = 0.05;
		
		System.out.println(groups4.toString());
		
		OneWayRepeatedMeasuresANOVA test4 = new OneWayRepeatedMeasuresANOVA(groups4, groups4Labels, criticalValue);
		
		test4.calculateStatistics();
		System.out.println(test4.printStatistics());
		
		ArrayList<Integer> groupKData = new ArrayList<Integer>();
		String groupKDataLabel = "Placebo/Morning";
		groupKData.add(4);
		groupKData.add(3);
		groupKData.add(4);
		groupKData.add(5);
		groupKData.add(4);
		groupKData.add(5);
		
		ArrayList<Integer> groupLData = new ArrayList<Integer>();
		String groupLDataLabel = "300 mg/Morning";
		groupLData.add(7);
		groupLData.add(7);
		groupLData.add(8);
		groupLData.add(9);
		groupLData.add(9);
		groupLData.add(10);
		
		ArrayList<Integer> groupMData = new ArrayList<Integer>();
		String groupMDataLabel = "Placebo/Evening";
		groupMData.add(5);
		groupMData.add(9);
		groupMData.add(7);
		groupMData.add(6);
		groupMData.add(5);
		groupMData.add(4);
		
		ArrayList<Integer> groupNData = new ArrayList<Integer>();
		String groupNDataLabel = "300 mg/Evening";
		groupNData.add(6);
		groupNData.add(2);
		groupNData.add(4);
		groupNData.add(3);
		groupNData.add(5);
		groupNData.add(3);
		
		ArrayList<ArrayList<Integer>> groups5 = new ArrayList<ArrayList<Integer>>();
		groups5.add(groupKData);
		groups5.add(groupLData);
		groups5.add(groupMData);
		groups5.add(groupNData);
		
		ArrayList<String> groups5Labels = new ArrayList<String>();
		groups5Labels.add(groupKDataLabel);
		groups5Labels.add(groupLDataLabel);
		groups5Labels.add(groupMDataLabel);
		groups5Labels.add(groupNDataLabel);
		
		System.out.println(groups5.toString());
		
		TwoByTwoBetweenSubjectsANOVA test5 = new TwoByTwoBetweenSubjectsANOVA(groups5, groups5Labels);
		
		test5.calculateStatistics();
		System.out.println(test5.printStatistics());
		*/
	}
}
