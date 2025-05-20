package io.ionic.starter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.getcapacitor.BridgeActivity;

import androidx.health.connect.client.HealthConnectClient;
import androidx.health.connect.client.records.StepsRecord;
import androidx.health.connect.client.request.ReadRecordsRequest;
import androidx.health.connect.client.response.ReadRecordsResponse;
import androidx.health.connect.client.records.Record;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.util.List;
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

    // Request permissions
    ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_PERMISSIONS);

    // Check availability and read steps if permissions are granted
    if (allPermissionsGranted()) {
      checkHealthConnectAvailabilityAndReadSteps();
    }
  }

  private boolean allPermissionsGranted() {
    for (String permission : permissions) {
      if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
        return false;
    }
    return true;
  }

  private void checkHealthConnectAvailabilityAndReadSteps() {
    int status = HealthConnectClient.getSdkStatus(this);

    if (status == HealthConnectClient.SDK_AVAILABLE) {
      HealthConnectClient client = HealthConnectClient.getOrCreate(this);

      // Create a background executor
      Executors.newSingleThreadExecutor().execute(() -> {
        try {
          Instant endTime = Instant.now();
          Instant startTime = endTime.minusSeconds(7 * 24 * 60 * 60); // Last 7 days

          ReadRecordsRequest<StepsRecord> request = new ReadRecordsRequest.Builder<>(StepsRecord.class)
            .setTimeRangeFilter(startTime, endTime)
            .build();

          ReadRecordsResponse<StepsRecord> response = client.readRecords(request).get();

          int totalSteps = 0;
          for (StepsRecord record : response.getRecords()) {
            totalSteps += record.getCount();
          }

          Log.i("HealthConnect", "Total steps in last 7 days: " + totalSteps);
        } catch (Exception e) {
          Log.e("HealthConnect", "Error reading Health Connect data", e);
        }
      });

    } else {
      Log.w("HealthConnect", "Health Connect is not available on this device.");
    }
  }

  // Optional: Handle permission result
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == REQUEST_CODE_PERMISSIONS && allPermissionsGranted()) {
      checkHealthConnectAvailabilityAndReadSteps();
    }
  }
}
