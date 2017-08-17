/**
 * This class is the view model for the Main view of the application.
 */
Ext.define('ChannelAdmin.store.LoginStore', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.login-cover',

    requires:['desktop.model.User'],

    data: { name: 'admin' },

    //TODO - add data, formulas and/or methods to support your view
    stores: { 
        AdminUser: {
            //extend: 'Ext.data.Store',
            model: 'desktop.model.User',
            pageSize:30,
            remoteSort: true,
            proxy: {
                type: 'ajax',
                url: './User/AdminInfo',
                remoteSort: true,
                extraParams: { view: "json"},
                reader: {
                    type: 'json',
                    rootProperty: 'resultSet',
                    totalProperty: 'count',
                    idproperty: 'id'
                }
            }
            ,autoLoad: true
        }
    }
});