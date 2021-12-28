<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <#--    <link rel="icon" href="../assets/img/favicon.ico">-->

    <title>People list</title>
    <#--    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"-->
    <#--          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">-->
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../assets/css/style.css"/>
    <div class="col-3 offset-1">
        <menu class="menu">
            <li class="menu__item"><a class="menu__ref" href="/users">USERS</a></li>
            <li class="menu__item"><a class="menu__ref" href="/messages">MESSAGES</a></li>
            <li class="menu__item"><a class="menu__ref" href="/logout">LOGOUT</a></li>
        </menu>
    </div>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <#list users as key, user>
                                <tr>
                                    <td width="10">
                                        <div class="avatar-img">
                                            <img class="img-circle" alt="avatar"
                                                 src="${user.getUrlPhoto()}"/>
                                        </div>

                                    </td>
                                    <td class="align-middle">
                                        ${user.getName()}
                                    </td>
                                    <td class="align-middle">
                                        Builder Sales Agent
                                    </td>
                                    <td class="align-middle">
                                        Last
                                        Login: <#if user.getLoginDate()??> ${user.getLoginDate()} <#else>for 5 yers ago</#if>
                                    </td>
                                </tr>
                            <#else> <h2>There's no users u've liked before</h2>
                            </#list>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>