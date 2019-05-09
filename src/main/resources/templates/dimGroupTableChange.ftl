<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
    <@adminNav.adminNav/>
    <@adminNav.changePage "${tableName}" "${message}" "${action}">
        <input class="form-control" name="width" placeholder="Ширина" style="margin-bottom: 3vh;">
        <input class="form-control" name="length" placeholder="Длина" style="margin-bottom: 3vh;">
        <input class="form-control" name="weight" placeholder="Вес" style="margin-bottom: 3vh;">
        <input class="form-control" name="color" placeholder="Цвет" style="margin-bottom: 3vh;">
        <input class="form-control" name="config" placeholder="Конфигурация" style="margin-bottom: 3vh;">
        <input type="hidden" name="_csrf" value="${_csrf.token}"> <!--Для безопасности-->
        <button class="btn btn-md btn-primary col-6" type="submit" style="margin-top: 2vh">${crudName}</button>
        <a class="btn btn-md btn-outline-primary col-6" href="/dimGroup" role="button" style="margin-top: 2vh">Отмена</a>
    </@adminNav.changePage>
</@common.page>
