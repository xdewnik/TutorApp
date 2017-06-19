package com.sadface.tutorapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sadface.tutorapp.R;
import com.sadface.tutorapp.activities.ChangeStudentActivity;
import com.sadface.tutorapp.activities.LoginActivity;
import com.sadface.tutorapp.activities.MainActivity;
import com.sadface.tutorapp.constr.GroupDTO;
import com.sadface.tutorapp.constr.StudentDTO;
import com.sadface.tutorapp.services.URL;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SADFACE on 24.05.2017.
 */

public class StudentFragment extends Fragment {
    private View view;
    private StudentDTO student;
    private int student_id;
    private TextView studentName,formOfAducation,studentPhone,birthday,studentHome,
            studentLive,motherName,motherPhone,motherWork,fatherName,fatherPhone, fatherWork;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_student, container, false);

        initTextView();


        Context context = view.getContext();

        Bundle bundle = this.getArguments();
        student_id = bundle.getInt("StudentID");


        new StudentTask().execute();


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.studentFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),
                        ChangeStudentActivity.class);
                intent.putExtra("studID", student_id);
                startActivity(intent);
            }
        });

        return view;
    }

    private class StudentTask extends AsyncTask<Object, Object, StudentDTO> {


        @Override
        protected StudentDTO doInBackground(Object... params) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            student =template.getForObject(URL.GET_STUDENT_BY_ID+student_id, StudentDTO.class );
            return student;
        }

        @Override
        protected void onPostExecute(StudentDTO studentDTO) {
            super.onPostExecute(studentDTO);
            populateStudents(studentDTO);
        }

    }

    private void populateStudents(StudentDTO studentDTO) {
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

    private void initTextView() {
        studentName = (TextView) view.findViewById(R.id.studentName);
        formOfAducation = (TextView) view.findViewById(R.id.studentformOfAducation);
        studentPhone = (TextView)view.findViewById(R.id.studentPhone);
        birthday = (TextView) view.findViewById(R.id.studentBirthday);
        studentHome = (TextView) view.findViewById(R.id.studentHome);
        studentLive = (TextView) view.findViewById(R.id.studentLiving);
        motherName = (TextView) view.findViewById(R.id.studentMom);
        motherPhone = (TextView) view.findViewById(R.id.studentMomPhone);
        motherWork = (TextView) view.findViewById(R.id.studentMomWork);
        fatherName = (TextView) view.findViewById(R.id.studentdad);
        fatherPhone = (TextView) view.findViewById(R.id.studentdadPhone);
        fatherWork = (TextView) view.findViewById(R.id.studentdadWork);

    }

}
