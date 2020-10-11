package com.example.quizzone_finale;
import com.example.quizzone_finale.QuizContract.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;



public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;
    private static QuizDbHelper instance;
    private SQLiteDatabase db;
    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }


    //inserisco qua le domande
    private void fillQuestionsTable() {
        Question q1 = new Question("Asa Akira di che nazionalità è?", "Americana", "Coreana", "Giapponese", 1);
        insertQuestion(q1);
        Question q2 = new Question("Asa Akira ha fatto porno di sesso orale?", "No", "Si", "Solo 1 volta", 2);
        insertQuestion(q2);
        Question q3 = new Question("Quanti film da regista ha girato Lisa Ann?", "58", "610", "59", 3);
        insertQuestion(q3);
        Question q4 = new Question("Lisa Ann ha le tette naturali?", "No", "Si", "Non guardo queste cose, basta che siano big", 1);
        insertQuestion(q4);
        Question q5 = new Question("Quali tra questi non è un sito Porno? ", "Pornhub", "SunPorno", "Pornhab", 3);
        insertQuestion(q5);
        Question q6 = new Question("Asa Akira ha partecipato al film Live Nude Girls?", "No", "Si", "No solo al film I Am Angela", 2);
        insertQuestion(q6);
        Question q7 = new Question("Hai mai acceduto a LupoPorno?", "No sono amico di Marco Casoni,quindi non guardo queste cose", "No sono un prete", "Si che domande del cazzo fai", 3);
        insertQuestion(q7);
        Question q8 = new Question("Quali tra queste NON è una categoria porno?", "Figa pelosa", "Donne mature", "Hashish girl", 3);
        insertQuestion(q8);
        Question q9 = new Question("Quanto è lungo il pene di Rocco Siffredi?", "24cm", "18cm", "31cm", 1);
        insertQuestion(q9);
        Question q10 = new Question("Quale è la categoria più amata dagli uomini tra queste 3?", "Cornuto", "Big Boobs(Grandi tette)", "Milf", 3);
        insertQuestion(q10);
        Question q11 = new Question("Quanto è lungo il pene di Roberto Esquivel Cabrera?", "16cm", "48cm", "30cm", 2);
        insertQuestion(q11);
        Question q12 = new Question("Di che nazionalità è l'attrice porno Aletta Ocean?", "Americana", "Slovacco", "Ungherese", 3);
        insertQuestion(q12);
        Question q13 = new Question("Colori capelli Aletta Ocean?", "Neri", "Biondi", "Castani", 1);
        insertQuestion(q13);
        Question q14 = new Question("Favorevole ai porno con doppia penetrazione?", "No sono volgari", "Bhe ovvio, che cazzo di domande", "Boh", 2);
        insertQuestion(q14);
        Question q15 = new Question("Come fa di cognome l'attrice porno Ava?", "Addams", "Adams", "Adrews", 1);
        insertQuestion(q15);
        Question q16 = new Question("Cosa è il clitoride?", "Organo maschile", "Organo femminile", "Organo erettile femminile", 3);
        insertQuestion(q16);
        Question q17 = new Question("Quale è un cibo,secondo la tradizione (e forse studi), addolcisce lo sperma?", "Bisteccone", "Ananasa", "Pomodoro", 2);
        insertQuestion(q17);
        Question q18 = new Question("La ghella le al furmai dla ..?", "Montedison", "cappella", "donna", 2);
        insertQuestion(q18);
        Question q19 = new Question("Cosa è il sesso anale?", "Penetrazione nel buco del culo", "Penetrazione nelle narici(a scelta sinistra o destra)", "Pompino", 1);
        insertQuestion(q19);
        Question q20 = new Question("Mia Khalifa è ancora una attrice pornografica?", "SI", "No ma riprenderà, già confermato", "No", 3);
        insertQuestion(q20);
    }


    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }


    public ArrayList<Question> getQuestions() {
            ArrayList<Question> questionList = new ArrayList<>();
            db = getReadableDatabase();
            Cursor c = db.query(
                    QuestionsTable.TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}