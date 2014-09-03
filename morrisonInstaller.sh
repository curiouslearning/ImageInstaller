su 
#Applocklite Databases
pm install /mnt/external_sd/swagApps/com.morrison.applocklite-1.apk
pm install /mnt/external_sd/swagApps/com.morrison.processmanager.applock-1.apk

mkdir /data/data/com.morrison.applocklite/databases
busybox cp -r /mnt/sdcard/external_sd/swagapps/data/com.morrison.applocklite/databases/applock.db /data/data/com.morrison.applocklite/databases/
chown ~~~ /data/data/com.morrison.applocklite/databases/applock.db
chmod 600 /data/data/com.morrison.applocklite/databases/applock.db

#Applocklite preferences

mkdir /data/data/com.morrison.applocklite/shared_prefs
busybox cp /mnt/sdcard/external_sd/swagapps/data/com.morrison.applocklite/shared_prefs/com.morrison.applocklite_preferences.xml  /data/data/com.morrison.applocklite/shared_prefs/
busybox cp /mnt/sdcard/external_sd/swagapps/data/com.morrison.applocklite/shared_prefs/gr_pref.xml /data/data/com.morrison.applocklite/shared_prefs/                         

chown ~~~ /data/data/com.morrison.applocklite/shared_prefs/com.morrison.applocklite_preferences.xml
chmod 600 /data/data/com.morrison.applocklite/shared_prefs/com.morrison.applocklite_preferences.xml

chown ~~~ /data/data/com.morrison.applocklite/shared_prefs/gr_pref.xml
chmod 600 /data/data/com.morrison.applocklite/shared_prefs/gr_pref.xml
