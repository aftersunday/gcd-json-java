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
import static org.junit.Assert.assertNull;

import java.util.List;

import org.google.datastore.test.entity.Domain;
import org.junit.Test;

import cloud.google.datastore.GCDConfig;
import cloud.google.datastore.GCDService;
import cloud.google.datastore.GCDServiceFactory;

/**
 * @author xuanhung2401
 * 
 */
public class LookupTest {

	String projectName = "s~testblogerapi3";
	String iss = "303659554328-hrtkn1d961aafp4ehbeckpa0r5rq2vn3@developer.gserviceaccount.com";
	String keyLocation = "testblogerapi3-ad717b93eb0d.p12";
	GCDConfig config = new GCDConfig(projectName, iss, keyLocation);
	GCDService ds = GCDServiceFactory.getInstance(config);

	@Test
	public void testLookupOne() {
		Domain d = ds.lookup(Domain.class).id("aquariuesNo1.com").get();
		assertEquals("aquariuesNo1.com", d.getName());
		assertEquals(7, d.getStatus());

	}

	@Test
	public void testLookupTwo() {
		List<Domain> list = ds.lookup(Domain.class)
				.ids("aquariuesNo1.com", "aquarius02.com").list();
		assertEquals(2, list.size());
	}

	@Test
	public void testLookupTwoNoResult() {
		List<Domain> list = ds.lookup(Domain.class)
				.ids("aquariuesNo11.com", "aquarius021.com").list();
		assertEquals(0, list.size());
	}

	@Test
	public void testLookupNullOne() {
		Domain d = ds.lookup(Domain.class).id("google.com").get();
		assertNull(d);
	}

}
