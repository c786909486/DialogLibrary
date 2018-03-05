package axun.com.quickdialog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import axun.com.quickdialog.R;

/**
 * Created by Administrator on 2018/3/5.
 */

public class SingleChooseAdapter extends BaseAdapter {
    String[] items;
    Context context;
    LayoutInflater inflater;
    int selectId;

    public SingleChooseAdapter(String[] items, Context context) {
        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final SingleHolder holder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_sigle_choose,parent,false);
            holder = new SingleHolder();
            holder.mShowText = convertView.findViewById(R.id.show_text);
            holder.mSingleBtn = convertView.findViewById(R.id.single_button);
            convertView.setTag(holder);
        }else {
            holder = (SingleHolder) convertView.getTag();
        }

//        holder.mSingleBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!holder.mSingleBtn.isChecked()){
//                    setSelectId(position);
//                }
//            }
//        });

        if (selectId == position){
            holder.mSingleBtn.setChecked(true);
        }else {
            holder.mSingleBtn.setChecked(false);
        }
        holder.mShowText.setText(items[position]);

        return convertView;
    }

    public void setSelectId(int selectId) {
        this.selectId = selectId;
        notifyDataSetChanged();
    }

    class SingleHolder{
        TextView mShowText;
        RadioButton mSingleBtn;
    }
}
