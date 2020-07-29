package com.GP.base;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

    // Driver initialization
    public static WebDriver getDriver(String browser, Logger log) {
        WebDriver driver;
        //String OS = null;
        log.info("Starting " + browser + " driver");

        switch (browser) {
            case "firefox":
                //closeDriverExeFile("geckodriver.exe");
                System.setProperty("webdriver.gecko.driver",
                        ".\\src\\main\\resources\\geckodriver.exe");

                driver = new FirefoxDriver();
                break;

            case "chrome":
                //closeDriverExeFile("chromedriver.exe");
			/*System.setProperty("webdriver.chrome.driver",
					"src/main/resources/chromedriver");*/

                WebDriverManager.chromedriver().setup();

                //OS = System.getProperty("os.name");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("window-size=1200x600");

                driver = new ChromeDriver(options);
                //driver = new ChromeDriver();

                break;

            case "ie":
                //closeDriverExeFile("IEDriverServer.exe");
                System.setProperty("webdriver.internetexplorer.driver",
                        ".\\src\\main\\resources\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;

            default:
                //closeDriverExeFile("geckodriver.exe");
                System.setProperty("webdriver.gecko.driver",
                        ".\\src\\main\\resources\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
        }

        // Enable ONLY for System With 2 Or More Displays !!!
		/*if (getNumberOfUserMonitors() > 1) {
			//driver.manage().window().setPosition(new Point(getdMonitor(1).getDisplayMode().getWidth(), 1));
			driver.manage().window().setPosition(new Point(2000, 1));
		}*/

        driver.manage().window().maximize();

        return driver;
    }

    // Get number of users monitors
	/*protected static int getNumberOfUserMonitors() {
		int numberOfScreens = 0;
		try {
			// Get local graphics environment
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();

			// Returns an array of all of the screen GraphicsDevice objects.
			GraphicsDevice[] devices = env.getScreenDevices();

			numberOfScreens = devices.length;

		} catch (HeadlessException e) {
			// We'll get here if no screen devices was found.
			e.printStackTrace();
		}
		return numberOfScreens;
	}

	// Returns an array of all of the screen GraphicsDevice objects.
	protected static GraphicsDevice getdMonitor(int monitorNumber) {
		GraphicsDevice[] devices = null;
		try {
			// Get local graphics environment
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();

			// Returns an array of all of the screen GraphicsDevice objects.
			devices = env.getScreenDevices();

		} catch (HeadlessException e) {
			// We'll get here if no screen devices was found.
			e.printStackTrace();
		}
		return devices[monitorNumber - 1];
	}*/

    // Close all existing driver exe
	/*protected static void closeDriverExeFile(String driverName) {

		String sDriverExeName = driverName;

		try {
			String line;
			Process p = Runtime.getRuntime()
					.exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe  /fo csv /nh");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				if (line.contains(sDriverExeName)) {
					String cmd = "taskkill /F /IM " + sDriverExeName;
					Runtime.getRuntime().exec(cmd);
					System.out.println("The Instance Of " + driverName + " Was Closed.");
				}
			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}*/

}

