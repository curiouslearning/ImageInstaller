#!/bin/bash

#echo "Downloading most current image installer program..."
#cd ~/Documents/GitHub/ImageInstaller
#git pull
#cd /home/globallit/Documents/GitHub/ImageInstaller/

cp openrecoveryscript* src
cp *.txt src
cp *.sh src
cp *.json src
cd src/
echo "Building the program..."
javac *.java
echo "Running the program"
/usr/lib/jvm/java-7-openjdk-amd64/bin/java -Dfile.encoding=UTF-8 -classpath /home/globallit/Documents/GitHub/ImageInstaller/src TabletInstallOperatingSystem

