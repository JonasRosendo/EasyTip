package jonas.rosendo.tipcalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class TipActivity extends AppCompatActivity {

    private EditText editText_billAmount, editText_numPeople, editText_other;
    private RadioButton radioButton_15, radioButton_20, radioButton_other;
    private FloatingActionButton actionButton;
    private TextView textView_tipAmount, textView_totalAmount, textView_valuePerson;
    double billAmount =0 , percentage=0, valuePerson=0, totalAmount=0, tipAmount=0;
    int numPeople=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        //inicia componentes
        //configura edittext para desativado
        //chama método calculate() para fazer o primeiro calculo com os valores vazios

        setComponents();
        editText_other.setEnabled(false);
        calculate();

        /*
        * a cada alteração de texto nos edittext's é chamado o método calculate para recalcular as alterações
        * */
        editText_billAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculate();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        editText_numPeople.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculate();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editText_other.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculate();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /*
        * A cada alteração nos radio buttons 15 e 20, é desativado o campo de texto other e seu valor
        * é resetado, e o método calculate é chamado para recalcular o valor final.
        */
        radioButton_15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editText_other.setEnabled(false);
                editText_other.getText().clear();
                calculate();
            }
        });

        radioButton_20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editText_other.setEnabled(false);
                editText_other.getText().clear();
                calculate();
            }
        });

        /*
        * Quando o usuário selecionar o radio button other, o campo de texto other é ativado para
        * que o usuário possa colocar o valor que desejar.
        * */
        radioButton_other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                calculate();
            }
        });

        //acessa página de configurações
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    //conecta os objetos criados em java com as views no xml
    private void setComponents()
    {
        editText_billAmount = findViewById(R.id.editText_billAmount);
        editText_numPeople = findViewById(R.id.editText_NumPeople);
        editText_other = findViewById(R.id.editText_perc);

        radioButton_15 = findViewById(R.id.RadioButton_15);
        radioButton_20 = findViewById(R.id.RadioButton_20);
        radioButton_other = findViewById(R.id.RadioButton_other);

        textView_tipAmount = findViewById(R.id.textView_tipAmount);
        textView_totalAmount = findViewById(R.id.textView_totalAmount);
        textView_valuePerson = findViewById(R.id.textView_valuePerson);

        actionButton = findViewById(R.id.floatingActionButton);
    }

    //efetua o calculo total da gorgeta, esse método é chamado a cada alteração nos edittext's
    @SuppressLint("SetTextI18n")
    private void calculate() {


        if(radioButton_15.isChecked())
        {
            percentage = 15;
        }
        else if(radioButton_20.isChecked())
        {
            percentage = 20;
        }
        else if(radioButton_other.isChecked())
        {
            editText_other.setEnabled(true);
            editText_other.setVisibility(View.VISIBLE);

            if (editText_other.length() == 0)
            {
                percentage = 0;
            }
            else
            {
                percentage = Double.parseDouble(editText_other.getText().toString());
            }
        }

        if(editText_numPeople.length() == 0)
        {
            numPeople = 1;
        }
        else
        {
            numPeople = Integer.parseInt(editText_numPeople.getText().toString());
        }

        if(editText_billAmount.length() == 0)
        {
            billAmount = 0;
        }
        else
        {
            billAmount = Double.parseDouble(editText_billAmount.getText().toString());
        }

        tipAmount = billAmount * percentage/100;
        totalAmount = billAmount + tipAmount;
        valuePerson = totalAmount / numPeople;

        DecimalFormat decimalFormat = new DecimalFormat("##.##");

        textView_tipAmount.setText("$ " + decimalFormat.format(tipAmount));
        textView_totalAmount.setText("$ " + decimalFormat.format(totalAmount));
        textView_valuePerson.setText("$ " + decimalFormat.format(valuePerson));
    }
}
