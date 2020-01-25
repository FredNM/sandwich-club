package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    ImageView imageIv;
    TextView originTv;
    TextView titleOriginTv;
    TextView descriptionTv;
    TextView titleDescriptionTv;
    TextView ingredientsTv;
    TextView titleIngredientsTv;
    TextView alsoKnownTv;
    TextView titleAlsoKnownTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageIv = (ImageView) findViewById(R.id.image_iv);
        originTv = (TextView) findViewById(R.id.origin_tv);
        descriptionTv = (TextView) findViewById(R.id.description_tv);
        ingredientsTv = (TextView) findViewById(R.id.ingredients_tv);
        alsoKnownTv = (TextView) findViewById(R.id.also_known_tv);

        titleOriginTv = (TextView) findViewById(R.id.title_origin_tv);
        titleDescriptionTv = (TextView) findViewById(R.id.title_description_tv);
        titleIngredientsTv = (TextView) findViewById(R.id.title_ingredients_tv);
        titleAlsoKnownTv = (TextView) findViewById(R.id.title_also_known_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        try{
            Sandwich sandwich = JsonUtils.parseSandwichJson(json);
            if (sandwich == null) {
                // Sandwich data unavailable
                closeOnError();
                return;
            }

            populateUI(sandwich);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        setTitle(sandwich.getMainName());

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(imageIv);

        utilsTextView(originTv, titleOriginTv, sandwich.getPlaceOfOrigin());
        utilsTextView(descriptionTv, titleDescriptionTv, sandwich.getDescription());


        //ingredientsTv ;
        // alsoKnownTv ;
    }

    private void utilsTextView(TextView textView, TextView titleTextView, String string) {
        if (string != null) {
            textView.setText(string);
        } else {
            textView.setVisibility(View.INVISIBLE);
            titleTextView.setVisibility(View.INVISIBLE);
        }
    }
}
