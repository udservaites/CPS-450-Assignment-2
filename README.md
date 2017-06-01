###K Rebecca Servaites
###CPS 450 Spring 2017
###05-31-2017


#Assignment 2: 

##Class: Point

This class is the data structure that the entire program revolves around. The class
Point represents points on a coordinate plane. It contains the following methods and
attributes:

private int x;
private int y;
public Point(int x, int y)
public void draw()
public void drawTo(Point that)
public String toString()
public int compareTo(Point that)
public double slopeTo(Point that)
public getX()
public getY()


##Class: Brute

This class compares four points at one time to see if they line within the same
line. This is done by using a brute force method. The brute force method in 
this cases required using four nested for loops, which makes it a costly
algorithm. This program runs at O(n^4) time. 

##Class: Fast

This class is to be an improvement on the Brute class. It attempts to find the same 
points in a line, but by comparing slopes in a sorted array. The points are sorted in the
array, by the slope in regard to a point p. This was all of the like slopes will be grouped
together. By iterating through the array, the current element can be compared against the 
previous element. If their slopes are the same, that means they lie within the same line
in relation to the point p. The algorith  
