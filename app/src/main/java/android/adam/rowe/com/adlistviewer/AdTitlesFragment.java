package android.adam.rowe.com.adlistviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ARo on 11/25/2016.
 * AdTitlesFragment used to show Ad data on the main screen in a ListView.
 * The Product Thumbnail is shown on the left side of the LinearLayout with the Product Name and Rating on the right
 */

public class AdTitlesFragment extends Fragment implements AdapterView.OnItemClickListener
{
    private ListView adsListView;

    private AdsListAdapter adsListAdapter;
    private ArrayList<Ad> adsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.ad_titles_layout, container, false);

        adsList = getArguments().getParcelableArrayList("adsList");
        adsListView = (ListView) rootView.findViewById(R.id.ads_list_view);
        adsListView.setOnItemClickListener(this);
        
        populateAdsList();

        return rootView;
    }

    /**
     * Populate Custom ArrayAdapter with the Ads List
     */
    private void populateAdsList()
    {
        adsListAdapter = new AdsListAdapter(getContext(), adsList);
        adsListView.setAdapter(adsListAdapter);
    }

    /**
     * Launch Ad Details Fragment when item is clicked.
     * Pass Parcelable Ad to fragment via Bundle
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        AdDetailsFragment adDetailsFragment = new AdDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ad",adsList.get(position));
        adDetailsFragment.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content,adDetailsFragment,"AdDetails")
                .addToBackStack("AdDetails")
                .commit();
    }
}
