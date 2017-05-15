package com.wilhelm.notaclicker.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.wilhelm.notaclicker.NotAClicker;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "NotAClicker";
		config.width = 320;
		config.height = 569;
		config.vSyncEnabled = false; // Setting to false disables vertical sync
		config.foregroundFPS = 120; // Setting to 0 disables foreground fps throttling
		config.backgroundFPS = 120; // Setting to 0 disables background fps throttling
		new LwjglApplication(new NotAClicker(), config);
	}
}
