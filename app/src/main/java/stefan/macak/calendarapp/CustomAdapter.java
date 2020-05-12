package stefan.macak.calendarapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import stefan.macak.calendarapp.R;
import stefan.macak.calendarapp.User;

public class CustomAdapter extends BaseAdapter {


    private Context mContext;
    private ArrayList<User> mUsers;

    public CustomAdapter(Context context){
        mContext = context;
        mUsers = new ArrayList<User>();
    }

    public void addUser(User user)
    {
        mUsers.add(user);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() { return mUsers.size(); }

    @Override
    public Object getItem(int position) {
        Object rv = null;
        try {
            rv = mUsers.get(position);
        }catch (IndexOutOfBoundsException e )
        {
            e.printStackTrace();
        }
        return  rv;
    }

    public User getUser(int position) {
        User rv = null;
        try {
            rv = mUsers.get(position);
        }catch (IndexOutOfBoundsException e )
        {
            e.printStackTrace();
        }
        return  rv;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item,
                    null);
            holder = new ViewHolder();
          //  holder.image = (ImageView) view.findViewById(R.id.imageItem);
            holder.name = (TextView) view.findViewById(R.id.textItem);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder)view.getTag();
        }

        User user = (User) getItem(position);
        holder = (ViewHolder) view.getTag();
        //holder.image.setImageDrawable(user.mImage);
        holder.name.setText(user.mName);

        return view;
    }
    public void remove(int position)
    {
        mUsers.remove(position);
        notifyDataSetChanged();

    }

    private class ViewHolder{
       // public ImageView image ;
        public TextView name ;
    }


}
