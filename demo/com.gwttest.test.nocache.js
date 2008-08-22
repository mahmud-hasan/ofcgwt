function com_gwttest_test(){
  var $intern_0 = '', $intern_23 = '" for "gwt:onLoadErrorFn"', $intern_21 = '" for "gwt:onPropertyErrorFn"', $intern_8 = '"><\/script>', $intern_10 = '/', $intern_35 = '1C10357EE865ECCDF5D2E4EB258D9F65.cache.html', $intern_37 = '3AE401A6C831AC9060BE2DCE0C80DF4E.cache.html', $intern_38 = '646A96F7F975E474B1B5478504D94170.cache.html', $intern_36 = '92979EFBA5C7ADE1D42FBCED9B13B763.cache.html', $intern_7 = '<script id="', $intern_51 = '<script language="javascript" src="', $intern_55 = "<script>com_gwttest_test.onInjectionDone('com.gwttest.test')<\/script>", $intern_18 = '=', $intern_20 = 'Bad handler "', $intern_46 = 'DOMContentLoaded', $intern_39 = 'FC9979D0F1CADCF2B2BF0C54210BADE2.cache.html', $intern_9 = 'SCRIPT', $intern_6 = '__gwt_marker_com.gwttest.test', $intern_11 = 'base', $intern_13 = 'clear.cache.gif', $intern_1 = 'com.gwttest.test', $intern_50 = 'com_rednels_ofcgwt_callback.js', $intern_52 = 'com_rednels_ofcgwt_callback.js"><\/script>', $intern_17 = 'content', $intern_30 = 'gecko', $intern_31 = 'gecko1_8', $intern_4 = 'gwt.hybrid', $intern_22 = 'gwt:onLoadErrorFn', $intern_19 = 'gwt:onPropertyErrorFn', $intern_16 = 'gwt:property', $intern_45 = 'head', $intern_34 = 'hosted.html?com_gwttest_test', $intern_44 = 'href', $intern_29 = 'ie6', $intern_47 = 'iframe', $intern_12 = 'img', $intern_48 = "javascript:''", $intern_41 = 'link', $intern_14 = 'meta', $intern_33 = 'moduleRequested', $intern_28 = 'msie', $intern_15 = 'name', $intern_25 = 'opera', $intern_49 = 'position:absolute;width:0;height:0;border:none', $intern_42 = 'rel', $intern_27 = 'safari', $intern_5 = 'selectionDone', $intern_3 = 'selectionStart', $intern_2 = 'startup', $intern_43 = 'stylesheet', $intern_53 = 'swfobject.js', $intern_54 = 'swfobject.js"><\/script>', $intern_40 = 'test.css', $intern_32 = 'unknown', $intern_24 = 'user.agent', $intern_26 = 'webkit';
  var $wnd = window, $doc = document, external = $wnd.external, $stats = $wnd.__gwtstatsEvent?function(a, b, c, d){
    $wnd.__gwtstatsEvent(a, b, c, d);
  }
  :null, scriptsDone, loadDone, bodyDone, base = $intern_0, metaProps = {}, values = [], providers = [], answers = [], onLoadErrorFunc, propertyErrorFunc;
  $stats && $stats($intern_1, $intern_2, $intern_3, {millis:(new Date()).getTime()});
  if (!$wnd.__gwt_stylesLoaded) {
    $wnd.__gwt_stylesLoaded = {};
  }
  if (!$wnd.__gwt_scriptsLoaded) {
    $wnd.__gwt_scriptsLoaded = {};
  }
  function isHostedMode(){
    try {
      return external && (external.gwtOnLoad && $wnd.location.search.indexOf($intern_4) == -1);
    }
     catch (e) {
      return false;
    }
  }

  function maybeStartModule(){
    if (scriptsDone && loadDone) {
      var iframe = $doc.getElementById($intern_1);
      var frameWnd = iframe.contentWindow;
      frameWnd.__gwt_initHandlers = com_gwttest_test.__gwt_initHandlers;
      if (isHostedMode()) {
        frameWnd.__gwt_getProperty = function(name){
          return computePropValue(name);
        }
        ;
      }
      com_gwttest_test = null;
      frameWnd.gwtOnLoad(onLoadErrorFunc, $intern_1, base);
      $stats && $stats($intern_1, $intern_2, $intern_5, {millis:(new Date()).getTime()});
    }
  }

  function computeScriptBase(){
    var thisScript, markerId = $intern_6, markerScript;
    $doc.write($intern_7 + markerId + $intern_8);
    markerScript = $doc.getElementById(markerId);
    thisScript = markerScript && markerScript.previousSibling;
    while (thisScript && thisScript.tagName != $intern_9) {
      thisScript = thisScript.previousSibling;
    }
    function getDirectoryOfFile(path){
      var eq = path.lastIndexOf($intern_10);
      return eq >= 0?path.substring(0, eq + 1):$intern_0;
    }

    ;
    if (thisScript && thisScript.src) {
      base = getDirectoryOfFile(thisScript.src);
    }
    if (base == $intern_0) {
      var baseElements = $doc.getElementsByTagName($intern_11);
      if (baseElements.length > 0) {
        base = baseElements[baseElements.length - 1].href;
      }
       else {
        var loc = $doc.location;
        var href = loc.href;
        base = getDirectoryOfFile(href.substr(0, href.length - loc.hash.length));
      }
    }
     else if (base.match(/^\w+:\/\//)) {
    }
     else {
      var img = $doc.createElement($intern_12);
      img.src = base + $intern_13;
      base = getDirectoryOfFile(img.src);
    }
    if (markerScript) {
      markerScript.parentNode.removeChild(markerScript);
    }
  }

  function processMetas(){
    var metas = document.getElementsByTagName($intern_14);
    for (var i = 0, n = metas.length; i < n; ++i) {
      var meta = metas[i], name = meta.getAttribute($intern_15), content;
      if (name) {
        if (name == $intern_16) {
          content = meta.getAttribute($intern_17);
          if (content) {
            var value, eq = content.indexOf($intern_18);
            if (eq >= 0) {
              name = content.substring(0, eq);
              value = content.substring(eq + 1);
            }
             else {
              name = content;
              value = $intern_0;
            }
            metaProps[name] = value;
          }
        }
         else if (name == $intern_19) {
          content = meta.getAttribute($intern_17);
          if (content) {
            try {
              propertyErrorFunc = eval(content);
            }
             catch (e) {
              alert($intern_20 + content + $intern_21);
            }
          }
        }
         else if (name == $intern_22) {
          content = meta.getAttribute($intern_17);
          if (content) {
            try {
              onLoadErrorFunc = eval(content);
            }
             catch (e) {
              alert($intern_20 + content + $intern_23);
            }
          }
        }
      }
    }
  }

  function unflattenKeylistIntoAnswers(propValArray, value){
    var answer = answers;
    for (var i = 0, n = propValArray.length - 1; i < n; ++i) {
      answer = answer[propValArray[i]] || (answer[propValArray[i]] = []);
    }
    answer[propValArray[n]] = value;
  }

  function computePropValue(propName){
    var value = providers[propName](), allowedValuesMap = values[propName];
    if (value in allowedValuesMap) {
      return value;
    }
    var allowedValuesList = [];
    for (var k in allowedValuesMap) {
      allowedValuesList[allowedValuesMap[k]] = k;
    }
    if (propertyErrorFunc) {
      propertyErrorFunc(propName, allowedValuesList, value);
    }
    throw null;
  }

  providers[$intern_24] = function(){
    var ua = navigator.userAgent.toLowerCase();
    var makeVersion = function(result){
      return parseInt(result[1]) * 1000 + parseInt(result[2]);
    }
    ;
    if (ua.indexOf($intern_25) != -1) {
      return $intern_25;
    }
     else if (ua.indexOf($intern_26) != -1) {
      return $intern_27;
    }
     else if (ua.indexOf($intern_28) != -1) {
      var result = /msie ([0-9]+)\.([0-9]+)/.exec(ua);
      if (result && result.length == 3) {
        if (makeVersion(result) >= 6000) {
          return $intern_29;
        }
      }
    }
     else if (ua.indexOf($intern_30) != -1) {
      var result = /rv:([0-9]+)\.([0-9]+)/.exec(ua);
      if (result && result.length == 3) {
        if (makeVersion(result) >= 1008)
          return $intern_31;
      }
      return $intern_30;
    }
    return $intern_32;
  }
  ;
  values[$intern_24] = {gecko:0, gecko1_8:1, ie6:2, opera:3, safari:4};
  com_gwttest_test.onInjectionDone = function(){
    scriptsDone = true;
    $stats && $stats($intern_1, $intern_2, $intern_33, {millis:(new Date()).getTime()});
    maybeStartModule();
  }
  ;
  com_gwttest_test.onScriptLoad = function(){
    if (frameInjected) {
      loadDone = true;
      maybeStartModule();
    }
  }
  ;
  computeScriptBase();
  processMetas();
  var strongName;
  if (isHostedMode()) {
    strongName = $intern_34;
  }
   else {
    try {
      unflattenKeylistIntoAnswers([$intern_29], $intern_35);
      unflattenKeylistIntoAnswers([$intern_25], $intern_36);
      unflattenKeylistIntoAnswers([$intern_27], $intern_37);
      unflattenKeylistIntoAnswers([$intern_31], $intern_38);
      unflattenKeylistIntoAnswers([$intern_30], $intern_39);
      strongName = answers[computePropValue($intern_24)];
    }
     catch (e) {
      return;
    }
  }
  var onBodyDoneTimerId;
  function onBodyDone(){
    if (!bodyDone) {
      bodyDone = true;
      if (!__gwt_stylesLoaded[$intern_40]) {
        var l = $doc.createElement($intern_41);
        __gwt_stylesLoaded[$intern_40] = l;
        l.setAttribute($intern_42, $intern_43);
        l.setAttribute($intern_44, base + $intern_40);
        $doc.getElementsByTagName($intern_45)[0].appendChild(l);
      }
      maybeStartModule();
      if ($doc.removeEventListener) {
        $doc.removeEventListener($intern_46, onBodyDone, false);
      }
      if (onBodyDoneTimerId) {
        clearInterval(onBodyDoneTimerId);
      }
    }
  }

  var frameInjected;
  function maybeInjectFrame(){
    if (!frameInjected) {
      frameInjected = true;
      var iframe = $doc.createElement($intern_47);
      iframe.src = $intern_48;
      iframe.id = $intern_1;
      iframe.style.cssText = $intern_49;
      iframe.tabIndex = -1;
      $doc.body.appendChild(iframe);
      iframe.contentWindow.location.replace(base + strongName);
    }
  }

  if ($doc.addEventListener) {
    $doc.addEventListener($intern_46, function(){
      maybeInjectFrame();
      onBodyDone();
    }
    , false);
  }
  var onBodyDoneTimerId = setInterval(function(){
    if (/loaded|complete/.test($doc.readyState)) {
      maybeInjectFrame();
      onBodyDone();
    }
  }
  , 50);
  if (!__gwt_scriptsLoaded[$intern_50]) {
    __gwt_scriptsLoaded[$intern_50] = true;
    document.write($intern_51 + base + $intern_52);
  }
  if (!__gwt_scriptsLoaded[$intern_53]) {
    __gwt_scriptsLoaded[$intern_53] = true;
    document.write($intern_51 + base + $intern_54);
  }
  $doc.write($intern_55);
}

com_gwttest_test.__gwt_initHandlers = function(resize, beforeunload, unload){
  var $wnd = window, oldOnResize = $wnd.onresize, oldOnBeforeUnload = $wnd.onbeforeunload, oldOnUnload = $wnd.onunload;
  $wnd.onresize = function(evt){
    try {
      resize();
    }
     finally {
      oldOnResize && oldOnResize(evt);
    }
  }
  ;
  $wnd.onbeforeunload = function(evt){
    var ret, oldRet;
    try {
      ret = beforeunload();
    }
     finally {
      oldRet = oldOnBeforeUnload && oldOnBeforeUnload(evt);
    }
    if (ret != null) {
      return ret;
    }
    if (oldRet != null) {
      return oldRet;
    }
  }
  ;
  $wnd.onunload = function(evt){
    try {
      unload();
    }
     finally {
      oldOnUnload && oldOnUnload(evt);
    }
  }
  ;
}
;
com_gwttest_test();
