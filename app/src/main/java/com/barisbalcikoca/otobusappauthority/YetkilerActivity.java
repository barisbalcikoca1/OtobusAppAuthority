package com.barisbalcikoca.otobusappauthority;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class YetkilerActivity extends AppCompatActivity {
    Intent gelenIntent;
    String islemTuru;
    int editTextSayisi =0;
    LinearLayout editTextContainer;
    ScrollView scrollView;
    Button onaylaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yetkiler);

        initComponents();


        if (islemTuru != null && islemTuru.equals("e_durak_ekle")) {
            editTextSayisi = 1;
        }
        else  if (islemTuru != null && islemTuru.equals("e_durak_sil")) {
            editTextSayisi = 1;
        }
        else  if (islemTuru != null && islemTuru.equals("e_durak_guncelle")) {
            editTextSayisi = 2;
        }
        else  if (islemTuru != null && islemTuru.equals("e_guzergah_ekle")) {
            editTextSayisi = 2;
        }
        else  if (islemTuru != null && islemTuru.equals("e_guzergah_sil")) {
            editTextSayisi = 1;
        }
        else  if (islemTuru != null && islemTuru.equals("e_guzergah_guncelle")) {
            editTextSayisi = 2;
        }
        else  if (islemTuru != null && islemTuru.equals("e_sefer_ekle")) {
            editTextSayisi = 3;
        }
        else  if (islemTuru != null && islemTuru.equals("e_sefer_guncelle")) {
            editTextSayisi = 6;
        }
        else  if (islemTuru != null && islemTuru.equals("e_sefer_sil")) {
            editTextSayisi = 1;
        }

        for (int i = 0; i < editTextSayisi; i++) {
            EditText editText = new EditText(this);
            editText.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            if(islemTuru.equals("e_durak_ekle")){
                editText.setHint("Eklenecek Durak Numarasını Giriniz: ");
                
                onaylaBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new e_durak_ekle().start();
                    }
                });
            }
            else  if(islemTuru.equals("e_durak_sil")){
                editText.setHint("Silinecek Durak Numarasını Giriniz: ");

                onaylaBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new e_durak_ekle().start();
                    }
                });
            }
            else  if(islemTuru.equals("e_durak_guncelle")){

                if (i == 0) {
                    editText.setHint("Eski Durak Numarasını Giriniz");
                } else if (i == 1) {
                    editText.setHint("Yeni Durak Numarasını Giriniz");
                }

                onaylaBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new e_durak_ekle().start();
                    }
                });
            }
            else  if(islemTuru.equals("e_guzergah_ekle")){
                if (i == 0) {
                    editText.setHint("Eklenecek Guzergah Numarasını Giriniz");
                } else if (i == 1) {
                    editText.setHint("Eklenecek Guzergah Adını Giriniz");
                }

                onaylaBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new e_durak_ekle().start();
                    }
                });
            }
            else  if(islemTuru.equals("e_guzergah_guncelle")){
                if (i == 0) {
                    editText.setHint("Eski Guzergah Adını Giriniz");
                } else if (i == 1) {
                    editText.setHint("Yeni Guzergah Adını Giriniz");
                }

                onaylaBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new e_durak_ekle().start();
                    }
                });
            }
            else  if(islemTuru.equals("e_guzergah_sil")){
                editText.setHint("Silinecek Guzergah Adını Giriniz: ");

                onaylaBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new e_durak_ekle().start();
                    }
                });
            }
            else  if(islemTuru.equals("e_sefer_ekle")){
                if (i == 0) {
                    editText.setHint("Guzergah Adını Giriniz");
                } else if (i == 1) {
                    editText.setHint("Otobus Numarasını Giriniz");
                }
                else if (i == 2) {
                    editText.setHint("Kalkış Saatini Giriniz");
                }

                onaylaBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new e_durak_ekle().start();
                    }
                });
            }
            else  if(islemTuru.equals("e_sefer_guncelle")){
                if (i == 0) {
                    editText.setHint("Eski Guzergah Adını Giriniz");
                } else if (i == 1) {
                    editText.setHint("Yeni Guzergah Adını Giriniz");
                }
                else if (i == 2) {
                    editText.setHint("Eski Otobus Numarasını Giriniz");
                }
                else if (i == 3) {
                editText.setHint("Yeni Otobus Numarasını Giriniz");
                }
                else if (i == 4) {
                editText.setHint("Eski Kalkış Saatini Giriniz");
                }
                else if (i == 5) {
                editText.setHint("Yeni Kalkış Saatini Giriniz");
                }

                onaylaBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new e_durak_ekle().start();
                    }
                });
            }
            else  if(islemTuru.equals("e_sefer_sil")){
                editText.setHint("Silinecek Sefer ID Giriniz: ");

                onaylaBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new e_durak_ekle().start();
                    }
                });
            }
            editTextContainer.addView(editText);
        }

    }
    private void initComponents() {
        gelenIntent= getIntent();
        editTextContainer = findViewById(R.id.editTextContainer);
        scrollView = findViewById(R.id.scrollView);
        onaylaBtn = findViewById(R.id.onaylaBtn);


        if (gelenIntent != null) {
            islemTuru = gelenIntent.getStringExtra("selectedValue");
        }
    }

    private class e_durak_ekle extends Thread {
        String data ="";
        String durum = "";
        @Override
        public void run(){
            {
                try {
                    JSONObject jsonData = new JSONObject();
                    jsonData.put("islem", islemTuru.toString());

                    JSONArray parametreler = new JSONArray();
                    // EditText alanlarından gelen verileri eklemek için bir döngü kullanın
                    for (int i = 0; i < editTextSayisi; i++) {
                        EditText editText = (EditText) editTextContainer.getChildAt(i);
                        String veri = editText.getText().toString();
                        parametreler.put(veri);
                    }
                    // Parametreler JSONArray'ini JSON nesnesine ekleyin
                    jsonData.put("parametreler", parametreler);

                    URL url = new URL("https://orakoglu.net/staj1/ayarlar1.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setDoOutput(true);

                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(("jsonData=" + URLEncoder.encode(jsonData.toString(), "UTF-8")).getBytes());
                    outputStream.flush();
                    outputStream.close();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        data = data+line;
                    }
                    if(!data.isEmpty()){
                        JSONObject obj = new JSONObject(data);

                        String otobusGuzergahAdinaGoreNumaraList= obj.getString("durum");
                        durum = otobusGuzergahAdinaGoreNumaraList.toString();

                        //Altta bulunan kod eklenmezse liste güncellenmediği için veriler eklense bile boş görünür.
                        //Verileri listeye ekledikten sonra adapter güncellenir ve bu sayede görünür hale gelr.
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(YetkilerActivity.this, durum, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}