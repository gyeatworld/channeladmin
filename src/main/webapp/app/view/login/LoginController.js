Ext.define('ChannelAdmin.view.login.LoginController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.login',
	onLoginClick : function() {

		var loginParam = {};
		loginParam.username = this.lookupReference('text_Username').getValue();
		loginParam.password = this.lookupReference('text_Password').getValue();

		Ext.Ajax.request({
			method : 'POST',
			url : './User/AdminLogin?view=json',
			params : loginParam,
			scope : this,
			failure : function(response, opts) {
				Ext.MessageBox.alert('操作结果', '通讯失败（' + response.status + '）:'
						+ response.statusText);
			},
			success : function(response, opts) {
				var oResult = Ext.JSON.decode(response.responseText)

				if (oResult.businessCode == 'success') {
					
					// Set the localStorage value to true
					localStorage.setItem("TutorialLoggedIn", true);

					// Remove Login Window
					this.getView().destroy();
					// 更新脏标记，是否要刷新
					this.getView().setHidden(true);
					// Add the main view to the viewport
					Ext.create({
						xtype : 'app-main'
					});
					//this.lookupReference('text_Username').setValue('');
					//this.lookupReference('text_Password').setValue('');

				} else {
					Ext.MessageBox.alert('登录失败:', oResult.description);
				}
			}
		});
	},
	onCancelClick : function() {
		// this.lookupReference('loginForm').form.reset();
		Ext.getCmp('loginForm').getForm().reset();
	}
})