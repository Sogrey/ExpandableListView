/**
 * 
 */
package com.expandableListViewDemo.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expandablelistviewdemo.R;
import com.expandableListViewDemo.javaBean.CharperBean;
import com.expandableListViewDemo.util.LogWrapper;
import com.expandableListViewDemo.view.MyExpandableListView;

/**
 * @author Administrator
 * 
 */
public class MorelevelAdapter extends BaseExpandableListAdapter {
	private List<CharperBean> list;
	private Context context;

	public MorelevelAdapter(Context context, List<CharperBean> list2) {
		this.context = context;
		this.list = list2;
	}

	@Override
	public int getGroupCount() {
		return null == this.list ? 0 : this.list.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return null == this.list.get(groupPosition).getChildren() ? 0
				: this.list.get(groupPosition).getChildren().size();
		// return
		// null==this.list.get(groupPosition).getChildren()?0:this.list.get(groupPosition).getChildren().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.list.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.list.get(groupPosition).getChildren().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return this.list.get(groupPosition).hashCode();
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return this.list.get(groupPosition).getChildren().get(childPosition)
				.hashCode();
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// return getGenericView(
		// this.list.get(groupPosition).getSection_name());

		View view = convertView;
		ViewHolderGroup holderGroup;
		if (view == null) {
			holderGroup = new ViewHolderGroup();
			if ("00".equals(this.list.get(groupPosition).getParent_code())) {// 首级
				view = ((Activity) context).getLayoutInflater().inflate(
						R.layout.item_practice_chapters_group, null);
				holderGroup.imgExpand = (ImageView) view
						.findViewById(R.id.img_item_practice_chapters_group_expand);
				holderGroup.viewTop = view
						.findViewById(R.id.img_item_practice_chapters_group_top);
				holderGroup.viewBottom = view
						.findViewById(R.id.img_item_practice_chapters_group_bottom);
				holderGroup.txtCharptersLabel = (TextView) view
						.findViewById(R.id.txt_item_practice_chapters_group_label);
				holderGroup.txtPracticeTimes = (TextView) view
						.findViewById(R.id.txt_item_practice_chapters_group_times);
				holderGroup.imgEdit = (ImageView) view
						.findViewById(R.id.img_item_practice_chapters_group_edit);

				holderGroup.viewTop.setVisibility(View.INVISIBLE);
			} else {// 次级以及更低级（没有上边距）
				view = ((Activity) context).getLayoutInflater().inflate(
						R.layout.item_practice_chapters_group2, null);
				holderGroup.imgExpand = (ImageView) view
						.findViewById(R.id.img_item_practice_chapters_group_expand2);
				holderGroup.viewTop = view
						.findViewById(R.id.img_item_practice_chapters_group_top2);
				holderGroup.viewBottom = view
						.findViewById(R.id.img_item_practice_chapters_group_bottom2);
				holderGroup.txtCharptersLabel = (TextView) view
						.findViewById(R.id.txt_item_practice_chapters_group_label2);
				holderGroup.txtPracticeTimes = (TextView) view
						.findViewById(R.id.txt_item_practice_chapters_group_times2);
				holderGroup.imgEdit = (ImageView) view
						.findViewById(R.id.img_item_practice_chapters_group_edit2);

				holderGroup.viewTop.setVisibility(View.VISIBLE);
			}
			view.setTag(holderGroup);
		} else {
			holderGroup = (ViewHolderGroup) view.getTag();
		}

		if (isExpanded) {
			holderGroup.imgExpand.setImageResource(R.drawable.ic_close_chapter);
		} else {
			holderGroup.imgExpand.setImageResource(R.drawable.ic_open_chapter);
		}
		if (isExpanded
				|| (!"00".equals(this.list.get(groupPosition).getParent_code()) && groupPosition != this.list
						.size() - 1)) {// 末尾
			holderGroup.viewBottom.setVisibility(View.VISIBLE);
		} else
			holderGroup.viewBottom.setVisibility(View.INVISIBLE);
		// 没有子章节，父章节前面的展开箭头隐藏
		if (null == this.list.get(groupPosition).getChildren()
				|| 0 == this.list.get(groupPosition).getChildren().size()) {
			holderGroup.imgExpand.setImageResource(R.drawable.ic_circle_gray);
		}

		holderGroup.txtCharptersLabel.setText(this.list.get(groupPosition)
				.getSection_name());
		holderGroup.imgEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 完善点击事件，获取题目，前往答题
				LogWrapper.e("父章节", list.get(groupPosition).getSection_name());
				// queryQuest(
				// list.get(groupPosition).getId(),list.get(groupPosition).getSection_name(),null);
				Toast.makeText(
						context,
						list.get(groupPosition).getId() + "==>"
								+ list.get(groupPosition).getSection_name(),
						Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		if (null != list.get(groupPosition).getChildren().get(childPosition)
				&& null != list.get(groupPosition).getChildren()
						.get(childPosition).getChildren()
				&& 0 != list.get(groupPosition).getChildren()
						.get(childPosition).getChildren().size()) {
			ExpandableListView expandableListView = getchild();
			List<CharperBean> dataList = new ArrayList<CharperBean>();
			if (0 == childPosition) {// 子集输出一遍，多遍重复
				dataList = list.get(groupPosition).getChildren();
			} else
				dataList = null;
			expandableListView.setAdapter(new MorelevelAdapter(context,
					dataList));
			return expandableListView;
		} else {
			View view = convertView;
			ViewHolderChild holderChild;
			if (view == null) {
				holderChild = new ViewHolderChild();
				view = ((Activity) context).getLayoutInflater().inflate(
						R.layout.item_practice_chapters_group2, null);
				holderChild.imgExpand = (ImageView) view
						.findViewById(R.id.img_item_practice_chapters_group_expand2);
				holderChild.viewTop = view
						.findViewById(R.id.img_item_practice_chapters_group_top2);
				holderChild.viewBottom = view
						.findViewById(R.id.img_item_practice_chapters_group_bottom2);
				holderChild.txtCharptersLabel = (TextView) view
						.findViewById(R.id.txt_item_practice_chapters_group_label2);
				holderChild.txtPracticeTimes = (TextView) view
						.findViewById(R.id.txt_item_practice_chapters_group_times2);
				holderChild.imgEdit = (ImageView) view
						.findViewById(R.id.img_item_practice_chapters_group_edit2);
				view.setTag(holderChild);
			} else {
				holderChild = (ViewHolderChild) view.getTag();
			}
			if (isLastChild
					&& childPosition == this.list.get(groupPosition)
							.getChildren().size() - 1// 最后一个child
					&& (groupPosition == this.list.size() - 1// 父类也是最后一个
					|| "00".equals(this.list.get(groupPosition)
							.getParent_code()))) {
				holderChild.viewBottom.setVisibility(View.INVISIBLE);
			} else {
				holderChild.viewBottom.setVisibility(View.VISIBLE);
			}
			holderChild.viewTop.setVisibility(View.VISIBLE);
			// //没有子章节，父章节前面的展开箭头隐藏
			holderChild.imgExpand.setVisibility(View.VISIBLE);// 显示
			holderChild.imgExpand.setImageResource(R.drawable.ic_circle_gray);
			holderChild.txtCharptersLabel.setText(this.list.get(groupPosition)
					.getChildren().get(childPosition).getSection_name());
			holderChild.imgEdit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// 完善点击事件，获取题目，前往答题
					LogWrapper.e("父章节", list.get(groupPosition)
							.getSection_name());
					// queryQuest(
					// list.get(groupPosition).getChildren().get(childPosition).getId(),
					// list.get(groupPosition).getChildren().get(childPosition).getSection_name(),null);
					Toast.makeText(
							context,
							list.get(groupPosition).getChildren()
									.get(childPosition).getId()
									+ "==>"
									+ list.get(groupPosition).getChildren()
											.get(childPosition)
											.getSection_name(),
							Toast.LENGTH_SHORT).show();
				}
			});
			return view;
		}
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public TextView getGenericView(String string) {
		// Layout parameters for the ExpandableListView

		AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT, 64);
		TextView text = new TextView(context);
		text.setLayoutParams(layoutParams);
		// Center the text vertically

		text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		// Set the text starting position

		text.setPadding(36, 0, 0, 0);
		text.setText(string);
		return text;
	}

	public ExpandableListView getchild() {
		AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		MyExpandableListView text = new MyExpandableListView(context);
		text.setDivider(null);
		text.setCacheColorHint(context.getResources().getColor(
				R.color.transparent));
		text.setGroupIndicator(null);
		text.setLayoutParams(layoutParams);
		text.setPadding(0, 0, 0, 0);
		return text;
	}

	class ViewHolderGroup {
		ImageView imgExpand, imgEdit;
		TextView txtCharptersLabel, txtPracticeTimes;
		View viewTop, viewBottom;
	}

	class ViewHolderChild {
		ImageView imgExpand, imgEdit;
		TextView txtCharptersLabel, txtPracticeTimes;
		View viewTop, viewBottom;
	}
}