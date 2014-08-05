pm install -f /mnt/extSdCard/apps/com.zoodles.book.theemperorsnewclothes-1.apk
pm install -f /mnt/extSdCard/apps/com.zoodles.book.thethreelittlepigs-1.apk
pm install -f /mnt/extSdCard/apps/com.zoodles.book.thetortoiseandthehare-1.apk
pm install -f /mnt/extSdCard/apps/com.zoodles.book.thevelveteenrabbit-1.apk
pm install -f /mnt/extSdCard/apps/air.bftv.larryABCs-2.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv1_Unit2-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv1_Unit3-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv1_Unit4-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv1_Unit5-1.apk
pm install -f /mnt/extSdCard/apps/pl.ayground.littlepiano-1.apk
pm install -f /mnt/extSdCard/apps/russh.toddler.game-1.apk
pm install -f /mnt/extSdCard/apps/zok.android.shapes-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv1_Unit6-1.apk
pm install -f /mnt/extSdCard/apps/edu.mit.media.prg.tinkerbook_unity-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv1_Unit7-1.apk
pm install -f /mnt/extSdCard/apps/jackpal.androidterm-1.apk
pm install -f /mnt/extSdCard/apps/org.connectbot-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv1_Unit8-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv1_Unit9-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv1_Unit10-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv2_Unit2-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv2_Unit3-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv2_Unit4-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv2_Unit5-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv2_Unit7-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv2_Unit8-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv2_Unit9-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv2_Unit10-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv3_Unit4-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv3_Unit5-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv3_Unit7-1.apk
pm install -f /mnt/extSdCard/apps/BMA_CO.Phonics_Lv3_Unit10-1.apk
pm install -f /mnt/extSdCard/apps/com.arent.myfirsttangrams-1.apk
pm install -f /mnt/extSdCard/apps/com.aruhat.mobileapps.animalabc-2.apk
pm install -f /mnt/extSdCard/apps/com.babyfirsttv.larrythingsthatgo-1.apk
pm install -f /mnt/extSdCard/apps/funfFileMover.apk
pm install -f /mnt/extSdCard/apps/com.dornbachs.zebra-2.apk
pm install -f /mnt/extSdCard/apps/com.goatella.beginningblends-1.apk
pm install -f /mnt/extSdCard/apps/com.intellijoy.sightwords-1.apk
pm install -f /mnt/extSdCard/apps/com.morrison.applocklite-1.apk
pm install -f /mnt/extSdCard/apps/com.morrison.processmanager.applock-1.apk
pm install -f /mnt/extSdCard/apps/com.oncilla.LetterTracing-1.apk
pm install -f /mnt/extSdCard/apps/com.tomatointeractive.gmz-1.apk
pm install -f /mnt/extSdCard/apps/com.twsitedapps.homemanager-2.apk
pm install -f /mnt/extSdCard/apps/com.zoodles.book.jackandthebeanstalk-1.apk
pm install -f /mnt/extSdCard/apps/com.zoodles.book.littleredridinghood-1.apk
pm install -f /mnt/extSdCard/apps/com.zoodles.book.thecountrymouseandthecitymouse-1.apk
pm install -f /mnt/extSdCard/apps/com.zoodles.book.theelvesandtheshoemaker-1.apk
pm install -f /mnt/extSdCard/apps/edu.mit.media.prg.launcher.apk
pm install -f /mnt/extSdCard/apps/stericson.busybox-1.apk

pm install -f /mnt/extSdCard/apps/edu.mit.media.prg.launcher.apk
pm install -f  /mnt/extSdCard/apps/funfFileMover.apk

su
mount -o remount,rw -t ext4 /system

pm set-install-location 0

busybox cp /mnt/extSdCard/apps/system/funf_background_collector.apk /system/apps/

mkdir /sdcard/launcher

busybox cp /mnt/extSdCard/apps/apps.json /sdcard/launcher

# Reboot to complete install
reboot
