package com.barisbalcikoca.otobusappauthority;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    ViewGroup yourEditTextContainer;
    private Intent gelenIntent;
    private String gelenKullaniciAdi, gelenSifre;
    private ListView listView;
    ArrayList<String> yetkiList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    private TextView txt;
    private String islem= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initComponents();
        registerEventHandlers();

        new yetkiler().start();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, yetkiList);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = yetkiList.get(position);


                Intent intent = new Intent(AdminActivity.this, YetkilerActivity.class);
                intent.putExtra("selectedValue", selectedValue);
                startActivity(intent);

            }
        });
    }

    private void initComponents() {
        gelenIntent = getIntent();

        gelenKullaniciAdi = gelenIntent.getStringExtra("kullaniciAdi");
        gelenSifre = gelenIntent.getStringExtra("sifre");
        listView = findViewById(R.id.listYetki);

    }

    private void registerEventHandlers() {

    }


    private class yetkiler extends Thread {
        String data ="";
        String veri = "";
        @Override
        public void run(){
            {

                //{"token":["BARIŞ","1234"]}

                try {
                    JSONObject jsonData = new JSONObject();

                    jsonData.put("islem","e_token_yetkiler");

                    JSONArray token = new JSONArray();
                    token.put(gelenKullaniciAdi);
                    jsonData.put("parametreler", token);

                    URL url = new URL("https://orakoglu.net/staj1/ayarlar1.php");
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

                        JSONArray tokenYetkiKontrol = obj.getJSONArray("result");
                        //Toast.makeText(ShowActivity.this, cityArray2,Toast.LENGTH_SHORT).show(); Bu kullanılamıyor alt tarafta yazdığım kod güvenli olması için yazıldı
                        for (int i = 0; i < tokenYetkiKontrol.length(); i++) {
                            yetkiList.add(tokenYetkiKontrol.getString(i)) ;
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
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