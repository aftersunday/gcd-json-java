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

import org.google.datastore.test.entity.Domain;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cloud.google.datastore.GCDConfig;
import cloud.google.datastore.GCDService;
import cloud.google.datastore.GCDServiceFactory;
import cloud.google.datastore.entity.core.Key;

/**
 * @author xuanhung2401
 * 
 */
@FixMethodOrder(MethodSorters.JVM)
public class CommitTest {

	String projectName = "s~testblogerapi3";
	String iss = "303659554328-hrtkn1d961aafp4ehbeckpa0r5rq2vn3@developer.gserviceaccount.com";
	String keyLocation = "testblogerapi3-ad717b93eb0d.p12";
	GCDConfig config = new GCDConfig(projectName, iss, keyLocation);
	GCDService ds = GCDServiceFactory.getInstance(config);

	@Test
	public void testInsertOne() {
		Domain d = new Domain();
		d.setName("example_01.com");
		List<Key<Domain>> listKey = ds.commit(Domain.class).entities(d)
				.insert();
		assertEquals(listKey.get(0).getPath().get(0).getName(), d.getName());
	}

	@Test
	public void testInsertTwo() {
		Domain d = new Domain();
		d.setName("example_02.com");
		Domain d1 = new Domain();
		d1.setName("example_03.com");
		List<Key<Domain>> listKey = ds.commit(Domain.class).entities(d, d1)
				.insert();
		assertEquals(listKey.size(), 2);
	}

	@Test
	public void testInsertList() {
		List<Domain> list = new ArrayList<Domain>();
		Domain d = new Domain();
		d.setName("example_04.com");
		list.add(d);
		d = new Domain();
		d.setName("example_05.com");
		list.add(d);
		d = new Domain();
		d.setName("example_06.com");
		list.add(d);
		d = new Domain();
		d.setName("example_07.com");
		list.add(d);
		d = new Domain();
		d.setName("example_08.com");
		list.add(d);
		d = new Domain();
		d.setName("example_09.com");
		list.add(d);
		d = new Domain();
		d.setName("example_10.com");
		list.add(d);
		List<Key<Domain>> listKey = ds.commit(Domain.class).entities(list)
				.insert();
		assertEquals(listKey.size(), 7);
	}

	@Test
	public void testUpdateOne() {
		Domain d = new Domain();
		d.setName("example_01.com");
		d.setContent("Content 1");
		List<Key<Domain>> listKey = ds.commit(Domain.class).entities(d)
				.update();
		assertEquals(listKey.get(0).getPath().get(0).getName(), d.getName());
	}

	@Test
	public void testUpdateTwo() {
		Domain d = new Domain();
		d.setName("example_02.com");
		d.setContent("Content 2");
		Domain d1 = new Domain();
		d1.setName("example_03.com");
		d1.setContent("Content 4");
		List<Key<Domain>> listKey = ds.commit(Domain.class).entities(d, d1)
				.update();
		assertEquals(listKey.size(), 2);
	}

	@Test
	public void testUpdateList() {
		List<Domain> list = new ArrayList<Domain>();
		Domain d = new Domain();
		d.setName("example_01.com");
		d.setContent("Content 1");
		list.add(d);
		d = new Domain();
		d.setName("example_02.com");
		d.setContent("Content 2");
		list.add(d);
		d = new Domain();
		d.setName("example_03.com");
		d.setContent("Content 3");
		list.add(d);
		d = new Domain();
		d.setName("example_04.com");
		d.setContent("Content 4");
		list.add(d);
		d = new Domain();
		d.setName("example_05.com");
		d.setContent("Content 5");
		list.add(d);
		d = new Domain();
		d.setName("example_06.com");
		d.setContent("Content 6");
		list.add(d);
		d = new Domain();
		d.setName("example_07.com");
		d.setContent("Content 7");
		list.add(d);
		d = new Domain();
		d.setName("example_08.com");
		d.setContent("Content 8");
		list.add(d);
		d = new Domain();
		d.setName("example_09.com");
		d.setContent("Content 9");
		list.add(d);
		d = new Domain();
		d.setName("example_10.com");
		d.setContent("Content 10");
		list.add(d);
		List<Key<Domain>> listKey = ds.commit(Domain.class).entities(list)
				.update();
		assertEquals(listKey.size(), 10);
	}

	@Test
	public void testDeleteOne() {
		Domain d = new Domain();
		d.setName("example_01.com");
		assertEquals(true, ds.commit(Domain.class).entities(d).delete());
	}

	@Test
	public void testDeleteTwo() {
		Domain d = new Domain();
		d.setName("example_02.com");
		Domain d1 = new Domain();
		d1.setName("example_03.com");
		assertEquals(true, ds.commit(Domain.class).entities(d, d1).delete());
	}

	@Test
	public void testDeleteAll() {
		List<Domain> list = new ArrayList<Domain>();
		Domain d = new Domain();
		d.setName("example_04.com");
		list.add(d);
		d = new Domain();
		d.setName("example_05.com");
		list.add(d);
		d = new Domain();
		d.setName("example_06.com");
		list.add(d);
		d = new Domain();
		d.setName("example_07.com");
		list.add(d);
		d = new Domain();
		d.setName("example_08.com");
		list.add(d);
		d = new Domain();
		d.setName("example_09.com");
		list.add(d);
		d = new Domain();
		d.setName("example_10.com");
		list.add(d);
		assertEquals(true, ds.commit(Domain.class).entities(list).delete());
	}
}
