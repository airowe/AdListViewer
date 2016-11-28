package android.adam.rowe.com.adlistviewer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ARo on 11/25/2016.
 */

public class Ad implements Parcelable
{
    public String appId;
    public String averageRatingImageURL;
    public double bidRate;
    public String callToAction;
    public int campaignDisplayOrder;
    public int campaignId;
    public int campaignTypeId;
    public String categoryName;
    public String clickProxyUrl;
    public int creativeId;
    public boolean homeScreen;
    public String impressionTrackingUrl;
    public boolean isRandomPick;
    public String minOSVersion;
    public String numberOfRatings;
    public String productDescription;
    public int productId;
    public String productName;
    public String productThumbnail;
    public double rating;

    public Ad(String productName, String productThumbnail, double rating)
    {
        this.productName = productName;
        this.productThumbnail = productThumbnail;
        this.rating = rating;
    }
    
    private Ad(Parcel in)
    {
        appId = in.readString();
        averageRatingImageURL = in.readString();
        bidRate = in.readDouble();
        callToAction = in.readString();
        campaignDisplayOrder = in.readInt();
        campaignId = in.readInt();
        campaignTypeId = in.readInt();
        categoryName = in.readString();
        clickProxyUrl = in.readString();
        creativeId = in.readInt();
        homeScreen = in.readInt() != 0;
        impressionTrackingUrl = in.readString();
        isRandomPick = in.readInt() != 0;
        minOSVersion = in.readString();
        numberOfRatings = in.readString();
        productDescription = in.readString();
        productId = in.readInt();
        productName = in.readString();
        productThumbnail = in.readString();
        rating = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(appId);
        dest.writeString(averageRatingImageURL);
        dest.writeString(callToAction);
        dest.writeString(categoryName);
        dest.writeString(clickProxyUrl);
        dest.writeString(impressionTrackingUrl);
        dest.writeString(minOSVersion);
        dest.writeString(numberOfRatings);
        dest.writeString(productDescription);
        dest.writeString(productName);
        dest.writeString(productThumbnail);
        dest.writeDouble(bidRate);
        dest.writeDouble(rating);
        dest.writeInt(campaignDisplayOrder);
        dest.writeInt(campaignId);
        dest.writeInt(campaignTypeId);
        dest.writeInt(creativeId);
        dest.writeInt(productId);
        dest.writeInt(homeScreen ? 1 : 0);
        dest.writeInt(isRandomPick ? 1 : 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ad> CREATOR = new Creator<Ad>()
    {
        @Override
        public Ad createFromParcel(Parcel in) {
            return new Ad(in);
        }

        @Override
        public Ad[] newArray(int size) {
            return new Ad[size];
        }
    };
}
