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

import java.util.LinkedList;
import java.util.List;


public enum TabletOptions {

	SAMSUNGTAB3STANDARD()
	{
		@Override
		public String getReadableName()
		{
			return "Samsung Tab 3 (Not Lite)";
		}
		
		@Override
		public Boolean getAdbWireless() {return false;}
		
		@Override
		public List<String> getInstallationCommands(String deviceSerialId)
		{
			List<String> commands = new LinkedList<String>();
			
			String idRsaPublic, idRsaPrivate, adb, adbAndSerial;
			
			adb = " adb -s ";
			idRsaPublic = "id_rsa.pub";
            idRsaPrivate = "id_rsa";
            adbAndSerial = adb + deviceSerialId;
			
            commands.add(adbAndSerial + " shell mkdir /sdcard/.ssh/");
            commands.add(adbAndSerial + " push " + idRsaPrivate + " /sdcard/.ssh/");
            commands.add(adbAndSerial + " push " + idRsaPublic + " /sdcard/.ssh/");
            commands.add(adbAndSerial + " push label.txt /sdcard/");
            commands.add(adbAndSerial + " push version.txt /sdcard/");
            commands.add(adbAndSerial + " push openrecoveryscript /sdcard/");
            commands.add(adbAndSerial + " push recoveryinstaller.sh /sdcard/");
            commands.add(adbAndSerial + " push apps.json /mnt/sdcard/Android/data/edu.mit.media.prg.mentoring_app/files/");
            if(new Util().isWindows()) 
            {
	            commands.add(adbAndSerial + " shell \"cat /sdcard/recoveryinstaller.sh | bash\" ");
	            commands.add(adbAndSerial + " shell \"cat /sdcard/recoveryinstaller.sh | sh\"");
            }
            else
            {
            	commands.add(adbAndSerial + " shell cat /sdcard/recoveryinstaller.sh | bash ");
	            commands.add(adbAndSerial + " shell cat /sdcard/recoveryinstaller.sh | sh ");	
            }
            commands.add(adbAndSerial + " reboot recovery");
            
            return commands;
		}
	},
	SAMSUNGTAB3LITET110()
	{
		@Override
		public String getReadableName()
		{
			return "Samsung Tab 3 Lite, T110";
		}
		
		@Override
		public Boolean getAdbWireless() {return false;}
		
		@Override
		public List<String> getInstallationCommands(String deviceSerialId)
		{
			List<String> commands = new LinkedList<String>();
			
			//TODO fill in logic
			
			return commands;
		}
	},
	SAMSUNGTAB3LITET111()
	{
		@Override
		public String getReadableName()
		{
			return "Samsung Tab 3 Lite, with cellular capabilities T111";
		}
		
		@Override
		public Boolean getAdbWireless() {return false;}
		
		@Override
		public List<String> getInstallationCommands(String deviceSerialId)
		{
			List<String> commands = new LinkedList<String>();

			//TODO fill in logic
			
			return commands;
		}
	},
	SWAG101ANDROID411()
	{
		@Override
		public String getReadableName()
		{
			return "Swag 101 with Android Version 4.1.1";
		}
		
		@Override
		public Boolean getAdbWireless() {return true;}
		
		@Override
		public List<String> getInstallationCommands(String deviceSerialId)
		{
			List<String> commands = new LinkedList<String>();

			String idRsaPublic, idRsaPrivate, adb, adbAndSerial, mentoringAppLocation;
			
			adb = " adb -s ";
			idRsaPublic = "id_rsa.pub";
            idRsaPrivate = "id_rsa";
            mentoringAppLocation = "/mnt/sdcard/Android/data/edu.mit.media.prg.mentoring_app/files";
            adbAndSerial = adb + deviceSerialId;
            			
            commands.add(adbAndSerial + " shell mkdir /sdcard/.ssh/");
            commands.add(adbAndSerial + " push " + idRsaPrivate + " /sdcard/.ssh/");
            commands.add(adbAndSerial + " push " + idRsaPublic + " /sdcard/.ssh/");
            commands.add(adbAndSerial + " push label.txt /sdcard/");
            commands.add(adbAndSerial + " push version.txt /sdcard/");
            commands.add(adbAndSerial + " push apps.json " + mentoringAppLocation);
            commands.add(adbAndSerial + " shell pm set-install-location 2");
            if(new Util().isWindows()) 
            {
            	//commands.add(adbAndSerial + " shell \"su; mount -o rw,remount -t ext4 /system\"");
	            commands.add(adbAndSerial + " shell \"busybox nohup cat /mnt/external_sd/swagapps/swagInstaller.sh | sh & > output.txt &\"");
			}
            else
            {
	            //commands.add(adbAndSerial + " shell su; mount -o rw,remount -t ext4 /system");
	            commands.add(adbAndSerial + " shell busybox nohup cat /mnt/external_sd/swagapps/swagInstaller.sh | sh & > output.txt &");            	
            }
			return commands;
			
		}
	};
	
	public abstract String getReadableName();
	public abstract List<String> getInstallationCommands(String deviceSerialId);
	public abstract Boolean getAdbWireless();
}





























