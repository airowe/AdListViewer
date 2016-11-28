package android.adam.rowe.com.adlistviewer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ARo on 11/22/2016.
 */

public class DataHandler extends DefaultHandler
{
    private ArrayList<Ad> adsList = new ArrayList<>();
    private boolean productNameElement = false;
    private boolean productThumbnailElement = false;
    private boolean ratingElement = false;

    private String productName;
    private String productThumbnail;
    private double rating;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        productNameElement = qName.equals("productName");
        productThumbnailElement = qName.equals("productThumbnail");
        ratingElement = qName.equals("rating");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        if(productNameElement)
        {
            productName = new String(ch, start, length);
            productNameElement = false;
        }
        else if(productThumbnailElement)
        {
            productThumbnail = new String(ch, start, length);
            productThumbnailElement = false;
        }
        else if(ratingElement)
        {
            rating = Double.valueOf(new String(ch, start, length));
            ratingElement = false;
            adsList.add(new Ad(productName, productThumbnail, rating));
        }
    }

    public ArrayList<Ad> getAdsList()
    {
        return adsList;
    }
}
