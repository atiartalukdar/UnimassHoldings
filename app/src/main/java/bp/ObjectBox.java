package bp;

import android.content.Context;
import android.util.Log;

import info.atiar.unimassholdings.BuildConfig;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import objectBox.MyObjectBox;

public class ObjectBox {
    private static BoxStore boxStore;

    public static void init(Context context) {

        boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();
/*

        boxStore.close();
        boxStore.deleteAllFiles();
*/

    }

    public static BoxStore get() { return boxStore; }
}