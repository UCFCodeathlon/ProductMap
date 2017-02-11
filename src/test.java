import java.awt.Point;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
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
			Point[] sortX = corners.toArray(new Point[corners.size()]);
			Arrays.sort(sortX, new Comparator<Point>() {
				public int compare(Point a, Point b) {
					int xComp = Integer.compare(a.x, b.x);
					if (xComp == 0)
						return Integer.compare(a.y, b.y);
					else
						return xComp;
				}
			});
			for (Point p : sortX) {
				System.out.println(p.y + " " + p.x);
			}

			// asile then row of the display ...not absolute
			int products[][] = { { 1, 8 }, { 24, 4 } };
			int locations[][] = new int[products.length][2];
			for (int i = 0; i < products.length; i++) {
				int[] item = products[i];
				int asile = item[0]; // 1 to 24?
				int bay = item[1]; // 1 to BAY_LENGTH

				if (asile <= 20) {
					locations[i][0] = bay - 1;
					locations[i][1] = asile;
				} else if (asile <= 40) {
					locations[i][0] = BAY_LENGTH + bay;
					locations[i][1] = asile - 20 + 1;
				} else {
					// 2 for spaces
					locations[i][0] = BAY_LENGTH * 2 + 2 + (asile - 24);
					locations[i][1] = 1 + bay + BAY_LENGTH * (asile - 24 + 1 % 2);
				}
			}
			System.out.println(Arrays.deepToString(locations));
			// int[][] locations = { { 8, 1 }, { 13, 6 } };
			for (int i = 0; i < locations.length; i++) {
				int y = locations[i][0];
				int x = locations[i][1];

				if (!mat[y][x - 1]) {// if there is a free spot to the // left
					// add a pt here
					Point pt = new Point(x - 1, y);
					System.out.println(pt);
					// corners.add(pt);
				} else if (!mat[y][x + 1]) {
					// add a pt at mat[i1 + 1][j1]
					Point pt = new Point(x + 1, y);
					System.out.println(pt);
					// corners.add(pt);
				}
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean inBounds(int col, int row, int length, int width) {
		return !(row < 0 || col < 0 || row >= length || col >= width);
	}

}
