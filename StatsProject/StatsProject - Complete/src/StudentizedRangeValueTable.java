
public class StudentizedRangeValueTable {
	private static final int[] numberOfLevels = {2, 3};
	private static final int[] degreesOfFreedomLevels = {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
	private static final double[][] studentizedRangeValuesForAlphaPoint05 = { {3.64, 4.60},
																			  {3.46, 4.34},
			                                                                  {3.34, 4.16},
			                                                                  {3.26, 4.04},
			                                                                  {3.20, 3.95},
			                                                                  {3.15, 3.88},
			                                                                  {3.11, 3.82},
			                                                                  {3.08, 3.77},
			                                                                  {3.06, 3.73},
			                                                                  {3.03, 3.70},
			                                                                  {3.01, 3.67},
			                                                                  {3.00, 3.65},
			                                                                  {2.98, 3.63},
			                                                                  {2.97, 3.61},
			                                                                  {2.96, 3.59},
			                                                                  {2.95, 3.58} };
	private static final double[][] studentizedRangeValuesForAlphaPoint01 = { {5.70, 6.98},
																			  {5.24, 6.33},
                                                                              {4.95, 5.92},
                                                                              {4.75, 5.64},
                                                                              {4.60, 5.43},
                                                                              {4.48, 5.27},
                                                                              {4.39, 5.15},
                                                                              {4.32, 5.05},
                                                                              {4.26, 4.96},
                                                                              {4.21, 4.89},
                                                                              {4.17, 4.84},
                                                                              {4.13, 4.79},
                                                                              {4.10, 4.74},
                                                                              {4.07, 4.70},
                                                                              {4.05, 4.67},
                                                                              {4.02, 4.64} };
	public static double calculateStudentizedRangeValue(double degreesOfFreedom, double criticalValue, int kLevel) {
		double studentizedRangeValue = 0.0;
		
		for(int i = 0; i < numberOfLevels.length; i++) {
			if(kLevel == numberOfLevels[i]) {
				for(int j = 0; j < degreesOfFreedomLevels.length; j++) {
					if(degreesOfFreedom == degreesOfFreedomLevels[j]) {
						if(criticalValue == .05) {
							studentizedRangeValue = studentizedRangeValuesForAlphaPoint05[j][i];
						} else {
							studentizedRangeValue = studentizedRangeValuesForAlphaPoint01[j][i];
						}
					}
				}
			}
		}
		
		return studentizedRangeValue;
	}
}
