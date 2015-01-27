package com.expandableListViewDemo.javaBean;

import java.util.List;

/**
 * 章节
 * @author Administrator
 *
 */
public class CharperBean {

//	"id":1840,"section_name":"入学教育","parent_code":"00","section_code":"11"
	public int id;
	public String section_name;
	public String parent_code;
	public String section_code;
	public List<CharperBean> children;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the section_name
	 */
	public String getSection_name() {
		return section_name;
	}
	/**
	 * @param section_name the section_name to set
	 */
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	/**
	 * @return the parent_code
	 */
	public String getParent_code() {
		return parent_code;
	}
	/**
	 * @param parent_code the parent_code to set
	 */
	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}
	/**
	 * @return the section_code
	 */
	public String getSection_code() {
		return section_code;
	}
	/**
	 * @param section_code the section_code to set
	 */
	public void setSection_code(String section_code) {
		this.section_code = section_code;
	}
	/**
	 * @return the children
	 */
	public List<CharperBean> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<CharperBean> children) {
		this.children = children;
	}
}
