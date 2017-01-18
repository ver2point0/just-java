package com.ver2point0.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 2;
    int pricePerCup = 5;
    int whippedCreamPrice = 1;
    int chocolatePrice = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        // get customer's name
        EditText nameEditText = (EditText) findViewById(R.id.name_field);
        String nameInputText = nameEditText.getText().toString();

        // figure out if user wants whipped cream
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean whippedCreamState = whippedCreamCheckBox.isChecked();

        // figure out if user want chocolate
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean chocolateState = chocolateCheckBox.isChecked();

        displayMessage(createOrderSummary(nameInputText, whippedCreamState, chocolateState, calculatePrice(whippedCreamState, chocolateState)));
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice(boolean whippedCreamAdded, boolean chocolateAdded) {
        // add $1 if user wants whipped cream
        if (whippedCreamAdded) {
            pricePerCup += whippedCreamPrice;
        }

        // add $2 if user wants chocolate
        if (chocolateAdded) {
            pricePerCup += chocolatePrice;
        }

        // calculate total order price by multiplying by quantity
        return pricePerCup * quantity;
    }

    /**
     * Displays the summary of the order.
     */
    private String createOrderSummary(String customerName, boolean hasWhippedCream, boolean hasChocolate, int orderPrice) {
        return "Name: " + customerName +
                "\nAdd whipped cream? " + hasWhippedCream +
                "\nAdd chocolate? " + hasChocolate +
                "\nQuantity: " + quantity +
                "\nTotal: $" + orderPrice +
                "\nThank you!";
    }

    public void increment(View view) {
        quantity += 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity -= 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}