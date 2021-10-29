package ru.croc.task2;

import ru.croc.task1.Figures.*;
import ru.croc.task1.Annotation;

class Main {
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

		Annotation annotation1 = new Annotation(circle, " –”√");
		Annotation annotation2 = new Annotation(rectangle, "œ–ﬂÃŒ”√ŒÀ‹Õ» ");

		System.out.println(annotation1);
		System.out.println(annotation2);

		Annotation[] annotations = new Annotation[2];
		annotations[0] = annotation1;
		annotations[1] = annotation2;

		AnnotatedImage image = new AnnotatedImage(annotations);
		System.out.println(image.findByLabel("”√"));
		System.out.println(image.findByPoint(-1, -1));
	}
}