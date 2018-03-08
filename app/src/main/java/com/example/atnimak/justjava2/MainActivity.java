package com.example.atnimak.justjava2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
        int priceWithToppings = price;
        String totalOrder = "Name: " + configName() + "\n";
        totalOrder += "Quantity: " + quantity + "\n";


        String toppings = "";
        if (isWhippedCream()) {
            toppings += "whipped cream";
            priceWithToppings++;
        }

        if (isChokolate()) {
            if (isWhippedCream()) {
                toppings += " and chocolate";
                priceWithToppings += 2;
            } else {
                toppings += "chocolate";
                priceWithToppings += 2;
            }
        }

        if (!isChokolate() && !isWhippedCream()) {
            toppings = "no toppings";
        }

        totalOrder += "Total: " + quantity * priceWithToppings + "\n";
        totalOrder += "Toppings: " + toppings + "\n";
        totalOrder += "Thank you!";

        displayPrice(totalOrder);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void decrementCoffee(View view) {
        if (quantity > 1) quantity--;
        display(quantity);
    }

    private void displayPrice(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    public void incrementCoffee(View view) {
        if (quantity <100 ) quantity++;
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
        if (name.equals("")) {
            name = "Заказчик";
        }
        return name;
    }
}