/*
 * The MIT License (MIT)

Copyright (c) 2014 The Dalai Lama Center

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class TabletInstallOperatingSystem {
	private static boolean TEST = false;
    private static String username, password;
    private static ServerConnect server;
    private static String cmd = "";
    private static List<String> devices = new LinkedList<>();
    private static TabletInformation tabletInfo = TabletInformation.getInstance();
    private static String[] commandsToIgnore = {"busybox nohup"};
        
    public static void main(String[] args) {
    	
    	
    	Util util = new Util();
    	
    	if(util.isWindows())
    		cmd = "cmd /c ";
        
    	System.out.println("*************************************************\n" +
    			"Welcome to the Image Installer for Android Images\n" +
    			"This installer will assist you in getting prebuilt images on your tablets\n" +
    			"You will need to have the following items to proceed:\n" +
    			"\t-UserName and Password (supplied to you by the project leader)\n" +
    			"\t-A stable internet connection\n" +
    			"\t-Pre-imaged SD cards inserted into the tablets\n" +
    			"\t-Make sure that tablets are charged 30% or more\n" +
    			"*************************************************");
    	
        //Check credentials before doing any processing
        server = new ServerConnect();
        while(true)
        {
            System.out.println("Please enter your username");
            username = readUserInput();
            System.out.println("Please Enter your password");
            password = readUserInput();
            
            System.out.println("Checking...");
            if(!server.checkCredentials(username, password))
                System.out.println("Your credentials don't match.\nPlease try again.");
            else
            {
            	server.saveCredentials(username, password);
                System.out.println("Your credentials match!");
                break;
            }
        }

        int tabletOption;
        while(true)
        {
        	int counter = 0;
        	System.out.println("Please select a tablet option from below:\n");
        	//Iterate over all tablets
        	for(TabletOptions name : TabletOptions.values())
        	{
        		System.out.println(counter++ + ". " + name.getReadableName() );
        	}
        	
        	tabletOption = Integer.parseInt(readUserInput());
        	
        	break;
        }
        
        TabletOptions[] tabletHardware = TabletOptions.values();
        //Save the tabletOption
        TabletConfigDetails.getInstance().setTabletOption(tabletHardware[tabletOption]);
        
        System.out.println("Will you be using a Raspberry Pi with this setup? Y or N \n"
        		+ "(If you are unsure, you prpbably arent)");
        if(readUserInput().toUpperCase().equals("y"))
        {
        	loadRPConfig();
        	TabletConfigDetails.getInstance().setIsUsingPi(true);
        }
        else
        {
        	getNetworkInformation();
        	TabletConfigDetails.getInstance().setIsUsingPi(false);
        }
        
//        File serialLabelMappingFile = new File("serialToLabelMapping.txt");
//        //Check if there is a file in our directory for versioning information

        getTabletLabelAndSequenceNumber();
        
//        //Get the serial number to label mapping
//        String[] serialToLabel = readFileIntoArray(serialLabelMappingFile);
        
        //Parse the array into a hash table 
        HashMap<String, String> serialToLabelHashMap = new HashMap<String, String>();	//mapIt(serialToLabel);
        
        //start processing tablets
        //Continue processing until the program closed
        while(true)
        {
            processTablets(serialToLabelHashMap);
            checkForPause();
            //Thread.sleep(4000);
        }
    }
    
    private static void getNetworkInformation()
    {
    	//Get network information that will be used for every tablet
        System.out.println("Please enter the SSID of the default network connection.\n"
                + "(Leave blank if there will not be a default connection)");
        String ssid = readUserInput();
        TabletConfigDetails tabletConfig = TabletConfigDetails.getInstance();
        
        if(ssid.isEmpty())
        {
            System.out.println("There will not be a default network.");
            tabletConfig.setNetworkPassword(null);
            tabletConfig.setSsid(null);
        }
        else
        {
        	tabletConfig.setSsid(ssid);
            while(true)
            {
                System.out.println("Please enter the network password");
                String networkPassword = readUserInput();
                System.out.println("Please enter the password again");
                if(!readUserInput().equals(networkPassword))
                    System.out.println("The two passwords do not match");
                else
                {
                	if(networkPassword.length()<8)
                	{
                		System.out.println("PASSWORD IS LESS THAN 8 CHARACTERS\n" +
                				"THIS WILL NOT WORK IN NETWORK SETTINGS");
                		System.out.println("Do you wish to continue with this password? (y/n)");
                		if(readUserInput().toLowerCase().equals("n"))
                			continue;
                	}
                	tabletConfig.setNetworkPassword(networkPassword);
                    System.out.println("Network credentials saved!");
                    break;
                }
            }
        }
    }
    
    private static void processTablets(HashMap<String, String> serialToLabelMapping)
    {
    	System.out.println("Getting number of devices");
        int numberOfDevices = getNumberOfDevices();
        TabletConfigDetails tabletConfig = TabletConfigDetails.getInstance();
        
        System.out.println("Number of devices: " + numberOfDevices);

        //Loop over all discovered devices
        for(int i = 0; i < devices.size(); i++)
        {
        	System.out.println("Reading the serial ID");
            if(tabletInfo.checkIfPresent(readSerialId(i)))
            	continue;
            
            String deviceSerialId = readSerialId(i);
            System.out.println("Generating/saving Label");
            generateLabel(i, deviceSerialId);
            System.out.println("Done Generating Label");
            
            String adbIdentifier = TabletConfigDetails.getInstance().getTabletOption().getAdbWireless() ? 
            		getIpAddressAndPortNumber(i) : deviceSerialId;
            
            String[] SSHKeys = new String[2];
            System.out.println("Getting SSH Keys");
            try 
            {
            	SSHKeys = server.getSSHKeys(deviceSerialId);
            }
            catch (Exception e) {}
            System.out.println("Done getting SSH Keys");
            
            String idRsaPublic, idRsaPrivate;
            idRsaPublic = "id_rsa.pub";
            idRsaPrivate = "id_rsa";
            
            Util util = new Util();
            util.writeToFile(idRsaPublic, SSHKeys[0]);
            util.writeToFile(idRsaPrivate, SSHKeys[1]);
            util.writeToFile("tempWpa.txt", TabletConfigDetails.getInstance().swagGetWiressInfo());
            
            
//            adb = " adb -s ";
//            
//            pushRecoveryScript = adb + deviceSerialId + " push openrecoveryscript /cache/recovery";
//            sshDirectory = adb + deviceSerialId + "shell mkdir /sdcard/.ssh/";
//            sshCmdPri = sshKeysPrivate = adb + deviceSerialId + " push " + idRsaPrivate + " /sdcard/.ssh/";
//            sshCmdPub = sshKeysPublic = adb + deviceSerialId + " push " + idRsaPublic + " /sdcard/.ssh/";
//            
//            
//            reboot =  adb + deviceSerialId + " reboot recovery";
            
            
            
//            //Get a list of all commands to run
//            String[] listOfCommands = readFileIntoArray(new File(CommandFile));
            

        	for(String command : tabletConfig.getTabletOption().getInstallationCommands(adbIdentifier))
        	{
        		System.out.println("Command: " + command);
        		if(command.contains("~~appLockInstaller~~"))
        		{
        			runAppLockInstaller(command);
        			continue;
        		}
        		else if(command.contains("~~OtherSpecialInstaller~~"))
				{
        			continue;
				}
        		
    			System.out.println(readCommandResponse(executeCommand(command)));
        		try { Thread.sleep(500);} catch(Exception e){}
        	}
        	
            //remove ssh keys from the file system
            util.removeAllWrittenFiles();
            
            tabletInfo.addDevice(deviceSerialId);

            System.out.println(tabletInfo.getTabletLabel() + "-" + tabletInfo.getSequenceNumber() + "  has been completed.");
        }
    }
    
    private static void runAppLockInstaller(String command)
    {
    	String adbAndSerial = command.split("\\|")[1];
    	//Get the user info for com.morrison.applocklite
    	String execute = adbAndSerial + " shell \"cat /sdcard/dataOutput.txt\"";
    	
    	String[] resultSet = readCommandResponse(executeCommand(execute)).split("\n");
    	String morrisonString = "";
    	for(String result : resultSet)
    	{
    		if(result.contains("com.morrison.applocklite"))
    		{
				morrisonString = result;
				break;
    		}
    	}
    	String userIdOfMorrison = morrisonString.split(" ")[1];

    	try{
    	
    		String content = new Scanner(new File("morrisonInstaller.sh")).useDelimiter("\\Z").next().replace("~~~", userIdOfMorrison);
    		Util util = new Util();
    		util.writeToFile("morrisonInstallerComplete.sh", content);
    		executeCommand(adbAndSerial + " push morrisonInstallerComplete.sh /sdcard/");
    		executeCommand(adbAndSerial + "shell \"cat /sdcard/morrisonInstallerComplete.sh | sh\"");
    	}
    	catch(IOException e){System.out.println("Error Reading morrisonInstaller File" + e);}
    }
    
    private static void runCondiInstaller(String command)
    {
    	String adbAndSerial = command.split("\\|")[1];
    	//Get the user info for com.morrison.applocklite
    	String execute = adbAndSerial + " shell \"cat /sdcard/condiOutput.txt\"";
    	
    	String result = readCommandResponse(executeCommand(execute));
    	
    	String userIdOfMorrison = result.split(" ")[1];

    	try{
    	
    		String content = new Scanner(new File("condiInstaller.sh")).useDelimiter("\\Z").next().replace("~~~", userIdOfMorrison);
    		Util util = new Util();
    		util.writeToFile("condiInstallerComplete.sh", content);
    		executeCommand(adbAndSerial + " push condiInstallerComplete.sh /sdcard/");
    		executeCommand(adbAndSerial + "shell \"cat /sdcard/condiInstallerComplete.sh | sh\"");
    	}
    	catch(IOException e){System.out.println("Error Reading condiInstaller File" + e);}
    }
    
    private static void getTabletLabelAndSequenceNumber()
    {
        TabletInformation tabletInfo = TabletInformation.getInstance();
        //Get the info from the user   
        System.out.println("Please enter the generic Tablet Label with no "
            + "spaces and no numeric identifier (i.e. ERL-GSU)");
        tabletInfo.setTabletLabel(readUserInput());

        System.out.println("Please enter the starting sequence number.  "
                + "Leave blank for default of \"01\"");
        String tempUserInput = readUserInput();

        //If no input, set to default
        tabletInfo.setSequenceNumber(
        		tempUserInput.isEmpty() ? 1 : Integer.parseInt(tempUserInput));
       
    }
    
    private static void generateLabel(int deviceNumber, String deviceSerialId)
    {
    	
    	TabletConfigDetails tabletConfig = TabletConfigDetails.getInstance();
    	
		String label = null;
        System.out.println("Installing Image for: " + deviceSerialId);
        
        //check if there is a pre-existing serial number/label in the DB that we need to use 
        if(((label = server.checkForPreExistingLabel(deviceSerialId)).toString()).equals("0"))
        {
        	//Generate the label
            label = tabletInfo.getTabletLabel() + "-" + tabletInfo.getSequenceNumber();
            
            //increment the sequence by one for the next iteration
            tabletInfo.incrementSequenceNumber();
            
            //Send the serialId to label relationship
            server.setLabel(label, deviceSerialId);
        }
        
        //tabletInfo.setTabletLabel(label);
        
        //Add the label to the config file
        new Util().writeToFile("label.txt", label);
        tabletConfig.insertLabelInfoFile(label);
    }
    
    private static void loadRPConfig()
    {
    	//TODO Load the preset wireless settings for the Raspberry Pi
    }
   
    private static int getNumberOfDevices()
    {
    	int numberOfDevices = 0;
    	List<String> ipAddresses;
    	
    	if(TabletConfigDetails.getInstance().getTabletOption().getAdbWireless())
    	{
    		ipAddresses = getIpAddresses();
    	
    		//Connect to the wireless addresses
    		for(String ipAddress : ipAddresses)
    		{
				executeCommand("adb connect " + ipAddress);	
    		}
    	}
    	
    	try 
        {
        	String line;
        	
        	BufferedReader reader = executeCommand(" adb devices");
            
        	//Skip the first line
            reader.readLine();
            while((line = reader.readLine()) != null) 
            { 
                if(line.length() > 0)
                {
                    numberOfDevices++;
                    devices.add(line);
                    System.out.println(line);
                }
            }
            //remove the empty line from the count
            //numberOfDevices--;
            if(numberOfDevices == -1)
            	numberOfDevices = 0;
        }
        catch(IOException e1) 
        {
            System.out.println("Exception: " + e1);
        } 
    	
    	return numberOfDevices;
    }
    
    private static List<String> getIpAddresses()
    {
    	List<String> ipAddresses = new LinkedList<String>();
    	
		System.out.println("Please enter the IP address of the tabet in the "
				+ "form of xxx.xxx.xxx.xxx and then hit Enter\n"
				+ "After you have entered in all IP's that you want, type in 'done' and hit 'Enter'");
		
		String userInput = "";
		while(!(userInput = readUserInput()).trim().toLowerCase().equals("done"))
		{
			if(Util.validateIp(userInput))
			{
				ipAddresses.add(userInput);
				System.out.println("Address Saved.\n"
						+ "Please enter the next address");
			}
			else
				System.out.println("That is not a valid IP address");
		}
		
		return ipAddresses;
    }
    
    private static String readSerialId(int deviceNumber)
    {
    	String getSerialId;
    	if(new Util().isWindows())
    		getSerialId = " shell \"cat /sys/class/android_usb/android0/iSerial\"";
    	else
    		getSerialId = " shell cat /sys/class/android_usb/android0/iSerial";
    	
    	String deviceSerialId;
    	
    	if(!TabletConfigDetails.getInstance().getTabletOption().getAdbWireless())
    	{
    		deviceSerialId = devices.get(deviceNumber).split("\t")[0] + " ";
    		if(deviceSerialId.contains(":5555"))
    		{
    			System.out.println("The devices is wireless but it is not configured this way!\n"
    					+ "Trying to resolve Serial Number over wireless network...");
    			deviceSerialId = readCommandResponse(executeCommand("adb -s " + deviceSerialId + getSerialId)).trim();
    			
    			System.out.println("The Serial Number that was resolved is: " + deviceSerialId);
    		}
    	}
    	else
    	{
    		String ipAddress = getIpAddressAndPortNumber(deviceNumber);
    		BufferedReader reader = executeCommand("adb -s " + ipAddress.trim() + getSerialId);
    		deviceSerialId = readCommandResponse(reader);
    	}
    	
    	return deviceSerialId;
    }
    
    private static String getIpAddressAndPortNumber(int deviceNumber)
    {
    	return (devices.get(deviceNumber).split("\t")[0] + " ");
    }
    
    private static String readUserInput()
    {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        }
        catch (IOException e){}
        
        //If try fails
        return "-1";
    }
    
    private static String[] readFileIntoArray(File file)
    {
        String line;
        List<String> serialToLabel = new LinkedList();
        
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            //Clear out the file
            while((line = br.readLine()) != null)
            {
                serialToLabel.add(line);
            }
        }
        catch (IOException e) {}
        return serialToLabel.toArray(new String[serialToLabel.size()]);
    }
    
    private static HashMap<String, String> mapIt(String[] arrayToMap)
    {
        String[] tempArray;
        HashMap<String, String> tempHashMap = new HashMap();
        for(int i = 0; i < arrayToMap.length; i++)
        {
            tempArray = arrayToMap[i].split(",");
            tempHashMap.put(tempArray[1].trim(), tempArray[0].trim());
        }
        return tempHashMap;
    }
    
    private static BufferedReader executeCommand(String command)
    {
    	command = cmd + command;
    	BufferedReader reader = null;
    	
    	try 
    	{
	        Process p = Runtime.getRuntime().exec(command); 
	        Thread.sleep(1000);
	       
		//If the command is an installer command, it will not return for 20 minutes.  Skip. 
	        for(String cmd : commandsToIgnore)
	        	if(command.contains(cmd))
	        		return null;
	        reader =new BufferedReader(
	            new InputStreamReader(p.getInputStream())
	        );
    	}
    	catch (Exception e)
    	{
    		System.out.println("Exception when trying to execute command: " + e);
    	}

    	return reader;
    }
    
    private static String readCommandResponse(BufferedReader reader)
    {
    	String commandResponse = "";
    	String line;
    	if(reader == null)
    		return "";
    	try
    	{
    		commandResponse = reader.readLine();
    		
    		while((line = reader.readLine()) != null)
    		commandResponse += "\n" + line;
    	}
    	catch(IOException e)
    	{
    		System.out.println("IO Exception when reading command response: " + e);
    	}
    	
    	return commandResponse;
    }
    
    private static void checkForPause()
    {
    	System.out.println("Waiting to continue... Hit 'Enter' to continue");
    	readUserInput();
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
