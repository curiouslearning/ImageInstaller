su
mount -o remount,rw /system
rm /sys/class/android_usb/android0/iSerial
mv /sdcard/iSerial /sys/class/android_usb/android0/
