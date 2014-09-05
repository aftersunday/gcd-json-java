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

import java.util.Calendar;
import java.util.List;

import org.google.datastore.test.entity.Domain;
import org.junit.Test;

import cloud.google.datastore.GCDConfig;
import cloud.google.datastore.GCDService;
import cloud.google.datastore.GCDServiceFactory;
import cloud.google.datastore.entity.query.FilterOperator;

/**
 * @author xuanhung2401
 * 
 */
public class QueryTest {

	String projectName = "s~testblogerapi3";
	String iss = "303659554328-hrtkn1d961aafp4ehbeckpa0r5rq2vn3@developer.gserviceaccount.com";
	String keyLocation = "testblogerapi3-ad717b93eb0d.p12";
	GCDConfig config = new GCDConfig(projectName, iss, keyLocation);
	GCDService ds = GCDServiceFactory.getInstance(config);

	@Test
	public void testQuery() {
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 8, 2, 0, 0);
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2014, 8, 3, 0, 0);
		System.out.println(cal.getTime());
		System.out.println(cal1.getTime());
		List<Domain> list = ds
				.query(Domain.class)
				.filter("status", 1, FilterOperator.EQUAL)
				.filter("doc", cal.getTime(),
						FilterOperator.GREATER_THAN_OR_EQUAL)
				.filter("doc", cal1.getTime(), FilterOperator.LESS_THAN).list();
		System.out.println("-----------------------");
		for (Domain domain : list) {
			System.out.println(domain.getName());
		}
	}

}
