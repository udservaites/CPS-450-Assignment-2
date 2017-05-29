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

	static ArrayList<ArrayList<Point>> findSlopes(Point[] points) {
		double previousSlope = 0;
		double currentSlope;
		int slopeCounter = 0;
		boolean foundIt = false;
		int counter = 0;
		ArrayList<ArrayList<Point>> lineList = new ArrayList<ArrayList<Point>>();

		int j = 0;
		// Make a copy of the array so we don't check the same points twice
		Point[] pointsCopy = new Point[points.length];
		System.arraycopy(points, 0, pointsCopy, 0, points.length);

		// find slopes of points in regard to the point we are currently looking
		// at
		for (Point p : pointsCopy) {
			// counter = 0;
			Arrays.sort(points, 0, points.length, p.SLOPE_ORDER);
			System.out.println("For point p: " + p);
			for (Point p2 : points) {
				System.out.println(p2);
			}
			// System.out.println("Entering for loop");
			// initial slope check
			// for (; counter < points.length && !foundIt; counter++) {
			// System.out.println("Made it past for loop");
			// if (p.compareTo(points[counter]) > 0) {
			// System.out.println("Found one!");
			// previousSlope = p.slopeTo(points[0]);
			// foundIt = true;
			// }
			// }

			// check the rest
			for (counter = 0, slopeCounter = 0; counter < points.length; counter++) {
				// to prevent permutations compare points
				if (p.compareTo(points[counter]) > 0) {
					// execute slope comparison
					currentSlope = p.slopeTo(points[counter]);
					if (currentSlope == previousSlope) {
						slopeCounter++;
					} else {
						// check slope counter; if >= 3, then add points to list
						// if (slopeCounter >= 3) {
						ArrayList<Point> pointList = new ArrayList<Point>();
						pointList.add(p);
						while (slopeCounter > 0) {
							pointList.add(points[counter - slopeCounter]);
							slopeCounter--;
						}
						lineList.add(pointList);

						// }
					}
					previousSlope = currentSlope;
					// counter = 0;
				}

			}

		}
		return lineList;

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
			// draw lines
			//pl.get(1).drawTo(pl.get(3));

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Enter the filepath: ");
		Scanner s = new Scanner(System.in);
		String path = s.nextLine();
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<ArrayList<Point>> lines = new ArrayList<ArrayList<Point>>();

		try {
			points = readInput(path);
			for (Point p : points) {
				System.out.println(p.toString());
			}
		} catch (IOException e) {
			System.err.println("Could not read file. Please try again later.");
			System.exit(1);
		}
		// convert ArrayList into an array
		Point[] pointsArray = points.toArray(new Point[points.size()]);

		outputLines(findSlopes(pointsArray));

	}

}
