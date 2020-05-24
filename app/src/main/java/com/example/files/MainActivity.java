package com.example.files;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //Yazılacak dosya oluştur; isim,mode belirle. private: sadece çağıran uygulama tarafından erişilebilir.
            //Eğer yazma hakkın yoksa program hata vermesin diye try/catch kullanılır.
            FileOutputStream fos = openFileOutput("file", Context.MODE_PRIVATE);
            String mesaj = "sancaryum\nsdfkdsfkksdfffffffffffffffffddddddddddddddddd\ndskfksdfkksddddddddddddddddd";
            //Mesajı byte'a çevirerek yazdık
            fos.write(mesaj.getBytes());
            //Yazdıktan sonra fileOutputStream objesinin yetkisini geri kapatıyoruz.
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            //Dosyayı okuma için açıyoruz (BORU-1)
            FileInputStream fis = openFileInput("file");
            //Gelen input akışını okuyacağız (BORU-2)
            InputStreamReader isr = new InputStreamReader(fis);
            //BufferedReader ile satır satır okuyabiliriz (BORU-3)
            BufferedReader br = new BufferedReader(isr);

            tv = findViewById(R.id.textView);
            tv.setText(br.readLine());

            //Yazdıktan sonra fileInputStream objesinin yetkisini geri kapatıyoruz.
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            //Uygulamanın kaynaklarına erişebilmek için pointer tanımlıyoruz.
            Resources r = getResources();
            //Girdi şeklinde bir akış oluşturuyoruz
            InputStream is = r.openRawResource(R.raw.dene);
            Scanner s = new Scanner(is);
            String str="";
            //yazılı bir şeyler oldukça, tüm yazılanları bir stringe ata
            while (s.hasNext()){
                str+= " " + s.next();
            }
            tv.setText(str);

            /*
            bu parça sadece 1 satır 1 satır okutur.
            tv.setText(s.nextLine());
             */




        }catch(Exception e){
            e.printStackTrace();
        }






    }
}
