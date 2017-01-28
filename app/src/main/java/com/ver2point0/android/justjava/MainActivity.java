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

import static com.ver2point0.android.justjava.R.string.quantity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int initialQuantity = 1;
    int pricePerCup = 5;
    int whippedCreamPrice = 1;
    int chocolatePrice = 2;
    int maxQuantity = 100;
    int minQuantity = 1;

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
        intent.setData(Uri.parse(getString(R.string.mail_to))); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_for) + nameInputText);
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
        return pricePerCup * initialQuantity;
    }

    /**
     * Displays the summary of the order.
     */
    private String createOrderSummary(String customerName, boolean hasWhippedCream, boolean hasChocolate, int orderPrice) {
        return getString(R.string.name) + customerName +
                getString(R.string.add_whipped_cream) + hasWhippedCream +
                getString(R.string.add_chocolate) + hasChocolate +
                getString(R.string.number_of_cups) + quantity +
                getString(R.string.total_price) + orderPrice +
                getString(R.string.thank_you);
    }

    public void increment(View view) {
        if (initialQuantity == maxQuantity) {
            // show error message a toast
            Toast.makeText(this, R.string.more_than_100_cups, Toast.LENGTH_SHORT).show();
            // exit method early because there is nothing to do
            return;
        }
        initialQuantity += 1;
        displayQuantity(initialQuantity);
    }

    public void decrement(View view) {
        if (initialQuantity == minQuantity) {
            // show error message a toast
            Toast.makeText(this, R.string.less_than_1_cup, Toast.LENGTH_SHORT).show();
            // exit method early because there is nothing to do
            return;
        }
        initialQuantity -= 1;
        displayQuantity(initialQuantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}