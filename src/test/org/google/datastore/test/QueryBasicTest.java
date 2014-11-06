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

package org.google.datastore.test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.google.datastore.test.entity.Foo;
import org.junit.Test;

import cloud.google.datastore.GCDConfig;
import cloud.google.datastore.GCDService;
import cloud.google.datastore.GCDServiceFactory;
import cloud.google.datastore.entity.query.FilterOperator;
import cloud.google.datastore.entity.query.OrderDirection;

/**
 * @author xuanhung2401
 * 
 */
public class QueryBasicTest {

	/**
	 * Create your project. Visit https://console.developers.google.com > Create
	 * Project, enter project name and project id.
	 * */

	/**
	 * Important : Enable Google Cloud Datastore API. Visit
	 * https://console.developers.google.com. Choose your created project > APIs
	 * & auth (left menu) > APIs > Google Cloud Datastore API > On.
	 * */

	/**
	 * Change these variable with yours to test api. Choose your created project
	 * > APIs & auth (left menu) > Credentials > Create New Client ID > Chooose
	 * Service account > Create Client ID. - projectName variable is your
	 * project id with "s~" before, example your project id : "source-gcd" ->
	 * projectName = "s~source-gcd". iss variable is Email address. To get p12
	 * key file, click Generate new P12 key, download, keyLocation variable is
	 * path to your p12 Key file.
	 * */
	static String projectName = "s~source-gcd";
	static String iss = "299520893014-d1kh9putd2n3hbqjsjlsbes1i8spkav5@developer.gserviceaccount.com";
	static String keyLocation = "source-gcd-542f0520e284.p12";
	static GCDConfig config = new GCDConfig(projectName, iss, keyLocation);
	static GCDService ds = GCDServiceFactory.getInstance(config);

	static {
		System.out.println("Start init data !");
		long startTime = System.currentTimeMillis();
		Foo f = new Foo();
		f.setId("this-is-id-01");
		f.setName("This is Name 01");
		f.setIndexString("category-01");
		f.setIndexInt(1);
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 07, 17);
		f.setDoc(cal.getTime());
		ds.commit(Foo.class).entities(f).delete();
		ds.commit(Foo.class).entities(f).insert();
		f = new Foo();
		f.setId("this-is-id-02");
		f.setName("This is Name-02");
		f.setIndexString("category-01");
		f.setIndexInt(1);
		cal = Calendar.getInstance();
		cal.set(2012, 07, 17);
		f.setDoc(cal.getTime());
		ds.commit(Foo.class).entities(f).delete();
		ds.commit(Foo.class).entities(f).insert();
		f = new Foo();
		f.setId("this-is-id-03");
		f.setName("This is Name-03");
		f.setIndexString("category-02");
		f.setIndexInt(1);
		cal = Calendar.getInstance();
		cal.set(2012, 07, 17);
		f.setDoc(cal.getTime());
		ds.commit(Foo.class).entities(f).delete();
		ds.commit(Foo.class).entities(f).insert();
		f = new Foo();
		f.setId("this-is-id-04");
		f.setName("This is Name-04");
		f.setIndexString("category-02");
		f.setIndexInt(2);
		cal = Calendar.getInstance();
		cal.set(2013, 07, 13);
		f.setDoc(cal.getTime());
		ds.commit(Foo.class).entities(f).delete();
		ds.commit(Foo.class).entities(f).insert();
		f = new Foo();
		f.setId("this-is-id-05");
		f.setName("This is Name-05");
		f.setIndexString("category-01");
		f.setIndexInt(2);
		cal = Calendar.getInstance();
		cal.set(2013, 07, 13, 0, 0, 0);
		f.setDoc(cal.getTime());
		ds.commit(Foo.class).entities(f).delete();
		ds.commit(Foo.class).entities(f).insert();
		f = new Foo();
		f.setId("this-is-id-06");
		f.setName("This is Name-06");
		f.setIndexString("category-02");
		f.setIndexInt(1);
		cal = Calendar.getInstance();
		cal.set(2014, 07, 13, 0, 0, 0);
		f.setDoc(cal.getTime());
		ds.commit(Foo.class).entities(f).delete();
		ds.commit(Foo.class).entities(f).insert();
		f = new Foo();
		f.setId("this-is-id-07");
		f.setName("This is Name-07");
		f.setIndexString("category-02");
		f.setIndexInt(2);
		cal = Calendar.getInstance();
		f.setDoc(cal.getTime());
		ds.commit(Foo.class).entities(f).delete();
		ds.commit(Foo.class).entities(f).insert();
		long endTime = System.currentTimeMillis();
		System.out.println("End init data !");
		System.out.println("Lost : " + (endTime - startTime) / 1000
				+ " second !");
	}

	@Test
	public void testQueryLimit3() {
		List<Foo> list = ds.query(Foo.class).limit(3).list();
		assertEquals(list.size(), 3);
	}

	@Test
	public void testQueryLimit5() {
		List<Foo> list = ds.query(Foo.class).limit(5).list();
		assertEquals(list.size(), 5);
	}

	@Test
	public void testQueryLimit7() {
		List<Foo> list = ds.query(Foo.class).limit(7).list();
		assertEquals(list.size(), 7);
	}

	@Test
	public void testQueryLimit10() {
		List<Foo> list = ds.query(Foo.class).limit(10).list();
		assertEquals(list.size(), 7);
	}

	@Test
	public void testQueryOffset3() {
		List<Foo> list = ds.query(Foo.class).offset(3).list();
		assertEquals(list.size(), 4);
	}

	@Test
	public void testQueryOffset2() {
		List<Foo> list = ds.query(Foo.class).offset(2).list();
		assertEquals(list.size(), 5);
	}

	@Test
	public void testQueryOffset7() {
		List<Foo> list = ds.query(Foo.class).offset(7).list();
		assertEquals(list.size(), 0);
	}

	@Test
	public void testQueryOrderASCENDING() {
		List<Foo> list = ds.query(Foo.class)
				.order("doc", OrderDirection.ASCENDING).list();
		assertEquals(list.get(0).getId(), "this-is-id-01");
	}

	@Test
	public void testQueryOrderDESCENDING() {
		List<Foo> list = ds.query(Foo.class)
				.order("doc", OrderDirection.DESCENDING).list();
		assertEquals(list.get(0).getId(), "this-is-id-07");
	}

	@Test
	public void testQueryFilterString() {
		List<Foo> list = ds.query(Foo.class)
				.filter("indexString", "category-01", FilterOperator.EQUAL)
				.list();		
		assertEquals(list.size(), 3);
	}

	@Test
	public void testQueryFilterNumberEqual() {
		List<Foo> list = ds.query(Foo.class)
				.filter("indexInt", 1, FilterOperator.EQUAL).list();
		assertEquals(list.size(), 4);
	}

	@Test
	public void testQueryFilterNumberGreateThan() {
		List<Foo> list = ds.query(Foo.class)
				.filter("indexInt", 1, FilterOperator.GREATER_THAN).list();
		assertEquals(list.size(), 3);
	}

	@Test
	public void testQueryFilterNumberGreateThanOrEqual() {
		List<Foo> list = ds.query(Foo.class)
				.filter("indexInt", 1, FilterOperator.GREATER_THAN_OR_EQUAL)
				.list();
		assertEquals(list.size(), 7);
	}

	@Test
	public void testQueryFilterNumberLessThan() {
		List<Foo> list = ds.query(Foo.class)
				.filter("indexInt", 2, FilterOperator.LESS_THAN).list();
		assertEquals(list.size(), 4);
	}

	@Test
	public void testQueryFilterNumberLessThanOrEqual() {
		List<Foo> list = ds.query(Foo.class)
				.filter("indexInt", 2, FilterOperator.LESS_THAN_OR_EQUAL)
				.list();
		assertEquals(list.size(), 7);
	}

	@Test
	public void testQueryFilterDateRange() {
		System.out
				.println("--------------------------2-----------------------");
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 07, 12, 23, 59, 59);
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2014, 07, 13, 23, 59, 59);
		System.out.println(cal.getTime());
		List<Foo> list = ds.query(Foo.class)
				.filter("doc", cal.getTime(), FilterOperator.GREATER_THAN)
				.filter("doc", cal2.getTime(), FilterOperator.LESS_THAN).list();
		for (Foo foo : list) {
			System.out.println(foo.getId());
			System.out.println(foo.getDoc());
		}
		assertEquals(list.size(), 3);
	}

	@Test
	public void testQueryFilterDate() {
		System.out.println("--------------------3---------------------");
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 07, 12, 23, 59, 59);
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2013, 07, 13, 23, 59, 59);
		System.out.println(cal.getTime());

		List<Foo> list = ds
				.query(Foo.class)
				.filter("doc", cal.getTime(), FilterOperator.GREATER_THAN)
				.filter("doc", cal2.getTime(),
						FilterOperator.LESS_THAN_OR_EQUAL).list();
		for (Foo foo : list) {
			System.out.println(foo.getId());
			System.out.println(foo.getDoc());
		}
		assertEquals(list.size(), 2);
		// ds.gqlQuery(Foo.class).queryString("SELECT * FROM Foo").list();
	}
}
