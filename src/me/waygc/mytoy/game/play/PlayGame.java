package me.waygc.mytoy.game.play;

import java.io.IOException;
import java.util.Properties;

import me.waygc.mytoy.game.core.IPlay;

public class PlayGame {

	public static void main(String[] args) {

		IPlay play = PlayGame.factory(args[0]);
		play.execute(10, 10, 10);

	}

	static IPlay factory(String name) {

		Properties p = new Properties();
		try {
			p.load(PlayGame.class.getResourceAsStream("/res/config.properties"));
		} catch (IOException e1) {
		}

		IPlay play = null;

		try {
			play = (IPlay) Class.forName(p.getProperty(name)).newInstance();
		} catch (Exception e) {
		}
		return play;
	}

}
