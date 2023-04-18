package com.example.cardflipper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class CardFlipActivity extends FragmentActivity {

    private boolean showingBack;
    RelativeLayout flip;

    //* A fragment representing the Front of the card.

    public static class CardFrontFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_front, container, false);
        }

    }

    /**
     * A fragment representing the back of the card.
     */

    public static class CardBackFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_back, container, false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }

        flip = findViewById(R.id.flip);
        flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });

    }

    /// Flip Method is to change the view
    private void flipCard() {
        if (showingBack) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.card_flip_right_in,
                            R.anim.card_flip_right_out,
                            R.anim.card_flip_left_in,
                            R.anim.card_flip_left_out)
                    .replace(R.id.container, new CardFrontFragment())
                    .addToBackStack(null)
                    .commit();
            showingBack = false;
            //flip.setBackgroundResource(R.drawable.dark);
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.card_flip_left_in,
                            R.anim.card_flip_left_out,
                            R.anim.card_flip_right_in,
                            R.anim.card_flip_right_out
                    )
                    .replace(R.id.container, new CardBackFragment())
                    .addToBackStack(null)
                    .commit();
            showingBack = true;
            //flip.setBackgroundResource(R.drawable.button);
        }
    }
}

