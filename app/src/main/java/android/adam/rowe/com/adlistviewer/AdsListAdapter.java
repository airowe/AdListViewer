package android.adam.rowe.com.adlistviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

/**
 * Created by ARo on 11/25/2016.
 */

public class AdsListAdapter extends ArrayAdapter<Ad>
{
    private List<Ad> adsList;

    public AdsListAdapter(Context context, List<Ad> adsList)
    {
        super(context, R.layout.ad_item);
        this.adsList = adsList;
    }

    private class ViewHolder
    {
        ImageView imageView;
        TextView adNameTextView;
        TextView adRatingTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;

        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.ad_item,null);

            holder = new ViewHolder();
            holder.adNameTextView = (TextView) convertView.findViewById(R.id.ad_text);
            holder.adRatingTextView = (TextView) convertView.findViewById(R.id.ad_rating);
            holder.imageView = (ImageView) convertView.findViewById(R.id.ad_thumbnail);

            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        Ad ad = adsList.get(position);
        if(ad != null)
        {
            holder.adNameTextView.setText(ad.productName);
            holder.adRatingTextView.setText(String.valueOf(ad.rating));

            Glide
                .with(getContext())
                .load(ad.productThumbnail)
                .into(holder.imageView);
        }

        return convertView;
    }

    @Override
    public int getCount() { return adsList.size(); }
}
