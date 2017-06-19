package com.sadface.tutorapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadface.tutorapp.R;
import com.sadface.tutorapp.adapters.GroupAdapter;
import com.sadface.tutorapp.constr.GroupDTO;
import com.sadface.tutorapp.constr.StudentDTO;
import com.sadface.tutorapp.services.URL;
import com.sadface.tutorapp.utils.ClickListener;
import com.sadface.tutorapp.utils.DividerItemDecoration;
import com.sadface.tutorapp.utils.RecyclerTouchListener;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


public class GroupsFragment extends Fragment {
    private GroupDTO group;
    private RecyclerView recyclerView;
    private GroupAdapter mAdapter;
    private int group_id;
    List<StudentDTO> studentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_groups,
                container, false);
        final Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        Bundle bundle = this.getArguments();
        group_id = bundle.getInt("group_id");

     //   mAdapter = new GroupAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));


       // recyclerView.setAdapter(mAdapter);

        new TutorTask().execute();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context.getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
               StudentDTO student = studentList.get(position);

                Bundle arguments = new Bundle();
                arguments.putInt("StudentID", student.getId_student());

                Fragment fragment = new StudentFragment();
                fragment.setArguments(arguments);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }

            @Override
            public void onLongClick(View view, int position) {
                StudentDTO student = studentList.get(position);
                Intent i = new Intent(Intent.ACTION_DIAL);
                String p = "tel:"+ student.getStudentPhone();
                i.setData(Uri.parse(p));
                view.getContext().startActivity(i);

            }
        }));
          //  prepareMovieData();


     /*   FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        return view;
    }
  /*  private void prepareMovieData() {
        StudentDTO movie = new StudentDTO();
        movie.setGroupName("333");
        movie.setTutorName("3212123");
        movieList.add(movie);



        mAdapter.notifyDataSetChanged();
    }
*/
      private class TutorTask extends AsyncTask<Object, Object, List<StudentDTO>> {

        @Override
        protected List<StudentDTO> doInBackground(Object... params) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            StudentDTO[] studArray =template.getForObject(URL.GET_STUDENT_BY_GRUOUPID+group_id, StudentDTO[].class );
            studentList= Arrays.asList(studArray);
            return studentList;
        }

      @Override
      protected void onPostExecute(List<StudentDTO> studentDTO) {
          super.onPostExecute(studentDTO);
          mAdapter = new GroupAdapter(studentList);
          recyclerView.setAdapter(mAdapter);
          mAdapter.notifyDataSetChanged();
      }
  }
}

