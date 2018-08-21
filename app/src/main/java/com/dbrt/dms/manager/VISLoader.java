package com.dbrt.dms.manager;

import android.Manifest;
import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

//import com.dbrt.dms.activity.AuthActivity;
//import com.dbrt.dms.activity.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import by.idev.jni.NativeNssUtils;


/**
 * Created by Sergey on 02.08.2017.
 */


public class VISLoader {
    //private String[] m_sCurToken;
    private static final String TAG = "myLogs";



    public void authNSS(String login, String password){

        String s2 = System.getenv().get( "LD_LIBRARY_PATH" );
        String s3 = System.getProperty( "java.library.path" );


        File source = new File("/data/data/com.dbrt.dms/lib/libls11sw.so");
        File dest = new File("/sdcard/lissi/lib/libls11sw.so");
        File sert = new File("/data/data/com.dbrt.dms/lib/qwerty.so");

        File dirs = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/lissi/lib/");

        File sertdest = new File("/sdcard/lissi/lib/qwerty.p12");

        //Environment.getExternalStorageDirectory().getAbsolutePath()

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/filename";



        if (dirs.mkdirs() == false)
        {
            //Toast.makeText(AuthActivity, new StringBuilder().append("Error MKDIR!!!!!!!!!").append(dirs.getAbsolutePath()),
            //       Toast.LENGTH_LONG).show();
            Log.d(TAG, "Source!!! = " + new StringBuilder().append("Error MKDIR!!!!!!!!!").append(dirs.getAbsolutePath()));
        }

        copyFile(source,dest);
        copyFile(sert,sertdest);

        //File dirs = new File("/sdcard/lissi/lib/");
        //dirs.mkdirs();
        //copyFile(source,dest);
        //copyFile(source1,dest1);
        //Log.d(TAG, "FILE PATH :" + dirs);

        String base64str = "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";
        byte[] data = Base64.decode(base64str, Base64.DEFAULT);

        int init_res = 0;
        String sInFile = null;
        String sTrusts = null;
        String[] token_list = null;
        String sNickName = null;
        String[] m_sCurToken = null;
        int nRes = -1;
        String certName = null;
        String callBackRequest = null;
        String def_apiVer = "Api/4.64";
        try {
            init_res = NativeNssUtils.StartInitializeNss();
            Log.d(TAG, "Succsess");
            m_sCurToken = NativeNssUtils.ListToken();
            Log.d(TAG, "SSSSSS :" + new StringBuilder().append(NativeNssUtils.ListToken()[2]));
            //public static native String  AddCert(String sToken, String sTrusts, String sContent, boolean isFile);
            //NativeNssUtils.AddCert("/data/data/com.dbrt.dms/lib/cert.p12", "asd", sContent: "sert", true);
            //NativeNssUtils.AddCert("/data/data/com.dbrt.dms/lib/cert.p12","","",true);
            sTrusts = "CT,C,C";

            try
            {
               // m_sCurToken = new String[]{(String) token_list[0]};



              //  char c = m_sCurToken.charAt(0);  // returns 'l'
              //  char[] c_arr = m_sCurToken.toCharArray();

                //sNickName = NativeNssUtils.AddCert( m_sCurToken[0], sTrusts, sInFile, true );

                //try(FileInputStream fin=new FileInputStream("C://SomeDir//notes.txt");
                    ;
                    try(FileOutputStream fos=new FileOutputStream("/sdcard/Download/qwerty.p12")){
                        fos.write(data, 0, data.length);
                    }


                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                //   {
                //    //byte[] buffer = new byte[fin.available()];
                    // считываем буфер
                    //fin.read(buffer, 0, buffer.length);
                    // записываем из буфера в файл

             //   }
             //   catch(IOException ex){

             //       System.out.println(ex.getMessage());
             //   }


                nRes = NativeNssUtils.P12ImportFile( m_sCurToken[2], "/sdcard/Download/qwerty.p12" );
                Log.d(TAG, "P12Import == " + nRes);
                //if(nRes == 0){
                    certName = NativeNssUtils.AllCert(m_sCurToken[2])[0];
                    //Log.d(TAG, "CERT NAME == " + certName);
                    String stringRequest = null;
                    //stringRequest = stringRequest.replace("\\\n", System.getProperty("line.separator"));
                    stringRequest = "GET /info.json?login=LinnikEN&hash=d657f015069158822494b75c47d7e47b&version="+def_apiVer+" HTTP/1.0\n\rUser-Agent: "+def_apiVer+"\n\r\n\r";
                    Log.d(TAG, "stringRequest == " + stringRequest);
                    callBackRequest = NativeNssUtils.TLSClient(certName,"me-dmz-sirius.minenergo.gov.ru", "443",stringRequest );

                    Log.d(TAG, "callBackRequest == " + callBackRequest);
               // }
            }
            catch( Error e )
            {
                Log.d(TAG, "Error calling StartInitialize()\n" + e.getMessage());
                return;
            }

        }
        catch( LinkageError e )
        {

            Log.d(TAG, "Error calling StartInitialize()\n" + e.getMessage());
//					return;
        }

        if ( ! ( init_res == 0 || init_res == 2 ) )
        {
            Log.d(TAG, "StartInitialize() failed: return code = " + init_res);

//					return;
        }

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


}
