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

public class Q4Activity extends AppCompatActivity implements Observer{

    int code=4;
    Model model;

    private Button previous;
    private Button next;
    private Button logout;

    private RadioButton rba;
    private RadioButton rbb;
    private RadioButton rbc;
    private RadioButton rbd;

    public void setRB(){
        rba=(RadioButton)findViewById(R.id.q4a);
        rbb=(RadioButton)findViewById(R.id.q4b);
        rbc=(RadioButton)findViewById(R.id.q4c);
        rbd=(RadioButton)findViewById(R.id.q4d);
    }

    public char getAnswer(){
        if(rba.isChecked()) return 'a';
        if(rbb.isChecked()) return 'b';
        if(rbc.isChecked()) return 'c';
        if(rbd.isChecked()) return 'd';
        return 'x';
    }

    private TextView name;
    private TextView qIndex;

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

        switch (model.selections[3][0]){
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
        setContentView(R.layout.activity_q4);

        model=Model.getInstance();

        previous=(Button)findViewById(R.id.q4PreButton);
        next=(Button)findViewById(R.id.q4NextButton);
        logout=(Button)findViewById(R.id.q4Logout);

        name=(TextView)findViewById(R.id.nameField);
        qIndex=(TextView)findViewById(R.id.q4Index);

        setRB();

        setComponents();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Logout!");
                model.reset();
                startActivity(new Intent(Q4Activity.this,
                        MainActivity.class));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.selections[3][0]=getAnswer();

                if(isLast()){
                    startActivity(new Intent(Q4Activity.this,
                            ResultActivity.class));
                }else {
                    model.notifyObservers();
                    startActivity(new Intent(Q4Activity.this,
                            Q5Activity.class));
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.selections[3][0]=getAnswer();

                model.notifyObservers();
                startActivity(new Intent(Q4Activity.this,
                        Q3Activity.class));
            }
        });
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
