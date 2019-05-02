package com.example.sqllite49;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText inputText;
    TextView resultText;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        inputText = findViewById(R.id.input_text);
        resultText = findViewById(R.id.result_text);
        dbHandler = new MyDBHandler(this, null, null,1);
        // NB public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        printDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Print the database contents
    public void printDatabase()
    {
        // fetch the db contents as a string
        String dbString = dbHandler.databaseToString();
        resultText.setText(dbString);
        inputText.setText("");      // reset input box from current use, ready for next use
    }

    // Add a product record to the database
    public void addButtonClicked(View view)     // NB remember the View view parameter
    {
        Products product = new Products(inputText.getText().toString());
        dbHandler.addProduct(product);
        printDatabase();        // show resulting db contents
    }

    public void deleteButtonClicked(View view)
    {
        String strProduct = inputText.getText().toString();
        dbHandler.deleteProduct(strProduct);
        printDatabase();        // show resulting db contents
    }
}
