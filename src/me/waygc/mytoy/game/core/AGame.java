package me.waygc.mytoy.game.core;

public abstract class AGame {

//	protected final boolean[] map;
	protected final Status[] status;
	public final int WIDTH;
	public final int HEIGHT;
	public final int COUNT;
	protected int found;

	public AGame(int count, int x, int y) {

		boolean[] map = new boolean[x * y];
		status = new Status[x * y];
		COUNT = count;
		WIDTH = x;
		HEIGHT = y;

		for (int i = 0; i < count;) {
			int num = java.util.concurrent.ThreadLocalRandom.current().nextInt(x * y);
			if (!map[num]) {
				map[num] = true;
				i++;
			}
		}

		for (int i = 0; i < map.length; i++) {
			status[i] = new Status(map[i]);
		}

		for (int i = 0; i < map.length; i++) {
			if (map[i]) {
				setMine(i);
			}
		}
	}

	private void setMine(int where) {

		int x = where % 10 + 1;
		int y = where / 10 + 1;

		if (y > 1) {
			if (x > 1) {
				status[where(x - 1, y - 1)].mine();
			}
			status[where(x, y - 1)].mine();
			if (x < WIDTH) {
				status[where(x + 1, y - 1)].mine();
			}
		}
		if (x > 1) {
			status[where(x - 1, y)].mine();
		}
		if (x < WIDTH) {
			status[where(x + 1, y)].mine();
		}
		if (y < HEIGHT) {
			if (x > 1) {
				status[where(x - 1, y + 1)].mine();
			}
			status[where(x, y + 1)].mine();
			if (x < WIDTH) {
				status[where(x + 1, y + 1)].mine();
			}
		}

	}

	protected boolean isWin() {

		if (found != COUNT) return false;

		return true;
	}

	protected void mark(int x, int y) {
		found++;
	}

	protected void cancel(int x, int y) {
		found--;
	}

	protected abstract void draw();

	public abstract void play();

	public abstract boolean next();

	protected abstract void gameOver();

	protected void open(int x, int y) {

		if (x == 0 || x > WIDTH || y == 0 || y > HEIGHT) return;

		if (status[where(x, y)].open()) {
			open(x - 1, y - 1);
			open(x - 1, y);
			open(x - 1, y + 1);
			open(x, y - 1);
			open(x, y + 1);
			open(x + 1, y - 1);
			open(x + 1, y);
			open(x + 1, y + 1);
		}
	}

	protected int where(int x, int y) {
		return (y - 1) * COUNT + x - 1;
	}

}
