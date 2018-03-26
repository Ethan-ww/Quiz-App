package w43wei.a4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TopicActivity extends AppCompatActivity implements Observer{
    private Button logout;
    private Button load;
    private Spinner spinner;
    private TextView nameField;

    Model model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        model=Model.getInstance();

        logout=(Button)findViewById(R.id.topicLogout);
        load=(Button)findViewById(R.id.topicLoad);
        spinner=(Spinner)findViewById(R.id.spinner);
        nameField=(TextView)findViewById(R.id.nameField);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Logout!");
                model.name=null;
                model.notifyObservers();
                startActivity(new Intent(TopicActivity.this,
                        MainActivity.class));
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i=spinner.getSelectedItemPosition()+1;
                model.setQuestionNum(i);

                System.out.println("Question num: "+model.getQuestionNum());
                System.out.println("Turn to Q1 activity");
                startActivity(new Intent(TopicActivity.this,
                        Q1Activity.class));
            }
        });

        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        nameField.setText(model.name);
    }



    @Override
    public void update(Observable observable, Object o) {
        nameField.setText(model.name);
    }
}
