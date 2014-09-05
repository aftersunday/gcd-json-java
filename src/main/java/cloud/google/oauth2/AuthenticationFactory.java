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

package cloud.google.oauth2;

import java.util.Calendar;

import cloud.google.datastore.GCDConfig;

/**
 * @author xuanhung2401
 */
public class AuthenticationFactory {

	private static Authentication oauth = null;
	private static long startTime = 0;

	/**
	 * Return authentication object.
	 * */
	public static Authentication getInstance(GCDConfig config) {
		if (oauth == null) {
			initAuthentication(config);
		} else {
			long endTime = Calendar.getInstance().getTimeInMillis();
			long secs = (endTime - startTime) / 1000;
			secs = secs % 3600;
			int mins = (int) (secs / 60);
			if (mins > 40) {
				initAuthentication(config);
			}
		}
		return oauth;
	}

	public static void initAuthentication(GCDConfig config) {
		oauth = new MyWayAuthentication(config);
		startTime = Calendar.getInstance().getTimeInMillis();
	}

}
