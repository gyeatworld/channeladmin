Ext.define('ChannelAdmin.model.User', {
     extend: 'Ext.data.Model',
     fields: [
         {name: 'x_user_id', type: 'int'},
         {name: 'username',  type: 'string'},
         {name: 'realname',  type: 'string'},
         {name: 'nickname',  type: 'string'},
         {name: 'image'   ,  type: 'string'}
     ]
});