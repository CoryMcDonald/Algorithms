//package com.company;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.util.*;
class AlgoAsgn6 {
	public static void main(String[] args) {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		int index = 0;
		int cases = 1;
		int numOfCases = 0;
		int vertices = 0;
		int startVertex = 0;
		int endVertex = 0;
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext())		
		{
			String input = scanner.nextLine();

			if(index == 0)
			{
				numOfCases = Integer.parseInt(input);
			}

			if( input.length() != 0 && scanner.hasNext() != false)
			{
				String[] split = input.split(" ");
				if(split.length == 4)
				{
					vertices = Integer.parseInt(split[0]);
					startVertex = Integer.parseInt(split[2]);
					endVertex = Integer.parseInt(split[3]);
				}else if(split.length == 3)
				{
					edges.add(new Edge(Integer.parseInt(split[0]),Integer.parseInt(split[1]),Integer.parseInt(split[2])));
				}
			}else if(index != 1)
			{
				System.out.println("Case #" + cases +": " + dijkstra(vertices, edges, startVertex,endVertex));	
				cases ++;
				edges.clear();
				vertices = 0;
				startVertex = 0;
				endVertex =0 ;
			}
			index++;
		}
	}
	static String dijkstra(int vertices, ArrayList<Edge> edges, int startVertex, int endVertex)
	{
		PriorityQueue<Point> queue = new PriorityQueue<Point>();
		ArrayList<Point> visited = new ArrayList<Point>();
		HashMap<Integer, Point> used = new HashMap<Integer, Point>();
		String returnValue = "unreachable";

		Point p = new Point(startVertex);
		p.priority = 0;
		visited.add(p);
		queue.add(p);


		while(queue.size() > 0) 
		{
			Point u = queue.remove();
			if(u.vertex == endVertex)
			{
				returnValue = Integer.toString(u.priority);

				break;
			}
			ArrayList<Edge> adj = new ArrayList<Edge>();
            // Find the adjacent points, this is not efficient but could be improved by breaking if you find a different x
			for(Edge tempPoint : edges)
			{
				if(tempPoint.v1 == u.vertex || tempPoint.v2 == u.vertex)
				{
					adj.add(tempPoint);
				}
			}
			// System.out.println("adjacent edges: " + adj.size());
			for (Edge e : adj)
			{
				int nextVertex = e.v2;
				if(e.v2 == u.vertex)
					nextVertex = e.v1;

				// This is a brand new point we need to add it
				Point next = new Point(nextVertex);
				next.parent = u;
				next.priority = u.priority + e.cost;
				if(used.containsKey(nextVertex))
				{
					if( (u.priority + e.cost) < used.get(nextVertex).priority)
					{
						queue.add(next);
						visited.add(next);
						used.put(nextVertex, next);
					}
				}else
				{
					queue.add(next);
					visited.add(next);
					used.put(nextVertex, next);
				}
			}
		}
		return returnValue;
	}
}
class Point implements Comparable<Point>
{
	int vertex;
	int priority;
	Point parent;

	public Point(int vertex)
	{
		this.vertex = vertex;
		priority = Integer.MAX_VALUE;
	}

	public Point(Point newPoint)
	{
		this.vertex = newPoint.vertex;
		this.priority = newPoint.priority;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Point)) return false;

		Point that = (Point) o;

		if (vertex != that.vertex) return false;

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
		return "("+ vertex + ") - " + priority;
	}
}
class Edge
{
	int v1;
	int v2;
	int cost;

	public Edge(int v1, int v2, int cost)
	{
		this.v1 = v1;
		this.v2 = v2;
		this.cost = cost;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Point)) return false;

		Edge that = (Edge) o;

		if ( v1 == that.v1 && v2 == that.v2)
			return true;
		else if (v1 == that.v2 && v2 == that.v1)
			return true;

		return false;
	}
}