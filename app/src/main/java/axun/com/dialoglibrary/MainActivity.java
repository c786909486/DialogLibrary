package axun.com.dialoglibrary;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import axun.com.quickdialog.MyDialogUtils;
import axun.com.quickdialog.dialog.QuickDialog;
import axun.com.quickdialog.dialog.SimpleDialog;
import axun.com.quickdialog.dialog.SimpleDialogInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mBtn1 = (Button) findViewById(R.id.btn_1);
        mBtn2 = (Button) findViewById(R.id.btn_2);
        mBtn3 = (Button) findViewById(R.id.btn_3);
        mBtn4 = (Button) findViewById(R.id.btn_4);
        mBtn5 = (Button) findViewById(R.id.btn_5);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);
        mBtn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                showNormalDialog();
                break;
            case R.id.btn_2:
                showListDialog();
                break;
            case R.id.btn_3:
                showSingleChooseDialog();
                break;
            case R.id.btn_4:
                showMultiChooseDialog();
                break;
            case R.id.btn_5:
                showCommentDialog();
                break;
            default:
                break;
        }
    }

    /**
     * 弹出普通对话框
     */
    private void showNormalDialog(){
        SimpleDialog.getInstance(this)
                .create()
                .setTitle("通知")
                .setPositiveButton(new SimpleDialogInterface.MyDialogListener() {
                    @Override
                    public void OnClick(Dialog dialog, int position) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,"点击了确定",Toast.LENGTH_SHORT).show();
                    }
                })
                .setCancelButton(new SimpleDialogInterface.MyDialogListener() {
                    @Override
                    public void OnClick(Dialog dialog, int position) {
                        dialog.dismiss();
                    }
                })
                .setMessage("对方已成功接收了您发送的离线文件")
                .show();
    }

    /**
     * 弹出列表对话框
     */
    private String[] list = new String[]{"item1","item2","item3","item4","item5","item6",
            "item7","item8","item9","item10","item4","item4","item4","item4","item4","item4"
    };

    private void showListDialog(){
//        MyDialogUtils.getInstance().createNormalListDialog(this, "", list, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
        SimpleDialog.getInstance(this)
                .create()
                .setTitle("请选择")
//
                .setItems(list, new SimpleDialogInterface.MyDialogListener() {
                    @Override
                    public void OnClick(Dialog dialog, int position) {
                        Toast.makeText(MainActivity.this,list[position],Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * 弹出单选对话框
     */
    private void showSingleChooseDialog(){
        SimpleDialog.getInstance(this)
                .create()
                .setTitle("请选择")
                .setSingleChooseItems(list, new SimpleDialogInterface.MyDialogListener() {
                    @Override
                    public void OnClick(Dialog dialog, int position) {

                    }
                }).setPositiveButton(new SimpleDialogInterface.MyDialogListener() {
            @Override
            public void OnClick(Dialog dialog, int position) {
                Toast.makeText(MainActivity.this,"选择了"+list[position],Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }).setGravity(Gravity.BOTTOM)
                .setAnimation(R.style.dialogAnim)
                .show();
    }

    private void showMultiChooseDialog(){
        boolean[] checkItem = new boolean[]{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
        MyDialogUtils.getInstance().createMuiltyListDialog(this, "多选框", list, checkItem, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }

    private void showCommentDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_comment_layout,null);
        EditText input = view.findViewById(R.id.comment_input);
        TextView cancle = view.findViewById(R.id.cancle_btn);
        final TextView apply = view.findViewById(R.id.apply_btn);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==0){
                    apply.setTextColor(Color.parseColor("#999999"));
                }else {
                    apply.setTextColor(Color.parseColor("#6699ff"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        QuickDialog.getInstance(this).setContent(view)
                .setGravity(Gravity.BOTTOM)
                .setOnDialogClickListener(R.id.cancle_btn, new QuickDialog.OnDialogClickListener() {
                    @Override
                    public void onClick(View view, DialogInterface dialog) {
                        dialog.dismiss();
                    }
                }).setOnDialogClickListener(R.id.apply_btn, new QuickDialog.OnDialogClickListener() {
            @Override
            public void onClick(View view, DialogInterface dialog) {
                Toast.makeText(MainActivity.this,"发布",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}
