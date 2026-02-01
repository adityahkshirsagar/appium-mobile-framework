package GenericUtilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServerManager {
	
	private AppiumDriverLocalService service;
	
	public void startServer() {
	    String nodeBinary = "/opt/homebrew/bin/node";  // Path to Node.js
	    File appiumJS = new File("/opt/homebrew/lib/node_modules/appium/index.js");

	    // Set environment variables to ensure Appium finds Android SDK
	    Map<String, String> environment = new HashMap<>(System.getenv());
	    environment.put("ANDROID_HOME", "/path/to/android/sdk");
	    environment.put("ANDROID_SDK_ROOT", "/path/to/android/sdk");
	    environment.put("PATH", environment.get("PATH")
	        + ":/path/to/android/sdk/platform-tools"
	        + ":/path/to/android/sdk/emulator"
	        + ":/opt/homebrew/bin"  // Needed for Node
	    );

	    service = new AppiumServiceBuilder()
	            .usingDriverExecutable(new File(nodeBinary))
	            .withAppiumJS(appiumJS)
	            .withEnvironment(environment)
	            .withIPAddress("127.0.0.1")
	            .usingPort(4723)
	            .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
	            .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
	            .build();

	    service.start();
	    System.out.println("✅ Appium Server started at: " + service.getUrl());
	}

    public void stopServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Appium Server stopped.");
        }
    }

    public boolean isServerRunning() {
        return service != null && service.isRunning();
    }

    public String getServerUrl() {
        return service.getUrl().toString();
    }

}
