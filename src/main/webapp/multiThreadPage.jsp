<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>multiThreadPage.jsp</title>
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
            <h1>multiThreadPage.jsp</h1>
            <div class="col-md-4 myBorder">
                One of three columns
                <br/>
                <input type="text" id="dataId_A" value="3"/><br/>
                <button type="button" id="btn1" class="btn btn-outline-secondary makeSpace">測試async/await</button>
            </div>
            <div class="col-md-4 myBorder">
                One of three columns
                <br/>
                <button type="button" id="btn2" class="btn btn-outline-primary makeSpace">同時發 2 個 POST 到 Servlet</button>
                <button type="button" id="btn3" class="btn btn-outline-success makeSpace">復原 mDepositAmount</button>
            </div>
            <div class="col-md-4 myBorder">
                One of three columns
            </div>
        </div>
        <script>

            const contextPath = "<%=request.getContextPath()%>";

            function fetchPost(_action, _postDataJson = {}) {
                const formData = new URLSearchParams();
                for (let prop in _postDataJson) {
                    formData.append(prop, _postDataJson[prop]);
                }

                let URI = contextPath + "/MultiThreadTestServlet?action=" + _action;
                fetch(URI, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded; charset=utf-8",
                        "Accept": "application/json; charset=utf-8"
                    },
                    body: formData.toString(),
                }).then(res => res.json())
                  .then(json => console.log(json))
            }

            async function sleep(_secs) {
                await new Promise(resolve => setTimeout(resolve, _secs));
            }

            window.onload = () => {

                // 同時發 2 個 POST 到 Servlet
                document.querySelector("#btn2").addEventListener("click", async (e) => {
                    e.preventDefault();

                    // thread-1 提款
                    fetchPost("withdraw", { "withdrawMoney": 30 })

                    // await sleep(3000)

                    // thread-2 提款
                    fetchPost("withdraw", { "withdrawMoney": 90 })
                });

                // 復原 mDepositAmount
                document.querySelector("#btn3").addEventListener("click", (e) => {
                    e.preventDefault();
                    // thread-1 提款
                    fetchPost("recoverMDepositAmount")
                });

                // 測試async/await
                document.querySelector("#btn1").addEventListener("click", async (event) => {
                    event.preventDefault();
                    // alert(`event.target.id = ` + event.target.id)
                    let id = document.querySelector("#dataId_A").value;
                    const URL = "https://jsonplaceholder.typicode.com/users/";

                    // let promise = await fetch(URL + idA, { method:'GET', headers: { "Content-Type": "application/json; charset=utf-8" } }); // 等待FETCH
                    // let respJson = await promise.json(); // 等待轉換JSON
                    // console.log(respJson)
                    // console.log(123)

                    await fetch(URL + id, { method:'GET', headers: { "Content-Type": "application/json; charset=utf-8" } })
                        .then(res => res.json())
                        .then(json => console.log(json));
                    console.log("加了 await，此行log必定會\"等\"到 fetch 資料回來才跑!")
                })
            }
        </script>
    </body>
</html>
