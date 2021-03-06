package com.sebas.sysfishapp.videofeed.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sebas.sysfishapp.videofeed.R;

/**
 * Created by sebastiandeira on 25/2/18.
 */

public class ContentReview extends LinearLayout {

    private int score;
    public ContentReview(Context context) {
        this(context, null);
    }

    public ContentReview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(final Context context) {
        setOrientation(HORIZONTAL);
        final LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        for (int i = 0; i < 5; i++) {
            final ImageView imageView = new ImageView(context);
            final LayoutParams imageLayoutParams = new LayoutParams(50,
                    50);
            imageView.setLayoutParams(imageLayoutParams);
            addView(imageView);
        }
    }

    /**
     * Set the score review to show the stars
     * @param score to show the stars
     */
    public void setReview(final int score) {
        this.score = score;
        for (int i = 0; i < getChildCount(); i++) {
            final ImageView image = (ImageView) getChildAt(i);
            final int starPosition = i + 1;
            if (score >= starPosition) {
                image.setImageResource(R.drawable.star_enable);
            } else {
                image.setImageResource(R.drawable.star_disable);
            }
        }
    }

    /**
     * Get the score review
     * @return the score
     */
    public int getScore() {
        return score;
    }
}
