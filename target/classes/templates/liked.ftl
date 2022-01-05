<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
<#--    <link rel="icon" href="../assets/img/favicon.ico">-->

    <title>Liked People List</title>
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
                    <h3 class="panel-title">Liked People List</h3>
                </div>
                <div class="panel-body" style="margin-top: 100px;">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <#list contactList as contact>
                                <tr>
                                    <td class="align-middle">
                                        <a href="/messages/${contact.getId()}">message:&nbsp&nbsp</a>
                                    <#if contact.getName()??>${contact.getName()}</#if></td>
                                    <td  class="align-middle">age: <#if contact.getAge()??>${contact.getAge()}</#if></td>
                                    <td  class="align-middle"><div  class="avatar-img">
                                            <img class="img-circle" alt="avatar" src="${contact.getUrlPhoto()}" /></div>
                                    </td>
                                    <td class="align-middle">
                                        Last Login:
                                        <#if contact.getLoginDateTimeString()??>${contact.getLoginDateTimeString()}</#if>
                                        <br><small class="text-muted">?? days ago</small>
                                    </td>
                                </tr>
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