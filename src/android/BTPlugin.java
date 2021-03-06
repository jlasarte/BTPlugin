package com.github.jlasarte.BTPlugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.Context;
import android.bluetooth.BluetoothAdapter;

/**
 * The Class MacAddressPlugin.
 */
public class BTPlugin extends CordovaPlugin {

    public boolean isSynch(String action) {
        if (action.equals("enableVisibility")) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.cordova.api.Plugin#execute(java.lang.String,
     * org.json.JSONArray, java.lang.String)
     */
    @Override
    public boolean execute(String action, JSONArray args,
            CallbackContext callbackContext) {

        if (action.equals("enableVisibility")) {

            String macAddress = this.enableVisibility();

            if (macAddress != null) {
                JSONObject JSONresult = new JSONObject();
                try {
                    JSONresult.put("mac", macAddress);
                    PluginResult r = new PluginResult(PluginResult.Status.OK,
                            JSONresult);
                    callbackContext.success(macAddress);
                    r.setKeepCallback(true);
                    callbackContext.sendPluginResult(r);
                    return true;
                } catch (JSONException jsonEx) {
                    PluginResult r = new PluginResult(
                            PluginResult.Status.JSON_EXCEPTION);
                    callbackContext.error("error");
                    r.setKeepCallback(true);
                    callbackContext.sendPluginResult(r);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the mac address.
     * 
     * @return the mac address
     */
    private String enableVisibility() {
        String macAddress = null;
       // WifiManager wm = (WifiManager) this.cordova.getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
       // macAddress = wm.getConnectionInfo().enableVisibility();
        Intent discoverableIntent = new
        Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        Context context=this.cordova.getActivity().getApplicationContext();
        context.startActivity(discoverableIntent);

        return "...";
    }
}
