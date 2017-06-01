/*************************************************************************
 * Name:K Rebecca Servaites
 * Date: 05-31-2017
 *
 * Compilation:  javac Fast.java
 *
 * Description: A faster way to find lines with all 3 or more points. Create slopes 
 * with all point in relation to point p. Then sort slopes to find collinear points. 
 *
 *************************************************************************/
package ud.cps450.spring2017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Fast {

	static ArrayList<ArrayList<Point>> findSlopes(ArrayList<Point> points) {
		ArrayList<ArrayList<Point>> linesList = new ArrayList<ArrayList<Point>>();
		Point[] pointArray = points.toArray(new Point[points.size()]);
		ArrayList<Point> line = new ArrayList<Point>();

		for (Point p : points) {
			Arrays.sort(pointArray, p.SLOPE_ORDER); // sort by slope to p
			// System.out.println("Sorted");
			/*
			 * for (Point p2 : pointArray) { System.out.println(p2); }
			 */
			// Point p = points[0];

			// set point at beginning of list
			line.add(0, p);

			double previousSlope = p.slopeTo(pointArray[0]);
			int pointsInLine = 1;
			// iterate through array to find like slopes and add them to the
			// line list
			for (int i = 0; i < pointArray.length; i++) {

				if (p.compareTo(pointArray[i]) >= 0) { // to prevent
														// permutations
					double currentSlope = p.slopeTo(pointArray[i]); // get slope
																	// of
																	// p
					// with current
					// point
					if (currentSlope == previousSlope) { // if slopes are the
															// same add point to
															// line
						line.add(pointArray[i]);
						// System.out.println("Line print: " + line);
						pointsInLine++;
					} else {
						if (pointsInLine >= 3) {
							// add to return list
							linesList.add(line);

						}
						// reset points in a line
						pointsInLine = 1;

					}
					previousSlope = currentSlope;
					line.clear();
				}

			}

			/*
			 * System.out.println("Test"); for (ArrayList<Point> line :
			 * linesList) { for (Point p : line) { System.out.println(p); }
			 */

		}
		return linesList;

	}

	static ArrayList<Point> readInput(String filepath) throws IOException {

		String data;
		int numLines, x, y;
		StringTokenizer st;
		ArrayList<Point> listOfPoints = new ArrayList<Point>();
		// set up the file readers
		File file = new File(filepath);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		// System.out.println("File ready to go");

		// read the first int
		data = br.readLine();
		// read the rest of the file
		if (data != null) {
			numLines = Integer.parseInt(data);
			data = br.readLine();
			for (int i = 0; i < numLines; i++) {
				st = new StringTokenizer(data);
				// System.out.println("Data: " + data);
				x = Integer.parseInt(st.nextToken());
				// System.out.println("x: " + x);
				y = Integer.parseInt(st.nextToken());
				// System.out.println("y: " + y);
				Point p = new Point(x, y);
				listOfPoints.add(p);
				data = br.readLine();
			}
		}

		return listOfPoints;
	}

	static void outputLines(ArrayList<ArrayList<Point>> pointsList) {

		for (ArrayList<Point> pl : pointsList) {
			// print points on line
			for (Point p : pl) {
				System.out.print(p + "->");
			}
			// draw lines /
			pl.get(0).drawTo(pl.get(pl.size()-1));
			System.out.println("\n");

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Enter the filepath: ");
		Scanner s = new Scanner(System.in);
		String path = s.nextLine();
		ArrayList<Point> points = new ArrayList<Point>();

		try {
			points = readInput(path);
			outputLines(findSlopes(points));

		} catch (IOException e) {
			System.err.println("Could not read file. Please try again later.");
			System.exit(1);
		}

	}

}
