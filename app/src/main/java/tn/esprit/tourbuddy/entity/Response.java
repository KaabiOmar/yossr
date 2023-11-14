package tn.esprit.tourbuddy.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity( tableName = "table_response",
        foreignKeys = @ForeignKey(
                entity = Claims.class,
                parentColumns = "clid",
                childColumns = "rid",
                onDelete = ForeignKey.CASCADE
        )


)
public class Response {

    @PrimaryKey(autoGenerate = true)
    private int rid;

    @ColumnInfo(name="rep")
    private String rep;


    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    @Override
    public String toString() {
        return "response{" +
                "rid=" + rid +
                ", rep='" + rep + '\'' +
                '}';
    }

    public Response() {
    }
}
