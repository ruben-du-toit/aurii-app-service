package io.ionic.starter;

import android.os.Bundle;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Future native plugin registrations can go here
  }

  // Placeholder for future Strava-native integration if needed
  public void connectToStrava() {
    // This method can be wired via Capacitor plugin or left empty for now
  }
}
