package ru.croc.task2;

class Main {
	public interface Movable {
  		void move(int dx, int dy);
	}

	static abstract class Figure {
		abstract boolean isPointIn(int x, int y);
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

		public String getCaption() {
			return this.caption;
		}

		public Figure getFigure() {
			return this.figure;
		}
	}

	static class Circle extends Figure implements Movable {
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

		@Override
		boolean isPointIn(int x, int y) {
			double dist = Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
			return dist <= this.r;
		}

		public void move(int dx, int dy) {
			this.x += dx;
			this.y += dy;
		}
	}

	static class Rectangle extends Figure implements Movable {
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

		@Override
		boolean isPointIn(int x, int y) {
			return (x >= this.x1) && (x <= this.x2) && (y >= this.y1) && (y <= this.y2);
		}

		public void move(int dx, int dy) {
			this.x1 += dx;
			this.y1 += dy;
			this.x2 += dx;
			this.y2 += dy;
		}
	}

	static class AnnotatedImage {
  		private final Annotation[] annotations;

		AnnotatedImage(Annotation...annotations) {
			this.annotations = annotations;
		}

		Annotation findByLabel(String label) {
			Annotation result = null;
			for(Annotation annotation : annotations) {
				if (annotation.getCaption().contains(label)) {
					result = annotation;
					break;
				}
			}

			return result;
		} 

		Annotation findByPoint(int x, int y) {
			Annotation result = null;
			for(Annotation annotation : annotations) {
				if (annotation.getFigure().isPointIn(x, y)) {
					result = annotation;
					break;
				}
			}

			return result;
		}
	}

	public static void main(String[] args) {
		System.out.println("Test example running...");

		Circle circle = new Circle(0, 0, 10);
		Rectangle rectangle = new Rectangle(-5, -5, 5, 5);

		Annotation annotation1 = new Annotation(circle, "ÊÐÓÃ");
		Annotation annotation2 = new Annotation(rectangle, "ÏÐßÌÎÓÃÎËÜÍÈÊ");

		System.out.println(annotation1);
		System.out.println(annotation2);

		Annotation[] annotations = new Annotation[2];
		annotations[0] = annotation1;
		annotations[1] = annotation2;

		AnnotatedImage image = new AnnotatedImage(annotations);
		System.out.println(image.findByLabel("ÓÃ"));
		System.out.println(image.findByPoint(-1, -1));
	}
}