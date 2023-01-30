<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>index.jsp</title>
        <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
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
    <body>
        <div id="app" class="container">
            <h1 :style="{ backgroundColor : 'pink' }">message1 = {{ message1 }}</h1>
            <h1 :style="{ backgroundColor : 'pink' }">reversedMessage1(計算屬性) = {{ reversedMessage1 }}</h1>
            <h1 :style="[styleA, styleB]">message2 = {{ message2 }}</h1>

            <button type="button" class="btn btn-outline-primary makeSpace"
                    @click="isActiveBorder = !isActiveBorder">切換Border({{isActiveBorder}})</button>

            <input type="text" v-model="empName"/>
            <button type="button" class="btn btn-outline-secondary makeSpace" @click="doAlert('Hello~', $event)">alert</button>

            <div class="row">
                <div class="col-md-3" :class="{ 'myBorder': isActiveBorder }">
                    One of three columns
                </div>
                <div class="col-md-6" :class="{ 'myBorder': isActiveBorder }">
                    One of three columns
                </div>
                <div class="col-md-3" :class="{ 'myBorder': isActiveBorder }">
                    One of three columns
                </div>
            </div>
        </div>
        <script>
            const { createApp } = Vue
            createApp({
                data() {
                    return {
                        message1: 'Hello Vue!',
                        message2: 'Hello Servlet!',
                        empName: '友寄隆輝',
                        isActiveBorder: true,
                        styleA: {
                            color: 'green',
                            fontSize: '30px'
                        },
                        styleB: {
                            'font-weight': 'bold'
                        }
                    }
                },
                methods: {
                    doAlert(welcomeMsg, event) {
                        // `this` inside methods points to the current active instance
                        console.log(`this - `, this)
                        // `event` is the native DOM event
                        console.log(`event.target - `, event.target)
                        alert(welcomeMsg + this.empName)
                    }
                },
                computed: {
                    reversedMessage1: function () {
                        return this.message1.split('').reverse().join('')
                    }
                }
            }).mount('#app')
        </script>
    </body>
</html>
