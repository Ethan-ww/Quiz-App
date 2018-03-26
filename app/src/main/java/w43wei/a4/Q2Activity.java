package w43wei.a4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class Q2Activity extends AppCompatActivity implements Observer{

    int code=2;
    Model model;

    private Button previous;
    private Button next;
    private Button logout;

    private TextView name;
    private TextView qIndex;

    private CheckBox q2a;
    private CheckBox q2b;
    private CheckBox q2c;
    private CheckBox q2d;

    int numOfChecked=0;
//    public int checkedNum(){
//        int num=0;
//        if(q2a.isChecked()) num++;
//        if(q2b.isChecked()) num++;
//        if(q2c.isChecked()) num++;
//        if(q2d.isChecked()) num++;
//
//        return num;
//    }



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

        switch (model.selections[1][0]){
            case 'a':
                q2a.setChecked(true);
                break;

            case 'b':
                q2b.setChecked(true);
                break;

            case 'c':
                q2c.setChecked(true);
                break;

            case 'd':
                q2d.setChecked(true);
                break;

            default:
                break;
        }

        switch (model.selections[1][1]){
            case 'a':
                q2a.setChecked(true);
                break;

            case 'b':
                q2b.setChecked(true);
                break;

            case 'c':
                q2c.setChecked(true);
                break;

            case 'd':
                q2d.setChecked(true);
                break;

            default:
                break;
        }

    }

    public String getAnswer(){
        String ans=new String();
        if(q2a.isChecked()) ans=ans+'a';
        if(q2b.isChecked()) ans=ans+'b';
        if(q2c.isChecked()) ans=ans+'c';
        if(q2d.isChecked()) ans=ans+'d';

        if(ans.length()==0) return "xx";
        if(ans.length()==1) return ans+"x";

        if(ans.charAt(0)<ans.charAt(1)){
            return ans;
        }else{
            String temp=new String();
            temp=temp+ans.charAt(1);
            temp=temp+ans.charAt(0);
            return temp;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q2);

        model=Model.getInstance();

        previous=(Button)findViewById(R.id.q2PreButton);
        next=(Button)findViewById(R.id.q2NextButton);
        logout=(Button)findViewById(R.id.q2Logout);

        name=(TextView)findViewById(R.id.nameField);
        qIndex=(TextView)findViewById(R.id.q2Index);

        q2a=(CheckBox)findViewById(R.id.q2a);
        q2b=(CheckBox)findViewById(R.id.q2b);
        q2c=(CheckBox)findViewById(R.id.q2c);
        q2d=(CheckBox)findViewById(R.id.q2d);


        setComponents();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Logout!");
                model.reset();
                startActivity(new Intent(Q2Activity.this,
                        MainActivity.class));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.selections[1][0]=getAnswer().charAt(0);
                model.selections[1][1]=getAnswer().charAt(1);
                if(isLast()){
                    startActivity(new Intent(Q2Activity.this,
                            ResultActivity.class));
                }else {
                    model.notifyObservers();
                    startActivity(new Intent(Q2Activity.this,
                            Q3Activity.class));
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model.selections[1][0]=getAnswer().charAt(0);
                model.selections[1][1]=getAnswer().charAt(1);

                model.notifyObservers();
                startActivity(new Intent(Q2Activity.this,
                        Q1Activity.class));
            }
        });

        // checkbox group

        q2a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numOfChecked >= 2) {
                    q2a.setChecked(false);
                } else {
                    if (isChecked) {
                        numOfChecked++;
                    } else {
                        numOfChecked--;
                    }
                }
            }
        });

        q2b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numOfChecked >= 2) {
                    q2b.setChecked(false);
                } else {
                    if (isChecked) {
                        numOfChecked++;
                    } else {
                        numOfChecked--;
                    }
                }
            }
        });

        q2c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numOfChecked >= 2) {
                    q2c.setChecked(false);
                } else {
                    if (isChecked) {
                        numOfChecked++;
                    } else {
                        numOfChecked--;
                    }
                }
            }
        });

        q2d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numOfChecked >= 2) {
                    q2d.setChecked(false);
                } else {
                    if (isChecked) {
                        numOfChecked++;
                    } else {
                        numOfChecked--;
                    }
                }
            }
        });
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
