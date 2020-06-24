
1- start appium server with `appium`

### to run in ios simulator
2- generate an ios .app

3- set correct values in AppManager.java file (line 113 and 116)

4- run with command `mvn test -P ios-simulator`


### to run in android simulator
2- install app with `react-native run-android`

3- run with command `mvn test -P android-simulator`