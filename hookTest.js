Java.perform( function () {
    send("Hook Start...");
    // Feature Hook
    
    Java.use("android.telephony.TelephonyManager").getImei.overload().implementation=function() {
        return "353436042253624" ;
    } 
    Java.use("android.telephony.TelephonyManager").getSubscriberId.overload().implementation=function() {
        return "310150123456789" ;
    } 
    Java.use("android.telephony.TelephonyManager").getLine1Number.overload().implementation=function() {
        return "12025550184" ;
    } 
    Java.use("android.telephony.TelephonyManager").getSimOperatorName.overload().implementation=function() {
        return "AT&T" ;
    } 
    // Build & System properties Hook
    var ver = Java.use('android.os.Build') ;
    ver.SERIAL.value = "ABC756867561766" ;
    ver.HARDWARE.value = "mdfpp" ;
    ver.BRAND.value = "Samsung" ;
    ver.MODEL.value = "Samsung S8" ;
    ver.TAGS.value = "keys" ;
    // console.log("Hardware : " + ver.BRAND.value);

    // Battery Hook
    var Battery = Java.use('android.os.BatteryManager') ;
    Battery.EXTRA_LEVEL.value = "95" ;
    Battery.EXTRA_SCALE.value = "100" ;
    Battery.BATTERY_PROPERTY_CAPACITY.value = 50 ;
    // console.log(Battery.BATTERY_PROPERTY_CAPACITY.value) ;


    // Wifi Hook
    Java.use("android.net.wifi.WifiInfo").getMacAddress.overload().implementation=function() {
        return "7C-57-23-06-C4-11" ;
    } 
    send("Hook end...") ;
});
