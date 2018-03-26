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

import javax.xml.transform.Result;

public class Q5Activity extends AppCompatActivity implements Observer{

    int code=5;
    Model model;

    private Button previous;
    private Button next;
    private Button logout;

    private TextView name;
    private TextView qIndex;

    private CheckBox qa;
    private CheckBox qb;
    private CheckBox qc;
    private CheckBox qd;

    int numOfChecked=0;

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

        switch (model.selections[4][0]){
            case 'a':
                qa.setChecked(true);
                break;

            case 'b':
                qb.setChecked(true);
                break;

            case 'c':
                qc.setChecked(true);
                break;

            case 'd':
                qd.setChecked(true);
                break;

            default:
                break;
        }

        switch (model.selections[4][1]){
            case 'a':
                qa.setChecked(true);
                break;

            case 'b':
                qb.setChecked(true);
                break;

            case 'c':
                qc.setChecked(true);
                break;

            case 'd':
                qd.setChecked(true);
                break;

            default:
                break;
        }

    }

    public String getAnswer(){
        String ans=new String();
        if(qa.isChecked()) ans=ans+'a';
        if(qb.isChecked()) ans=ans+'b';
        if(qc.isChecked()) ans=ans+'c';
        if(qd.isChecked()) ans=ans+'d';

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
        setContentView(R.layout.activity_q5);

        model=Model.getInstance();

        previous=(Button)findViewById(R.id.q5PreButton);
        next=(Button)findViewById(R.id.q5NextButton);
        logout=(Button)findViewById(R.id.q5Logout);

        name=(TextView)findViewById(R.id.nameField);
        qIndex=(TextView)findViewById(R.id.q5Index);

        qa=(CheckBox)findViewById(R.id.q5a);
        qb=(CheckBox)findViewById(R.id.q5b);
        qc=(CheckBox)findViewById(R.id.q5c);
        qd=(CheckBox)findViewById(R.id.q5d);


        setComponents();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                System.out.println("Logout!");
                model.reset();
                startActivity(new Intent(Q5Activity.this,
                        MainActivity.class));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.selections[1][0]=getAnswer().charAt(0);
                model.selections[1][1]=getAnswer().charAt(1);

                if(isLast()){
                    startActivity(new Intent(Q5Activity.this,
                            ResultActivity.class));
                }

//                else {
//                    model.notifyObservers();
//                    startActivity(new Intent(Q5Activity.this,
//                            Q5Activity.class));
//                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.selections[1][0]=getAnswer().charAt(0);
                model.selections[1][1]=getAnswer().charAt(1);
                model.notifyObservers();
                startActivity(new Intent(Q5Activity.this,
                        Q4Activity.class));
            }
        });

        // checkbox group

        qa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numOfChecked >= 2) {
                    qa.setChecked(false);
                } else {
                    if (isChecked) {
                        numOfChecked++;
                    } else {
                        numOfChecked--;
                    }
                }
            }
        });

        qb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numOfChecked >= 2) {
                    qb.setChecked(false);
                } else {
                    if (isChecked) {
                        numOfChecked++;
                    } else {
                        numOfChecked--;
                    }
                }
            }
        });

        qc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numOfChecked >= 2) {
                    qc.setChecked(false);
                } else {
                    if (isChecked) {
                        numOfChecked++;
                    } else {
                        numOfChecked--;
                    }
                }
            }
        });

        qd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numOfChecked >= 2) {
                    qd.setChecked(false);
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
