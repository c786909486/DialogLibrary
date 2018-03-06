package axun.com.quickdialog.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2018/3/6.
 */

public class QuickDialog {
    private Context context;
    private AlertDialog dialog;
    private View view;
    private static QuickDialog instance;
    private QuickDialog(Context context){
        this.context = context;

    }
    public static QuickDialog getInstance(Context context){
        if (instance == null){
            synchronized (QuickDialog.class){
                if (instance == null){
                    instance = new QuickDialog(context);
                }
            }
        }
        return instance;
    }

    public QuickDialog setContent(View view){
        this.view = view;
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setContentView(view);
        return this;
    }

    public QuickDialog setContent(@LayoutRes int layout){
        return setContent(LayoutInflater.from(context).inflate(layout,null));
    }

    public QuickDialog setOnDialogClickListener(@IdRes int resId, final OnDialogClickListener listener){
        if (view!=null){
            view.findViewById(resId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v,dialog);
                }
            });
        }
        return this;
    }

    public QuickDialog setGravity(int gravity){
        Window window = dialog.getWindow();
        window.setGravity(gravity);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();

        if (gravity == Gravity.BOTTOM){
            p.width = display.getWidth();
            window.setAttributes(p);
        }
        return this;
    }

    private OnDialogClickListener listener;
    public interface OnDialogClickListener{
        void onClick(View view, DialogInterface dialog);
    }
}
