package ru.croc.task1;

import java.util.Scanner;

class Main {
	static class Point {
		int x, y;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Point[] points = new Point[3]; 

			for (int i = 0; i < 3; i++) {
			points[i] = new Point();

			System.out.printf("¬ведите значение координаты X%d: ", i + 1);
			points[i].x = scanner.nextInt();
			System.out.printf("¬ведите значение координаты Y%d: ", i + 1);
			points[i].y = scanner.nextInt();
		}

		double s = (((points[1].x - points[0].x) * (points[2].y - points[0].y)) 
			- ((points[2].x - points[0].x) * (points[1].y - points[0].y))) / 2;
		System.out.printf("ѕлощадь треугольника = %.2f", s);
	}
}