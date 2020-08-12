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
- Prerequisites
```
mkdir appium && cd appium
sudo npm install -g appium --unsafe-perm=true --allow-root
sudo npm intall -g appium-doctor
```
- Run appium-doctor to check for any missing variables or dependencies
```
appium-doctor
x Android_Home is not setup
check Android SDK path
nano ~/.bashrc
export ANDROID_HOME=/home/[username]/Android/Sdk
sudo npm install -g mjpeg-consumer
which bundletool.jar # if you can't find it need to download download and move to the appium folder, change name bundletool-blah-blah.jar into bundletool.jar
export PATH=$PATH:$PWD
chmod +x bundletool.jar
which bundletool.jar # you should see path  home/~~
appium-doctor
```
- Test case setup
make new tab on terminal
```
mkdir tests $$ cd tests
npm init -y 
sudo npm install webdriverio
```
nano index.js
```
"const wdio = require(""webdriverio"");

// javascript
const opts = {
  path: '/wd/hub',
  port: 4723,
  capabilities: {
    platformName: ""Android"",
    platformVersion: ""9"",
    deviceName: ""Nexus 6 API 28"",   //check this out on Android-studio 
    app: ""/home/[username]/AndroidStudioProjects/app/build/app/outputs/apk/app.apk"", //apk location
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
```
