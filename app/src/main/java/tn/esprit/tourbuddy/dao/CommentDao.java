package tn.esprit.tourbuddy.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.tourbuddy.entity.Comment;
import tn.esprit.tourbuddy.entity.Publication;

@Dao
public interface CommentDao {
    @Insert
    void insererComment(Comment comment);

    @Update
    void modifierComment(Comment    comment);

    @Delete
    void supprimerComment(Comment comment);

    @Query("SELECT * FROM table_comment WHERE publication_id = :publicationId")
    List<Comment> obtenirCommentairesPourPublication(int publicationId);
}
