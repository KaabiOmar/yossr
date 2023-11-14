package tn.esprit.tourbuddy.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.tourbuddy.entity.Comment;
import tn.esprit.tourbuddy.entity.Publication;
import tn.esprit.tourbuddy.entity.Response;

@Dao
public interface ResponseDao {
    @Insert
    void insererResponse(Response response);

    @Update
    void modifierComment(Response    response);

    @Delete
    void supprimerComment(Response response);
    @Query("SELECT * FROM table_response")
    List<Response> ListResponse();
}
