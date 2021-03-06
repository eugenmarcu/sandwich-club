package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView alsoKnownAsTextView;
    TextView placeOforiginTextView;
    TextView descriptionTextView;
    TextView ingredientsTextView;

    Sandwich sandwich;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        alsoKnownAsTextView = findViewById(R.id.also_known_tv);
        placeOforiginTextView = findViewById(R.id.origin_tv);
        descriptionTextView = findViewById(R.id.description_tv);
        ingredientsTextView = findViewById(R.id.ingredients_tv);

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
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        placeOforiginTextView.setText(sandwich.getPlaceOfOrigin());
        descriptionTextView.setText(sandwich.getDescription());
        List<String> ingredientsList = sandwich.getIngredients();
        String ingredientsString = "";
        int i;
        for (i = 0; i < ingredientsList.size(); i++) {
            ingredientsString += ingredientsList.get(i) + "\n";
        }
        ingredientsTextView.setText(ingredientsString);
        String alsoKnownAsString = "";
        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();
        for (i = 0; i < alsoKnownAsList.size(); i++) {
            alsoKnownAsString += alsoKnownAsList.get(i) + "\n";
        }
        alsoKnownAsTextView.setText(alsoKnownAsString);
    }
}
