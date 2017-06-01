### K Rebecca Servaites
### CPS 450 Spring 2017
### 05-31-2017
### https://github.com/udservaites/CPS-450-Assignment-2


# Assignment 2: 

## Class: Point

This class is the data structure that the entire program revolves around. The class
Point represents points on a coordinate plane. It contains the following methods and
attributes:

* private int x;
* private int y;
* public Point(int x, int y)
* public void draw()
* public void drawTo(Point that)
* public String toString()
* public int compareTo(Point that)
* public double slopeTo(Point that)
* public getX()
* public getY()


## Class: Brute

This class compares four points at one time to see if they line within the same
line. This is done by using a brute force method. The brute force method in 
this cases required using four nested for loops, which makes it a costly
algorithm. This program runs at O(n^4) time. 
Contains the following methods:

* public static ArrayList<Point> readInput(String filepath)
* public static void outputLines(ArrayList<ArrayList<Point>> pointsList)
* public static ArrayList<ArrayList<Point>> checkSlopes(Point[] points)


## Class: Fast

This class is to be an improvement on the Brute class. It attempts to find the same 
points in a line, but by comparing slopes in a sorted array. The points are sorted in the
array, by the slope in regard to a point p. This is so all of the like slopes will be grouped
together. By iterating through the array, the current element can be compared against the 
previous element. If their slopes are the same, that means they lie within the same line
in relation to the point p. Those points that have the same slope are added to a ArrayList<Point> line.
If line contains three or more Points, then we have found a line of at least four points. This 
program runs at O(n^2) time. 


My code has a couple bugs in it, and I am having difficulty working them out. The slopes are sorted
in an array, compared, and added to line. However, something happens when the lines are added to 
ArrayList<ArrayList<Point>> lineList in the method findSlopes. If I had more time, I could probably 
debug the program so it would display the correct output. So far, just with my testing, it
does not output anything. 
Contains the following methods: 

* public static ArrayList<ArrayList<Point>> findSlopes(ArrayList<Point> points)
* public static ArrayList<Point> readInput(String filepath)
* public static void outputLines(ArrayList<ArrayList<Point>> pointsList)


## Class: StdDraw

This class is a library that was recommended in the assignment instructions. I could not find a jar
file to import to my library folder, so I copied the code and createda  class for it. This class is 
used to draw the coordinate plane, points, and lines used in Brute and Fast. 


## makefile

There is a makefile included with this project to make compiling easier. It is not required 
for using this program. You can use javac *java in the command line to compile and then run
Brute and Fast. 








