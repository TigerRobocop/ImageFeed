package com.unibratec.livia.picturefeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Livia on 20/10/2015.
 */
public class ImageAdapter extends ArrayAdapter<Image> {
    public ImageAdapter(Context context, List<Image> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView ==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.image_list_item, null);
            viewHolder = new ViewHolder();

            viewHolder.imgThumb = (ImageView)convertView.findViewById(R.id.imageView_thumb);
            viewHolder.txtId = (TextView)convertView.findViewById(R.id.textView_id);
            viewHolder.txtTitle = (TextView)convertView.findViewById(R.id.textView_title);

            convertView.setTag(viewHolder);
        }else  {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Image img = getItem(position);

        if(img != null){
            Picasso.with(getContext()).load(img.thumbnailUrl).into(viewHolder.imgThumb);
            viewHolder.txtId.setText(img.id);
            viewHolder.txtTitle.setText(img.title);
        }

        return convertView;
    }
}

class ViewHolder {
    ImageView imgThumb;
    TextView txtId;
    TextView txtTitle;
}
