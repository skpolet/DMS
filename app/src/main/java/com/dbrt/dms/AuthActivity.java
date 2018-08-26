package com.dbrt.dms;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import by.idev.jni.NativeNssUtils;
import com.dbrt.dms.manager.VISLoader;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //start
    /*
        String s2 = System.getenv().get( "LD_LIBRARY_PATH" );

        String s3 = System.getProperty( "java.library.path" );


        File source = new File("/data/data/ru.testdms/lib/libls11sw.so");
        File dest = new File("/sdcard/lissi/lib/libls11sw.so");



        TelephonyManager mTelephonyMgr =
                (TelephonyManager)getSystemService(TELEPHONY_SERVICE);


        if (Calendar.getInstance().get((Calendar.MONTH)) + 1 > 10) {
            Toast.makeText(AuthActivity.this, new StringBuilder().append("Лицензия закончилась !!!"),
                    Toast.LENGTH_LONG).show();
            return;
        }
        else
        {

            Toast.makeText(AuthActivity.this, new StringBuilder().append("Лицензия действительна до 01.03.2016г. !!!").append(Environment.getExternalStorageDirectory().getAbsolutePath()),
                    Toast.LENGTH_LONG).show();
        }

        //File dirs = new File("/sdcard/lissi/lib/");
        File dirs = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/lissi/lib/");
        //Environment.getExternalStorageDirectory().getAbsolutePath()

        if (dirs.mkdirs() == false)
        {
            Toast.makeText(AuthActivity.this, new StringBuilder().append("Error MKDIR!!!!!!!!!").append(dirs.getAbsolutePath()),
                    Toast.LENGTH_LONG).show();
        }
        copyFile(source,dest);

        int init_res = 0;
//				String s = null;
        try {
            init_res = NativeNssUtils.StartInitializeNss();
        }
        catch( LinkageError e )
        {
            Toast.makeText(AuthActivity.this, "Error calling StartInitialize()\n" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
//					return;
        }

        if ( ! ( init_res == 0 || init_res == 2 ) )
        {
            Toast.makeText(AuthActivity.this, "StartInitialize() failed: return code = " + init_res,
                    Toast.LENGTH_SHORT).show();
//					return;
        }
*/


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE , Manifest.permission.WRITE_EXTERNAL_STORAGE},
                23401);


    }

    public void buttonAuth(View view)
    {
        VISLoader visLoader = new VISLoader();
        visLoader.authNSS("1","2");
    }

    public static Boolean copyFile(File source, File dest) {
        FileInputStream is = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            int nLength;
            byte[] buf = new byte[8000];
            while (true) {
                nLength = is.read(buf);
                if (nLength < 0) {
                    break;
                }
                os.write(buf, 0, nLength);
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();

        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ex) {
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (Exception ex) {
                }
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_auth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
