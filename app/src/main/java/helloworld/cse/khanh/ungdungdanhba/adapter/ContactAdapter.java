package helloworld.cse.khanh.ungdungdanhba.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import helloworld.cse.khanh.ungdungdanhba.R;
import helloworld.cse.khanh.ungdungdanhba.model.Contact;

/**
 * Created by khanh on 10/01/2017.
 */

public class ContactAdapter extends BaseAdapter {

    private Activity activity;
    private List<Contact> contactList;


    public ContactAdapter(Activity activity, List<Contact> contactList) {
        this.activity = activity;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_contact_listview, parent, false);
            viewHolder.imgAvatar = (ImageView) convertView.findViewById(R.id.img_avatar);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            viewHolder.txtNumber = (TextView) convertView.findViewById(R.id.txtNumber);
            convertView.setTag(viewHolder);

        }
        viewHolder = (ViewHolder) convertView.getTag();
        Contact contact = contactList.get(position);
        viewHolder.txtName.setText(contact.getmName());
        viewHolder.txtNumber.setText(contact.getmNumber());
        if (contact.isMale()) {
            viewHolder.imgAvatar.setBackgroundResource(R.drawable.male);
        } else {
            viewHolder.imgAvatar.setBackgroundResource(R.drawable.female);
        }
        return convertView;
    }

    public class ViewHolder {
        ImageView imgAvatar;
        TextView txtName;
        TextView txtNumber;

    }
}
