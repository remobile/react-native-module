# React Native Module (remobile)
Dynamic loading third party react-native app/module for react-native
## Installation
```sh
npm install @remobile/react-native-module --save
```

### Installation (iOS)
* Drag RCTModule.xcodeproj to your project on Xcode.
* Click on your main project file (the one that represents the .xcodeproj) select Build Phases and drag libRCTModule.a from the Products folder inside the RCTModule.xcodeproj.

### Installation (Android)
```gradle
...
include ':react-native-module'
project(':react-native-module').projectDir = new File(settingsDir, '../node_modules/@remobile/react-native-module/android')
```

* In `android/app/build.gradle`

```gradle
...
dependencies {
    ...
    compile project(':react-native-module')
}
```

* register module (in MainApplication.java)

```java
......
import com.remobile.module.RCTModulePackage;  // <--- import

......

@Override
protected List<ReactPackage> getPackages() {
   ......
   new RCTModulePackage(),            // <------ add here
   ......
}

```

## Usage

### Example
##### main enter jsfile
```js
'use strict';

var React = require('react');
var ReactNative = require('react-native');
var {
    StyleSheet,
    View,
    Platform,
    NativeModules,
} = ReactNative;

var Button = require('@remobile/react-native-simple-button');
var Module = require('@remobile/react-native-module');

module.exports = React.createClass({
    test() {
        const url = Platform.OS === 'android' ?
         '/sdcard/www/index.android.bundle' :
         '/Users/fang/rn/react-native-template/moduleLoad/www/ios/index.ios.bundle';
        Module.load(url, 'SimpleApp', {fang:1, yun:2}, (result)=>{
            Toast(JSON.stringify(result));
        });
    },
    render() {
        return (
            <View style={styles.container}>
                <Button onPress={this.test}>测试</Button>
            </View>
        );
    }
});


var styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: 'transparent',
        justifyContent: 'space-around',
        paddingVertical: 150,
    },
});
```
##### thirdparty app jsfile
```js
'use strict';

var React = require('react');
var ReactNative = require('react-native');
var {
    Text,
    View,
    TouchableOpacity,
    NativeModules,
    Dimensions,
} = ReactNative;

var width = Dimensions.get('window').width;
var Toast = require('@remobile/react-native-toast').show;
var Module = require('@remobile/react-native-module');

var SimpleApp = React.createClass({
    goBack() {
        Module.unload({text: 'I come back'});
    },
    render() {
        return (
            <View style={{flex: 1, backgroundColor: 'transparent', width:width}}>
                <View style={{flex:1,  backgroundColor: 'gray', justifyContent:'center', alignItems:'center'}}>
                    <TouchableOpacity onPress={this.goBack}>
                        <Text style={{color:'white', fontSize:25}}>Back</Text>
                    </TouchableOpacity>
                </View>
                <View style={{flex:1,  backgroundColor: 'blue'}}>
                    <Text>
                        {JSON.stringify(this.props)}
                    </Text>
                </View>
            </View>
        )
    }
});

ReactNative.AppRegistry.registerComponent('SimpleApp', () => SimpleApp);
```

### Screencasts

![demo](https://github.com/remobile/react-native-module/blob/master/screencasts/demo.gif)

### Method
- `load(url, moduleName, props, callback): PropTypes.function` call in main app
- `unload(props): PropTypes.function` call in thirdparty app

### see detail use
* https://github.com/remobile/react-native-template
