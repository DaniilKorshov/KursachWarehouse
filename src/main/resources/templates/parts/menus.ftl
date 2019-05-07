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
                    <a class="nav-link" href="#">Задания</a>
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

<#macro crudButtons>
    <td><button class="btn btn-success btn-sm">+</button></td>
    <td><button class="btn btn-danger btn-sm">-</button></td>
    <td><button class="btn btn-warning btn-sm">Изменить</button></td>
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