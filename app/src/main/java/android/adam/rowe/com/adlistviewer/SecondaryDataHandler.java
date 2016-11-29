package android.adam.rowe.com.adlistviewer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

/**
 * Created by ARo on 11/22/2016.
 * DefaultHandler implementation to parse XML data
 */

class SecondaryDataHandler extends DefaultHandler
{
    private List<Ad> adsList;

    private boolean appIdElement = false;
    private boolean averageRatingImageURLElement = false;
    private boolean bidRateElement = false;
    private boolean callToActionElement = false;
    private boolean campaignDisplayOrderElement = false;
    private boolean campaignIdElement = false;
    private boolean campaignTypeIdElement = false;
    private boolean categoryNameElement = false;
    private boolean clickProxyUrlElement = false;
    private boolean creativeIdElement = false;
    private boolean homeScreenElement = false;
    private boolean impressionTrackingUrlElement = false;
    private boolean isRandomPickElement = false;
    private boolean minOSVersionElement = false;
    private boolean numberOfRatingsElement = false;
    private boolean productDescriptionElement = false;
    private boolean productIdElement = false;

    private String appId;
    private String averageRatingImageURL;
    private double bidRate;
    private String callToAction;
    private int campaignDisplayOrder;
    private int campaignId;
    private int campaignTypeId;
    private String categoryName;
    private String clickProxyUrl;
    private int creativeId;
    private boolean homeScreen;
    private String impressionTrackingUrl;
    private boolean isRandomPick;
    private String minOSVersion;
    private String numberOfRatings;
    private String productDescription;
    private int productId;

    private int counter = 0;

    SecondaryDataHandler(List<Ad> adsList)
    {
        this.adsList = adsList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        switch(qName)
        {
            case "appId":
                appIdElement = true;
                break;
            case "averageRatingImageURL":
                averageRatingImageURLElement = true;
                break;
            case "bidRate":
                bidRateElement = true;
                break;
            case "callToAction":
                callToActionElement = true;
                break;
            case "campaignDisplayOrder":
                campaignDisplayOrderElement = true;
                break;
            case "campaignId":
                campaignIdElement = true;
                break;
            case "campaignTypeId":
                campaignTypeIdElement = true;
                break;
            case "categoryName":
                categoryNameElement = true;
                break;
            case "clickProxyURL":
                clickProxyUrlElement = true;
                break;
            case "creativeId":
                creativeIdElement = true;
                break;
            case "homeScreen":
                homeScreenElement = true;
                break;
            case "impressionTrackingURL":
                impressionTrackingUrlElement = true;
                break;
            case "isRandomPick":
                isRandomPickElement = true;
                break;
            case "minOSVersion":
                minOSVersionElement = true;
                break;
            case "numberOfRatings":
                numberOfRatingsElement = true;
                break;
            case "productDescription":
                productDescriptionElement = true;
                break;
            case "productId":
                productIdElement = true;
                break;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        if(appIdElement)
        {
            appIdElement = false;
            appId = new String(ch, start, length);
        }

        if(averageRatingImageURLElement)
        {
            averageRatingImageURLElement = false;
            averageRatingImageURL = new String(ch, start, length);
        }

        if(bidRateElement)
        {
            bidRateElement = false;
            bidRate = Double.valueOf(new String(ch, start, length));
        }

        if(callToActionElement)
        {
            callToActionElement = false;
            callToAction = new String(ch, start, length);
        }

        if(campaignDisplayOrderElement)
        {
            campaignDisplayOrderElement = false;
            campaignDisplayOrder = Integer.valueOf(new String(ch, start, length));
        }

        if(campaignIdElement)
        {
            campaignIdElement = false;
            campaignId = Integer.valueOf(new String(ch, start, length));
        }

        if(campaignTypeIdElement)
        {
            campaignTypeIdElement = false;
            campaignTypeId = Integer.valueOf(new String(ch, start, length));
        }

        if(categoryNameElement)
        {
            categoryNameElement = false;
            categoryName = new String(ch, start, length);
        }

        if(clickProxyUrlElement)
        {
            clickProxyUrlElement = false;
            clickProxyUrl = new String(ch, start, length);
        }

        if(creativeIdElement)
        {
            creativeIdElement = false;
            creativeId = Integer.valueOf(new String(ch, start, length));
        }

        if(homeScreenElement)
        {
            homeScreenElement = false;
            homeScreen = Boolean.valueOf(new String(ch, start, length));
        }

        if(impressionTrackingUrlElement)
        {
            impressionTrackingUrlElement = false;
            impressionTrackingUrl = new String(ch, start, length);
        }

        if(isRandomPickElement)
        {
            isRandomPickElement = false;
            isRandomPick = Boolean.valueOf(new String(ch, start, length));
        }

        if(minOSVersionElement)
        {
            minOSVersionElement = false;
            minOSVersion = new String(ch, start, length);
        }

        if(numberOfRatingsElement)
        {
            numberOfRatingsElement = false;
            numberOfRatings = new String(ch, start, length);
        }

        if(productDescriptionElement)
        {
            productDescriptionElement = false;
            productDescription = new String(ch, start, length);
        }

        if(productIdElement)
        {
            productIdElement = false;
            productId = Integer.valueOf(new String(ch, start, length));
            setAdDetails();
        }
    }

    private void setAdDetails()
    {
        Ad ad = adsList.get(counter);
        //set all the ad properties
        ad.appId = appId;
        ad.averageRatingImageURL = averageRatingImageURL;
        ad.bidRate = bidRate;
        ad.callToAction = callToAction;
        ad.campaignDisplayOrder = campaignDisplayOrder;
        ad.campaignId = campaignId;
        ad.campaignTypeId = campaignTypeId;
        ad.categoryName = categoryName;
        ad.clickProxyUrl = clickProxyUrl;
        ad.creativeId = creativeId;
        ad.homeScreen = homeScreen;
        ad.impressionTrackingUrl = impressionTrackingUrl;
        ad.minOSVersion = minOSVersion;
        ad.numberOfRatings = numberOfRatings;
        ad.productDescription = productDescription;
        ad.productId = productId;
        ad.isRandomPick = isRandomPick;

        counter++;
    }
}
