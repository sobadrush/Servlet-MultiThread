<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>dept.jsp</title>
        <style>
            .myBorder {
                border: 2px solid black;
            }
            .makeSpace {
                margin: 1rem;
            }
        </style>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    </head>
    <body class="container">
        <div class="row">
            <h1 style="color: blue;">dept.jsp</h1>
            <div class="col-md-1 myBorder">
                One of three columns
            </div>
            <div class="col-md-7 myBorder">
                One of three columns<br/>
                <button type="button" id="queryBtn" class="btn btn-outline-primary makeSpace">查詢Dept動態JS長出TABLE</button>

                <table id="myDeptTable" class="table table-success table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">deptNo</th>
                            <th scope="col">deptLoc</th>
                            <th scope="col">deptName</th>
                        </tr>
                    </thead>
                    <tbody>
<%--                        <tr>--%>
<%--                            <th scope="row">1</th>--%>
<%--                            <td>Mark</td>--%>
<%--                            <td>Otto</td>--%>
<%--                            <td>@mdo</td>--%>
<%--                        </tr>--%>
                    </tbody>
                </table>

            </div>
            <div class="col-md-4 myBorder">
                One of three columns
                <br/>
                \${requestScope.myDeptList} = ${requestScope.myDeptList}
            </div>
        </div>
        <script>
            const URL = "<%=request.getContextPath()%>"

            window.onload = () => {
                document.querySelector("#queryBtn").addEventListener("click", async (e) => {
                    e.preventDefault();
                    let promise = await fetch(URL + "/DeptServlet.do?action=queryAllDeptJson", {
                        "method": "GET",
                        "headers": {
                            "Accept": "application/json; charset=utf-8"
                        }
                    });
                    let jsonData = await promise.json();
                    // console.log("jsonData = ", jsonData);

                    let frag = document.createDocumentFragment();
                    jsonData.forEach((jsonObj, idx) => {
                        console.log(jsonObj)
                        let tr = document.createElement("tr");
                        let th = document.createElement("th");
                        let td1 = document.createElement("td");
                        let td2 = document.createElement("td");
                        let td3 = document.createElement("td");
                        th.setAttribute("scope", "row");
                        th.innerText = idx + 1;
                        td1.innerText = jsonObj["deptNo"];
                        td2.innerText = jsonObj["deptName"];
                        td3.innerText = jsonObj["deptLoc"];
                        tr.append(th, td1, td2, td3);
                        frag.append(tr);
                    })
                    document.querySelector("#myDeptTable > tbody").append(frag);
                })
            }
        </script>
    </body>
</html>
