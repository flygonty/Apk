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
    ver.HARDWARE.value = "Samsung S10" ;
    console.log("Hardware : " + ver.HARDWARE.value);
    // ver.HARDWARE = "Sama;sldfj" ;
    // ver.Hardware = "Samsung S10" ;
    // console.log("Hardware after : " + ver.Hardware) ;
    
    // Wifi Hook
    Java.use("android.net.wifi.WifiInfo").getMacAddress.overload().implementation=function() {
        return "7C-57-23-06-C4-11" ;
    } 
    send("Hook end...") ;
});
