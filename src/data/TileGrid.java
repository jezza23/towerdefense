package data;

import helpers.Vector2f;

public class TileGrid 
{

	public Tile[][] map;
	private int tilesWide, tilesHigh;
	
	public TileGrid()
	{
		map = new Tile[tilesWide][tilesHigh];
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Grass);
			}
		}
	}
	
	public TileGrid(int[][] newMap)
	{
		this.tilesWide = newMap[0].length;
		this.tilesHigh = newMap.length;
		map = new Tile[20][13];
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				if(TileType.getAmtTiles() >= 1)
					map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.getTileByID(newMap[j][i]));
			}
		}
	}
	
	public void setTile(int x, int y, TileType tile)
	{
		map[x][y] = new Tile(x * 64, y * 64, 64, 64, tile);
	}
	
	public Tile getTile(Vector2f loc)
	{
		if(loc.getX() < tilesWide && loc.getY() < tilesHigh && loc.getX() > -1 && loc.getY() > -1)
			return map[(int)loc.getX()][(int)loc.getY()];
		else
			return new Tile(0, 0, 0, 0, TileType.Null);
	}
	
	public Tile getTile(int x, int y)
	{
		if(x < tilesWide && y < tilesHigh && x > -1 && y > -1)
			return map[x][y];
		else
			return new Tile(0, 0, 0, 0, TileType.Null);
	}
	
	public int getTilesHigh() 
	{
		return tilesHigh;
	}
	
	public int getTilesWide() 
	{
		return tilesWide;
	}
	
	public void draw()
	{
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				map[i][j].draw();
			}
		}
	}
	
}
