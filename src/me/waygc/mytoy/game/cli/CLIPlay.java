package me.waygc.mytoy.game.cli;

import me.waygc.mytoy.game.core.AGame;
import me.waygc.mytoy.game.core.IPlay;

public class CLIPlay implements IPlay {

	public void execute(int count, int x, int y) {

		System.out.println("Welcome to play the Command Mine.");

		AGame game;

		do {
			game = new CLIGame(count, x, y);
			game.play();
		} while (game.next());
	}

}
