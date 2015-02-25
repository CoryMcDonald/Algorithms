//package com.company;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.util.*;
class AlgoAsgn5 {
	public static void main(String[] args) {
		ArrayList<Point> tests = new ArrayList<Point>();

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext())
		{
			String input = scanner.nextLine();
			if(input != "")
			{
				String[] inputSplit = input.split(" ");
				if(inputSplit.length > 1)
				{
					tests.add(new Point(Double.parseDouble(inputSplit[0]),Double.parseDouble(inputSplit[1])));
				}
				else
				{
					if(tests.size() > 0)
						System.out.println(Primms(tests.toArray(new Point[tests.size()])));
					tests.clear();
				}

				if(scanner.hasNext() == false)
				{

					if(tests.size() > 0)
						System.out.println(Primms(tests.toArray(new Point[tests.size()])));
					tests.clear();
				}
			}
		}
	}

	static double Primms(Point[] points)
	{
		double cost = 0.0;
		ArrayList<Point> visited = new ArrayList<Point>();
		PriorityQueue<Point> queue = new PriorityQueue<Point>();

		points[0].priority = 0;
		for(Point p : points)
		{
			queue.add(p);
		}
		while(queue.size() > 0) {
			Point u = queue.remove();
			cost += u.priority;

            //For each adjacent vertex to (v) to (u)
			for(int i=0; i<points.length; i++)
			{
				Point v = points[i];
				if(queue.contains(v) && distance(u,v) < v.priority)
				{
					queue.remove(v);
					v.parent = u;
					v.priority = distance(u,v);
					queue.add(v);
				}
			}

		}

		return round(cost,2);
	}

    //http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	private static double distance (Point p1,Point p2) {
		double x = Math.abs(p1.x-p2.x);
		double y = Math.abs(p1.y-p2.y);
		return Math.sqrt(y*y + x*x);
	}
}
class Point implements Comparable<Point>
{
	double x;
	double y;
	double priority;
	Point parent;

	public Point(double x, double y)
	{
		this.x = x;
		this.y =y;
		priority = Double.MAX_VALUE;
	}

	public Point(Point newPoint)
	{
		this.x = newPoint.x;
		this.y = newPoint.y;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Point)) return false;

		Point that = (Point) o;

		if (x != that.x) return false;
		if (y != that.y) return false;

		return true;
	}

	@Override
	public int compareTo(Point other) {
		if(priority > other.priority) {
			return 1;
		}
		else if(priority == other.priority)
			return 0;

		return -1;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}
}