package com.neu.coder.mathmodeling.Resources;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;

/**
 * Created by zxy on 16/1/6.
 */
public class ResourceItem implements AsymmetricItem {
    private int columnSpan;
    private int rowSpan;
    private int position;

    public ResourceItem(Parcel in) {
        this(1, 1, 0);
    }

    public ResourceItem(int columnSpan, int rowSpan, int position) {
        this.columnSpan = columnSpan;
        this.rowSpan = rowSpan;
        this.position = position;
    }

    @Override
    public int getColumnSpan() {
        return columnSpan;
    }

    @Override
    public int getRowSpan() {
        return rowSpan;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        columnSpan = in.readInt();
        rowSpan = in.readInt();
        position = in.readInt();
    }

    @Override public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(columnSpan);
        dest.writeInt(rowSpan);
        dest.writeInt(position);
    }

    /* Parcelable interface implementation */
    public static final Parcelable.Creator<ResourceItem> CREATOR = new Parcelable.Creator<ResourceItem>() {

        @Override public ResourceItem createFromParcel(@NonNull Parcel in) {
            return new ResourceItem(in);
        }

        @Override @NonNull public ResourceItem[] newArray(int size) {
            return new ResourceItem[size];
        }
    };

    @Override
    public String toString() {
        return String.format("%s: %sx%s", position, rowSpan, columnSpan);
    }
}
