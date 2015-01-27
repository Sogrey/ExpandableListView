package com.expandableListViewDemo;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.expandablelistviewdemo.R;
import com.expandableListViewDemo.adapter.MorelevelAdapter;
import com.expandableListViewDemo.javaBean.CharperBean;
import com.expandableListViewDemo.util.InitData;

public class TreeLevelActivity extends Activity {
	ExpandableListView expandable;
	private ArrayList<CharperBean> mList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable_activity);
		expandable = (ExpandableListView) this
				.findViewById(R.id.expandableListView1);
		expandable.setDivider(null);
		expandable.setGroupIndicator(null);
		init();
		expandable.setAdapter(new MorelevelAdapter(this, mList));
	}

	private void init() {

		String fileName = "sections.txt";// assetsÏÂÎÄ¼þ
		String ret = "";
		JSONObject object = null;
		try {
			InputStream is = getResources().getAssets().open(fileName);
			int len = is.available();
			byte[] buffer = new byte[len];
			is.read(buffer);
			ret = EncodingUtils.getString(buffer, "utf-8");
			try {
				object = new JSONObject(ret);
				System.out.println(object.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mList = InitData.GetCharperList(object);
	}
}
