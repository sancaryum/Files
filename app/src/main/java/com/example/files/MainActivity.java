package com.example.files;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

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

            TextView tv = findViewById(R.id.textView);
            tv.setText(br.readLine());

            //Yazdıktan sonra fileInputStream objesinin yetkisini geri kapatıyoruz.
            fis.close();






        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
