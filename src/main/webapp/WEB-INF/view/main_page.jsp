<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Upload your bot</title>

      <script src="http://www.google.com/jsapi"></script>
      <script language="javascript">
      google.load('prototype', '1.6.0.2');
      </script>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
<h1>Welcome to Hunger games</h1>
<a href="file_upload">Add bot</a>

<form:form modelAttribute="gameInfoWrapper" method="post" action="playgame.html" enctype="multipart/form-data">
<table>
    <tr>
        <td><form:label path="maxRounds">Max rounds</form:label></td>
        <td><form:input type="number" path="maxRounds" /></td>
        <td><form:errors path="maxRounds" cssClass="error"></form:errors></td>
    </tr>
    <tr>
                                  <td><form:errors path="playersInfoMap" cssClass="error"></form:errors></td>
                                  </tr>
       <tr>
                   <th>Player Name</th>
                   <th>Count</th>
               </tr>

               <c:forEach items="${gameInfoWrapper.playersInfoMap}" var="playersInfoMap" varStatus="curFieldStatus">
                       <tr>
                           <td>${playersInfoMap.key}</td>
                           <td><input type="number" name="playersInfoMap['${playersInfoMap.key}']" value="${playersInfoMap.value}"/></td>
                       </tr>
               </c:forEach>
    <tr>
        <td colspan="2">
            <input type="submit" value="Play"/>
        </td>
    </tr>
</table>
</form:form>

<textarea  id="text-area" rows="1" cols="50">${gamelog}</textarea>
<script type="text/javascript" language="javascript">
  resizeIt = function() {
    var str = $('text-area').value;
    var cols = $('text-area').cols;

    var linecount = 0;
    $A(str.split("\n")).each( function(l) {
      linecount += Math.ceil( l.length / cols ); // take into account long lines
    } )
    $('text-area').rows = linecount + 1;
  };

  Event.observe('text-area', 'keydown', resizeIt ); // you could attach to keyUp, etc if keydown doesn't work
  resizeIt(); //initial on load
  </script>

</body>
</html>