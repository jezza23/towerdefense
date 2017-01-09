 package helpers;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import data.Tile;

public class Artist 
{

	public static final int WIDTH = 1280, HEIGHT = 832;
	public static int FRAME_CAP = 60;
	
	public static void BeginSession()
	{
		Display.setTitle("TowerSomething");
		try 
		{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) 
		{
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void DestroySession()
	{
		Display.destroy();
		System.exit(0);
	}
	
	public static void DrawQuad(float x, float y, float width, float height)
	{
		glBegin(GL_QUADS);
		glVertex2f(x, y); // Top left corner
		glVertex2f(x + width, y); // Top Right Corner
		glVertex2f(x + width, y + height); // Bottom Right Corner
		glVertex2f(x, y + height); // Bottom Left Corner
		glEnd();
	}
	
	public static void DrawQuadTex(Texture tex, float x, float y, float width, float height)
	{
		tex.bind();
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}
	
	public static void DrawQuadTexRot(Texture tex, float x, float y, float width, float height, float angle)
	{
		tex.bind();
		glTranslatef(x + width / 2, y + height / 2, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef( - width / 2,  - height / 2, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}
	
	public static void DrawLine(Vector2f pos1, Vector2f pos2)
	{
		glBegin(GL_LINES);
		glVertex2f(pos1.getX(), pos1.getY());
		glVertex2f(pos2.getX(), pos2.getX());
		glEnd();
	}
	
	public static Texture loadTexture(String path)
	{
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream("res/" + path + ".png");
		try 
		{
			tex = TextureLoader.getTexture("PNG", in);
		} catch (IOException e) 
		{
			Announcer.printf("Could not load file '" + path + "'", LogLevel.WARN);
		}
		return tex;
	}
	
	public static Texture loadTexture(String path, String fileType)
	{
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try 
		{
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return tex;
	}
}