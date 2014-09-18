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
            commands.add(adbAndSerial + " shell \" mkdir /sdcard/launcher/\"");
            commands.add(adbAndSerial + " push apps.json /sdcard/launcher/");
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
            //commands.add(adbAndSerial + " reboot recovery");  //FOR TAB 3 LITE THIS WILL BOOTLOOP.  BOOT INTO RECOVERY MANUALLY 
            
            return commands;
		}
	},
	SAMSUNGTAB2STANDARD()
	{
		@Override
		public String getReadableName()
		{
			return "Samsung Tab 2 Standard";
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
            commands.add(adbAndSerial + " shell \" mkdir /sdcard/launcher/\"");
            commands.add(adbAndSerial + " shell \" rm -r /sdcard/Movies\"");

            commands.add(adbAndSerial + " push apps.json /sdcard/launcher/");
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
            commands.add(adbAndSerial + " shell reboot recovery");
            //commands.add(adbAndSerial + " reboot recovery");  //FOR TAB 3 LITE THIS WILL BOOTLOOP.  BOOT INTO RECOVERY MANUALLY 
            
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
            commands.add(adbAndSerial + " push RoanokeInstaller.sh /sdcard/");
            commands.add(adbAndSerial + " shell \"mkdir /sdcard/launcher\"");
            commands.add(adbAndSerial + " install com.morrison.applocklite-1.apk");
            commands.add(adbAndSerial + " install com.morrison.processmanager.applock-1.apk");
            commands.add(adbAndSerial + " push catdata.sh /sdcard/");
            commands.add(adbAndSerial + " push simpleRecoveryScript.sh /sdcard/");
            commands.add(adbAndSerial + " shell \"cat /sdcard/simpleRecoveryScript.sh | sh\"");
            
            if(new Util().isWindows()) 
            	commands.add(adbAndSerial + " shell \"cat /sdcard/catdata.sh | sh\"");
            else
            	commands.add(adbAndSerial + " shell cat /sdcard/catdata.sh | sh");
            
            commands.add(adbAndSerial + " push apps.json /sdcard/launcher/");
            //Applock instructions
            commands.add("~~appLockInstaller~~|" + adbAndSerial);
            //Condi app?
            commands.add("~~OtherSpecialInstaller~~|" + adbAndSerial);

            if(new Util().isWindows()) 
            {
	            commands.add(adbAndSerial + " shell \"busybox nohup cat /sdcard/RoanokeInstaller.sh | bash\" ");
	            commands.add(adbAndSerial + " shell \"busybox nohup cat /sdcard/RoanokeInstaller.sh | sh\"");
            }
            else
            {
            	commands.add(adbAndSerial + " shell \"busybox nohup cat /sdcard/RoanokeInstaller.sh | bash \" ");
	            commands.add(adbAndSerial + " shell \"busybox nohup cat /sdcard/RoanokeInstaller.sh | sh \" ");	
            }
            //commands.add(adbAndSerial + " reboot recovery");
			
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
			TabletConfigDetails configDetails = TabletConfigDetails.getInstance(); 
			List<String> commands = new LinkedList<String>();

			String idRsaPublic, idRsaPrivate, adb, adbAndSerial, mentoringAppLocation;
			String swagInstaller = "swag101Installer.sh";
			
			adb = " adb -s ";
			idRsaPublic = "id_rsa.pub";
            idRsaPrivate = "id_rsa";
            mentoringAppLocation = "/mnt/sdcard/Android/data/edu.mit.media.prg.mentoring_app/";
            adbAndSerial = adb + deviceSerialId;
            

            commands.add(adbAndSerial + " shell mkdir /sdcard/.ssh/");
            commands.add(adbAndSerial + " push " + idRsaPrivate + " /sdcard/.ssh/");
            commands.add(adbAndSerial + " push " + swagInstaller + " /sdcard/");
            commands.add(adbAndSerial + " push " + idRsaPublic + " /sdcard/.ssh/");
			commands.add(adbAndSerial + " push label.txt /sdcard/");
            commands.add(adbAndSerial + " push version.txt /sdcard/");
            commands.add("dos2unix installAppLock.sh");
            commands.add(adbAndSerial + " push installAppLock.sh /sdcard/");
            commands.add(adbAndSerial + " shell \"cat /sdcard/installAppLock.sh | sh\"");
            commands.add(adbAndSerial + " shell mkdir /sdcard/launcher/");
            commands.add(adbAndSerial + " install com.morrison.applocklite-1.apk");
            commands.add(adbAndSerial + " install com.morrison.processmanager.applock-1.apk");
            commands.add(adbAndSerial + " shell \"mkdir /data/data/com.morrison.applocklite/databases\"");
            commands.add(adbAndSerial + " shell \"mkdir /data/data/com.morrison.applocklite/shared_prefs\"");
            commands.add(adbAndSerial + " push catdata.sh /sdcard/");
            commands.add(adbAndSerial + " shell pm set-install-location 2");
            commands.add(adbAndSerial + " push tempWpa.txt /sdcard/");

            if(new Util().isWindows()) 
            	commands.add(adbAndSerial + " shell \"cat /sdcard/catdata.sh | sh\"");
            else
            	commands.add(adbAndSerial + " shell cat /sdcard/catdata.sh | sh");
            
            commands.add(adbAndSerial + " push apps.json /sdcard/launcher/");
            commands.add("~~appLockInstaller~~|" + adbAndSerial);  //For copying appLock config files over under the correct user
            
            if(new Util().isWindows()) 
            {
            	//commands.add(adbAndSerial + " shell \"su; mount -o rw,remount -t ext4 /system\"");
	            commands.add(adbAndSerial + " shell \"busybox nohup cat /sdcard/" + swagInstaller + " | sh & > output.txt &\"");
			}
            else
            {
	            //commands.add(adbAndSerial + " shell su; mount -o rw,remount -t ext4 /system");
	            commands.add(adbAndSerial + " shell busybox nohup cat /sdcard/" + swagInstaller + " | sh & > output.txt &");            	
            }
			return commands;
			
		}
	},
	MOTOROLAXOOM()
	{
		@Override
		public String getReadableName()
		{
			return "Motorola Xoom";
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
            commands.add(adbAndSerial + " shell \" mkdir /sdcard/launcher/\"");
            commands.add(adbAndSerial + " push apps.json /sdcard/launcher/");
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
            commands.add(adbAndSerial + " reboot recovery");  //FOR TAB 3 LITE THIS WILL BOOTLOOP.  BOOT INTO RECOVERY MANUALLY 
            
            return commands;
		}
	};
	
	public abstract String getReadableName();
	public abstract List<String> getInstallationCommands(String deviceSerialId);
	public abstract Boolean getAdbWireless();
}





























