/*
 * BTPlugin
 * plugin for some BT functions
 * @author Olivier Brand
 */

/**
 * @return the mac address class instance
 */
 var BTPlugin = {

 	getMacAddress: function(successCallback, failureCallback){
 		cordova.exec(successCallback, failureCallback, 'BTPlugin',
 			'enableVisibility', []);
 	}
 };

 module.exports = BTPlugin;