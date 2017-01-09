package helpers;

import java.awt.Color;

public enum LogLevel 
{
	INFO ("INFO"),
	WARN("WARN"),
	FATAL("FATAL");
	public String logColor;
	public String type;
	
	LogLevel(String type) {
		this.type = type;
	}
	
}
