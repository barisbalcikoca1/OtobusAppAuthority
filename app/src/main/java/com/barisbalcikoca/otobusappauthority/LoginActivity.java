package com.barisbalcikoca.otobusappauthority;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

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

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText kullaniciAdi, sifre;
    private Button btnGiris;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initComponents();
        registerEventHandlers();
    }

    private void initComponents() {
        kullaniciAdi=findViewById(R.id.labelKullaniciAdi);
        sifre = findViewById(R.id.labelSifre);
        btnGiris = findViewById(R.id.btnGiris);

    }

    private void registerEventHandlers() {


        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new token_kontrol().start();
            }
        });

    }


    private class token_kontrol extends Thread {
        String data ="";
        String veri = "";
        @Override
        public void run(){
            {

                //{"token":["BARIŞ","1234"]}
                try {
                    JSONObject jsonData = new JSONObject();

                    JSONArray parametreler = new JSONArray();
                    parametreler.put(kullaniciAdi.getText().toString());
                    parametreler.put(sifre.getText().toString());
                    jsonData.put("token", parametreler);

                    URL url = new URL("https://orakoglu.net/staj1/tokenayar.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setDoOutput(true);

                    System.out.println(jsonData);

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


                        //{"result":["true"]}

                        JSONArray tokenKontrol = obj.getJSONArray("result");
                        //Toast.makeText(ShowActivity.this, cityArray2,Toast.LENGTH_SHORT).show(); Bu kullanılamıyor alt tarafta yazdığım kod güvenli olması için yazıldı
                        /*for (int i = 0; i < otobusGuzergahArray.length(); i++) {
                            String numara_guzergah_seferler = otobusGuzergahArray.getString(i);
                            txtSeferler.setText(numara_guzergah_seferler);
                        }*/

                        veri = (String) tokenKontrol.get(0);
                        System.out.println(veri);

                        if(!veri.equals("")){
                            intent = new Intent(LoginActivity.this, AdminActivity.class);
                            intent.putExtra("kullaniciAdi",kullaniciAdi.getText().toString());
                            intent.putExtra("sifre",sifre.getText().toString());
                            startActivity(intent);
                        }

                    }
                    else if(veri.equals(""))
                    {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "YANLIŞ KULLANICI ADI VEYA ŞİFRE GİRDİNİZ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

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

