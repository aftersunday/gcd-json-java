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

import java.util.ArrayList;
import java.util.List;

import org.google.datastore.test.entity.Foo;
import org.junit.Test;

import static org.junit.Assert.*;
import cloud.google.datastore.GCDConfig;
import cloud.google.datastore.GCDService;
import cloud.google.datastore.GCDServiceFactory;
import cloud.google.datastore.entity.core.Key;

/**
 * @author xuanhung2401
 * 
 */
public class CommitBasicTest {

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
	String projectName = "s~source-gcd";
	String iss = "299520893014-d1kh9putd2n3hbqjsjlsbes1i8spkav5@developer.gserviceaccount.com";
	String keyLocation = "source-gcd-542f0520e284.p12";
	GCDConfig config = new GCDConfig(projectName, iss, keyLocation);
	GCDService ds = GCDServiceFactory.getInstance(config);

	@Test
	public void testInsertOne() {
		Foo f = new Foo();
		f.setId("this-is-id");
		f.setName("This is Name");

		// remove enity if exists
		ds.commit(Foo.class).entities(f).delete();

		// start insert.
		List<Key<Foo>> listKey = ds.commit(Foo.class).entities(f).insert();

		assertEquals(listKey.size(), 1);
		assertEquals(listKey.get(0).getPath().get(0).getName(), f.getId());

		Foo lookupFoo = ds.lookup(Foo.class).id(f.getId()).get();
		assertEquals(lookupFoo.getId(), f.getId());
	}

	@Test
	public void testUpdateOne() {
		Foo f = new Foo();
		f.setId("this-is-id");
		f.setName("This is Name");

		// remove enity if exists
		ds.commit(Foo.class).entities(f).delete();

		// start insert.
		ds.commit(Foo.class).entities(f).insert();
		f.setName("This is Name update !");
		ds.commit(Foo.class).entities(f).update();

		Foo lookupFoo = ds.lookup(Foo.class).id(f.getId()).get();
		assertEquals(lookupFoo.getName(), f.getName());
	}

	@Test
	public void testInsertMany() {
		Foo f1 = new Foo();
		f1.setId("this-is-id-01");
		f1.setName("This is Name 01");

		Foo f2 = new Foo();
		f2.setId("this-is-id-02");
		f2.setName("This is Name 02");

		ds.commit(Foo.class).entities(f1, f2).delete();

		List<Key<Foo>> listKey = ds.commit(Foo.class).entities(f1, f2).insert();
		assertEquals(listKey.size(), 2);

		Foo lookupFoo = ds.lookup(Foo.class).id(f1.getId()).get();
		assertEquals(lookupFoo.getId(), f1.getId());

		lookupFoo = ds.lookup(Foo.class).id(f2.getId()).get();
		assertEquals(lookupFoo.getId(), f2.getId());
	}

	@Test
	public void testUpdateMany() {
		Foo f = new Foo();
		f.setId("this-is-id");
		f.setName("This is Name");

		Foo f1 = new Foo();
		f1.setId("this-is-id-01");
		f1.setName("This is Name 01");

		// remove enity if exists
		ds.commit(Foo.class).entities(f, f1).delete();

		// start insert.
		ds.commit(Foo.class).entities(f, f1).insert();
		f.setName("This is Name update !");
		f1.setName("This is Name update 01 !");
		ds.commit(Foo.class).entities(f, f1).update();

		Foo lookupFoo = ds.lookup(Foo.class).id(f.getId()).get();
		assertEquals(lookupFoo.getName(), f.getName());
		lookupFoo = ds.lookup(Foo.class).id(f1.getId()).get();
		assertEquals(lookupFoo.getName(), f1.getName());
	}

	@Test
	public void testInsertList() {
		Foo f1 = new Foo();
		f1.setId("this-is-id-01");
		f1.setName("This is Name 01");

		Foo f2 = new Foo();
		f2.setId("this-is-id-02");
		f2.setName("This is Name 02");

		Foo f3 = new Foo();
		f3.setId("this-is-id-03");
		f3.setName("This is Name 03");

		List<Foo> list = new ArrayList<Foo>();
		list.add(f1);
		list.add(f2);
		list.add(f3);

		ds.commit(Foo.class).entities(list).delete();

		List<Key<Foo>> listKey = ds.commit(Foo.class).entities(list).insert();
		assertEquals(listKey.size(), 3);

		Foo lookupFoo = ds.lookup(Foo.class).id(f1.getId()).get();
		assertEquals(lookupFoo.getId(), f1.getId());

		lookupFoo = ds.lookup(Foo.class).id(f2.getId()).get();
		assertEquals(lookupFoo.getId(), f2.getId());

		lookupFoo = ds.lookup(Foo.class).id(f3.getId()).get();
		assertEquals(lookupFoo.getId(), f3.getId());
	}
}
