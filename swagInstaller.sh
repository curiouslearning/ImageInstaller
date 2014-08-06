pm set-install-location 2

pm install -s /mnt/external_sd/swagapps/air.com.speakaboos.kidstime.the_abc_song-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv1_Unit10-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv1_Unit2-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv1_Unit3-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv1_Unit4-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv1_Unit5-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv1_Unit6-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv1_Unit7-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv1_Unit8-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv1_Unit9-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv2_Unit10-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv2_Unit2-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv2_Unit3-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv2_Unit4-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv2_Unit5-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv2_Unit7-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv2_Unit8-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv2_Unit9-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv3_Unit10-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv3_Unit4-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv3_Unit5-1.apk
pm install -s /mnt/external_sd/swagapps/BMA_CO.Phonics_Lv3_Unit7-1.apk
pm install -s /mnt/external_sd/swagapps/com.dornbachs.zebra-1.apk
pm install -s /mnt/external_sd/swagapps/com.oncilla.LetterTracing-1.apk
pm install -s /mnt/external_sd/swagapps/com.sevenacademy.comptejusquadixipad-1.apk
pm install -s /mnt/external_sd/swagapps/com.sevenacademy.leBonheurDeLire-1.apk
pm install -s /mnt/external_sd/swagapps/com.sevenacademy.TicTacTime-1.apk
pm install -s /mnt/external_sd/swagapps/com.sight.words.rock.learn-1.apk
pm install -s /mnt/external_sd/swagapps/com.storychimes.uglyducklingnoad-1.apk
pm install -s /mnt/external_sd/swagapps/com.tb.hd.en.gato_con_botas.main-1.apk
pm install -s /mnt/external_sd/swagapps/com.tb.hd.en.granja.main-1.apk
pm install -s /mnt/external_sd/swagapps/com.tb.hd.en.luna.main-1.apk
pm install -s /mnt/external_sd/swagapps/com.tb.sd.en.playa.main-1.apk
pm install -s /mnt/external_sd/swagapps/com.vanluyen.KidColoringNoAds-1.apk
pm install -s /mnt/external_sd/swagapps/edu.mit.media.funf.bgcollector-1.apk
pm install -s /mnt/external_sd/swagapps/edu.mit.media.prg.tinkerbook_unity-1.apk
pm install -s /mnt/external_sd/swagapps/pl.aygorund.littlepiano.pro-1.apk
pm install -s /mnt/external_sd/swagapps/pl.ayground.clockwise-1.apk
pm install -s /mnt/external_sd/swagapps/pl.ayground.littlepiano-1.apk
pm install -s /mnt/external_sd/swagapps/pl.ayground.timestable-1.apk
pm install -s /mnt/external_sd/swagapps/russh.toddler.colors-1.apk
pm install -s /mnt/external_sd/swagapps/russh.toddler.game-1.apk
pm install -s /mnt/external_sd/swagapps/russh.toddler.shapes-1.apk

pm install /mnt/external_sd/swagapps/mentoring_app.apk

su
mount -o remount,rw -t ext4 /system

pm set-install-location 0

busybox cp /mnt/external_sd/swagapps/systemApps/* /system/app

# After install, move the data files to their respective locations

#Applocklite data

mkdir /data/data/com.morrison.applocklite/shared_prefs
busybox cp /mnt/external_sd/swagapps/data/com.morrison.applocklite/shared_prefs/com.morrison.applocklite_preferences.xml  /data/data/com.morrison.applocklite/shared_prefs/
busybox cp /mnt/external_sd/swagapps/data/com.morrison.applocklite/shared_prefs/gr_pref.xml /data/data/com.morrison.applocklite/shared_prefs/                         

# Reboot to complete install
reboot
