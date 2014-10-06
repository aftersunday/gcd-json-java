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

import java.util.ArrayList;
import java.util.List;

import org.google.datastore.test.entity.Foo;
import org.junit.Test;

import cloud.google.datastore.GCDConfig;
import cloud.google.datastore.GCDService;
import cloud.google.datastore.GCDServiceFactory;

/**
 * @author xuanhung2401
 * 
 */
public class LookupBasicTest {

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
	public void testLookupByOneStringId() {
		Foo f = new Foo();
		f.setId("this-is-id");
		f.setName("This is Name");
		ds.commit(Foo.class).entities(f).delete();
		ds.commit(Foo.class).entities(f).insert();
		Foo lookupFoo = ds.lookup(Foo.class).id(f.getId()).get();
		assertEquals(lookupFoo.getName(), f.getName());
		assertEquals(lookupFoo.getId(), f.getId());
	}

	@Test
	public void testLookupByTwoStringId() {
		Foo f = new Foo();
		f.setId("this-is-id-01");
		f.setName("This is Name");
		ds.commit(Foo.class).entities(f).delete();
		ds.commit(Foo.class).entities(f).insert();
		Foo f1 = new Foo();
		f1.setId("this-is-id-02");
		f1.setName("This is Name");
		ds.commit(Foo.class).entities(f1).delete();
		ds.commit(Foo.class).entities(f1).insert();
		List<Foo> listResult = ds.lookup(Foo.class).ids(f1.getId(), f.getId())
				.list();
		assertEquals(listResult.size(), 2);
		boolean catchF1 = false;
		boolean catchF2 = false;

		for (Foo foo : listResult) {
			if (foo.getId().equals(f.getId())) {
				catchF1 = true;
			}
			if (foo.getId().equals(f1.getId())) {
				catchF2 = true;
			}
		}
		assertEquals(catchF1, true);
		assertEquals(catchF2, true);
	}

	@Test
	public void testLookupByListStringId() {
		Foo f = new Foo();
		f.setId("this-is-id-03");
		f.setName("This is Name");
		ds.commit(Foo.class).entities(f).delete();
		ds.commit(Foo.class).entities(f).insert();
		Foo f1 = new Foo();
		f1.setId("this-is-id-04");
		f1.setName("This is Name");
		ds.commit(Foo.class).entities(f1).delete();
		ds.commit(Foo.class).entities(f1).insert();

		List<String> listId = new ArrayList<String>();
		listId.add(f.getId());
		listId.add(f1.getId());

		List<Foo> listResult = ds.lookup(Foo.class).ids(listId).list();
		assertEquals(listResult.size(), 2);
		boolean catchF1 = false;
		boolean catchF2 = false;

		for (Foo foo : listResult) {
			if (foo.getId().equals(f.getId())) {
				catchF1 = true;
			}
			if (foo.getId().equals(f1.getId())) {
				catchF2 = true;
			}
		}
		assertEquals(catchF1, true);
		assertEquals(catchF2, true);
	}

}
