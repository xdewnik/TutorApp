package com.sadface.tutorapp.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sadface.tutorapp.R;
import com.sadface.tutorapp.constr.TutorDTO;
import com.sadface.tutorapp.services.URL;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SADFACE on 11.06.2017.
 */

public class LoginActivity  extends AppCompatActivity {
    EditText usr,psd;
    Button btn;
    List<TutorDTO> glist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        btn =(Button) findViewById(R.id.btn);
        usr =(EditText) findViewById(R.id.usr);
        psd =(EditText) findViewById(R.id.psd);

        new TutorTask().execute();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (TutorDTO temp: glist) {
                if ((temp.getLogpas().containsKey(usr.getText().toString())) &&
                        temp.getLogpas().containsValue(psd.getText().toString())) {
                    Intent intent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    intent.putExtra("TutorList", temp);
                    startActivity(intent);
                    break;
                }

                }


            }
        });
    }


    private class TutorTask extends AsyncTask<Object, Object, List<TutorDTO>>{

        @Override
        protected List<TutorDTO> doInBackground(Object... params) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            TutorDTO[] list =template.getForObject(URL.GET_ALL_TUTORS, TutorDTO[].class );
            glist = Arrays.asList(list);
            return glist;
        }

        @Override
        protected void onPostExecute(List<TutorDTO> tutorDTOs) {
            btn.setVisibility(View.VISIBLE);
        }
    }




}
