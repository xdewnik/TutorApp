package com.sadface.tutorapp.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sadface.tutorapp.R;
import com.sadface.tutorapp.constr.StudentDTO;
import com.sadface.tutorapp.services.URL;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;

/**
 * Created by SADFACE on 12.06.2017.
 */

public class ChangeStudentActivity extends AppCompatActivity {
    private int student_id;
    private StudentDTO student;
    private EditText studentName,formOfAducation,studentPhone,birthday,studentHome,
            studentLive,motherName,motherPhone,motherWork,fatherName,fatherPhone, fatherWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_student_activity);


        Intent intent = getIntent();
        student_id = intent.getIntExtra("studID", 0);

        initEdit();

        new StudentGetTask().execute();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.changeFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postChangedStudent();
                new StudentPostTask().execute();
                Snackbar.make(view, "Saved", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private class StudentGetTask extends AsyncTask<Object, Object, StudentDTO> {

        @Override
        protected StudentDTO doInBackground(Object... params) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            student = template.getForObject(URL.GET_STUDENT_BY_ID + student_id, StudentDTO.class);
            return student;
        }


        @Override
        protected void onPostExecute(StudentDTO studentDTO) {
            super.onPostExecute(studentDTO);
            pupulateEdits(studentDTO);
        }

    }

    private class StudentPostTask extends AsyncTask<Object, Object, StudentDTO> {
        @Override
        protected StudentDTO doInBackground(Object... params) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            StudentDTO returns = template.postForObject(URL.SAVE_STUDENT ,student, StudentDTO.class);
            return returns;
        }

        @Override
        protected void onPostExecute(StudentDTO studentDTO) {
            super.onPostExecute(studentDTO);
            finish();
        }

    }

    private void pupulateEdits(StudentDTO studentDTO) {
        studentName.setText(studentDTO.getStudentName());
        formOfAducation.setText(studentDTO.getFormOfAducation());
        studentPhone.setText(studentDTO.getStudentPhone()) ;
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        birthday.setText(simpleDate.format(studentDTO.getBirthday()));
        studentHome.setText(studentDTO.getStudentHome());
        studentLive.setText(studentDTO.getStudentLive());
        motherName.setText(studentDTO.getMotherName());
        motherPhone.setText(studentDTO.getMotherPhone());
        motherWork.setText(studentDTO.getMotherWork());
        fatherName.setText(studentDTO.getFatherName());
        fatherPhone.setText(studentDTO.getFatherPhone());
        fatherWork.setText(studentDTO.getFatherWork());
    }

    private void initEdit() {
        studentName = (EditText) findViewById(R.id.editName);
        formOfAducation = (EditText) findViewById(R.id.editformOfAducation);
        studentPhone = (EditText) findViewById(R.id.editPhone);
        birthday = (EditText) findViewById(R.id.editBirthday);
        studentHome = (EditText) findViewById(R.id.editHome);
        studentLive = (EditText) findViewById(R.id.editLiving);
        motherName = (EditText) findViewById(R.id.editMom);
        motherPhone = (EditText) findViewById(R.id.editMomPhone);
        motherWork = (EditText) findViewById(R.id.editMomWork);
        fatherName = (EditText) findViewById(R.id.editdad);
        fatherPhone = (EditText) findViewById(R.id.edittdadPhone);
        fatherWork = (EditText) findViewById(R.id.edittdadWork);
    }
    private void postChangedStudent() {
        student.setStudentName(String.valueOf(studentName.getText()));
        student.setFormOfAducation(String.valueOf(formOfAducation.getText()));
        student.setStudentPhone(String.valueOf(studentPhone.getText()));
        student.setStudentHome(String.valueOf(studentHome.getText()));
        student.setStudentLive(String.valueOf(studentLive.getText()));
        student.setMotherName(String.valueOf(motherName.getText()));
        student.setMotherPhone(String.valueOf(motherPhone.getText()));
        student.setMotherWork(String.valueOf(motherWork.getText()));
        student.setFatherName(String.valueOf(fatherName.getText()));
        student.setFatherPhone(String.valueOf(fatherPhone.getText()));
        student.setFatherPhone(String.valueOf(fatherWork.getText()));
    }
}
