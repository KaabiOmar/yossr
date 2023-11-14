package tn.esprit.tourbuddy.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_comment",
        foreignKeys = @ForeignKey(
                entity = Publication.class,
                parentColumns = "pid",
                childColumns = "publication_id",
                onDelete = ForeignKey.CASCADE
        )
)
public class Comment {
    @PrimaryKey(autoGenerate = true)
    public int cid;

    @ColumnInfo(name ="contenuc")
    private String contenuc;

    @ColumnInfo(name = "publication_id")
    private int publicationId;

    public Comment(int cid, String contenuc, int publicationId) {
        this.cid = cid;
        this.contenuc = contenuc;
        this.publicationId = publicationId;
    }

    public Comment(String contenuc, int publicationId) {
        this.contenuc = contenuc;
        this.publicationId = publicationId;
    }

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "cid=" + cid +
                ", contenuc='" + contenuc + '\'' +
                ", publicationId=" + publicationId +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getContenuc() {
        return contenuc;
    }

    public void setContenuc(String contenuc) {
        this.contenuc = contenuc;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }
}
