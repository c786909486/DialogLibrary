package axun.com.quickdialog.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import axun.com.quickdialog.R;
import axun.com.quickdialog.adapter.ListChooseAdapter;
import axun.com.quickdialog.adapter.SingleChooseAdapter;

/**
 * Created by Administrator on 2018/2/25.
 */

public class SimpleDialog {
    private Context context;
    private AlertDialog dialog;
    private TextView mTvTitle;
    private LinearLayout mContentLayout;
    private TextView mCancleBtn;
    private TextView mApplyBtn;
    private View view;
    private int witch = 0;

    private SimpleDialog(Context context) {
        this.context = context;

    }

    private static SimpleDialog instance;

    /**
     * 单例模式
     * @param context
     * @return
     */
    public static SimpleDialog getInstance(Context context) {
        if (instance == null) {
            synchronized (SimpleDialog.class) {
                if (instance == null) {
                    instance = new SimpleDialog(context);
                }
            }
        }
        return instance;
    }

    /**
     * 创建dialog
     * @return
     */
    public SimpleDialog create() {
        witch = 0;
        dialog = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.MyDialog))
                .create();
        //默认设置点击外部可取消
        dialog.setCanceledOnTouchOutside(true);
        view = LayoutInflater.from(context).inflate(R.layout.normal_dialog_layout, null);
        initView(view);
        mContentLayout.removeAllViews();

        return this;
    }

    /**
     * 设置标题
     * @param title
     * @return
     */
    public SimpleDialog setTitle(String title,float textSize,int textColor) {
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setVisibility(View.VISIBLE);
            mTvTitle.setText(title);
            mTvTitle.setTextSize(textSize);
            mTvTitle.setTextColor(textColor);
        }else {
            mTvTitle.setVisibility(View.GONE);
        }
        return this;
    }

    public SimpleDialog setTitle(String title) {
        return setTitle(title,16,Color.BLACK);
    }

    public SimpleDialog setTitle(CharSequence title){
        return setTitle(title.toString());
    }
    public SimpleDialog setTitle(@StringRes int resId){
        return setTitle(context.getResources().getText(resId));
    }


    /**
     * 设置确定按键
     * @param text
     * @param textColor
     * @param listener
     * @return
     */
    public SimpleDialog setPositiveButton(String text, int textColor, final MyDialogInterface.MyDialogListener listener){
        if (!TextUtils.isEmpty(text)){
            mApplyBtn.setVisibility(View.VISIBLE);
            mApplyBtn.setTextColor(textColor);
            mApplyBtn.setText(text);
            mApplyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnClick(dialog,witch);
                }
            });
        }else {
            mApplyBtn.setVisibility(View.GONE);
        }
        return this;
    }

    public SimpleDialog setPositiveButton(CharSequence text, int textColor, MyDialogInterface.MyDialogListener listener){
        return setPositiveButton(text.toString(),textColor,listener);
    }

    public SimpleDialog setPositiveButton(@StringRes int resId, int textColor, MyDialogInterface.MyDialogListener listener){
        return setPositiveButton(context.getResources().getText(resId),textColor,listener);
    }

    public SimpleDialog setPositiveButton(String text, MyDialogInterface.MyDialogListener listener){
        return setPositiveButton(text, Color.RED,listener);
    }

    public SimpleDialog setPositiveButton(CharSequence text, MyDialogInterface.MyDialogListener listener){
        return setPositiveButton(text.toString(),listener);
    }

    public SimpleDialog setPositiveButton(@StringRes int resId, MyDialogInterface.MyDialogListener listener){
        return setPositiveButton(context.getResources().getText(resId),listener);
    }

    public SimpleDialog setPositiveButton(MyDialogInterface.MyDialogListener listener){
        return setPositiveButton("确定", listener);
    }

    /**
     * 设置取消按键
     * @param text
     * @param textColor
     * @param listener
     * @return
     */
    public SimpleDialog setCancelButton(String text, int textColor, final MyDialogInterface.MyDialogListener listener){
        if (!TextUtils.isEmpty(text)){
            mCancleBtn.setVisibility(View.VISIBLE);
            mCancleBtn.setTextColor(textColor);
            mCancleBtn.setText(text);
            mCancleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnClick(dialog,0);
                }
            });
        }else {
            mCancleBtn.setVisibility(View.GONE);

        }
        return this;
    }

    public SimpleDialog setCancelButton(CharSequence text, int textColor, MyDialogInterface.MyDialogListener listener){
        return setCancelButton(text.toString(),textColor,listener);
    }

    public SimpleDialog setCancelButton(@StringRes int resId, int textColor, MyDialogInterface.MyDialogListener listener){
        return setCancelButton(context.getResources().getText(resId),textColor,listener);
    }

    public SimpleDialog setCancelButton(String text, MyDialogInterface.MyDialogListener listener){
        return setCancelButton(text, Color.BLACK,listener);
    }

    public SimpleDialog setCancelButton(CharSequence text, MyDialogInterface.MyDialogListener listener){
        return setCancelButton(text.toString(),listener);
    }

    public SimpleDialog setCancelButton(@StringRes int resId, MyDialogInterface.MyDialogListener listener){
        return setCancelButton(context.getResources().getText(resId),listener);
    }

    public SimpleDialog setCancelButton(MyDialogInterface.MyDialogListener listener){
        return setCancelButton("取消",listener);
    }


    /**
     *设置点击外部可取消
     * @param cancelEnable
     * @return
     */
    public SimpleDialog setCanceledOnTouchOutside(boolean cancelEnable) {
        dialog.setCanceledOnTouchOutside(cancelEnable);
        return this;
    }

    /**
     * 设置信息
     * @param message
     * @param textColor
     * @param textSize
     * @param gravity
     * @return
     */
    public SimpleDialog setMessage(String message, @ColorInt int textColor,float textSize,int gravity){
        if (!TextUtils.isEmpty(message)){
            TextView textView = new TextView(context);
            textView.setTextColor(textColor);
            textView.setTextSize(textSize);
            textView.setGravity(gravity);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,20,0,20);
            textView.setLayoutParams(layoutParams);
            textView.setText(message);
            mContentLayout.addView(textView);
        }
        return this;
    }

    public SimpleDialog setMessage(String message, @ColorInt int textColor,int gravity){
        return setMessage(message,textColor,13,gravity);
    }

    public SimpleDialog setMessage(String message,int gravity){
        return setMessage(message,Color.BLACK,13,gravity);
    }

    public SimpleDialog setMessage(String message){
        return setMessage(message,Color.BLACK,13, Gravity.CENTER);
    }

    /**
     * 设置列表dialog
     * @param items
     * @param listener
     * @return
     */
    public SimpleDialog setItems(String[] items,BaseAdapter adapter, final MyDialogInterface.MyDialogListener listener){
        if (items!=null && items.length>0){
            ListView listView = new ListView(context);
            listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            listView.setVerticalScrollBarEnabled(false);
            mContentLayout.addView(listView);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listener.OnClick(dialog,position);
                }
            });
        }
        return this;
    }

    public SimpleDialog setItems(String[] items,MyDialogInterface.MyDialogListener listener){
        ListChooseAdapter adapter = new ListChooseAdapter(items,context);
        return setItems(items,adapter,listener);
    }

    /**
     * 设置单选列表
     * @param items
     * @param listener
     * @return
     */
    public SimpleDialog setSingleChooseItems(String[] items, final MyDialogInterface.MyDialogListener listener){
        if (items!=null && items.length>0){
            final ListView listView = new ListView(context);
            listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            listView.setVerticalScrollBarEnabled(false);
            mContentLayout.addView(listView);
            final SingleChooseAdapter adapter = new SingleChooseAdapter(items,context);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    adapter.setSelectId(position);
                    witch = position;
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (listener!=null){
                                listener.OnClick(dialog,position);
                            }
                        }
                    },500);
                }
            });

        }
        return this;
    }

    /**
     * 设置位置
     * @param gravity
     * @return
     */
    public SimpleDialog setGravity(int gravity){
        if (dialog!=null){
            dialog.getWindow().setGravity(gravity);
        }
        return this;
    }

    public SimpleDialog setAnimation(@StyleRes int animation){
        if (dialog!=null){
            dialog.getWindow().setWindowAnimations(animation);
        }
        return this;
    }


    /**
     * 显示
     */
    public void show(){
        if (mApplyBtn.getVisibility() == View.VISIBLE && mCancleBtn.getVisibility() == View.GONE){
            mApplyBtn.setBackgroundResource(R.drawable.bottom_drawable);
        }else if (mApplyBtn.getVisibility() == View.GONE && mCancleBtn.getVisibility() == View.VISIBLE){
            mCancleBtn.setBackgroundResource(R.drawable.bottom_drawable);
        }else if (mApplyBtn.getVisibility() == View.VISIBLE && mCancleBtn.getVisibility() == View.VISIBLE){
            mApplyBtn.setBackgroundResource(R.drawable.right_drawable);
            mCancleBtn.setBackgroundResource(R.drawable.left_drawable);
        }
        dialog.show();
        dialog.setContentView(view);

        mContentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Window window = dialog.getWindow();
                WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                Display display = windowManager.getDefaultDisplay();
                android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
                Log.d("SimpleDialog",mContentLayout.getHeight()+"");
                if (mContentLayout.getHeight()> display.getHeight() *0.75){
                    p.height = (int) (display.getHeight() * 0.75);
                    window.setAttributes(p);
                }
            }
        });
    }

    private void initView(View view) {
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mContentLayout = (LinearLayout) view.findViewById(R.id.content_layout);
        mCancleBtn = (TextView) view.findViewById(R.id.cancle_btn);
        mApplyBtn = (TextView) view.findViewById(R.id.apply_btn);
        mTvTitle.setVisibility(View.GONE);
        mCancleBtn.setVisibility(View.GONE);
        mApplyBtn.setVisibility(View.GONE);
    }
}
