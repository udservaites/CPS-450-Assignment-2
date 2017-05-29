/*************************************************************************
 * Name:K Rebecca Servaites
 * Date: 05-31-2017
 *
 * Compilation:  javac Point.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/
package ud.cps450.spring2017;

import java.util.Comparator;

public class Point implements Comparable<Point> {

	// compare points by slope
	public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() { // YOUR DEFINITION HERE
		@Override
		public int compare(Point point1, Point point2) {
			double slope1 = slopeTo(point1);
			double slope2 = slopeTo(point2);
			return Double.compare(slope1, slope2);
		}
	};

	private final int x; // x coordinate
	private final int y; // y coordinate

	// create the point (x, y)
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// plot this point to standard drawing
	// this method is not required, but for receiving bonus points
	public void draw() {
		/* YOUR CODE HERE */
		StdDraw.point(this.x, this.y);
	}

	// draw line between this point and that point to standard drawing
	// this method is not required, but for receiving bonus points
	public void drawTo(Point that) {
		/* YOUR CODE HERE */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// slope between this point and that point
	public double slopeTo(Point that) {
		/* YOUR CODE HERE */
		double slope;
		if ((double)(that.x - this.x) == 0) {
			slope = 0;
		} else {
			slope = (double)(that.y - this.y) / (double)(that.x - this.x);
		}
		return slope;
	}

	// is this point lexicographically smaller than that one?
	// comparing y-coordinates and breaking ties by x-coordinates
	public int compareTo(Point that) {
		/* YOUR CODE HERE */
		if (that.y > this.y) {
			return 1;
		} else if (that.y < this.y) {
			return -1;
		} else { // the y's are even. Use x as a tie breaker
			if (that.x > this.x) {
				return 1;
			} else if (that.x < this.x) {
				return -1;
			} else {
				return 0;
			}

		}
	}

	
	// return string representation of this point (x, y)
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}