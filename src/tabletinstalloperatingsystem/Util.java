package tabletinstalloperatingsystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Util {

	private static String MAC_OS = "MAC";
	private static String WIN_OS = "WINDOWS";
	
	private String returnOS(){

	    String currentOs = System.getProperty("os.name").toUpperCase();
	    if( currentOs.contains(MAC_OS)){
	        currentOs = MAC_OS;
	    }
	    else if( currentOs.contains(WIN_OS) ){
	        currentOs = WIN_OS;
	    }
	    else{
	        currentOs = null;
	    }
	    return currentOs;
	}
	
	public Boolean isWindows()
	{
		if(returnOS().equals(WIN_OS))
			return true;
		else 
			return false;
	}
	
	public boolean writeToFile(String fileName)
	{
		
		return true;
	}
	
}
