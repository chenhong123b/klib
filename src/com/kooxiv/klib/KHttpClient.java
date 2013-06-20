/**
 * 
 */
package com.kooxiv.klib;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.kooxiv.klib.override.Params;
import com.kooxiv.klib.util.KUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * 描述：
 * 
 * <br/>
 * 
 * @author kooxiv@qq.com <br/>
 *         2013-6-20 <br/>
 */
public class KHttpClient<T> {

	private AsyncHttpClient client;
	private Context context;
	private Class<T> clazz;
	private ProgressDialog dialog;

	public KHttpClient(Context context, Class<T> class1, boolean isShowDialog) {
		init(context, class1, isShowDialog);
	}

	public KHttpClient(Context context, Class<T> class1) {
		init(context, class1, true);
	}

	private void init(Context context, Class<T> class1, boolean isShowDialog) {
		client = new AsyncHttpClient();
		this.clazz = class1;
		this.context = context;

		if (isShowDialog) {
			dialog = new ProgressDialog(context);
			dialog.setCancelable(false);
			dialog.setMessage(context.getString(R.string._loading));
		}
	}

	protected void onStart() {
		if (dialog != null) {
			dialog.show();
		}

	}

	protected void onSuccess(int statusCode, T entity) {

	}

	protected void onFailure(Throwable error, String content) {
		if (content != null) {
			KUtil.show(context, content);
		}
	}

	protected void onFinish() {
		if (dialog != null) {
			dialog.dismiss();
		}
	}

	public void get(String urlString, Params params) {

		client.get(getUrlWithQueryString(urlString, params), handler);

	}

	public void post(String urlString, Params params) {

		client.post(getUrlWithQueryString(urlString, params), handler);

	}

	public void setTimeout(int milliseconds) {
		client.setTimeout(milliseconds);

	}

	public void setUserAgent(String userAgent) {
		client.setUserAgent(userAgent);
	}

	public String getUrlWithQueryString(String url, Params params) {
		if (params != null) {
			String paramString = params.getParamString();
			if (url.indexOf("?") == -1) {
				url += "?" + paramString;
			} else {
				url += "&" + paramString;
			}
		}

		return url;
	}

	private AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
		@Override
		public void onStart() {
			KHttpClient.this.onStart();
		}

		@Override
		public void onSuccess(int statusCode, String content) {

			T json = null;
			try {
				json = new Gson().fromJson(content, clazz);
			} catch (Exception e) {
			}

			if (json == null) {
				KHttpClient.this.onFailure(null, content);
			} else {
				KHttpClient.this.onSuccess(statusCode, json);
			}

		}

		@Override
		public void onFailure(Throwable error, String content) {
			KHttpClient.this.onFailure(error, content);
		}

		@Override
		public void onFinish() {
			KHttpClient.this.onFinish();
		}
	};

}
