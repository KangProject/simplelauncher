package ch.arnab.simplelauncher;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;

import org.chrome.remote.JavaWebServer;
import org.chrome.remote.JavaWebServerService;

public class HomeScreenDirectLaunch extends FragmentActivity {

    public static final String EXTRA_OPEN_URL = "OPEN_URL";
    public static HomeScreenDirectLaunch mySelf = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        if (JavaWebServerService.mySelf == null)
        {
            Intent remoteWebServerIntent = new Intent(this, JavaWebServerService.class);
            getApplicationContext().startService(remoteWebServerIntent);
        }

        mySelf = this;
    }

    /**
     * This will be called when the user click on the notification
     */
    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("SimpleLauncher", "In onNewIntent()");
        super.onNewIntent(intent);
        boolean isOpenWebpage = intent.getBooleanExtra(EXTRA_OPEN_URL, false);
        if (isOpenWebpage) {
            String url = "http://127.0.0.1:4300";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }
}
