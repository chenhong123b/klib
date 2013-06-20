/**
 * 
 */
package com.kooxiv.klib.override;

import com.loopj.android.http.RequestParams;

/**
 * 描述：修改原RequestParams.getParamString()为Public
 * 
 * <br>
 * 
 * @author kooxiv@qq.com <br>
 *         2013-6-20 <br>
 */
public class Params extends RequestParams {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.loopj.android.http.RequestParams#getParamString()
	 */
	@Override
	public String getParamString() {
		// TODO Auto-generated method stub
		return super.getParamString();
	}
}
