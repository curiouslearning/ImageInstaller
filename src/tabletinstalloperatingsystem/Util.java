package tabletinstalloperatingsystem;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Util {

	private static String MAC_OS = "MAC";
	private static String WIN_OS = "WINDOWS";
	private List<String> listOfFilesWrittenTo = new LinkedList<String>();
	
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
	
	public boolean writeToFile(String fileName, String dataToWrite)
	{
		try (FileWriter fr = new FileWriter(new File(fileName)))
		{
			BufferedWriter writer = new BufferedWriter(fr);
			
			writer.write(dataToWrite);
			
			writer.close();
			listOfFilesWrittenTo.add(fileName);
			return true;
		}
		catch(IOException e)
		{
			System.out.println("File Exception: " + e);
			return false;
		}
	}
	
	public void removeAllWrittenFiles()
	{
		for(String file : listOfFilesWrittenTo)
		{
			if(!new File(file).delete())
				System.out.println("Unable to delete file: " + file);
		}
		listOfFilesWrittenTo.clear();	
	}
	
}
