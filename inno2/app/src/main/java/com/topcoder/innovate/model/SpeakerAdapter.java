package com.topcoder.innovate.model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.topcoder.innovate.R;

import java.util.List;

/**
 * Created by Sdite on 2017/9/3.
 */

public class SpeakerAdapter extends ArrayAdapter<Speaker> {
    public int resourceId;

    private int selectedPosition = -1;// 选中的位置
    private Context context;
    private List<Speaker>contactdata;

    public SpeakerAdapter(Context context, int textViewResourceId,
                          List<Speaker> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.context = context;
        contactdata = objects;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    private ViewHolder viewHolder;

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent){
        View view;
        Speaker speaker = getItem(position);
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.speakerImage = (ImageView) view.findViewById(R.id.speaker_pic);
            viewHolder.speakerName = (TextView) view.findViewById(R.id.speaker_name);
            viewHolder.speakerTitle = (TextView) view.findViewById(R.id.speaker_title);
            viewHolder.speakerLayout = (RelativeLayout) view.findViewById(R.id.speaker_layout);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        if (selectedPosition == position) {
            viewHolder.speakerName.setSelected(true);
            viewHolder.speakerImage.setPressed(true);
            viewHolder.speakerTitle.setPressed(true);
            viewHolder.speakerLayout.setHovered(true);
            view.setBackgroundColor(convertView.getResources().getColor(R.color.colorOrange));
        } else {
            viewHolder.speakerName.setSelected(false);
            viewHolder.speakerImage.setPressed(false);
            viewHolder.speakerTitle.setPressed(false);
            view.setBackgroundColor(Color.TRANSPARENT);
        }
        viewHolder.speakerImage.setImageResource(speaker.getPicId());
        viewHolder.speakerName.setText(speaker.getName());
        viewHolder.speakerTitle.setText(speaker.getTitle());
        return view;
    }
}
