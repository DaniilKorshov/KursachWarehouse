<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
<@adminNav.adminNav/>
<@adminNav.changePage "${tableName}" "${message}" "${action}">
            <input class="form-control" name="name" placeholder="Название" style="margin-bottom: 3vh;">
            <input class="form-control" name="warehouseAddress" placeholder="Адрес" style="margin-bottom: 3vh;">
            <input type="hidden" name="_csrf" value="${_csrf.token}"> <!--Для безопасности-->
            <button class="btn btn-md btn-primary col-6" type="submit" style="margin-top: 2vh">${crudName}</button>
            <a class="btn btn-md btn-outline-primary col-6" href="/warehouse" role="button" style="margin-top: 2vh">Отмена</a>
    </@adminNav.changePage>
</@common.page>
