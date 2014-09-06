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

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cloud.google.datastore.GCDConfig;
import cloud.google.datastore.GCDService;
import cloud.google.datastore.GCDServiceFactory;

/**
 * @author xuanhung2401
 * 
 */
@FixMethodOrder(MethodSorters.JVM)
public class CommitBasicTest {

	String projectName = "s~source-gcd";
	String iss = "299520893014-d1kh9putd2n3hbqjsjlsbes1i8spkav5@developer.gserviceaccount.com";
	String keyLocation = "source-gcd-542f0520e284.p12";
	GCDConfig config = new GCDConfig(projectName, iss, keyLocation);
	GCDService ds = GCDServiceFactory.getInstance(config);

	@Test
	public void testInsertOne() {

	}

	@Test
	public void testInsertTwo() {

	}

	@Test
	public void testInsertList() {

	}

}
