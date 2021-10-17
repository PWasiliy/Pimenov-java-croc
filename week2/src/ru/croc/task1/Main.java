package ru.croc.task1;

class Main {
	static abstract class Figure {

	}

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

	static class Circle extends Figure {
		private int x, y, r;

		Circle(int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}

		@Override
		public String toString() {
			return String.format("C (%d, %d) %d", this.x, this.y, this.r);
		}
	}

	static class Rectangle extends Figure {
		private int x1, y1, x2, y2;

		Rectangle(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		@Override
		public String toString() {
			return String.format("R (%d, %d), (%d, %d)", this.x1, this.y1, this.x2, this.y2);
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