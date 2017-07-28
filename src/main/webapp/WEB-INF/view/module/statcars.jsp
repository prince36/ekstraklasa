<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <div class="col-lg-4">
        <h2>Ranking ilości modeli:</h2>
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Model</th>
                    <th>Ile</th>
                    <th>%</th>
                </tr>
                </thead>

                <c:forEach items="${countcars}" var="tmp">
                    <tbody>
                    <tr>
                        <td>${tmp.model}</td>
                        <td>${tmp.countmodel}</td>
                        <td>${fn:substring(((tmp.countmodel / countcarsb)*100),0,4)}%</td>
                    </tr>

                    </tbody>
                </c:forEach>
            </table>
        </div>
    </div>
</div>