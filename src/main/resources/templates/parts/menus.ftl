<#macro adminNav>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/">Warehouse</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/main">Личные данные</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/choose_table">База данных</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/currentUserWarehouseOrderLine">Задания</a>
                </li>
            </ul>
            <ul class="nav navbar-nav ml-auto">
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"> <!--Для безопасности-->
                    <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Сменить пользователя</button>
                </form>
            </ul>
        </div>
    </nav>
</#macro>

<#macro crudButtons AddPage DeleteAction>
    <td><a class="btn btn-success btn-sm" href="${AddPage}">+</a></td>
    <td><a class="btn btn-danger btn-sm" href="${DeleteAction}">-</a> </td>
</#macro>

<#macro tableHead tableName fieldForFound action>
    <div class="container-fluid">
        <h1 class="page-header text-center">${tableName}</h1>
    </div>
    <div style="margin-left:1vw;">
        <form method="get" style="margin-bottom: 2vh;" action="${action}">
            <input class="form-control col-md-3 col-sm-12" type="text" name="filter" placeholder="${fieldForFound}" value="${filter}" style="margin-bottom: 1vh;">
            <button class="btn btn-md btn-primary col-md-3 col-sm-12" type="submit">Поиск</button>
        </form>
    </div>
</#macro>

<#macro tableBlock tableName info action>
    <div class="card mb-4 box-shadow">
        <div class="card-header">
            <h4 class="my-0 font-weight-normal">${tableName}</h4>
        </div>
        <div class="card-body">
            <p style="margin-top: 5vh">${info}</p>
            <form action="${action}">
                <button class="btn btn-lg btn-block btn-outline-primary" style="margin-top: 5vh">Просмотреть
                </button>
            </form>
        </div>
    </div>
</#macro>

<#macro changePage tableName message action>
    <div class="container col-7 text-center" style="padding-top: 10vh">
    <h4 style="padding-top:1vh;padding-bottom:2vh;text-align: center">${tableName}</h4>
        <h6 style="padding-top:2vh;padding-bottom:2vh;text-align: center;color:red;"><#if message??>${message}<#else>Впишите данные</#if></h6>
    <form class="form-signin align-middle" role="form" action="${action}" method="post" >
        <#nested>
    </form>
    </div>
</#macro>