Ext.define('ChannelAdmin.view.login.LoginView', {
	extend : 'Ext.window.Window',
	xtype : 'login',

	requires : [ 'Ext.form.Panel', 'ChannelAdmin.view.login.LoginController' ],

	controller : 'login',

	bodyPadding : 10,
	title : '用户登录',

	closable : false,// 窗口是否可关闭
	autoShow : true,// windows默认是隐藏，要设置为显示

	items : {
		xtype : 'form',
		id : 'loginForm',
		reference : 'loginForm',
		items : [ {
			xtype : 'textfield',
			reference: 'text_Username',
			name : 'username',
			fieldLabel : '用户名',
			allowBlank : false,
			blankText : '用户名不能为空!'
		}, {
			xtype : 'textfield',
			reference: 'text_Password',
			name : 'password',
			fieldLabel : '密码',
			allowBlank : false,
			blankText : '请输入密码!'
		} ],
		buttons : [ {
			text : '登录',
			formBind : true,// 按钮是否可用取决于form
			margin:'0px 15px',
			handler : 'onLoginClick'
		}, {
			text : '重置',
			handler :'onCancelClick'// 重置表单
		} ]
	}
});