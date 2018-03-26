package w43wei.a4;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer{

    private Button nextButton;
    private EditText nameText;


    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model=Model.getInstance();
        nextButton=(Button)findViewById(R.id.mainNext);
        nameText=(EditText)findViewById(R.id.mainText);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.name=nameText.getText().toString();
                //model.notifyObservers();

                System.out.println("User name: "+model.name);
                System.out.println("Turn to topic activity");
                startActivity(new Intent(MainActivity.this,
                        TopicActivity.class));
            }
        });

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
