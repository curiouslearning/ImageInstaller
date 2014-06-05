adb push openrecoveryscript /sdcard/
adb push recoveryinstaller.sh /sdcard/
adb push apps.json /sdcard/

adb shell "cat /sdcard/recoveryinstaller.sh | bash"
adb shell "cat /sdcard/recoveryinstaller.sh | sh"
sleep 1
adb reboot recovery
