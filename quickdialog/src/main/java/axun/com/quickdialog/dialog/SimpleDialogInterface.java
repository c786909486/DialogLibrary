package axun.com.quickdialog.dialog;

import android.app.Dialog;
import android.content.DialogInterface;

/**
 * Created by Administrator on 2018/2/25.
 */

public class SimpleDialogInterface {

    public interface MyDialogListener {

        void OnClick(Dialog dialog, int position);

    }

    public  interface MutilyChooseDialogListener{

        void onMutilyChooseListener(DialogInterface dialog,int position,boolean isChecked);
    }
}
