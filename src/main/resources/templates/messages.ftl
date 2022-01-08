<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Chat</title>

<#--    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">-->
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../assets/css/style.css"/>
    <menu class="menu col-1 offset-1">
        <li class="menu__item"><a class="menu__ref" href="/liked">LIKED</a></li>
        <li class="menu__item"><a class="menu__ref" href="/users">USERS</a></li>
        <li class="menu__item"><a class="menu__ref" href="/logout">LOGOUT</a></li>
    </menu>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="chat-main col-6 offset-2">
            <div class="col-md-12 chat-header">
                <div class="row header-one text-white p-1">
                    <div class="col-md-6 name pl-2">
                        <i class="fa fa-comment"></i>
                        <h6 class="ml-1 mb-0"><#if contact?? && contact.getName()??>${contact.getName()} <#else>error: wrong endpoint entered</#if></h6>
                        <h6 class="ml-1 mb-0"><#if contact?? && contact.getId()??>id:${contact.getId()}</#if></h6>
                    </div>
                    <div class="col-md-6 options text-right pr-0">
                        <i class="fa fa-window-minimize hide-chat-box hover text-center pt-1"></i>
                        <p class="arrow-up mb-0">
                            <i class="fa fa-arrow-up text-center pt-1"></i>
                        </p>
                        <i class="fa fa-times hover text-center pt-1"></i>
                    </div>
                </div>
                <div class="row header-two w-100">
                    <div class="col-md-6 options-left pl-1">
                        <i class="fa fa-video-camera mr-3"></i>
                        <i class="fa fa-user-plus"></i>
                    </div>
                    <div class="col-md-6 options-right text-right pr-2">
                        <i class="fa fa-cog"></i>
                    </div>
                </div>
            </div>
            <div class="chat-content">
                <div class="col-md-12 chats pt-3 pl-2 pr-3 pb-3">
                    <ul class="p-0">



                        <li class="send-msg float-right mb-2">
                            <h6><#if user?? && user.getName()??>${user.getName()}</#if></h6>
                            <h6><#if user?? && user.getId()??>id:${user.getId()}</#if></h6>
                            <#list senderMessages as message><p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                            <#if message??>${message.getContent()}</#if>
                            </p></#list>
                        </li>


                        <li class="receive-msg float-left mb-2">

                            <div class="sender-img">
                                <h6><#if contact?? && contact.getName()??></#if>${contact.getName()}</h6>
                                <img src="${contact.getUrlPhoto()}" class="float-left" alt="avatar">
                            </div>

                            <div class="receive-msg-desc float-left ml-2">
<#--                                <h6><#if contact?? && contact.getName()??>${contact.getName()}</#if></h6>-->
                                <#list receiverMessages as message>
                                    <p class="bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded">
                                    <#if message??>${message.getContent()}</#if>
                                    </p></#list>

                                <span class="receive-msg-time">ketty, Jan 25, 6:20 PM</span>
                            </div>
                        </li>





                    </ul>
                </div>
                <div class="col-md-12 p-2 msg-box border border-primary">
                    <div class="row">
                        <div class="col-md-2 options-left">
                            <i class="fa fa-smile-o"></i>
                        </div>
                        <div class="col-md-7 pl-0">
                            <form action="/messages/${contact.getId()}" method="POST">
                                <label> input msg: <input type="text" name="message" class="border-0" placeholder="write a message here"/></label>
                                <button type="submit">send message</button>
                            </form>
                        </div>
                        <div class="col-md-3 text-right options-right">
                            <i class="fa fa-picture-o mr-2"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>