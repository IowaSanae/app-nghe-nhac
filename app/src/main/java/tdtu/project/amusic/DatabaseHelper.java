package tdtu.project.amusic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    // Tên đặt hơi rối, có thể sửa lại nếu cần thiết
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "amusic";
    private static final String MUSIC_TABLE = "song_info";

    private static final String SONG_ID = "id";
    private static final String SONG_NAME = "name";
    private static final String SONG_IMAGE = "image";
    private static final String SONG_PLAYLIST = "playlist";

    // Table create statement
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + DATABASE_NAME + "(" + SONG_ID + ", " +SONG_NAME + " TEXT," + SONG_IMAGE + " BLOB," + SONG_PLAYLIST + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_IMAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int olderVersion, int newVersion) {
        // Drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MUSIC_TABLE);

        // Create a new one
        onCreate(sqLiteDatabase);
    }
}
