package com.cd.wzjkj.canyi.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.cd.wzjkj.canyi.deletlistview.ListItemDelete;
import com.cd.wzjkj.canyi.entity.CaiPing;
import com.cd.wzjkj.canyi.entity.Shopcar;
import com.cd.wzjkj.canyi.entity.Shopcar2;
import com.cd.wzjkj.canyi.entity.TuanGuo;
import com.google.gson.Gson;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类描述：
 * 创建时间：2016/9/5,0005 上午:9:50
 * 修改人：Administrator
 * 修改时间：2016/9/5,0005 上午:9:50
 * 修改备注：
 */
public class Tools {
    public static ListItemDelete itemDelete;
    public static DbUtils shopdb;
    public static DbUtils addressdb;
    public static DbUtils userdb;
    public static double latitudeold;
    public static double longitudeold;

    public static double getkm(double latitude,double longitude){
        return todouble(Math.sqrt(Math.pow(latitude-latitudeold,2)+Math.pow(longitude-longitudeold,2)),3);
    }

    public static void ItemDeleteReset() {
    }


    /**
     * @param d   要转化的double
     * @param num 保留小数点位数
     * @return 保留num位小数的double
     * @quthor Administrator
     * @time 2016/8/5,0005 下午:17:25
     */
    public static double todouble(double d, int num) {
        BigDecimal bg = new BigDecimal(d);
        double f1 = bg.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }


    /**
     * @param d 要转化的double
     * @return 保留num位小数的double
     * @quthor Administrator
     * @time 2016/8/5,0005 下午:17:25
     */
    public static int getIntfordouble(double d) {
        BigDecimal bg = new BigDecimal(d);
        double f1 = bg.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
        return Integer.parseInt(new java.text.DecimalFormat("0").format(f1));
    }

    /**
     * 〈一句话功能简述〉
     * 〈功能详细描述〉
     *
     * @param //评分
     * @param //5个全星
     * @param //1个半星
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static void setXingji(double xingji, ImageView[] xinxins, View xinxinhalf) {
        for (int i = 0; i < (int) xingji; i++) {
            if(i>=5){
                return;
            }
            xinxins[i].setVisibility(View.VISIBLE);
        }
        if (xingji - Tools.getIntfordouble(xingji) < 0.5 && Tools.getIntfordouble(xingji) != 5) {
            xinxins[Tools.getIntfordouble(xingji)].setVisibility(View.GONE);
            xinxinhalf.setVisibility(View.VISIBLE);
        } else {
            xinxinhalf.setVisibility(View.GONE);
        }
    }

    /**
     * 〈一句话功能简述〉
     * 〈功能详细描述〉
     *
     * @param
     * @param context
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static Bitmap blurBitmap(Bitmap bitmap, Context context) {

        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(context.getApplicationContext());

        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur
        blurScript.setRadius(10.f);

        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
        bitmap.recycle();

        //After finishing everything, we destroy the Renderscript.
        rs.destroy();

        return outBitmap;


    }

    /**
     * 〈一句话功能简述〉
     * 〈功能详细描述〉
     *
     * @param
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

    public static List<CaiPing> getCountByCaiPings(List<CaiPing> caiPings) throws DbException {
        for (int i = 0; i < caiPings.size(); i++) {
            Shopcar shopcar = shopdb.findFirst(Selector.from(Shopcar.class).where(WhereBuilder.b("userid", "=", Httptools.user.getUserid()).and("spid", "=", caiPings.get(i).getId())));
            if(shopcar==null){
                caiPings.get(i).setCount(0);
            }else {
                caiPings.get(i).setCount(shopcar.getCount());
            }
        }
        return caiPings;
    }
    public static List<CaiPing> getCountByCaiPings_nocount(List<CaiPing> caiPings) throws DbException {
        List<CaiPing> caiPings1 = new ArrayList<>();
        for (int i = 0; i < caiPings.size(); i++) {
            Shopcar shopcar = shopdb.findFirst(Selector.from(Shopcar.class).where(WhereBuilder.b("userid", "=", Httptools.user.getUserid()).and("spid", "=", caiPings.get(i).getId())));
            if(shopcar==null){
                caiPings.get(i).setCount(0);
            }else {
                caiPings.get(i).setCount(shopcar.getCount());
                caiPings1.add(caiPings.get(i));
            }
        }
        return caiPings1;
    }
    public static List<Shopcar2> getCountByTuanGou(List<TuanGuo> caiPings) throws DbException {
        List<Shopcar2> shopcars = new ArrayList<>();
        for (int i = 0; i < caiPings.size(); i++) {
            List<Shopcar2> scs = shopdb.findAll(Selector.from(Shopcar2.class).where(WhereBuilder.b("userid", "=", Httptools.user.getUserid()).and("spid", "=", caiPings.get(i).getId())));
            if(scs==null){
                scs = new ArrayList<>();
            }
            for(int j=0;j<scs.size();j++){
                shopcars.add(scs.get(j));
            }
        }
        return shopcars;
    }

    public static void plus(CaiPing caiPing) {
        try {
            if (caiPing.getCount() == 1) {
                Shopcar shopcar = new Shopcar();
                shopcar.setCount(1);
                shopcar.setUserid(Httptools.user.getUserid());
                shopcar.setSpid(caiPing.getId());
                shopdb.save(shopcar);
            }else{
                Shopcar shopcar = new Shopcar();
                shopcar.setCount(caiPing.getCount());
                shopcar.setUserid(Httptools.user.getUserid());
                shopcar.setSpid(caiPing.getId());
                shopdb.update(shopcar,WhereBuilder.b("userid", "=", Httptools.user.getUserid()).and("spid", "=", caiPing.getId()),"count");
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public static void minus(CaiPing caiPing) {
        try {
            if (caiPing.getCount() == 0) {
                shopdb.delete(Shopcar.class,WhereBuilder.b("userid", "=", Httptools.user.getUserid()).and("spid", "=", caiPing.getId()));
            }else{
                Shopcar shopcar = new Shopcar();
                shopcar.setCount(caiPing.getCount());
                shopcar.setUserid(Httptools.user.getUserid());
                shopcar.setSpid(caiPing.getId());
                shopdb.update(shopcar,WhereBuilder.b("userid", "=", Httptools.user.getUserid()).and("spid", "=", caiPing.getId()),"count");
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public static void plus(TuanGuo caiPing) {

        try {
            int num = 0;
            int first = getchoose(caiPing.getFirst_cp());
            int second = getchoose(caiPing.getSecond_cp());
            int third = getchoose(caiPing.getThird_cp());
            int tianpint = getchoose(caiPing.getTianping_cp());
            int drink = getchoose(caiPing.getDrinks_cp());
            String str = new Gson().toJson(caiPing);
            List<Shopcar2> scs = shopdb.findAll(Selector.from(Shopcar2.class).where(WhereBuilder.b("userid", "=", Httptools.user.getUserid()).and("spid", "=", caiPing.getId())));
            if(scs==null){
                scs = new ArrayList<>();
            }
            for(int i=0;i<scs.size();i++){
                Shopcar2 sc2 = scs.get(i);
                if(sc2.getFirst()==first&&sc2.getSecond()==second&&sc2.getThird()==third&&sc2.getTianping()==tianpint&&sc2.getDrink()==drink){
                    scs.get(i).setCount(scs.get(i).getCount()+1);
                    num = 1;
                    shopdb.update(sc2,WhereBuilder.b("userid", "=", Httptools.user.getUserid()).and("spid", "=", caiPing.getId()),"count");
                }
            }
            if(num==0){
                Shopcar2 shopcar2 = new Shopcar2();
                shopcar2.setCount(1);
                shopcar2.setData(System.currentTimeMillis());
                shopcar2.setUserid(Httptools.user.getUserid());
                shopcar2.setSpid(caiPing.getId());
                shopcar2.setFirst(first);
                shopcar2.setSecond(second);
                shopcar2.setThird(third);
                shopcar2.setTianping(tianpint);
                shopcar2.setDrink(drink);
                shopcar2.setDatajson(str);
                shopdb.save(shopcar2);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private static int getchoose(List<CaiPing> caiPings) {
        for(int i=0;i<caiPings.size();i++){
            if(caiPings.get(i).isChoosed()){
                return i;
            }
        }
        return 0;
    }
    public static String[] getstrsByString(String str){
        if("".equals(str)){
            return new String[]{};
        }
        String str1 = str.substring(1,str.length()-1);
        return str1.split(",");
    }

    public static void minus(TuanGuo caiPing) throws DbException {
        Shopcar2 sc2 = shopdb.findFirst(Selector.from(Shopcar2.class).where(WhereBuilder.b("userid", "=", Httptools.user.getUserid()).and("spid", "=", caiPing.getId()).and("data","=",caiPing.getData())));
        if(sc2.getCount()==1){
            shopdb.delete(sc2);
        }else{
            sc2.setCount(sc2.getCount()-1);
            shopdb.update(sc2,WhereBuilder.b("userid", "=", Httptools.user.getUserid()).and("spid", "=", caiPing.getId()),"count");
        }
    }

    public static boolean getViewNull(EditText et) {
        return "".equals(et.getText().toString())||"null".equals(et.getText().toString())||et.getText()==null;
    }

    public static String getorderTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        String time= sdf.format( new Date());
        Log.e("TAG",time);
        return time;
    }
}
