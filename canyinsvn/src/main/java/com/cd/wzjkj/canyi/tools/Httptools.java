package com.cd.wzjkj.canyi.tools;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.entity.Paramss;
import com.cd.wzjkj.canyi.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2016/8/5,0005.
 */
public class Httptools {
	private static final String COOKIES_NAME = "COOKIES1";
	public static User user = new User();
	private static ProgressDialog mdialog;
//	public static String Http = "http://192.168.1.138:81";
//	public static String Http = "http://192.168.1.198:81";
//	public static String Http = "http://118.89.31.123:81";
public static String Http = "http://customerapi.toeat.eu";
//	public static String Http2 = "http://118.89.31.123";
	public static String Http2 = "http://enjoyimage.toeat.eu";
//	public static String Http = "http://192.168.1.198:85/HOME/Index/2";
	private static Context context1;


	protected static Handler handlertools = new Handler() {
		public void handleMessage(Message msg) {
			if(msg.what==1){

			}
		}
	};
	public static int timeout = 500;
	private static int size;
	private static boolean flag;

	/**
	 * 封装VOLLEY网络请求
	 *
	 * @quthor Administrator
	 * @time 2016/8/9,0009 下午:14:56
	 */
	public static void sendpost1(Context context, final ArrayList<Paramss> paramsses, final Handler handler, boolean isshowdailog){
		RequestQueue mQueue = Volley.newRequestQueue(context);
		if(isshowdailog){
			mdialog = ProgressDialog.show(context, "", "正在为您努力加载", true);
			mdialog.show();
			size= paramsses.size();
		}
		context1 = context;
		flag = true;
		for(int i=0;i<paramsses.size();i++) {
			final Paramss p = paramsses.get(i);
			String str="";
			if(p.getMap()!=null) {
				str = getparams(p.getMap());
                Log.e("TAG", Http + p.getUrl() + str);
			}
			StringRequest stringRequest = new StringRequest(Request.Method.GET, Httptools.Http+p.getUrl()+str,  new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					Log.e( "zhang: ","response="+response );
					Message msg = new Message();
					try {
						msg.obj = new JSONObject(response);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					size--;
					if(size==0){
						hide();
					}
					msg.what = p.getNum();
					handler.sendMessage(msg);
				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					size--;
					flag = false;
					if(size==0){
						hide();
					}
				}
			}) {
				@Override
				public Map<String, String> getHeaders() throws AuthFailureError {
					HashMap localHashMap = new HashMap();
					localHashMap.put("Cookie", getSettingNote(COOKIES_NAME));//向请求头部添加Cookie-本地得到cookie
					return localHashMap;
				}
				@Override
				protected Map<String, String> getParams() throws AuthFailureError {
					if(p.getMap()==null){
						return new HashMap<String, String>();
					}
					return p.getMap();
				}
				@Override
				protected Response<String> parseNetworkResponse(NetworkResponse response) {
					try {

						Map<String, String> responseHeaders = response.headers;
						String rawCookies = responseHeaders.get("Set-Cookie");
						if(rawCookies!=null) {
							//ASP.NET_SessionId=3ib4zpyqfbnyv3jno2jiyyxw; path=/; HttpOnly
							Log.e("TAG","cookies1"+rawCookies+"\n");
							saveSettingNote(COOKIES_NAME ,rawCookies);
						}
						String dataString = new String(response.data, "UTF-8");
						Log.e("TAG","cookies2"+dataString);
						return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
					} catch (UnsupportedEncodingException e) {
						Log.e("TAG","rawCookies===error");
						return Response.error(new ParseError(e));
					}
				}
			};

			mQueue.add(stringRequest);
		}



	}

	private static void saveSettingNote(String s,String saveData){//保存设置
		SharedPreferences.Editor note = context1.getSharedPreferences("Cookies",MODE_PRIVATE).edit();
		note.putString(s, saveData);
		note.commit();
	}
	private static String getSettingNote(String s){//获取保存设置
		SharedPreferences read = context1.getSharedPreferences("Cookies", MODE_PRIVATE);
		return read.getString(s, "");
	}

	private static String getparams(Map<String, String> map) {
		StringBuffer sb  = new StringBuffer();
		sb.append("?");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append(entry.getKey()+"="+entry.getValue()+"&");
		}
		sb.delete(sb.length()-1,sb.length());
		return sb.toString();
	}

	/**
	 * 〈一句话功能简述〉
	 * 〈功能详细描述〉
	 * @param   //得到当前行
	 * @return  [返回类型说明]
	 * @exception/throws [违例类型] [违例说明]
	 * @see          [类、类#方法、类#成员]
	 */
	private static String getLogTagWithMethod(Throwable stack) {
//			Throwable stack = new Throwable().fillInStackTrace();
			StackTraceElement[] trace = stack.getStackTrace();
			return trace[0].getClassName() + "." + trace[0].getMethodName() + ":" + trace[0].getLineNumber();
	}
	public static void makeText1(Context context,Throwable stack) {
		Toast toast = Toast.makeText(context, getLogTagWithMethod(stack), Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout toastView = (LinearLayout) toast.getView();
		ImageView imageCodeProject = new ImageView(context);
		imageCodeProject.setImageResource(R.mipmap.error);
		toastView.addView(imageCodeProject, 0);
		toast.show();
	}


	/**
	 * 隐藏界面
	 * @quthor Administrator
	 * @time 2016/8/26,0026 下午:14:39
	 */
	public static void hide(){
		if(flag&&mdialog!=null){
			if(mdialog.isShowing()){
				mdialog.dismiss();
			}
		}else if(!flag&&mdialog!=null){
			if(mdialog.isShowing()){
				mdialog.dismiss();
				Httptools.makeText(context1,"刷新失败",false);
			}
		}
	}


	/**
	 * 提示对话框
	 * @quthor Administrator
	 * @time 2016/8/5,0005 下午:17:34
	 * @param string
	 */
	public static void makeText(Context context, String string, boolean b) {
		Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout toastView = (LinearLayout) toast.getView();
		ImageView imageCodeProject = new ImageView(context);
		if (b) {
			imageCodeProject.setImageResource(R.mipmap.success);
		} else {
			imageCodeProject.setImageResource(R.mipmap.error);
		}
		toastView.addView(imageCodeProject, 0);
		toast.show();
	}


	/**
	 * 提示登录*//*
	public static void maketoLogo(final Context context) {
		final View logonView = View.inflate(context, R.layout.view_logon_1,
				null);
		// 在对话框中添加一个view
		new AlertDialog.Builder(context).setView(logonView)
				// 登陆phone,pwd
				.setPositiveButton("取消", null)
				.setNegativeButton("去登录", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent();
						intent.setClass(context, LoginActivity.class);// 打开一个activity
						context.startActivity(intent);
					}
				}).setCancelable(false).create().show();
	}
	*//**
	 * 提示登录加返回*//*
	public static void maketoLogo2(final Context context) {
		final View logonView = View.inflate(context, R.layout.view_logon_1,
				null);
		// 在对话框中添加一个view
		new AlertDialog.Builder(context).setView(logonView)
				// 登陆phone,pwd
				.setPositiveButton("取消", null)
				.setNegativeButton("去登录", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent();
						intent.putExtra("data",2);
						intent.setClass(context, LoginActivity.class);// 打开一个activity
						context.startActivity(intent);
					}
				}).setCancelable(false).create().show();
	}*/
}
