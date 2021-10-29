package ru.croc.task1;

import ru.croc.task1.Figures.*;

class Main {
	static class Annotation {
		private Figure figure;
		private String caption;

		Annotation(Figure figure, String caption) {
			this.figure = figure;
			this.caption = caption;
		}

		@Override
		public String toString() {
			return String.format("%s: %s", this.figure.toString(), caption);
		}
	}

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