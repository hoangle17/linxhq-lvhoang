package com.example.videodemo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private int layout;
    private ArrayList<VideoYoutube> videoYoutubeList;
    private ArrayList<VideoYoutube> backupData;

    public VideoAdapter(Context context, int layout, ArrayList<VideoYoutube> videoYoutubeList) {
        this.context = context;
        this.layout = layout;
        this.videoYoutubeList = videoYoutubeList;
    }

    public void setData(ArrayList<VideoYoutube> videoYoutubes) {
        this.videoYoutubeList = videoYoutubes;
    }

    @Override
    public int getCount() {
        return videoYoutubeList.size();
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
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (backupData == null) {
                    backupData = new ArrayList<>(videoYoutubeList);
                }
                if (constraint == null || constraint.length() == 0) {
                    filterResults.values = backupData;
                    filterResults.count = backupData.size();
                } else {
                    ArrayList<VideoYoutube> newData = new ArrayList<>();
                    for (VideoYoutube c : videoYoutubeList) {
                        if (c.getTitle().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            newData.add(c);
                        }
                    }
                    filterResults.values = newData;
                    filterResults.count = newData.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                ArrayList<VideoYoutube> tmp = (ArrayList<VideoYoutube>) results.values;
                videoYoutubeList.clear();
                for (VideoYoutube c : tmp) {
                    videoYoutubeList.add(c);
                }
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    private class ViewHolder {
        ImageView imgThumbnail;
        TextView txtTitle;
        TextView txtChannel;
        TextView txtTime;
    }

    public String timeHumanReadable(String youtubeTimeFormat) {
// Gets a PThhHmmMssS time and returns a hh:mm:ss time

        String
                temp = "",
                hour = "",
                minute = "",
                second = "",
                returnString;

        // Starts in position 2 to ignore P and T characters
        for (int i = 2; i < youtubeTimeFormat.length(); ++i) {
            // Put current char in c
            char c = youtubeTimeFormat.charAt(i);

            // Put number in temp
            if (c >= '0' && c <= '9')
                temp = temp + c;
            else {
                // Test char after number
                switch (c) {
                    case 'H': // Deal with hours
                        // Puts a zero in the left if only one digit is found
                        if (temp.length() == 1) temp = "0" + temp;

                        // This is hours
                        hour = temp;

                        break;

                    case 'M': // Deal with minutes
                        // Puts a zero in the left if only one digit is found
                        if (temp.length() == 1) temp = "0" + temp;

                        // This is minutes
                        minute = temp;

                        break;

                    case 'S': // Deal with seconds
                        // Puts a zero in the left if only one digit is found
                        if (temp.length() == 1) temp = "0" + temp;

                        // This is seconds
                        second = temp;

                        break;

                } // switch (c)

                // Restarts temp for the eventual next number
                temp = "";

            } // else

        } // for

        if (hour == "" && minute == "") // Only seconds
            returnString = second;
        else {
            if (hour == "") // Minutes and seconds
                returnString = minute + ":" + second;
            else // Hours, minutes and seconds
                returnString = hour + ":" + minute + ":" + second;
        }

        // Returns a string in hh:mm:ss format
        return returnString;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.txtTitle = view.findViewById(R.id.textviewTitle);
            viewHolder.imgThumbnail = (ImageView) view.findViewById(R.id.imageviewThumbnail);
            viewHolder.txtChannel = view.findViewById(R.id.textviewChannel);
            viewHolder.txtTime = view.findViewById(R.id.textViewTime);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        VideoYoutube video = videoYoutubeList.get(i);
        viewHolder.txtTitle.setText(video.getTitle());
        viewHolder.txtChannel.setText(video.getChannel());
        viewHolder.txtTime.setText(timeHumanReadable(video.getDuration()));
        Picasso.get().load(video.getThumbnail()).into(viewHolder.imgThumbnail);
        return view;
    }
}