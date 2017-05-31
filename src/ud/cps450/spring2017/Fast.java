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
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Fast {

	static ArrayList<ArrayList<Point>> findSlopes(Point p, ArrayList<Point> points) {

		ArrayList<ArrayList<Point>> lineList = new ArrayList<ArrayList<Point>>();

		// iterate through array and find like slopes and put them into a
		// list and delete them from arraylist
		for (Point point : points) {
			// find slope of p and point
			double slope = p.slopeTo(point);
			ArrayList<Point> line = new ArrayList<Point>();
			for (Point p2 : points) {
				if (p.slopeTo(p2) == slope) {
					line.add(p2);
					points.remove(p2);
				}
			}
			if (line != null) {
				line.add(0, p);
				if (line.size() >= 4) {
					lineList.add(line);
				}
			}
		}

		// Go through line list and remove the ones that are smaller than 4
		// for (ArrayList<Point> line : lineList) {
		// if (line.size() < 4) {
		// line.remove(line);
		// }
		// }
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
			// pl.get(1).drawTo(pl.get(3));

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Enter the filepath: ");
		Scanner s = new Scanner(System.in);
		String path = s.nextLine();
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<ArrayList<ArrayList<Point>>> lines = new ArrayList<ArrayList<ArrayList<Point>>>();

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

		for (Point point : points) {
			lines.add(findSlopes(point, points));
		}

		// outputLines(findSlopes(points));

	}

}
