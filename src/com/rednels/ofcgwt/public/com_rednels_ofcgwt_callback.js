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
function open_flash_chart_data()
{
    //alert('open_flash_chart_data');
	// this function is for callback support for com.rednels.ofcgwt !! DO NOT REMOVE !!
	var r = window.dataFileJS();
	return r;
}
function open_flash_chart_dataswfID_0()
{
    alert('swfID_0');
	// this function is for callback support for com.rednels.ofcgwt !! DO NOT REMOVE !!
	var r = window.dataFileJS();
	return r;
}
function open_flash_chart_dataswfID_1()
{
    alert('swfID_1');
	return '{\"title\":{\"text\":\"\"},\"elements\":[]}';
}
function ofc_ready()
{
	//window.onChartReady(); //TODO add gwt callback, but need chart id to tell them apart !!
    //alert('ofc_ready');
}
