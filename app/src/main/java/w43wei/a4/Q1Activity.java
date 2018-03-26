package w43wei.a4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class Q1Activity extends AppCompatActivity implements Observer{

    int code=1;
    Model model;

    private Button previous;
    private Button next;
    private Button logout;

    private TextView name;
    private TextView qIndex;

    private RadioButton rba;
    private RadioButton rbb;
    private RadioButton rbc;
    private RadioButton rbd;

    public void setRB(){
        rba=(RadioButton)findViewById(R.id.q1a);
        rbb=(RadioButton)findViewById(R.id.q1b);
        rbc=(RadioButton)findViewById(R.id.q1c);
        rbd=(RadioButton)findViewById(R.id.q1d);
    }

    public char getAnswer(){
        if(rba.isChecked()) return 'a';
        if(rbb.isChecked()) return 'b';
        if(rbc.isChecked()) return 'c';
        if(rbd.isChecked()) return 'd';
        return 'x';
    }

    public boolean isLast(){
        return code==model.getQuestionNum();
    }

    public void setComponents(){
        if(code==1) previous.setEnabled(false);
        if(isLast()){
            next.setText("Finish");
            //next.setEnabled(false);
        }else {
            next.setText("Next");
        }

        String index;
        index=code+"/"+model.getQuestionNum();

        qIndex.setText(index);

        name.setText(model.name);

        switch (model.selections[0][0]){
            case 'a':
                rba.setChecked(true);
                break;

            case 'b':
                rbb.setChecked(true);
                break;

            case 'c':
                rbc.setChecked(true);
                break;

            case 'd':
                rbd.setChecked(true);
                break;

            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1);

        model=Model.getInstance();

        previous=(Button)findViewById(R.id.q1PreButton);
        next=(Button)findViewById(R.id.q1NextButton);
        logout=(Button)findViewById(R.id.q1Logout);

        name=(TextView)findViewById(R.id.nameField);
        qIndex=(TextView)findViewById(R.id.q1Index);

        setRB();

        setComponents();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Logout!");
                model.reset();
                startActivity(new Intent(Q1Activity.this,
                        MainActivity.class));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model.selections[0][0]=getAnswer();
                if(isLast()){
                    startActivity(new Intent(Q1Activity.this,
                            ResultActivity.class));
                }else {
                    model.notifyObservers();
                    startActivity(new Intent(Q1Activity.this,
                            Q2Activity.class));
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.selections[0][0]=getAnswer();
                model.notifyObservers();
                startActivity(new Intent(Q1Activity.this,
                        Q1Activity.class));
            }
        });

    }

    @Override
    public void update(Observable observable, Object o) {
        name.setText(model.name);
    }
}
