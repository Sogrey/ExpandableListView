package com.expandableListViewDemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ExpandableListView;

public class MyExpandableListView extends ExpandableListView {

	public MyExpandableListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public MyExpandableListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		Log.v("MyExpandableListView", "onMeasure");
		super.onMeasure(widthMeasureSpec, mExpandSpec);
	}

}
