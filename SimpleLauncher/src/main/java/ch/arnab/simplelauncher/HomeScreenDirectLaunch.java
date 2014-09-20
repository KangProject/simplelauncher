package ch.arnab.simplelauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import org.chrome.remote.JavaWebServer;
import org.chrome.remote.JavaWebServerService;

public class HomeScreenDirectLaunch extends FragmentActivity {

    public static JavaWebServer myWebServer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        if (JavaWebServerService.mySelf == null)
        {
            Intent remoteWebServerIntent = new Intent(this, JavaWebServerService.class);
            getApplicationContext().startService(remoteWebServerIntent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }
}
