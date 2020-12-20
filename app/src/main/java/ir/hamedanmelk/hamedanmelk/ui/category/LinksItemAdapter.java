package ir.hamedanmelk.hamedanmelk.ui.category;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.micro.LinksModel;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

public class LinksItemAdapter extends BaseAdapter {
    private final ArrayList<LinksModel> linksModels;
    private final Context context;
    private static final String TAG = "LinksItemAdapter";

    public LinksItemAdapter(ArrayList<LinksModel>items,Context c) {
        linksModels = items;
        context = c;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = ViewGroup.inflate(parent.getContext(), R.layout.fragment_links_item_adapter, null);
        }
        ImageView logoImg = (ImageView)view.findViewById(R.id.LinksFragmentLogoImg);
        TextView  titleTxt= (TextView)view.findViewById(R.id.LinksFragmentTitleTxt);
        titleTxt.setText(linksModels.get(position).getTitle());
        if(linksModels.get(position).getLogo()!="null") {
            new DownloadImage(logoImg).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Urls.getBaseURL() + "/" + linksModels.get(position).getLogo());
        }
        logoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: "+Uri.parse(linksModels.get(position).getLink()));
                if(URLUtil.isValidUrl(linksModels.get(position).getLink())){
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(linksModels.get(position).getLink()));
                    context.startActivity(i);
                }
                else
                {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(Urls.getBaseURL()+linksModels.get(position).getLink()));
                    context.startActivity(i);
                }

            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return linksModels.size();
    }

    @Override
    public Object getItem(int position) {
        return linksModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}