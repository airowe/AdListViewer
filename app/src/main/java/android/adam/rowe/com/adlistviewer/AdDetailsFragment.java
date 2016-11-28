package android.adam.rowe.com.adlistviewer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Field;

/**
 * Created by ARo on 11/25/2016.
 */

public class AdDetailsFragment extends Fragment
{
    public Ad ad;
    private TableLayout tableLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ScrollView rootView = (ScrollView) inflater.inflate(R.layout.ad_details_layout, container, false);
        tableLayout = (TableLayout) rootView.findViewById(R.id.details_table);

        ad = getArguments().getParcelable("ad");

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        populateTable();
    }

    private void populateTable()
    {
        TableRow.LayoutParams firstColumnLayoutParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.25f);
        TableRow.LayoutParams secondColumnLayoutParams = new TableRow.LayoutParams(1, TableRow.LayoutParams.WRAP_CONTENT, 0.75f);
        Class<?> objClass = ad.getClass();

        Field[] fields = objClass.getFields();
        for(Field field : fields)
        {
            String name = field.getName();
            if(!name.equals("$change") && !name.equals("serialVersionUID") && !name.equals("CONTENTS_FILE_DESCRIPTOR")
                    && !name.contains("PARCELABLE") && !name.equals("CREATOR"))
            {
                TableRow tableRow = new TableRow(getContext());
                tableRow.setLayoutParams(firstColumnLayoutParams);
                TextView detailText = new TextView(getContext());
                detailText.setText(name);
                detailText.setGravity(Gravity.CENTER);
                tableRow.addView(detailText);
                try
                {
                    Object value = field.get(ad);
                    if(name.equals("averageRatingImageURL") || name.equals("productThumbnail"))
                    {
                        ImageView imageView = new ImageView(getContext());

                        Glide
                                .with(getContext())
                                .load(value)
                                .into(imageView);

                        tableRow.addView(imageView, firstColumnLayoutParams);
                    }
                    else
                    {
                        TextView detailValueView = new TextView(getContext());
                        detailValueView.setText(String.valueOf(value));
                        detailValueView.setSingleLine(false);
                        detailValueView.setGravity(Gravity.CENTER);

                        tableRow.addView(detailValueView, secondColumnLayoutParams);
                    }
                }
                catch(IllegalAccessException exc)
                {
                    Log.e("AdDetails",exc.getMessage());
                }

                tableLayout.addView(tableRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }

        }
    }
}
