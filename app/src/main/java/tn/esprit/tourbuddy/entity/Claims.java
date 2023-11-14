package tn.esprit.tourbuddy.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;



import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_claims")
public class Claims implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int clid;

    @ColumnInfo(name ="rec")
    private String rec;

    @ColumnInfo(name ="sujet")
    private String sujet;

    // Constructors
    public Claims() {
    }

    public Claims(int clid, String rec, String sujet) {
        this.clid = clid;
        this.rec = rec;
        this.sujet = sujet;
    }

    // Getters and Setters
    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public int getClid() {
        return clid;
    }

    public void setClid(int clid) {
        this.clid = clid;
    }

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }

    // Parcelable implementation
    protected Claims(Parcel in) {
        clid = in.readInt();
        rec = in.readString();
        sujet = in.readString();
    }

    public static final Creator<Claims> CREATOR = new Creator<Claims>() {
        @Override
        public Claims createFromParcel(Parcel in) {
            return new Claims(in);
        }

        @Override
        public Claims[] newArray(int size) {
            return new Claims[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(clid);
        dest.writeString(rec);
        dest.writeString(sujet);
    }
}
