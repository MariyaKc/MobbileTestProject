package config;

import org.aeonbits.owner.Config;

@org.aeonbits.owner.Config.LoadPolicy(org.aeonbits.owner.Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "file:src/resources/executor.properties",
})
public interface ExecutorConfig extends Config {
    /**
     * general
     */
    @Key("sortVersionEndpoint")
    String sortVersionEndpoint();

    @Key("perPageEndpoint")
    String perPageEndpoint();

    @Key("gitlabProjectUrl")
    String gitlabProjectUrl();

    @Key("tokenEndpoint")
    String tokenEndpoint();

    @Key("startAppium")
    String startAppium();

    @Key("killAppium")
    String killAppium();

    @Key("killEmulator")
    String killEmulator();
    @Key("portArray")
    String[] portArray();

    @Key("startServerStressTestSSS")
    String startServerStressTestSSS();

    /**
     * android
     */
    @Key("packageSettingsName")
    String packageSettingsName();

    @Key("getAndroidDevices")
    String getAndroidDevices();

    @Key("gmsVersion")
    String gmsVersion();

    @Key("getVersion")
    String getVersion();

    @Key("hms")
    String hms();

    @Key("gms")
    String gms();

    @Key("androidAppEndpoint")
    String androidAppEndpoint();

    @Key("androidHMSAppEndpoint")
    String androidHMSAppEndpoint();

    @Key("clearLogcat")
    String clearLogcat();

    @Key("deleteOutput")
    String deleteOutput();

    @Key("recordLogs")
    String recordLogs();

    @Key("pullLogs")
    String pullLogs();

    @Key("outputAppLogFile")
    String outputAppLogFile();

    @Key("uninstallAndroidApp")
    String uninstallAndroidApp();

    @Key("getProp")
    String getProp();

    @Key("getAndroidListPackages")
    String getAndroidListPackages();

    @Key("getWiFiStatus")
    String getWiFiStatus();

    @Key("turnOnMobileNetwork")
    String turnOnMobileNetwork();

    @Key("turnOffMobileNetwork")
    String turnOffMobileNetwork();

    @Key("createGSMCall")
    String createGSMCall();

    @Key("acceptGSMCall")
    String acceptGSMCall();

    @Key("cancelGSMCall")
    String cancelGSMCall();

    @Key("androidOpenAdbCommandSS")
    String androidOpenAdbCommandSS();

    @Key("androidCloseAdbCommandSS")
    String androidCloseAdbCommandSS();

    @Key("androidSettingsBundleId")
    String androidSettingsBundleId();

    @Key("androidWifiSettingsBundleId")
    String androidWifiSettingsBundleId();

    @Key("androidMobileSettingsBundleId")
    String androidMobileSettingsBundleId();

    @Key("androidPhotoBundleId")
    String androidPhotoBundleId();

    @Key("androidVideoBundleId")
    String androidVideoBundleId();

    @Key("androidPhoneAppBundleId")
    String androidPhoneAppBundleId();

    /**
     * ios
     */
    @Key("packageWebDriverRunnerName")
    String packageWebDriverRunnerName();

    @Key("uninstallIosApp")
    String uninstallIosApp();

    @Key("iosAppEndpoint")
    String iosAppEndpoint();

    @Key("getIosDeviceId")
    String getIosDeviceId();

    @Key("getIosListPackages")
    String[] getIosListPackages();
}
