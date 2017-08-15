/*
 * This file launches the application by asking Ext JS to create
 * and launch() the Application class.
 */
Ext.application({
    extend: 'ChannelAdmin.Application',

    name: 'ChannelAdmin',

    requires: [
        // This will automatically load all classes in the ChannelAdmin namespace
        // so that application classes do not need to require each other.
        'ChannelAdmin.*'
    ],

    // The name of the initial view to create.
    //禁用main，打开跳登录
    //mainView: 'ChannelAdmin.view.main.Main'
});
