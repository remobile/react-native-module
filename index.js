'use strict';

const ReactNative = require('react-native');
const {
    NativeModules,
} = ReactNative;

module.exports = {
    load (url, moduleName, props = {}, callback = () => {}) {
        NativeModules.Module.load(url, moduleName, props, callback);
    },
    unload (props = {}) {
        NativeModules.Module.unload(props);
    },
};
