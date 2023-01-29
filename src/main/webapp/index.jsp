<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>index.jsp</title>
        <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
        <style>

        </style>
    </head>
    <body>
        <div id="app">{{ message }}</div>
        <script>
            const { createApp } = Vue
            createApp({
                data() {
                    return {
                        message: 'Hello Vue!'
                    }
                }
            }).mount('#app')
        </script>
    </body>
</html>
