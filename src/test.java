import java.awt.Point;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class test {

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

						// if (i == 0 && mat[i + 1][j]) {
						// // to the right
						// pt = null;
						// continue;
						// }
						// if (i == (width - 1) && mat[i - 1][j]) {
						// // to the left
						// pt = null;
						// System.out.println("before cont");
						// continue;
						// }

						System.out.println("After cont");
						if (pt != null)
							corners.add(pt);
					}
					// if (mat[row - 1][col] && mat[row + 1][col]) {
					//
					// }
					// if (mat[row][col - 1] && mat[row][col + 1]) {
					//
					// }
					//
					// for (int ii = 0; ii < di.length; ii++) {
					// for (int jj = 0; jj < dj.length; jj++) {
					// int row = i + di[ii];
					// int col = j + dj[jj];
					//
					// if (inBounds(col, row, length, width)) {
					// if (mat[row][col]) {
					//
					// corners.add(new Point(col, row));
					// continue;
					// // they are connected yo
					// }
					// }
					// }
					// }

				}
			}
			System.out.println();

			// Arrays.sort(corners, new Comparator<Point>() {
			// public int compare(Point x1, Point x2) {
			// int result = Double.compare(x1.getX(), x2.getX());
			// if (result == 0) {
			// // both X are equal -> compare Y too
			// result = Double.compare(x1.getY(), x2.getY());
			// }
			// return result;
			// }
			// });

			Point[] ps = corners.toArray(new Point[corners.size()]);
			Arrays.sort(ps, new Comparator<Point>() {
				public int compare(Point a, Point b) {
					int xComp = Integer.compare(a.x, b.x);
					if (xComp == 0)
						return Integer.compare(a.y, b.y);
					else
						return xComp;
				}
			});

			for (Point p : ps) {
				System.out.println(p.y + " " + p.x);
			}

			// for (int i = 0; i < matt.length; i++) {
			// for (int j = 0; j < matt.length; j++) {
			// if (mat[i + 1][j] == true) {
			//
			// } else if (mat[i + 1][j] == true) {
			//
			// } else if (mat[i + 1][j] == true) {
			//
			// } else if (mat[i + 1][j] == true) {
			//
			// }
			// }
			// }

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean inBounds(int col, int row, int length, int width) {
		return !(row < 0 || col < 0 || row >= length || col >= width);
	}

}
