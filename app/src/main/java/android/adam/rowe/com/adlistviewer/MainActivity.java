package android.adam.rowe.com.adlistviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.SaxAsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * MainActivity for the Application. Parses the XML and displays the initial fragment in the main content area
 */
public class MainActivity extends AppCompatActivity
{
    private static final String adsUrl = "http://ads.appia.com/getAds?id=236&password=OVUJ1DJN&siteId=4288&deviceId=4230&sessionId=techtestsession&totalCampaignsRequested=10&lname=rowe";
    private AsyncHttpClient asyncHttpClient;
    private DataHandler dataHandler;
    private SecondaryDataHandler secondaryDataHandler;
    private ArrayList<Ad> adsList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asyncHttpClient = new AsyncHttpClient();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        dataHandler = new DataHandler(); // init handler instance
        getXML();
    }

    /**
     * Asynchronous call to get XML data from server and parse it into a List using a SAX parser
     * Once the first parse is complete and the main data has been parsed, a second call is made to
     * parse the rest of the data for the second screen while the first screen is shown
     */
    private void getXML()
    {
        asyncHttpClient.get(adsUrl, new SaxAsyncHttpResponseHandler<DataHandler>(dataHandler)
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, DataHandler t)
            {
                adsList = dataHandler.getAdsList();

                secondaryDataHandler = new SecondaryDataHandler(adsList);
                getSecondaryXMLData();

                showTitlesFragment();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, DataHandler t)
            {
                adsList = new ArrayList<>();
            }
        });
    }

    /**
     * Instantiate a new AdTitlesFragment, pass a ParcelableArrayList of the Ads to it via Bundle
     */
    private void showTitlesFragment()
    {
        AdTitlesFragment adTitlesFragment = new AdTitlesFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("adsList",adsList);
        adTitlesFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content,adTitlesFragment,"AdTitles")
                .addToBackStack("AdTitles")
                .commit();
    }

    private void getSecondaryXMLData()
    {
        asyncHttpClient.get(adsUrl, new SaxAsyncHttpResponseHandler<SecondaryDataHandler>(secondaryDataHandler)
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, SecondaryDataHandler t)
            {
                // Request got HTTP success statusCode
                Log.d("SecondaryData","success");
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, SecondaryDataHandler t)
            {
                // Request got HTTP fail statusCode
                Log.d("SecondaryData","failure");
            }
        });
    }
}
