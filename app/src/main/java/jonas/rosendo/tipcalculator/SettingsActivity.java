package jonas.rosendo.tipcalculator;

import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    CheckBox cb_darktheme;
    ConstraintLayout cv_constraint, layout_settings;
    CardView cv_theme;
    TextView textView_dev, textView_jonas, textView_theme, textView_dark, textView_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setComponents();
    }


    private void setComponents()
    {
        cb_darktheme= findViewById(R.id.cb_darkTheme);
        cv_theme = findViewById(R.id.cv_theme);
        cv_constraint = findViewById(R.id.cv_constraint);
        layout_settings = findViewById(R.id.Layout_settings);
        textView_dark = findViewById(R.id.textView_dark);
        textView_dev = findViewById(R.id.textView_dev);
        textView_jonas = findViewById(R.id.textView_jonas);
        textView_theme = findViewById(R.id.textView_theme);
    }
}
