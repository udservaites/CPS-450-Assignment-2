/*************************************************************************
 * Name:K Rebecca Servaites
 * Date: 05-31-2017
 *
 * Compilation:  javac Brute.java
 *
 * Description: Examines four points at a time and checks to 
 * see whether they all lie on the same line segment, printing out
 * any such line segments to standard output and drawing them using 
 * standard drawing.
 *
 *************************************************************************/
package ud.cps450.spring2017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Brute {

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
			System.out.println(pl.get(0) + "->" + pl.get(1) + "->" + pl.get(2) + "->" + pl.get(3));
			// draw lines
			pl.get(1).drawTo(pl.get(3));

		}
	}

	/*
	 * checks the slopes of all the points to see if they lie along the same
	 * line
	 */

	static ArrayList<ArrayList<Point>> checkSlopes(Point[] points) {
		double slopeOfPToQ, slopeOfPToR, slopeOfPToS;
		// List of lists to store the same slope points
		ArrayList<ArrayList<Point>> fourPointsInARow = new ArrayList<ArrayList<Point>>();
		Set<ArrayList<Point>> PointsInARow = new HashSet<ArrayList<Point>>();

		// check all combinations of p q r s
		// check if array meets length requirements
		for (int p = 0; p < points.length; p++) {
			for (int q = p + 1; q < points.length; q++) {
				slopeOfPToQ = points[p].slopeTo(points[q]);
				for (int r = q + 1; r < points.length; r++) {
					slopeOfPToR = points[p].slopeTo(points[r]);
					for (int s = r + 1; s < points.length; s++) {
						slopeOfPToS = points[p].slopeTo(points[s]);
						if ((slopeOfPToQ == slopeOfPToS) && (slopeOfPToQ == slopeOfPToR)
								&& (slopeOfPToQ == slopeOfPToS)) {
							// if we get here, then all slopes are equal
							// add the points to the list
							ArrayList<Point> fourPoints = new ArrayList<Point>();
							fourPoints.add(points[p]);
							fourPoints.add(points[q]);
							fourPoints.add(points[r]);
							fourPoints.add(points[s]);
							fourPointsInARow.add(fourPoints);

						}
					}
				}
			}
		}

		return fourPointsInARow;
	}

	/* reset the coordinate system */
	static void setUpCoordinateSystem(Point[] points) {
		// Resize the coordinate area
		StdDraw.setXscale(0, 32767);
		StdDraw.setYscale(0, 32767);

		// Draw the points on the coordinate system
		for (Point p : points) {
			p.draw();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// check command line arguments
		/*
		 * if (args.length < 2) { System.err.println("Brute format: <filepath>"
		 * ); System.exit(1); }
		 */

		System.out.println("Enter the filepath: ");
		Scanner s = new Scanner(System.in);
		String path = s.nextLine();
		ArrayList<Point> points = new ArrayList<Point>();
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

		// reset coordinate system
		setUpCoordinateSystem(pointsArray);

		// Pass into methods
		outputLines(checkSlopes(pointsArray));

	}

}
