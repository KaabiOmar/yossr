package tn.esprit.tourbuddy.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.tourbuddy.entity.Claims;

@Dao
public interface ClaimsDao {

    @Insert
    Long insererClaims(Claims claims);

    @Update
    void modifierClaims(Claims    claims);

    @Delete
    void supprimerClaims(Claims claims);

    @Query("SELECT * FROM table_claims")
    List<Claims> ListClaims();

}
