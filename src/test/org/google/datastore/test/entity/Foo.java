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
public class Foo {

	@Id
	private String id = "";

	private String name = "";

	@Index
	private String indexString = "";

	@Index
	private int indexInt = 1;

	@Index
	private Date doc = Calendar.getInstance().getTime();

	private List<String> listString = new ArrayList<String>();

	private List<Integer> listInt = new ArrayList<Integer>();

	private List<Double> listDouble = new ArrayList<Double>();

	public Foo() {
		super();
		this.listString.add("String 1");
		this.listString.add("String 2");
		this.listString.add("String 3");
		this.listInt.add(1);
		this.listInt.add(2);
		this.listInt.add(3);
		this.listDouble.add(4.0);
		this.listDouble.add(5.0);
		this.listDouble.add(6.0);
	}

	public Date getDoc() {
		return doc;
	}

	public void setDoc(Date doc) {
		this.doc = doc;
	}

	public List<String> getListString() {
		return listString;
	}

	public void setListString(List<String> listString) {
		this.listString = listString;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndexString() {
		return indexString;
	}

	public void setIndexString(String indexString) {
		this.indexString = indexString;
	}

	public int getIndexInt() {
		return indexInt;
	}

	public void setIndexInt(int indexInt) {
		this.indexInt = indexInt;
	}

}
