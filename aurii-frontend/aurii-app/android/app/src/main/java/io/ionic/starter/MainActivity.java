package io.ionic.starter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.getcapacitor.BridgeActivity;

import androidx.health.connect.client.HealthConnectClient;
import androidx.health.connect.client.records.StepsRecord;
import androidx.health.connect.client.request.AggregateRequest;
import androidx.health.connect.client.time.TimeRangeFilter;


import java.time.Instant;
import java.util.Collections;
import java.util.concurrent.Executors;

public class MainActivity extends BridgeActivity {

  private static final int REQUEST_CODE_PERMISSIONS = 1234;

  private final String[] permissions = new String[]{
    Manifest.permission.ACTIVITY_RECOGNITION,
    Manifest.permission.BODY_SENSORS,
    "android.permission.READ_HEALTH_DATA"
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    registerPlugin(HealthConnectPlugin.class);
  }

  private boolean allPermissionsGranted() {
    for (String permission : permissions) {
      if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
        return false;
    }
    return true;
  }

  private void readAggregatedSteps() {
    int status = HealthConnectClient.getSdkStatus(this);

    switch (status) {
      case HealthConnectClient.SDK_AVAILABLE:
        Log.i("HealthConnect", "Health Connect SDK is AVAILABLE on this device.");
        break;
      case HealthConnectClient.SDK_UNAVAILABLE_PROVIDER_UPDATE_REQUIRED:
        Log.w("HealthConnect", "Health Connect is UNAVAILABLE: Provider update required.");
        break;
      default:
        Log.w("HealthConnect", "Health Connect status UNKNOWN: " + status);
        break;
    }
  }


  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == REQUEST_CODE_PERMISSIONS && allPermissionsGranted()) {
      readAggregatedSteps();
    }
  }
}
