<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
<#--    <link rel="icon" href="../assets/css/favicon.ico">-->

    <title>Like page</title>
<#--    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"-->
<#--          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">-->
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../assets/css/style.css"/>
    <menu class="menu col-3 offset-1">
        <li class="menu__item"><a class="menu__ref" href="/messages">MESSAGES</a></li>
        <li class="menu__item"><a class="menu__ref" href="/liked">LIKED</a></li>
        <li class="menu__item"><a class="menu__ref" href="/logout">LOGOUT</a></li>
    </menu>

</head>
<body style="background-color: #f5f5f5;">

<div class="col-4 offset-1">
    <div class="card">
        <div class="card-body">
            <form action="/users" method="POST">
                <div class="row">

                    <img src='<#if user??>${user.getUrlPhoto()}</#if>' alt = "current_user" class="mx-auto rounded-circle img-fluid" />
                    <div class="col-12 col-lg-12 col-md-12 text-center">
                        <h3 class="mb-0 text-truncated">${user.getName()}</h3>
                        <br>
                    </div>
                    <div class="col-12 col-lg-6">
                        <button name="Dislike" value = "Dislike" class="btn btn-outline-danger btn-block"><span
                                    class="fa fa-times"></span>
                            Dislike
                        </button>
                    </div>
                    <div class="col-12 col-lg-6">
                        <button name="Like" value="Like" class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span>
                            Like
                        </button>
                    </div>

                    <!--/col-->
                </div>
            </form>
            <!--/row-->
        </div>
        <!--/card-block-->
    </div>
</div>

</body>
</html>