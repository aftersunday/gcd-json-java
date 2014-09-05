/**
 * Copyright (C) 2014 xuanhung2401.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.google.datastore.test.entity;

/**
 * @author xuanhung2401
 * 
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cloud.google.datastore.annotation.Annotation.Entity;
import cloud.google.datastore.annotation.Annotation.Id;
import cloud.google.datastore.annotation.Annotation.Index;

@Entity
public class Domain {

	@Id
	private String name = "";
	private String content = "";
	@Index
	private int status = 1;
	@Index
	private Date doc = Calendar.getInstance().getTime();
	private List<String> listTag = new ArrayList<String>();
	private List<Integer> listInt = new ArrayList<Integer>();
	private List<Double> listDouble = new ArrayList<Double>();

	public List<Integer> getListInt() {
		return listInt;
	}

	public void setListInt(List<Integer> listInt) {
		this.listInt = listInt;
	}

	public List<Double> getListDouble() {
		return listDouble;
	}

	public void setListDouble(List<Double> listDouble) {
		this.listDouble = listDouble;
	}

	public Domain() {
		listTag.add("this 2");
		listTag.add("this 3");
		listTag.add("this 4");
		listInt.add(1);
		listInt.add(2);
		listInt.add(3);
		listDouble.add(0.0);
		listDouble.add(0.1);
		listDouble.add(0.2);
	}

	public List<String> getListTag() {
		return listTag;
	}

	public void setListTag(List<String> listTag) {
		this.listTag = listTag;
	}

	public Date getDoc() {
		return doc;
	}

	public void setDoc(Date doc) {
		this.doc = doc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
