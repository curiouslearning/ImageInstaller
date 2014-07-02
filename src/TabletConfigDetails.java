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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TabletConfigDetails {
	
	private String ssid, networkPassword;
	private TabletOptions tabletOption;
	private boolean IsWireless;
	
	public static TabletConfigDetails tabletConfig = null;
	
	public TabletConfigDetails() {}  //Singleton
	
	public static TabletConfigDetails getInstance()
	{
		if(tabletConfig == null)
			tabletConfig = new TabletConfigDetails();
		
		return tabletConfig;
	}

	public void insertLabelInfoFile(String label)
    {
        String labelDirectory = "/sdcard/label.txt";
        File file = new File("openrecoveryscript.unnamed");
        if(!file.exists()) 
        {
            System.out.println("openrecoveryscript.unnamed doesn't exist!");
        }
        String line;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("openrecoveryscript")));
            //Clear out the file
            bw.write("");
            String newLines = "\n";
            while((line = br.readLine()) != null)
            {
                if(line.contains("--"))
                {
                    line = "cmd echo -e \"" + label + "\" > " + labelDirectory;
                    line += newLines;
                    line += "print \"Setting up wirless\"";
                    line += newLines;
                    line += "cmd echo -e \'network={\\n\\tssid=\""+ ssid +"\"\\n\\tpsk=\"" + networkPassword + "\"\\n\\tkey_mgmt=WPA-PSK\\n\\tpriority=2\\n}\' >> /data/misc/wifi/wpa_supplicant.conf";
                }
                if(line.contains("***ssid***"))
        		{
                	line += ssid + "\n";
        		}
                if(line.contains("***psk***"))
                {
                	line += networkPassword + "\n";
                }
                		
                bw.append(line + "\n");
            }
            bw.flush();
        }
        catch (IOException e) {}
    }

	public void setTabletOption(TabletOptions tabletOption)
	{
		this.tabletOption = tabletOption;
	}
	
	public TabletOptions getTabletOption()
	{
		return this.tabletOption;
	}
	
	public void setSsid(String ssid)
	{
		this.ssid = ssid;
	}
	
	public void setNetworkPassword(String networkPassword)
	{
		this.networkPassword = networkPassword;
	}
	
	public boolean isWireless()
	{
		return IsWireless;
	}
	
	public void setWirelessStatus(boolean isWireless)
	{
		this.IsWireless = isWireless;
	}
}