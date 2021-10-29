package ru.croc.task1;

import ru.croc.task1.Figures.*;

class Main {
	public static void main(String[] args) {
		System.out.println("Test example running...");

		Circle circle = new Circle(0, 0, 10);
		Rectangle rectangle = new Rectangle(-5, -5, 5, 5);

		Annotation annotation1 = new Annotation(circle, " –”√");
		Annotation annotation2 = new Annotation(rectangle, "œ–ﬂÃŒ”√ŒÀ‹Õ» ");

		System.out.println(annotation1);
		System.out.println(annotation2);
	}
}