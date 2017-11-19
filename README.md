# ChooseDialog
选择照片提示框
![image](https://github.com/maqingwei/ChooseDialog/raw/master/screenshots/choose.png)

WebView部分：
  
private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebview = findViewById(R.id.webview);
        WebSettings webSettings = mWebview.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

//        复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
      String url=  getIntent().getStringExtra("url");
      mWebview.loadUrl(url);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
         if (getIntent().getStringExtra("url").equals("http://all.vic.sina.com.cn/210711jingdongfangboe/index.html")){
            Log.e("l","s");
            finish();
        }else if ((keyCode == KEYCODE_BACK) && mWebview.canGoBack()) {
            mWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

Activity 部分：

private RecyclerView mHome_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHome_recycler = findViewById(R.id.home_recycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mHome_recycler.setLayoutManager(linearLayoutManager);

        initData();
    }

    private void initData() {
        List<Object> mHome_data=new ArrayList<>();

        final List<Banner_bean> banner_beans_list=new ArrayList<>();
        banner_beans_list.add(new Banner_bean("https://storessl.boe.com/sc-webui/upload/cms/article/images/d7a4773d-ac61-495a-af5e-28bc5e49e061.jpeg",
                "http://all.vic.sina.com.cn/210711jingdongfangboe/index.html"));
        banner_beans_list.add(new Banner_bean("https://storessl.boe.com/sc-webui/upload/cms/article/images/194fd9cd-001e-40ab-b287-99ae7b53b646.jpeg",
                ""));
//        RxVolley.get("http://www.kymjs.com/feed.xml", new HttpCallback() {
//            @Override
//            public void onSuccess(String t) {
////                Gson gson=new
////                JSONObject f=JSONObject.wrap();
//            }
//
//            @Override
//            public void onFailure(int errorNo, String strMsg) {
//                banner_beans_list.add(new Banner_bean("https://storessl.boe.com/sc-webui/upload/cms/article/images/013b828e-9bcd-4cdc-bf51-48984a0fc4fb.jpeg",
//                        "https://m.boe.com/mobileStore/index.html#!mallPeriProduct/productInfo/%257B%2522productId%2522%3A%252210138%2522%2C%2522btnback2%2522%3A%2522btnback2%2522%257D"));
//            }
//        });
        mHome_data.add(banner_beans_list);

        mHome_data.add("df");

        List<grid_bean> beanList=new ArrayList<>();
        beanList.add(new grid_bean(R.mipmap.newscenter,"新闻中心"));
        beanList.add(new grid_bean(R.mipmap.btnlink_productservice,"产品服务"));
        beanList.add(new grid_bean(R.mipmap.btnlink_createchnology,"产品创新"));
        beanList.add(new grid_bean(R.mipmap.btnlink_investmentship,"投资者关系"));
        beanList.add(new grid_bean(R.mipmap.btnlink_joinus,"加入我们"));
        beanList.add(new grid_bean(R.mipmap.btnlink_videocenter,"BOE说"));
        mHome_data.add(beanList);

        mHome_data.add("df");
        mHome_data.add("df");
        mHome_data.add("df");



 mHome_recycler.setAdapter(new HomeAdapter(this,mHome_data));
    }


HomeAdapter部分：

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public final Context context;
    public List mlist;
//    List<grid_bean> mList;

    public HomeAdapter(Context context, List mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view=null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case 0:
                return holder = new BannerHolder(LayoutInflater.from(context).inflate(R.layout.item_banner,parent,false));
            case 1:
                return holder =  new SearchHolder(LayoutInflater.from(context).inflate(R.layout.item_search,parent,false));
            case 2:
                return  holder = new GridleHolder(LayoutInflater.from(context).inflate(R.layout.item_gradeview,parent,false));
            case 3:
                return  holder = new TuijianHolder(LayoutInflater.from(context).inflate(R.layout.item_tuijian,parent,false));
            case 4:
                return  holder = new GuanyuHolder(LayoutInflater.from(context).inflate(R.layout.item_jieshao,parent,false));
            case 5:
                return holder =  new ButtomHolder(LayoutInflater.from(context).inflate(R.layout.item_buttom,parent,false));
        }
        return  holder =  new ButtomHolder(LayoutInflater.from(context).inflate(R.layout.item_buttom,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
       int viewtype= holder.getItemViewType();
        switch (viewtype){
            case 0:
                BannerHolder bannerHolder= (BannerHolder) holder;
                final List<Banner_bean> imglist=(List<Banner_bean>)mlist.get(position);

                List img=new ArrayList();
                img.add(imglist.get(0).imgUrl);
                img.add(imglist.get(1).imgUrl);
                class ImgLoad extends ImageLoader{
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                }
                bannerHolder.mBanner.setImages(img).setImageLoader(new ImgLoad()).start();
                bannerHolder.mBanner.setOnBannerListener(new OnBannerListener() {
                    Intent intent=new Intent(context,WebViewActivity.class);
                    @Override
                    public void OnBannerClick(int position) {
                        switch (position){
                            case 0:
                                intent.putExtra("url",imglist.get(position).webUrl);
                                break;
                            case 1:
                                intent.putExtra("url",imglist.get(position).webUrl);
                                break;
                        }
                        context.startActivity(intent);
                    }
                });
                break;
            case 1:
                final SearchHolder baolder= (SearchHolder) holder;
                baolder.btn_seach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String s = baolder.img.getText().toString();
                        String url="http://www.boe.com/search/ps.html?_q="+s;
                        Intent intent=new Intent(context,WebViewActivity.class);
                        intent.putExtra("url",url);
                           context.startActivity(intent);
                           baolder.img.setText("");
                           baolder.img.clearFocus();
                    }
                });
                break;
            case 2:
                GridleHolder baoclder= (GridleHolder) holder;
                GridLayoutManager   managerjj = new GridLayoutManager(context,3);
                baoclder.grid_recy.setLayoutManager(managerjj);
                DividerGridItemDecoration divider = new DividerGridItemDecoration(context);
                baoclder.grid_recy.addItemDecoration(divider);
                baoclder.grid_recy.setAdapter(new GridAdapter(context,(List<grid_bean>)mlist.get(position)));
                break;
            case 3:
                TuijianHolder cc= (TuijianHolder) holder;
                cc.btn_huaping.setOnClickListener(new clik());
                cc.btn_jiankang.setOnClickListener(new clik());
                break;
            case 4:
                GuanyuHolder baol5der= (GuanyuHolder) holder;
                baol5der.btn_guau.setOnClickListener(new clik());
                baol5der.btn_shangc.setOnClickListener(new clik());
                baol5der.btn_ditu.setOnClickListener(new clik());
                baol5der.btn_yinsi.setOnClickListener(new clik());
                baol5der.btn_yingy.setOnClickListener(new clik());
                break;
            case 5:
                ButtomHolder bao6lder= (ButtomHolder) holder;
                break;
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }else if (position==1){
            return 1;
        }else if (position==2){
            return 2;
        }else if (position==3){
            return 3;
        }else if (position==4){
            return 4;
        }else if (position==5){
            return 5;
        }
        return 6;
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class BannerHolder extends RecyclerView.ViewHolder{

        private final Banner mBanner;

        public BannerHolder(View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.item_banner);
        }
    }
    class SearchHolder extends RecyclerView.ViewHolder{

        private final EditText img;
        private final Button btn_seach;

        public SearchHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_search);
            btn_seach = itemView.findViewById(R.id.btn_search);
        }
    }
    class GridleHolder extends RecyclerView.ViewHolder{
     public    RecyclerView   grid_recy;

        public GridleHolder(View itemView) {
            super(itemView);
            grid_recy = itemView.findViewById(R.id.item_grideview);

        }
    }
    class TuijianHolder extends RecyclerView.ViewHolder{

        private final Button btn_huaping;
        private final Button btn_jiankang;

        public TuijianHolder(View itemView) {
            super(itemView);
            btn_huaping = itemView.findViewById(R.id.btn_huaping);
            btn_jiankang = itemView.findViewById(R.id.btn_jiankang);
        }
    }
    class GuanyuHolder extends RecyclerView.ViewHolder{

        private final Button btn_guau;
        private final Button btn_shangc;
        private final Button btn_ditu;
        private final Button btn_yinsi;
        private final Button btn_yingy;

        public GuanyuHolder(View itemView) {
            super(itemView);
            btn_guau = itemView.findViewById(R.id.btn_guanyu);
            btn_shangc = itemView.findViewById(R.id.btn_shangcheng);
            btn_ditu = itemView.findViewById(R.id.btn_ditu);
            btn_yinsi = itemView.findViewById(R.id.btn_yinsi);
            btn_yingy = itemView.findViewById(R.id.btn_yingyu);
        }
    }
    class ButtomHolder extends RecyclerView.ViewHolder{
        public ButtomHolder(View itemView) {
            super(itemView);
        }
    }
}

GridAdapter 部分：
public class GridAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<grid_bean> list;
    Context context;

    public GridAdapter(Context context,List<grid_bean> list) {
        this.list = list;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gride_item,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Holder holder1= (Holder) holder;
        holder1.tex.setImageResource(list.get(position).img);
        holder1.img.setText(list.get(position).text);
        final Intent intent=new Intent(context,WebViewActivity.class);
        holder1.layut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (position){
                    case 0:
                        intent.putExtra("url","http://www.boe.com/news/pn.html");
                                break;
                    case 1:
                        intent.putExtra("url","http://www.boe.com/product/xsqj/ppx.html");
                                break;
                    case 2:
                        intent.putExtra("url","http://www.boe.com/cxkj/pc.html");
                        break;
                    case 3:
                        intent.putExtra("url","http://www.boe.com/tzzgx/pt.html");
                        break;
                    case 4:
                        intent.putExtra("url","http://www.boe.com/join/pj.html");
                        break;
                    case 5:
                        intent.putExtra("url","http://www.boe.com/dmtzx/boeshuo/pdb.html");
                        break;
                }
                context.startActivity(intent);
            }
        });
    }

    class Holder extends RecyclerView.ViewHolder{

        private TextView img;
        private ImageView tex;
        private RelativeLayout layut;

        public Holder(View itemView) {
            super(itemView);
            tex = itemView.findViewById(R.id.gride_img);
            img = itemView.findViewById(R.id.gride_text);
            layut =itemView.findViewById(R.id.gridelayout);
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

}

DividerGridItemDecoration部分：

        import android.content.Context;
        import android.content.res.TypedArray;
        import android.graphics.Canvas;
        import android.graphics.Rect;
        import android.graphics.drawable.Drawable;
        import android.support.v7.widget.GridLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.RecyclerView.LayoutManager;
        import android.support.v7.widget.RecyclerView.State;
        import android.support.v7.widget.StaggeredGridLayoutManager;
        import android.view.View;

/**
 *
 * @author zhy
 *
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration
{

    private static final int[] ATTRS = new int[] { android.R.attr.listDivider };
    private Drawable mDivider;

    public DividerGridItemDecoration(Context context)
    {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, State state)
    {

        drawHorizontal(c, parent);
        drawVertical(c, parent);

    }

    private int getSpanCount(RecyclerView parent)
    {
        // 列数
        int spanCount = -1;
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

    public void drawHorizontal(Canvas c, RecyclerView parent)
    {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin
                    + mDivider.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent)
    {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                int childCount)
    {
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else
            {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount)
    {
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,
                               RecyclerView parent)
    {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
        {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        } else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
        {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else
        {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(),
                    mDivider.getIntrinsicHeight());
        }
    }
}

click 部分：
class clik implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(view.getContext(),WebViewActivity.class);
        switch (view.getId()){
            case R.id.btn_guanyu:
                intent.putExtra("url","http://www.boe.com/about/pa.html");
                break;
            case R.id.btn_shangcheng:
                intent.putExtra("url","https://m.boe.com/mobileStore/index.html#!mall/%257B%257D");
                break;
            case R.id.btn_ditu:
                intent.putExtra("url","http://www.boe.com/wzdt/pw.html");
                break;
            case R.id.btn_yinsi:
                intent.putExtra("url","http://www.boe.com/ystk/py.html");
                break;
            case R.id.btn_yingyu:
                intent.putExtra("url","http://www.boe.com/en/index/pei.html");
                break;
            case R.id.btn_huaping:
                intent.putExtra("url","http://www.boe.com/en/index/pei.html");
                break;
            case R.id.btn_jiankang:
//                intent.putExtra("url","http://www.boe.com/en/index/pei.html");
                break;
        }
        view.getContext().startActivity(intent);
    }
}

Banner_bean部分：

public class Banner_bean {
       public String imgUrl;
       public String webUrl;

    public Banner_bean(String imgUrl, String webUrl) {
        this.imgUrl = imgUrl;
        this.webUrl = webUrl;
    }
}

grid_bean 部分：
public class grid_bean {
    public int img;
    public String text;

    public grid_bean(int img, String text) {
        this.img = img;
        this.text = text;
    }
}

主布局：
<android.support.v7.widget.RecyclerView
    android:id="@+id/home_recycler"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent" />

webview布局：
<WebView
    android:id="@+id/webview"
    android:layout_width="match_parent"
    android:layout_height="match_parent"></WebView>

gride_item布局
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/gridelayout"
    android:background="@drawable/kuang">

    <ImageView
        android:id="@+id/gride_img"
        android:src="@mipmap/ic_launcher"
        android:layout_width="60dp"
        android:layout_centerInParent="true"
        android:layout_height="60dp" />
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/gride_text"
    android:textColor="#000"
    android:layout_below="@+id/gride_img"
    android:layout_centerHorizontal="true"
    android:text="创新科技"
text_size=13sp
    android:layout_gravity="center"
    android:layout_marginTop="5dp"/>

</RelativeLayout>

item_banner布局

<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
    android:layout_height="wrap_content">
<com.youth.banner.Banner
    android:id="@+id/item_banner"
    android:src="@mipmap/ic_launcher"
    android:layout_width="match_parent"
    android:layout_height="170dp" />
</RelativeLayout>
item_buttomb布局
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
    android:layout_height="wrap_content">
<ImageView
    android:id="@+id/item_buttom"
    android:src="@mipmap/a20171118085407"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
</RelativeLayout>
item_gradeview布局
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
<android.support.v7.widget.RecyclerView
    android:id="@+id/item_grideview"
    android:layout_width="match_parent"
    android:layout_marginLeft="5dp"
    android:layout_marginRight="5dp"
    android:layout_height="match_parent" />
</LinearLayout>
item_jieshao布局
<LinearLayout
    android:layout_margin="10dp"
    xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">
    <Button
    android:id="@+id/btn_guanyu"
    android:text="关于我们"
        android:textSize="13sp"
        android:layout_marginLeft="15dp"
        android:layout_weight="1"
    android:background="@null"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
    <include layout="@layout/line" />

    <Button
        android:textSize="13sp"
        android:layout_weight="1"
        android:id="@+id/btn_shangcheng"
        android:text="BOE商城"
        android:background="@null"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
    <include layout="@layout/line" />

    <Button
        android:textSize="13sp"
        android:layout_weight="1"
        android:id="@+id/btn_ditu"
        android:text="网站地图"
        android:background="@null"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
    <include layout="@layout/line" />

    <Button
        android:textSize="13sp"
        android:layout_weight="1"
        android:id="@+id/btn_yinsi"
        android:text="隐私条款"
        android:background="@null"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <include layout="@layout/line" />

    <Button
        android:textSize="13sp"
        android:layout_weight="1"
        android:id="@+id/btn_yingyu"
        android:text="English"
        android:layout_marginRight="15dp"
        android:background="@null"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />


</LinearLayout>
item_search布局
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:focusable="true"
    android:layout_margin="5dp"
    android:focusableInTouchMode="true">
<EditText
    android:focusable="true"
    android:hint="Search"
    android:id="@+id/item_search"
    android:background="@drawable/kuang"
    android:src="@mipmap/ic_launcher"
    android:layout_width="match_parent"
    android:layout_height="30dp" />

    <Button
        android:id="@+id/btn_search"
        android:layout_width="20dp"
        android:layout_height="20dp"
        android:layout_marginTop="5dp"
        android:layout_alignParentRight="true"

        android:background="@mipmap/btn_search" />
</RelativeLayout>
item_tuijian布局
<RelativeLayout
    android:layout_margin="5dp"
    xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
    android:layout_height="wrap_content">
<TextView
    android:layout_marginTop="5dp"
    android:id="@+id/item_tuijian_text"
    android:text="应用推荐"
    android:textColor="#000"
    android:textSize="12sp"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
    <View
        android:id="@+id/line_"
        android:layout_marginTop="3dp"
        android:layout_below="@id/item_tuijian_text"
        android:background="#000"
        android:layout_width="match_parent"
        android:layout_height="1dp"/>

    <Button
        android:id="@+id/btn_huaping"
        android:layout_marginTop="5dp"
        android:background="@mipmap/igallery"
        android:layout_below="@id/line_"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:layout_marginLeft="15dp"/>
    <Button
        android:id="@+id/btn_jiankang"
        android:layout_marginTop="5dp"
        android:layout_toRightOf="@id/btn_huaping"
        android:layout_marginLeft="25dp"
        android:background="@mipmap/health"
        android:layout_below="@id/line_"
        android:layout_width="50dp"
        android:layout_height="50dp" />
    <TextView
        android:id="@+id/text_huaping"
        android:text="画屏"
        android:textColor="#000"
        android:textSize="10sp"
        android:layout_below="@id/btn_huaping"
        android:layout_marginLeft="25dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignLeft="@+id/btn_jiankang"
        android:layout_below="@id/btn_jiankang"
        android:layout_alignStart="@+id/btn_jiankang"
        android:layout_marginLeft="2dp"
        android:text="移动健康"
        android:textColor="#000"
        android:textSize="10sp" />
</RelativeLayout>
line布局
<View xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:background="#000"
    android:layout_width="2dp"
    android:layout_height="20dp"
    android:layout_marginTop="15dp"
    tools:showIn="@layout/item_jieshao" />
divider_bg     draw able：
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle" >

  <solid android:color="#ffffff"/>

    <size android:height="13dp" android:width="25dp"/>

</shape>
kuang    drawble
<shape xmlns:android="http://schemas.android.com/apk/res/android" android:shape="rectangle" >
<stroke android:width="0.5dp"></stroke>

</shape>


《style》 里面加：
<item name="android:listDivider">@drawable/divider_bg</item>
