package dennis.balmoris.com.happytiger;

import android.app.Application;
import android.os.SystemClock;

public class SplashTime extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(3000);
    }
}
