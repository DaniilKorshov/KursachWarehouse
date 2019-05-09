<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
    <@adminNav.adminNav/>
         <@adminNav.changePage "${tableName}" "${message}" "${action}">
            <input class="form-control" name="description" placeholder="Описание" style="margin-bottom: 3vh;">
            <select class="form-control" name="type" placeholder="Тип груза" style="margin-bottom: 3vh;">
                <option>${OrderTypeR}</option>
                <option>${OrderTypeS}</option>
                <option>${OrderTypeM}</option>
            </select>
            <input type="hidden" name="_csrf" value="${_csrf.token}"> <!--Для безопасности-->
            <button class="btn btn-md btn-primary col-6" type="submit" style="margin-top: 2vh">${crudName}</button>
            <a class="btn btn-md btn-outline-primary col-6" href="/warehouseOrder" role="button" style="margin-top: 2vh">Отмена</a>
            </@adminNav.changePage>
</@common.page>
