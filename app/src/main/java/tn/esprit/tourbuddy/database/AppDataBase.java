package tn.esprit.tourbuddy.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import tn.esprit.tourbuddy.dao.ClaimsDao;
import tn.esprit.tourbuddy.dao.CommentDao;
import tn.esprit.tourbuddy.dao.PublicationDao;
import tn.esprit.tourbuddy.dao.ResponseDao;
import tn.esprit.tourbuddy.entity.Claims;
import tn.esprit.tourbuddy.entity.Comment;
import tn.esprit.tourbuddy.entity.Publication;
import tn.esprit.tourbuddy.entity.Response;

@Database(entities = {Publication.class, Comment.class , Claims.class , Response.class }, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "tour_buddy").build();
        }
        return instance;
    }

    public abstract ClaimsDao claimsDao();
    public abstract ResponseDao responseDao();
    public abstract PublicationDao publicationDao();
    public abstract CommentDao commentDao();
}
