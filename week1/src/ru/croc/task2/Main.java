package ru.croc.task2;

import java.util.Scanner;

class Main {
	static void printBytes(long bytes) {
		double value = bytes;
		int degree = 0;
		while (value >= 1024) {
			degree += 3;
			value = value / Math.pow(10, 3);
		}

		String unit = "";
		switch (degree) {
			case 0 : unit = "B"; break;
			case 3 : unit = "KB"; break;
			case 6 : unit = "MB"; break;
			case 9 : unit = "GB"; break;
			case 12 : unit = "TB"; break;
			case 15 : unit = "PB"; break;
			case 18 : unit = "EB"; break;
			case 21 : unit = "ZB"; break;
			case 24 : unit = "YB"; break;
		} 

		System.out.printf("Отформатированная величина: %.1f " + unit, value);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите количество байт: ");
		printBytes(scanner.nextLong());
	}
}