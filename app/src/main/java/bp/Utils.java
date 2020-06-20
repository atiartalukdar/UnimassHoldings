package bp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import info.atiar.unimassholdings.R;
import session.MyApplication;

/**
 * Created by Atiar Talukdar on 7/11/2019.
 */
public class Utils {
    public static void openDialPad(Activity activity, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        activity.startActivity(intent);
    }

    public static void setHtmlTextToTextView(TextView textView, String htmlText){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
        }
    }

    public static void showDialog(Activity context, String message) {
        new SweetAlertDialog(context)
                .setTitleText(message)
                .show();

    }

    public static void showDialog(Activity context, String message, int AlertType) {
        new SweetAlertDialog(context, AlertType)
                .setContentText(message)
                .setConfirmText("OK")
                .show();

    }

    public static void showDialog(final Activity context, String message, final Class toClass) {

        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(message)
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        context.startActivity(new Intent(context, toClass));
                        context.finish();
                    }
                })
                .show();

    }

    public static void showDialog(final Activity context, String message, final Class toClass, int AlertType) {

        new SweetAlertDialog(context, AlertType)
                .setTitleText(message)
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        context.startActivity(new Intent(context, toClass));
                        context.finish();
                    }
                })
                .show();
    }

    public static void showDialog(Activity context){
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public static KProgressHUD showProgressDialog(Activity activity, String description){
        KProgressHUD kProgressHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait ... ")
                .setDetailsLabel(description)
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f)
                .show();
        return kProgressHUD;
    }

    public static HashMap<String, String> getHeaders(){
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization","Basic NTZmNWZiYTctNmNiYi00NTUwLWI5YWItMjgwMDdiNGFiNDRm");
        return  headers;
    }

    public static String getCurrentDateTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

}
