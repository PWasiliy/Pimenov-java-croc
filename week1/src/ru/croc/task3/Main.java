package ru.croc.task3;

import java.util.Arrays;

class Main {
	public static void main(String[] args) {
		int[] intArr = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			intArr[i] = Integer.parseInt(args[i]);
		}

		Arrays.sort(intArr);
		System.out.print(Arrays.toString(intArr));
	}
}