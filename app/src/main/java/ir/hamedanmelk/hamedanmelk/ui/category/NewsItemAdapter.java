package ir.hamedanmelk.hamedanmelk.ui.category;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.micro.NewsModel;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

public class NewsItemAdapter extends BaseAdapter {
    private final ArrayList<NewsModel> NewsModels;
    private final Context context;
    private static final String TAG = "NewsItemAdapter";

    public NewsItemAdapter(ArrayList<NewsModel>items, Context c) {
        NewsModels = items;
        context = c;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = ViewGroup.inflate(parent.getContext(), R.layout.fragment_news_item_adapter, null);
        }
        ImageView logoImg = (ImageView)view.findViewById(R.id.NewsFragmentLogoImg);
        TextView  titleTxt= (TextView)view.findViewById(R.id.NewsFragmentTitleTxt);
        titleTxt.setText(NewsModels.get(position).getTitle());
        if(NewsModels.get(position).getImage()!="null") {
//            new DownloadImage(logoImg).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Urls.getBaseURL() + "/" + NewsModels.get(position).getImage());
            Glide.with(context).load(Urls.getBaseURL() + "/" + NewsModels.get(position).getImage()).into(logoImg);
        }
        logoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(URLUtil.isValidUrl("/ShowNews/"+NewsModels.get(position).getId())){
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("/ShowNews/"+NewsModels.get(position).getId()));
                    context.startActivity(i);
                }
                else
                {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(Urls.getBaseURL()+"/ShowNews/"+ NewsModels.get(position).getId()));
                    context.startActivity(i);
                }

            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return NewsModels.size();
    }

    @Override
    public Object getItem(int position) {
        return NewsModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}