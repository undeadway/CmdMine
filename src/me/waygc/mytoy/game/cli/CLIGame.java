package me.waygc.mytoy.game.cli;

import java.util.Scanner;

import me.waygc.mytoy.game.core.AGame;

public final class CLIGame extends AGame {

	private Scanner scn = new Scanner(System.in);
	private int where;

	public CLIGame(int count, int x, int y) {
		super(count, x, y);
	}

	public void play() {

		do {
			System.out.println("\n================================");
			draw();
			System.out.print("\n\nPlease enter the (x y)");

			int x = scn.nextInt();
			int y = scn.nextInt();
			String action = scn.next();
			if (action == null) {
				action = "";
			}

			where = where(x, y);

			if (status[where].MINE && action.equalsIgnoreCase("O")) {
				System.out.println("GAME OVER");
				System.out.println("You Found : " + found);
				gameOver();
				return;
			} else if (action.equalsIgnoreCase("M")) {
				if (status[where(x, y)].mark()) {
					mark(x, y);
				}
			} else if (action.equalsIgnoreCase("C")) {
				if (status[where(x, y)].cancel()) {
					cancel(x, y);
				}
			} else {
				open(x, y);
			}

		} while (!isWin());

		draw();
		System.out.println("You win!");
	}

	public boolean next() {

		System.out.print("\nPlay again?(y/n) ?");
		return scn.next().equalsIgnoreCase("Y");

	}

	protected void draw() {

		for (int i = 0; i < status.length; i++) {
			if (i % COUNT == 0) System.out.println();

			if (status[i].isOpen()) {
				System.out.print(status[i].getValue());
			} else if (status[i].isMarked()) {
				System.out.print("M");
			} else {
				System.out.print("L");
			}

			System.out.print(" ");
		}
	}

	protected void gameOver() {

		for (int i = 0; i < status.length; i++) {
			if (i % COUNT == 0) System.out.println();

			if (status[i].MINE) {
				if (i == where) {
					System.out.print("X");
				} else if (status[i].isMarked()) {
					System.out.print("F");
				} else {
					System.out.print("O");
				}
			} else {
				if (status[i].isMarked()) {
					System.out.print("E");
				} else {
					System.out.print("L");
				}
			}
			System.out.print(" ");
		}
	}

}
