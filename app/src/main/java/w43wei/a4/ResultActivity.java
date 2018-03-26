package w43wei.a4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class ResultActivity extends AppCompatActivity implements Observer{

    Model model;

    TextView score;
    TextView name;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        model=Model.getInstance();

        score=(TextView) findViewById(R.id.scoreText);
        name=(TextView) findViewById(R.id.nameField);
        logout=(Button) findViewById(R.id.endLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                System.out.println("Logout!");
                model.reset();
                startActivity(new Intent(ResultActivity.this,
                        MainActivity.class));
            }
        });

        score.setText(model.calScore()+"/"+model.getQuestionNum());
        name.setText(model.name);


    }

    @Override
    public void update(Observable observable, Object o) {
        score.setText(model.calScore());
        name.setText(model.name);
    }
}
