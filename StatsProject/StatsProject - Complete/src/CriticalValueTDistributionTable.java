
public class CriticalValueTDistributionTable {
	private static final double[] levelsOfSignificanceForDirectionalTest = {.10, .05, .025, .01, .005, .0005};
	private static final double[] levelsOfSignificanceForNonDirectionalTest = {.20, .10, .05, .02, .01, .001};
	private static final double[] degreesOfFreedomLevels = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
	private static final double[][] criticalValuesForTDistribution = { {3.078, 6.314, 12.706, 31.821, 63.657, 636.619},
																{1.886, 2.920, 4.303, 6.965, 9.925, 31.598},
																{1.638, 2.353, 3.182, 4.541, 5.841, 12.941},
																{1.533, 2.132, 2.776, 3.747, 4.604, 8.610},
																{1.476, 2.015, 2.571, 3.365, 4.032, 6.859},
																{1.440, 1.943, 2.447, 3.143, 3.707, 5.959},
																{1.415, 1.895, 2.365, 2.998, 3.499, 5.405},
																{1.397, 1.860, 2.306, 2.896, 3.355, 5.041},
																{1.383, 1.833, 2.262, 2.821, 3.250, 4.781},
																{1.372, 1.812, 2.228, 2.764, 3.169, 4.587},
																{1.363, 1.796, 2.201, 2.718, 3.106, 4.437},
																{1.356, 1.782, 2.179, 2.681, 3.055, 4.318},
																{1.350, 1.771, 2.160, 2.650, 3.012, 4.221},
																{1.345, 1.761, 2.145, 2.624, 2.977, 4.140},
																{1.341, 1.753, 2.131, 2.602, 2.947, 4.073},
																{1.337, 1.746, 2.120, 2.583, 2.921, 4.015},
																{1.333, 1.740, 2.110, 2.567, 2.898, 3.965},
																{1.330, 1.734, 2.101, 2.552, 2.878, 3.922},
																{1.328, 1.729, 2.093, 2.539, 2.861, 3.883},
																{1.325, 1.725, 2.086, 2.528, 2.845, 3.850} };
	
	public static double calculateCriticalValue(double degreesOfFreedom, double levelOfSignificance, String typeOfDirectionalTest) {
		double criticalValue = 0.0;
		if(typeOfDirectionalTest.equals("D")) {
			for(int i = 0; i < levelsOfSignificanceForDirectionalTest.length; i++) {
				if(levelOfSignificance == levelsOfSignificanceForDirectionalTest[i]) {
					for(int j = 0; j < degreesOfFreedomLevels.length; j++) {
						if(degreesOfFreedom == degreesOfFreedomLevels[j]) {
							criticalValue = criticalValuesForTDistribution[j][i];
						}
					}
				}
			}
		} else {
			for(int i = 0; i < levelsOfSignificanceForNonDirectionalTest.length; i++) {
				if(levelOfSignificance == levelsOfSignificanceForNonDirectionalTest[i]) {
					for(int j = 0; j < degreesOfFreedomLevels.length; j++) {
						if(degreesOfFreedom == degreesOfFreedomLevels[j]) {
							criticalValue = criticalValuesForTDistribution[j][i];
						}
					}
				}
			}
		}
		return criticalValue;
	}
												
}
