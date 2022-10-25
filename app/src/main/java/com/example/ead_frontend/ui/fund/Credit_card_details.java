package com.example.ead_frontend.ui.fund;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ead_frontend.MainActivity;
import com.example.ead_frontend.R;
import com.example.ead_frontend.ui.fund.HelperClasses.AdapterCreditCard;
import com.example.ead_frontend.ui.fund.HelperClasses.CreditCardHelper;

import java.util.ArrayList;

public class Credit_card_details extends AppCompatActivity implements AdapterCreditCard.ListItemClickListener{
    RecyclerView creditCardRecycler;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit_card_details);
        //Hooks
        creditCardRecycler = findViewById(R.id.my_recycler);
        phoneRecycler();

    }

    private void phoneRecycler() {
        //All Gradients
        creditCardRecycler.setHasFixedSize(true);
        creditCardRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<CreditCardHelper> phonelocations = new ArrayList<>();
        phonelocations.add(new CreditCardHelper(R.drawable.visa_slide));
        phonelocations.add(new CreditCardHelper(R.drawable.master_slide));
        phonelocations.add(new CreditCardHelper(R.drawable.master2_slide));


        adapter = new AdapterCreditCard(phonelocations,this);
        creditCardRecycler.setAdapter(adapter);

    }

    public void backToHome(View view) {
        Intent intent = new Intent(this,  MainActivity.class);
        ImageButton image = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }

    @Override
    public void onphoneListClick(int clickedItemIndex) {


//            Intent mIntent;
//            switch (clickedItemIndex){
//                case 0: //first item in Recycler view
//                    mIntent  = new Intent(FirstActivity.this, samsung.class);
//                    startActivity(mIntent);
//                    break;
//                case 1: //second item in Recycler view
//                    mIntent = new Intent(FirstActivity.this, vivo.class);
//                    startActivity(mIntent);
//                    break;
//                case 2: //third item in Recycler view
//                    mIntent = new Intent(FirstActivity.this, apple.class);
//                    startActivity(mIntent);
//                    break;
        //              case 3: //third item in Recycler view
//                    mIntent = new Intent(FirstActivity.this, realme.class);
//                    startActivity(mIntent);
//                    break;
//                case 4: //third item in Recycler view
//                    mIntent = new Intent(FirstActivity.this, poco.class);
//                    startActivity(mIntent);
//                    break;
//
//        }


    }
}
