package axun.com.quickdialog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import axun.com.quickdialog.R;

/**
 * Created by Administrator on 2018/3/5.
 */

public class ListChooseAdapter extends BaseAdapter {
    String[] items;
    Context context;
    LayoutInflater inflater;

    public ListChooseAdapter(String[] items, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_list_choose,parent,false);
        TextView textView = convertView.findViewById(R.id.show_text);
        textView.setText(items[position]);
        return convertView;
    }
}
