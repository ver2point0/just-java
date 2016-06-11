package com.ver2point0.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrders(View view) {
        int coffeeQuantity = 10;
        display(coffeeQuantity);
        displayPrice(coffeeQuantity * 3);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        int incrementQuantity = 3;
        display(incrementQuantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void decrement(View view) {
        int decrementQuantity = 1;
        display(decrementQuantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}