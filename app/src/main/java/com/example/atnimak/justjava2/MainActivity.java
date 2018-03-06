package com.example.atnimak.justjava2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 0;
    int price = 5;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //display(quantity);
        if(!isWhippedCream()){
            displayPrice("Name: Antonov Maxim\n"+"Quantity: "+quantity+"\nTotal: "+quantity *price+"$\n"+"Thank you!");
        }else {
            displayPrice("Name: Antonov Maxim\n"+"Quantity: "+quantity+"\nToppings: whipped cream"+"\nTotal: "+quantity *price+"$\n"+"Thank you!");
        }

       // displayPrice(quantity *price);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void decrementCoffee(View view){
        if(quantity>0) quantity--;
        display(quantity);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayPrice(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    public void incrementCoffee(View view) {
        quantity++;
        display(quantity);
    }

    public boolean isWhippedCream() {
        CheckBox checkBox = findViewById(R.id.checkBox);
        return checkBox.isChecked();
    }
}