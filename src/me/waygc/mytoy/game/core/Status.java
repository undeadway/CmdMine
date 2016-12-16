package me.waygc.mytoy.game.core;

public class Status {

	private boolean opened;
	public int value = 0;
	public final boolean MINE;
	private boolean marked = false;

	public Status(boolean val) {
		MINE = val;
		if (val) {
			value = -1;
		}
	}

	public boolean isOpen() {
		return opened;
	}

	public boolean open() {
		if (marked || opened)
			return false;

		if (value == -1) {
			return false;
		} else {
			opened = true;
			return value == 0;
		}
	}

	public void mine() {
		if (value == -1)
			return;
		value += 1;
	}

	public int getValue() {
		return value;
	}

	public boolean cancel() {
		marked = false;
		return MINE;
	}

	public boolean mark() {
		if (!opened) {
			marked = true;
		}
		return MINE;
	}

	public boolean isMarked() {
		return marked;
	}

}
