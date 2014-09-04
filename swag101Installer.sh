su

pm set-install-location 2

pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv1_Unit10-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv1_Unit2-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv1_Unit3-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv1_Unit4-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv1_Unit5-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv1_Unit6-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv1_Unit7-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv1_Unit8-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv1_Unit9-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv2_Unit10-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv2_Unit2-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv2_Unit3-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv2_Unit4-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv2_Unit5-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv2_Unit7-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv2_Unit8-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv2_Unit9-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv3_Unit10-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv3_Unit4-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv3_Unit5-1.apk
pm install -s /mnt/external_sd/swagApps/BMA_CO.Phonics_Lv3_Unit7-1.apk
pm install -s /mnt/external_sd/swagApps/air.com.speakaboos.kidstime.the_abc_song-1.apk
pm install -s /mnt/external_sd/swagApps/air.com.uxpractitioners.apps.phonicsreaderLite-1.apk
pm install -s /mnt/external_sd/swagApps/com.SightCards-1.apk
pm install -s /mnt/external_sd/swagApps/com.absolutist.abcmysteriezfree-1.apk
pm install -s /mnt/external_sd/swagApps/com.codingcaveman.Solo-1.apk
pm install -s /mnt/external_sd/swagApps/com.dornbachs.zebra-1.apk
pm install -s /mnt/external_sd/swagApps/com.learningtouch.bobbooksliteversion-1.apk
pm install -s /mnt/external_sd/swagApps/com.lostlarry-1.apk
pm install -s /mnt/external_sd/swagApps/com.oncilla.LetterTracing-1.apk
pm install -s /mnt/external_sd/swagApps/com.sevenacademy.TicTacTime-1.apk
pm install -s /mnt/external_sd/swagApps/com.sevenacademy.comptejusquadixipad-1.apk
pm install -s /mnt/external_sd/swagApps/com.sevenacademy.leBonheurDeLire-1.apk
pm install -s /mnt/external_sd/swagApps/com.sight.words.rock.learn-1.apk
pm install -s /mnt/external_sd/swagApps/com.storychimes.uglyducklingnoad-1.apk
pm install -s /mnt/external_sd/swagApps/com.tb.hd.en.gato_con_botas.main-1.apk
pm install -s /mnt/external_sd/swagApps/com.tb.hd.en.granja.main-1.apk
pm install -s /mnt/external_sd/swagApps/com.tb.hd.en.luna.main-1.apk
pm install -s /mnt/external_sd/swagApps/com.tb.sd.en.playa.main-1.apk
pm install -s /mnt/external_sd/swagApps/com.vanluyen.KidColoringNoAds-1.apk
pm install -s /mnt/external_sd/swagApps/edu.mit.media.prg.tinkerbook_unity-1.apk
pm install -s /mnt/external_sd/swagApps/emantras.in-1.apk
pm install -s /mnt/external_sd/swagApps/kr.co.smartstudy.phonicsiap_android_googlemarket-1.apk
pm install -s /mnt/external_sd/swagApps/pl.ayground.clockwise-1.apk
pm install -s /mnt/external_sd/swagApps/pl.ayground.littlepiano-1.apk
pm install -s /mnt/external_sd/swagApps/pl.ayground.timestable-1.apk
pm install -s /mnt/external_sd/swagApps/russh.toddler.colors-1.apk
pm install -s /mnt/external_sd/swagApps/russh.toddler.game-1.apk
pm install -s /mnt/external_sd/swagApps/russh.toddler.shapes-1.apk
pm install -s /mnt/external_sd/swagApps/uk.co.readwritephonics-1.apk

pm set-install-location 0
pm install -f /mnt/external_sd/swagApps/funfFileMover.apk
pm install -f /mnt/external_sd/swagApps/edu.media.mit.prg.launcher-1.apk

su
mount -o remount,rw -t ext4 /system

busybox cp /mnt/external_sd/swagApps/systemApps/* /system/app

# Reboot to complete install
reboot
