package data;

import static helpers.Artist.HEIGHT;
import static helpers.Artist.WIDTH;
import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.loadTexture;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import helpers.StateManager;
import helpers.StateManager.GameState;
import ui.UI;

public class MainMenu 
{

	private Texture background;
	private UI menuUI;
	private boolean clicked = false;
	
	public MainMenu()
	{
		this.background = loadTexture("mainmenu");
		this.menuUI = new UI();
		menuUI.addButton("Play", "playbutton", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f));
		menuUI.addButton("Editor", "editorbutton", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f) + 128);
		menuUI.addButton("Quit", "quitbutton", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f) + 256);
	}
	
	private void updateButtons()
	{
		if(Mouse.isButtonDown(0) && !clicked)
		{
			clicked = true;
			if(menuUI.isButtonClicked("Play")) 
				StateManager.setState(GameState.GAME);
			if(menuUI.isButtonClicked("Editor"))
				StateManager.setState(GameState.EDITOR);
			if(menuUI.isButtonClicked("Quit"))
				System.exit(0);
		}
		if(!Mouse.isButtonDown(0))
			clicked = false;
	}
	
	public void update()
	{
		DrawQuadTex(background, 0, 0, 2048, 1024);
		menuUI.draw();
		updateButtons();
	}
	
}
