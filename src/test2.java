import java.util.Arrays;
import java.util.InputMismatchException;

public class test2 {

	public static void main(String[] args) {
double xxxx = Double.POSITIVE_INFINITY;
double[][] DistanceMap = {
	{	0,2,xxxx,4,xxxx,xxxx,xxxx,xxxx,xxxx,xxxx,xxxx,xxxx,xxxx         },
	{	2,0,2,xxxx,4,xxxx,xxxx,xxxx,xxxx,xxxx,xxxx,2,xxxx               },
	{	xxxx,2,0,xxxx,xxxx,xxxx,4,xxxx,xxxx,xxxx,xxxx,xxxx,xxxx         },
	{	4,xxxx,xxxx,0,2,xxxx,xxxx,xxxx,xxxx,6,xxxx,xxxx,5               },
	{	xxxx,4,xxxx,2,0,1,xxxx,xxxx,xxxx,xxxx,xxxx,2,xxxx               },
	{	xxxx,xxxx,xxxx,xxxx,1,0,1,2,xxxx,xxxx,xxxx,xxxx,xxxx            },
	{	xxxx,xxxx,4,xxxx,xxxx,1,0,xxxx,xxxx,xxxx,xxxx,xxxx,xxxx         },
	{	xxxx,xxxx,xxxx,xxxx,xxxx,2,xxxx,0,2,xxxx,xxxx,xxxx,xxxx         },
	{	xxxx,xxxx,xxxx,xxxx,xxxx,xxxx,xxxx,2,0,xxxx,2,xxxx,xxxx         },
	{	xxxx,xxxx,xxxx,xxxx,6,xxxx,xxxx,xxxx,xxxx,0,3,xxxx,1            },
	{	xxxx,xxxx,xxxx,xxxx,xxxx,xxxx,xxxx,xxxx,2,3,0,xxxx,xxxx         },
	{	xxxx,2,xxxx,xxxx,2,xxxx,xxxx,xxxx,xxxx,xxxx,xxxx,0,xxxx         },
	{	xxxx,xxxx,xxxx,5,xxxx,xxxx,xxxx,xxxx,xxxx,1,xxxx,xxxx,0         },

};

//double[][] DistanceMap = {{0,2, Double.POSITIVE_INFINITY	,4,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	},
//	{2,0,2,Double.POSITIVE_INFINITY	,4,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	},
//	{Double.POSITIVE_INFINITY	,2,0,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,4,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	},
//	{4,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,0,2,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,6,Double.POSITIVE_INFINITY	},
//	{Double.POSITIVE_INFINITY	,4,Double.POSITIVE_INFINITY	,2,0,1,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	},
//	{Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,1,0,1,2,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	},
//	{Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,4,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,1,0,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	},
//	{Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,2,Double.POSITIVE_INFINITY	,0,2,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	},
//	{Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,2,0,Double.POSITIVE_INFINITY	,2},
//	{Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,6,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,0,3},
//	{Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,2,3,0}
//		
//};
		
		
		FloydWarshall fw = new FloydWarshall(DistanceMap);

//		fw.distances();
//		System.out.println(Arrays.deepToString(DistanceMap));
//		System.out.println();
//		System.out.println(Arrays.deepToString(fw.distances()));
//		
		for (int i = 0; i < DistanceMap.length; i++) {
			System.out.print(Arrays.toString(DistanceMap[i]));
			// make true if blocked
			System.out.println();
		}
		
		int  sub[][] = new int[3][3];
		
		int len = 11;
		for (int i = len - 1; i < DistanceMap.length; i++) {
			for (int j = len - 1; j < DistanceMap.length; j++) {
				sub[len +1-i][len+1	- j] = (int) DistanceMap[i][j];
			}
		}
		System.out.println();
		for (int i = 0; i < sub.length; i++) {
			System.out.print(Arrays.toString(sub[i]));
			// make true if blocked
			System.out.println();
		}
		
		try {
			TSPNearestNeighbour tspNearestNeighbour = new TSPNearestNeighbour();
			tspNearestNeighbour.tsp(sub);
		} catch (InputMismatchException inputMismatch) {
			System.out.println("Wrong Input format");
		}
	
		
	} 

}
