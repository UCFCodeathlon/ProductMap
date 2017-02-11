import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class test {

	public static final int BAY_LENGTH = 8;

	public static void main(String[] args) {
		boolean[][] mat;

		try {
			Scanner scanny = new Scanner(new File("input/inputGraph.txt"));

			// gather dimensions of map
			int length = scanny.nextInt();
			int width = scanny.nextInt();

			// clean up line after ints
			scanny.nextLine();

			// output dimensions
			System.out.println(length);
			System.out.println(width);

			mat = new boolean[length][width];

			int q = 0;
			while (scanny.hasNextLine()) {
				String[] array1 = scanny.nextLine().split("");
				boolean[] arr = new boolean[array1.length];

				for (int i = 0; i < array1.length; i++) {
					System.out.print(array1[i]);
					// make true if blocked
					if (array1[i].equals("x")) {
						arr[i] = array1[i].equals("x");
					}
				}
				mat[q++] = arr;
				System.out.println();
			}

			// Find the adjacent points to obstacles
			Set<Point> corners = new HashSet<Point>();
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < width; j++) {
					Point pt = null;
					int cornerCnt = 0;
					{// Up down left right check, is there adj point
						int[] di = { -1, -1, 1, 1 };
						int[] dj = { -1, 1, -1, 1 };
						for (int ii = 0; ii < di.length; ii++) {
							// for (int jj = 0; jj < dj.length; jj++) {
							int row = i + di[ii];
							int col = j + dj[ii];

							if (inBounds(col, row, length, width)) {
								if (mat[row][col]) {
									cornerCnt++;
									pt = new Point(j, i);
									System.out.println("- " + pt);
									// they are connected yo
								}
							}
							// }
						}
					}

					if (pt != null) {
						// i is row
						// j is col
						// i then j
						int row = pt.y;
						int col = pt.x;

						if (col == 0 && mat[row][col + 1]) {
							// to the right
							// System.out.println(i + " " + j);
							pt = null;
							continue;
						} else if (col == (width - 1) && mat[row][col - 1]) {
							// to the left
							// System.out.println(i + " " + j);
							pt = null;
							continue;
						}

						System.err.println(col);
						if (row >= 1 && row < length - 1) {
							if (mat[row - 1][col] && mat[row + 1][col]) {
								// top and bottom
								// System.out.println(i + " " + j);
								pt = null;
								continue;
							}

						}

						if (col >= 1 && col < width - 1) {
							if (mat[row][col - 1] && mat[row][col + 1] || cornerCnt < 2) {
								// left and right
								// System.out.println(i + " " + j);
								pt = null;
								continue;
							}
						}

						{
							int matches = 0;
							// Up down left right check, is there adj point
							int[] di = { 0, 0, -1, 1 };
							int[] dj = { -1, 1, 0, 0 };
							for (int ii = 0; ii < di.length; ii++) {

								int row1 = row + di[ii];
								int col1 = col + dj[ii];

								if (inBounds(col1, row1, length, width)) {
									if (mat[row1][col1]) {
										matches++;

										System.out.println("* " + pt + ", " + matches);
										// they are connected yo
									}
								}
								if (matches >= 2) {
									pt = null;
									continue;
								}

							}
						}

						System.out.println("After cont");
						if (pt != null)
							corners.add(pt);
					}

				}
			}
			System.out.println();

			HashSet<Point> items = new HashSet<Point>();

			// asile then row of the display ...not absolute
			// int products[][] = { { 1, 7 }, { 24, 3 }, { 39, 1 }, { 39, 8 },
			// { 39, 5 } };
			int products[][] = { { 2, 1 }, { 3, 1 }, { 4, 1 }, { 7, 1 }, { 8, 1 } };

			int locations[][] = new int[products.length][2];
			for (int i = 0; i < products.length; i++) {
				int[] item = products[i];
				int asile = item[0]; // 1 to 24?
				int bay = item[1]; // 1 to BAY_LENGTH

				if (asile <= 20) {
					locations[i][0] = bay;
					locations[i][1] = asile + (int) (asile / 2d - .5);
				} else if (asile <= 40) {
					locations[i][0] = BAY_LENGTH + bay;
					locations[i][1] = (asile - 20) + (int) ((asile - 20) / 2d - .5);
				} else {
					// 2 for spaces
					locations[i][0] = BAY_LENGTH * 2 + 2 + (asile - 24);
					locations[i][1] = 1 + bay + BAY_LENGTH * (asile - 24 + 1 % 2);
				}
			}
			System.out.println(Arrays.deepToString(locations));
			// int[][] locations = { { 12, 6 }, { 13, 27 }, { 10, 27 } };
			// for (int i = 0; i < locations.length; i++) {
			// int y = locations[i][0];
			// int x = locations[i][1];
			// Point pt = null;
			//
			// if (!mat[y][x - 1]) {// if there is a free spot
			// // to the // left
			// // add a pt here
			// Point p = new Point(x - 1, y);
			// System.out.println("to left " + p);
			// items.add(p);
			// continue;
			// // corners.add(pt);
			// } else if (!mat[y][x + 1]) {
			// // add a pt at mat[i1 + 1][j1]
			//
			// Point p2 = new Point(x + 1, y);
			// items.add(p2);
			// System.out.println("to right " + p2);
			// // corners.add(pt);
			// }
			// // if (pt != null)
			// // items.add(pt);
			// }

			items.add(new Point(27, 10));
			items.add(new Point(6, 1));
			items.add(new Point(12, 6));
			items.add(new Point(27, 17));
			items.add(new Point(27, 14));

			Point[] temp = items.toArray(new Point[items.size()]);
			// Collections.shuffle(Arrays.asList(temp));

			System.out.println(Arrays.deepToString(temp));
			System.err.println("-");
			Point[] sorted = sortY(corners);

			Point[] points = new Point[corners.size() + items.size()];
			System.arraycopy(sorted, 0, points, 0, sorted.length);
			System.out.println(Arrays.deepToString(points));
			System.arraycopy(temp, 0, points, sorted.length, items.size());
			System.out.println(Arrays.deepToString(points));
			System.err.println("------");
			//
			// corners.addAll(items);
			// System.out.println(Arrays.deepToString(corners.toArray()));
			// Point[] points = corners.toArray(new Point[corners.size()]);
			double weights[][] = new double[points.length][points.length];
			for (int i = 0; i < points.length; i++) {
				for (int j = 0; j < points.length; j++) {
					weights[i][j] = Math.abs(points[j].x - points[i].x) + Math.abs(points[j].y - points[i].y);
					// dist = Math.abs(x2 - x1) + Math.abs(y2 - y1);
				}
			}

			for (int i = 0; i < weights.length; i++) {
				System.out.println(Arrays.toString(weights[i]));
			}
			System.err.println("------");

			// FloydWarshall2.floydWarshall2(weights, points.length);
			// FloydWarshall2.printResult(dist, next);
			// FloydWarshall fw = new FloydWarshall(weights);

			// fw.distances();
			// System.out.println(Arrays.deepToString(DistanceMap));
			// System.out.println();
			// System.out.println(Arrays.deepToString(fw.distances()));
			//
			for (int i = 0; i < weights.length; i++) {
				System.out.print(Arrays.toString(weights[i]));
				// make true if blocked
				System.out.println();
			}

			int sub[][] = new int[items.size() + 1][items.size() + 1];

			int len = weights.length - sub.length + 1;
			for (int i = len - 1; i < weights.length; i++) {
				for (int j = len - 1; j < weights.length; j++) {
					System.out.println(j + " " + i);
					sub[i - len + 1][j - len + 1] = (int) weights[i][j];
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

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean inBounds(int col, int row, int length, int width) {
		return !(row < 0 || col < 0 || row >= length || col >= width);
	}

	public static Point[] sortY(Set<Point> corners) {
		Point[] sortY = corners.toArray(new Point[corners.size()]);
		Arrays.sort(sortY, new Comparator<Point>() {
			public int compare(Point a, Point b) {
				int xComp = Integer.compare(a.y, b.y);
				if (xComp == 0)
					return Integer.compare(a.x, b.x);
				else
					return xComp;
			}
		});

		for (Point p : sortY) {
			System.out.println(p.y + " " + p.x);
		}
		System.err.println("-----------");
		return sortY;
	}

}
