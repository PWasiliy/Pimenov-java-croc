package ru.croc.task2;

class Main {
	static class IllegalPositionException extends Exception {};
	static class Position {
		private int x, y;

		public Position(int x, int y) throws IllegalPositionException {
			this.setX(x);
			this.setY(y);
		}

		public int getX() {
			return this.x;
		}
		public int getY() {
			return this.y;
		}
		public void setX(int x) throws IllegalPositionException {
			if ((x < 0) || (x > 8)) throw new IllegalPositionException();
			this.x = x;
		}
		public void setY(int y) throws IllegalPositionException {
			if ((y < 0) || (y > 8)) throw new IllegalPositionException();
			this.y = y;
		}

		@Override
		public String toString() {
			return (char)(this.x + 97) + Integer.toString(this.y + 1); 
		}
	}

	static Position getPosition(String position) throws IllegalPositionException {
		int x = (int)position.charAt(0) - 97;
		int y = Integer.parseInt(String.copyValueOf(position.toCharArray(), 1, 1)) - 1;
		return new Position(x, y);
	}

	static class IllegalMoveException extends Exception {
		private Position from, to;
		private String figureName;
		public IllegalMoveException(Position from, Position to, String figureName) {
			this.from = from;
			this.to = to;
			this.figureName = figureName;
		} 	

		@Override
		public String getMessage() {
			return String.format("Фигура \"%s\" не может выполнить ход %s -> %s.", this.figureName, this.from.toString(), this.to.toString());
		}
	}

	static void checkHorseMove(Position from, Position to) throws IllegalMoveException {
		int dx = Math.abs(from.x - to.x);
		int dy = Math.abs(from.y - to.y);
		if (!((dx == 2 && dy == 1) || (dx == 1 && dy == 2))) throw new IllegalMoveException(from, to, "Конь");
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Вы не указали позиции на доске.");
			return;
		}

		try {
			for (int i = 0; i < args.length - 1; i++) {
				Position from = getPosition(args[i]);
				Position to = getPosition(args[i + 1]);
				checkHorseMove(from, to);	
			}

			System.out.println("OK");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}