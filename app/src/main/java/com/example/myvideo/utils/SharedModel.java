package com.example.myvideo.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import androidx.annotation.NonNull;

import com.example.myvideo.local.MyRoomDatabase;
import com.example.myvideo.models.ArticleModel;
import com.example.myvideo.models.BookModel;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.models.ModelAuthCache;
import com.example.myvideo.models.MyUniversityModel;
import com.example.myvideo.models.PostModel;
import com.example.myvideo.models.RegModel;
import com.example.myvideo.models.UniversityModel;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SharedModel {

    public static boolean cache =false;


    private static String username;
    private static String mail;
    private static String id;
    private static String phone;
    private static String birth;
    private static String image;
    private static String password;
    private static String track;
    private static Uri uri;

    public static String getTrack() {
        return track;
    }

    public static void setTrack(String track) {
        SharedModel.track = track;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SharedModel.password = password;
    }

    public static Uri getUri() {
        return uri;
    }

    public static void setUri(Uri uri) {
        SharedModel.uri = uri;
    }

    private static BookModel selected_book;

    private static ArticleModel selected_article;

    private static RegModel selected_user;

    public static RegModel getSelected_user() {
        return selected_user;
    }

    public static void setSelected_user(RegModel selected_user) {
        SharedModel.selected_user = selected_user;
    }

    public static ArticleModel getSelected_article() {
        return selected_article;
    }

    public static void setSelected_article(ArticleModel selected_article) {
        SharedModel.selected_article = selected_article;
    }

    private static MyUniversityModel myUniversity;

    public static MyUniversityModel getMyUniversity() {
        return myUniversity;
    }

    public static void setMyUniversity(MyUniversityModel myUniversity) {
        SharedModel.myUniversity = myUniversity;
    }

    private static String explore_item;

    private static String explore_cat;

    public static String getExplore_cat() {
        return explore_cat;
    }

    public static void setExplore_cat(String explore_cat) {
        SharedModel.explore_cat = explore_cat;
    }

    public static String getExplore_item() {
        return explore_item;
    }

    public static void setExplore_item(String explore_item) {
        SharedModel.explore_item = explore_item;
    }

    private static CourseModel selected_course;

    private static UniversityModel selected_university;

    private static UniversityModel.Grades selected_Grade;

    private static UniversityModel.Grades.Departments selected_department;

    private static UniversityModel.Grades.Departments.Terms selected_term;

    private static PostModel selected_post;

    public static PostModel getSelected_post() {
        return selected_post;
    }

    public static void setSelected_post(PostModel selected_post) {
        SharedModel.selected_post = selected_post;
    }

    public static UniversityModel.Grades.Departments.Terms getSelected_term() {
        return selected_term;
    }

    public static void setSelected_term(UniversityModel.Grades.Departments.Terms selected_term) {
        SharedModel.selected_term = selected_term;
    }

    public static UniversityModel.Grades.Departments getSelected_department() {
        return selected_department;
    }

    public static void setSelected_department(UniversityModel.Grades.Departments selected_department) {
        SharedModel.selected_department = selected_department;
    }

    public static UniversityModel.Grades getSelected_Grade() {
        return selected_Grade;
    }

    public static void setSelected_Grade(UniversityModel.Grades selected_Grade) {
        SharedModel.selected_Grade = selected_Grade;
    }

    public static UniversityModel getSelected_university() {
        return selected_university;
    }

    public static void setSelected_university(UniversityModel selected_university) {
        SharedModel.selected_university = selected_university;
    }

    private static String selected_pdf;

    private static String video_id;
    private static String video_title;

    private static int index;


    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        SharedModel.index = index;
    }

    public static String getVideo_title() {
        return video_title;
    }

    public static void setVideo_title(String video_title) {
        SharedModel.video_title = video_title;
    }

    public static String getVideo_id() {
        return video_id;
    }

    public static void setVideo_id(String video_id) {
        SharedModel.video_id = video_id;
    }

    public static String getSelected_pdf() {
        return selected_pdf;
    }

    public static void setSelected_pdf(String selected_pdf) {
        SharedModel.selected_pdf = selected_pdf;
    }

    public static CourseModel getSelected_course() {
        return selected_course;
    }

    public static void setSelected_course(CourseModel selected_course) {
        SharedModel.selected_course = selected_course;
    }

    public static BookModel getSelected_book() {
        return selected_book;
    }

    public static void setSelected_book(BookModel selected_book) {
        SharedModel.selected_book = selected_book;
    }

    public static boolean isCache() {
        return cache;
    }

    public static void setCache(boolean cache) {
        SharedModel.cache = cache;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SharedModel.username = username;
    }

    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        SharedModel.mail = mail;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        SharedModel.id = id;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        SharedModel.phone = phone;
    }

    public static String getBirth() {
        return birth;
    }

    public static void setBirth(String birth) {
        SharedModel.birth = birth;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        SharedModel.image = image;
    }

    public static void cache(List<ModelAuthCache> list){

        MyRoomDatabase.getInstance().getDao().insert(list).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    public static void delete(ModelAuthCache model){

        MyRoomDatabase.getInstance().getDao().delete(model).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    public static String getPathFromUri(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }



}
