package com.zamrud.radio.mobile.app.svara.Realm

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import com.zamrud.radio.mobile.app.svara.Realm.model.ImageMenu
import io.realm.Realm
import java.io.ByteArrayOutputStream

class RealmMenu {
    companion object {
        fun clear() {
            val realm: Realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.delete(ImageMenu::class.java)
            realm.commitTransaction()
        }

        fun saveImageMenu(url: String?, image: Bitmap) {
            val realm: Realm = Realm.getDefaultInstance()
            val byteArrayOutputStream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            realm.executeTransaction { realm1: Realm ->
                val imageMenu = ImageMenu()
                imageMenu.setId(url)
                imageMenu.setData(byteArrayOutputStream.toByteArray())
                realm1.insertOrUpdate(imageMenu)
            }
            //        realm.beginTransaction();
//        ImageMenu imageMenu = new ImageMenu();
//        imageMenu.setId(url);
//        imageMenu.setData(byteArrayOutputStream.toByteArray());
//        realm.insertOrUpdate(imageMenu);
//        realm.commitTransaction();
        }

        fun getImageMenu(context: Context, url: String?): Drawable? {
            val realm: Realm = Realm.getDefaultInstance()
            val imageMenu: ImageMenu = realm.where(ImageMenu::class.java).equalTo("id", url).findFirst()
                ?: return null

            val bitmap: Bitmap = BitmapFactory.decodeByteArray(imageMenu.getData(), 0, imageMenu.getData()!!.size)
            return BitmapDrawable(context.resources, bitmap)
        }

        fun isIconExist(iconUrl: String?): Boolean {
            val realm: Realm = Realm.getDefaultInstance()
            var imageMenu: ImageMenu? = realm.where(ImageMenu::class.java).equalTo("id", iconUrl).findFirst()
            return imageMenu != null
        }
    }
}