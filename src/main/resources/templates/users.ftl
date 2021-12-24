<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../assets/img/favicon.ico">

    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../assets/css/style.css"/>
    <div class="col-3 offset-1">
        <menu class="menu">
            <li class="menu__item"><a class="menu__ref" href="/">HOME</a></li>
            <li class="menu__item"><a class="menu__ref" href="/liked">LIKED</a></li>
            <li class="menu__item"><a class="menu__ref" href="/messages">MESSAGES</a></li>
            <li class="menu__item"><a class="menu__ref" href="/logout">LOGOUT</a></li>
        </menu>


<#--                <#if users??><p>Ваш ID: ${users.get(1).getName()}</p></#if>-->
        <#--        <#if user??><p>Ваш email: ${user.getEmail()}</p></#if>-->
        <#--        <#if user.getName()??><p>Ваше имя: ${user.getName()}</p></#if>-->
        <#--        <#if (user.getAge() > 5) ><p>Ваш возраст: ${user.getAge()}</p></#if>-->
        <#--        <#if sessionId??><p>Ваша sessionId: ${sessionId}</p></#if>-->
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
                                            <img class="img-circle"
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
<#--                                        <br><small-->
<#--                                                class="text-muted">5 days ago</small>-->
                                    </td>
                                </tr>
                                </p>
                            <#else> <h1>There's no users u've liked before</h1>
                            </#list>

                            <#--                            <tr>-->
                            <#--                                <td width="10">-->
                            <#--                                    <div class="avatar-img">-->
                            <#--                                        <img class="img-circle"-->
                            <#--                                             src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxhcCYW4QDWMOjOuUTxOd50KcJvK-rop9qE9zRltSbVS_bO-cfWA"/>  -->
                            <#--                                    </div>-->

                            <#--                                </td>-->
                            <#--                                <td class="align-middle">-->
                            <#--                                    Herbert Hoover-->
                            <#--                                </td>-->
                            <#--                                <td class="align-middle">-->
                            <#--                                    Builder Sales Agent-->
                            <#--                                </td>-->
                            <#--                                <td class="align-middle">-->
                            <#--                                    Last Login: 6/10/2017<br><small class="text-muted">5 days ago</small>-->
                            <#--                                </td>-->
                            <#--                            </tr>-->

                            <#--                            <tr>-->
                            <#--                                <td width="10">-->
                            <#--                                    <div class="avatar-img">-->
                            <#--                                        <img class="img-circle"-->
                            <#--                                             src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxhcCYW4QDWMOjOuUTxOd50KcJvK-rop9qE9zRltSbVS_bO-cfWA"/>  -->
                            <#--                                    </div>-->

                            <#--                                </td>-->
                            <#--                                <td class="align-middle">-->
                            <#--                                    Herbert Hoover-->
                            <#--                                </td>-->
                            <#--                                <td class="align-middle">-->
                            <#--                                    Builder Sales Agent-->
                            <#--                                </td>-->
                            <#--                                <td class="align-middle">-->
                            <#--                                    Last Login: 6/10/2017<br><small class="text-muted">5 days ago</small>-->
                            <#--                                </td>-->
                            <#--                            </tr>-->

                            <#--                            <tr>-->
                            <#--                                <td width="10">-->
                            <#--                                    <div class="avatar-img">-->
                            <#--                                        <img class="img-circle"-->
                            <#--                                             src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxhcCYW4QDWMOjOuUTxOd50KcJvK-rop9qE9zRltSbVS_bO-cfWA"/>  -->
                            <#--                                    </div>-->

                            <#--                                </td>-->
                            <#--                                <td class="align-middle">-->
                            <#--                                    Herbert Hoover-->
                            <#--                                </td>-->
                            <#--                                <td class="align-middle">-->
                            <#--                                    Builder Sales Agent-->
                            <#--                                </td>-->
                            <#--                                <td class="align-middle">-->
                            <#--                                    Last Login: 6/10/2017<br><small class="text-muted">5 days ago</small>-->
                            <#--                                </td>-->
                            <#--                            </tr>-->
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