# 🍃 DHX-UI Test with Appium(written in JAVA)

Jumpstart your next Gatsby mapping project with Leaflet!


## 🧰 What This Includes
* you can check it out in pom.xml

## 🚀 Getting Started

### Requirements
* [Appium](http://appium.io/)

### Optional
* [uiautomator](http://appium.io/docs/en/drivers/android-uiautomator/)

### Quick Start
Run the Appium server
$ appium

Run the following in your favorite terminal:
```
$ -DskipTests -P prepare-for-upload clean package
$ verify
```

### Starting from Scratch

mkdir appium && d appium
sudo npm install -g appium
~/appium$ sudo npm intall -g appium-doctor
~/appium$ appium-doctor
x Android_Home is not setup
check Android SDK path
~/appium$ nano ~/.bashrc
export ANDROID_HOME=/home/namgyeong/Andriod/Sdk
~/appium$ sudo npm install -g mjpeg-consumer
~/appium$  which bundletool.jar  <-- if you can't find it need to download
download and move to the appium folder.
change name bundletool-blah-blah.jar into bundletool.jar
which bundletool.jar
export PATH=$PATH:$PWD
ls -l
check permissions -rw-rw-r-- 1 namgyeong namgyeong [date] bundletool.jar
~/appium$ chmod +x bundletool.jar
which bundletool.jar  you should see path  home/~~
appium doctor
which appium    
ls -la
~/appium$ sudo npm install -g appium   
~/appium$ sudo npm install -g appium  --chromedriver-skip-install
~/appium$ appium
make new tab on terminal
mkdir tests $$ cd tests
~/appium/tests$ npm init -y 
~/appium/tests$ sudo npm install webdriverio
ls -l 

~/appium/tests$ nano index.js
"const wdio = require(""webdriverio"");

// javascript
const opts = {
  path: '/wd/hub',
  port: 4723,
  capabilities: {
    platformName: ""Android"",
    platformVersion: ""9"",
    deviceName: ""Nexus 6 API 28"",   //check this out on Android-studio 
    app: ""/home/namgyeong/AndroidStudioProjects/app/build/app/outputs/apk/app.apk"", //apk location
    appPackage: ""com.datahighway.app"",  // AndroidManifest.xml
    appActivity: "".MainActivity"",  // AndroidManifest.xml
    automationName: ""UiAutomator2""
  }
};

async function main () {
  const browser =  await wdio.remote(opts);
  const btnCreateAccount = 'new UISelector().text(""Create Account"")';
  const btn = await browser.$('android=new UiSelector().text(""Create Account"")');
  await btn.waitForExist({timeout: 5000});
  await btn.waitForDisplayed({timeout: 5000});

  await btn.click();

  const txtName = await browser.$('android=new UiSelector().text(""Name"")');
  await txtName.waitForExist({timeout: 5000});
  await txtName.setValue('test');

  

  await browser.deleteSession();
}

main()
"