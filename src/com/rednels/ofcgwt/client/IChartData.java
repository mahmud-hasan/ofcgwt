/*
 *    Copyright 2008 Grant K Slender
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *   
 */
package com.rednels.ofcgwt.client;

public interface IChartData {

	/**
	 * Get the currently set JSON data
	 * @return json String
	 */
	public String getJsonData();
	/**
	 * Notify that the chart widget is ready to go
	 */
	public void notifyReady();
	/**
	 * Get the currently set swf ID of this chart widget
	 * @return swf id String
	 */
	public String getSwfId();		

}