package inc.smart.solutions.dismas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import inc.smart.solutions.dismas.MainActivity;
import inc.smart.solutions.dismas.R;

public class ScreenSlidePagerAdapter extends PagerAdapter {

    public CardView[] cardViews;
    private String[] images;
    private Context context;

    public ScreenSlidePagerAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
        cardViews = new CardView[images.length];
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NotNull View container, int position, @NotNull Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @NotNull
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slide_screen, container, false);

        ImageView ivImage = view.findViewById(R.id.ivImage);
        CardView cardView = view.findViewById(R.id.cardView);
        cardViews[position] = cardView;

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).setVisibilityGone();
            }
        });

        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).openPlayStore();
            }
        });

        Glide.with(context)
                .load(images[position])
                .placeholder(R.drawable.pre_loader)
                .into(ivImage);

        container.addView(view);
        return view;
    }


}