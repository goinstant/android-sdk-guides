package com.salesforce.androidsosguides;

import android.app.Application;

public class GuidesApplication extends Application {
  public static boolean atleastBasic() {
    return BuildConfig.FLAVOR.equals("basic") || BuildConfig.FLAVOR.equals("advanced");
  }
}
