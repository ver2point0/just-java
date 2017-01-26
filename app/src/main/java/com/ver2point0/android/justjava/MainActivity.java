package com.ver2point0.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 1;
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

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + nameInputText);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(nameInputText, whippedCreamState, chocolateState, calculatePrice(whippedCreamState, chocolateState)));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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
        if (quantity == 100) {
            // show error message a toast
            Toast.makeText(this, "You can't order more than 100 cups of coffee", Toast.LENGTH_SHORT).show();
            // exit method early because there is nothing to do
            return;
        }
        quantity += 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            // show error message a toast
            Toast.makeText(this, "You can't order less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
            // exit method early because there is nothing to do
            return;
        }
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

}