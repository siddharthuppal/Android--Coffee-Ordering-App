
package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity =2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolte_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();


        int price=calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage=createOrderSummary(price, hasWhippedCream, hasChocolate, name);


        displayMessage(priceMessage);
    }


    public void mailOrder(View view) {

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolte_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();


        int price=calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage=createOrderSummary(price, hasWhippedCream, hasChocolate, name);

        String[] st={"sid_uppal@hotmail.com"};

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, st);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order For " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }



    }



    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if(quantity<100){quantity=quantity+1;}
        display(quantity);
    }


    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity>0){quantity=quantity-1;}
        display(quantity);
    }

    private int calculatePrice(boolean addWhippedCream , boolean addChocolate){
        int basePrice = 5;

        if(addWhippedCream){
            basePrice = basePrice +1;
        }

        if(addChocolate){
            basePrice = basePrice +2;
        }

        return quantity*basePrice;
        }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name){
        String priceMessage = "Name: "+name;
        priceMessage += "\nAdd Whipped Cream?" + addWhippedCream;
        priceMessage += "\nAdd Chocolate?" + addChocolate;
        priceMessage += "\nQuantity: "+quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }




    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSammaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSammaryTextView.setText(message);
    }

}