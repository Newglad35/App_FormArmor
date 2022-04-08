package com.example.app_formarmor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "";
        String url_base = "http://192.168.1.137:8080/WebService_Recette/webresources/inscription/lesinscription";
        String param = "";

        // Lancement de la tache asynchrone (obligatoire car l'appel à un WebService est une "tache longue")
        AccesWebServices accesWS = new AccesWebServices();
        BufferedReader rd = null;
        String retourWS = "";
        try
        {
            //Récupération et exploitation de la valeur de retour
            HttpResponse rep = accesWS.execute().get();

            rd = new BufferedReader(new InputStreamReader(rep.getEntity().getContent()));

            retourWS = rd.readLine();

        }
        catch(Exception e)
        {
            System.out.println("ERREUR APPEL DU WEBSERVICE : " + e.getMessage());
        }
        /* PARSING DU TEXTE (retourWS) RETOURNE (format JSON) */
        // Création du tableau JSON
        JSONArray jTab = null;
        try {
            jTab = new JSONArray(retourWS);
        }
        catch (Exception e)
        {
            System.out.println("ERREUR TABLEAU JSON : " + e.getMessage());
        }
        Client[] LesClients = new Client[jTab.length()];
        // Pour exploiter le tableau JSON
        for (int i=0; i < jTab.length(); i++)
        {
            try
            {
                if()
                LesClients[i] = new Client(jTab.getJSONObject(i).getJSONObject("leclient").getInt("id"),jTab.getJSONObject(i).getJSONObject("leclient").getString("nom"),jTab.getJSONObject(i).getJSONObject("leclient").getString("num_tel"));
            }
            catch (JSONException jse)
            {
                System.out.println("ERREUR OBJET JSON : " + jse.getMessage());
            }
        }



    }
        private class AccesWebServices extends AsyncTask<Void, Void, HttpResponse>
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                Toast.makeText(getApplicationContext(), "Début du traitement asynchrone", Toast.LENGTH_LONG).show();
            }
            @Override
            protected HttpResponse doInBackground(Void... params)
            {
                String url_base = "http://192.168.1.137:8080/WebService_Recette/webresources/inscription/lesinscription";
                try
                {
                    HttpClient c=new DefaultHttpClient();
                    HttpGet req=new HttpGet(url_base);
                    req.addHeader("Accept", "application/json");
                    req.addHeader("Content-Type", "application/json");
                    HttpResponse reponse = c.execute(req);
                    return reponse;
                }
                catch (Exception ex)
                {
                    System.out.println("ERREUR HTTP : " + ex.getMessage());
                    return null;
                }
            }



        }




}






