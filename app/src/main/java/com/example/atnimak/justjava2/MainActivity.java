package com.example.atnimak.justjava2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        if (configName().trim().equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_havetoWriteAname), Toast.LENGTH_SHORT).show();
        } else {
            int priceWithToppings = price;
            String name = configName();
            String totalOrder = getString(R.string.name, name);
            totalOrder += getString(R.string.quantity_sum, quantity);

            String toppings = "";
            if (isWhippedCream()) {
                toppings += getString(R.string.whipped_cream_sum);
                priceWithToppings++;
            }

            if (isChokolate()) {
                if (isWhippedCream()) {
                    toppings += getString(R.string.andChocolate_sum);
                    priceWithToppings += 2;
                } else {
                    toppings += getString(R.string.chocolate_sum);
                    priceWithToppings += 2;
                }
            }

            if (!isChokolate() && !isWhippedCream()) {
                toppings = getString(R.string.notoppings);
            }

            // totalOrder += getString(R.string.total_sum, quantity * priceWithToppings);
            totalOrder += getString(R.string.total_sum, NumberFormat.getCurrencyInstance().format(quantity * priceWithToppings));
            totalOrder += getString(R.string.toppings_sum, toppings);
            totalOrder += getString(R.string.thankU);

            String[] adresses = {getString(R.string.orderEmail)};
            composeEmail(adresses, getString(R.string.justCoffeeFor) + name, totalOrder);
            // displayPrice(totalOrder);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void decrementCoffee(View view) {
        if (quantity > 1) {
            quantity--;
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.toast_lessOne), Toast.LENGTH_SHORT);
            toast.show();
        }
        display(quantity);
    }

    // We need not this code any more, we use an e-mail intent.
    /*private void displayPrice(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }*/

    public void incrementCoffee(View view) {
        if (quantity < 100) {
            quantity++;
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.toast_more100), Toast.LENGTH_SHORT);
            toast.show();
        }
        display(quantity);
    }

    public boolean isWhippedCream() {
        CheckBox checkBox = findViewById(R.id.whippedCream);
        return checkBox.isChecked();
    }

    public boolean isChokolate() {
        CheckBox checkBox = findViewById(R.id.chocolate);
        return checkBox.isChecked();
    }

    public String configName() {
        EditText editText = (EditText) findViewById(R.id.editName);
        String name = editText.getText().toString();
        return name;
    }

    public void composeEmail(String[] addresses, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}