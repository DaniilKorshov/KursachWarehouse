<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
    <@adminNav.adminNav/>
    <@adminNav.changePage "${tableName}" "${message}" "${action}">
        <p>ID складского заказа</p>
        <input class="form-control" readonly name="warehouseOrder.id" value=" <#if ThisWOL.warehouseOrder.id??>${ThisWOL.warehouseOrder.id}<#else></#if>" placeholder="ID складского заказа" style="margin-bottom: 3vh;">
        <p>ID груза</p>
        <input class="form-control" readonly name="invent.id" value="<#if ThisWOL.invent.id??>${ThisWOL.invent.id}<#else></#if>" placeholder="ID груза" style="margin-bottom: 3vh;">
        <p>Количество</p>
        <input class="form-control" readonly name="qty" value="<#if ThisWOL.qty??>${ ThisWOL.qty}<#else></#if>" placeholder="Количество" style="margin-bottom: 3vh;">
        <p>Стартовая ячейка</p>
        <input class="form-control" readonly name="startLocation.id" value="<#if ThisWOL.startLocation.id??>${ThisWOL.startLocation.id}<#else></#if>" placeholder="ID стартовой ячейки" style="margin-bottom: 3vh;">
        <p>Финишная ячейка</p>
        <input class="form-control" readonly name="finishLocation.id" value="<#if ThisWOL.finishLocation.id??>${ThisWOL.finishLocation.id}<#else></#if>" placeholder="ID финишной ячейки" style="margin-bottom: 3vh;">
        <p>ID исполнителя</p>
        <input class="form-control" readonly name="user.id" value="<#if ThisWOL.user.id??>${ThisWOL.user.id}<#else></#if>" placeholder="ID выполняющего" style="margin-bottom: 3vh;">
        <p>Статус отборки</p>
        <select class="form-control" name="takeStatus" placeholder="Статус отборки" style="margin-bottom: 3vh;">
            <option>${StatusN}</option>
            <option>${StatusY}</option>
        </select>
        <p>Статус доставки</p>
        <select class="form-control" name="putStatus" placeholder="Статус доставки" style="margin-bottom: 3vh;">
            <option>${StatusN}</option>
            <option>${StatusY}</option>
        </select>
        <input type="hidden" name="id" value="${ThisWOL.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}"> <!--Для безопасности-->
        <button class="btn btn-md btn-primary col-6" type="submit" style="margin-top: 2vh">${crudName}</button>
        <a class="btn btn-md btn-outline-primary col-6" href="/warehouseOrderLine" role="button" style="margin-top: 2vh">Отмена</a>
    </@adminNav.changePage>
</@common.page>
