package axun.com.dialoglibrary;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import axun.com.quickdialog.dialog.MyDialogInterface;
import axun.com.quickdialog.dialog.SimpleDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;

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
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);
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

                break;
            case R.id.btn_4:

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
                .setPositiveButton(new MyDialogInterface.MyDialogListener() {
                    @Override
                    public void OnClick(Dialog dialog, int position) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,"点击了确定",Toast.LENGTH_SHORT).show();
                    }
                })
                .setCancelButton(new MyDialogInterface.MyDialogListener() {
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
//                .setPositiveButton(new MyDialogInterface.MyDialogListener() {
//                    @Override
//                    public void OnClick(Dialog dialog, int position) {
//                        dialog.dismiss();
//                        Toast.makeText(MainActivity.this,"点击了确定",Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setCancelButton(new MyDialogInterface.MyDialogListener() {
//                    @Override
//                    public void OnClick(Dialog dialog, int position) {
//                        dialog.dismiss();
//                    }
//                })
                .setItems(list, new MyDialogInterface.MyDialogListener() {
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

    }
}
