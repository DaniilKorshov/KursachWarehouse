<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
    <@adminNav.adminNav/>
    <@adminNav.changePage "${tableName}" "${message}" "${action}">
        <input class="form-control" name="warehouseOrder.id" placeholder="ID складского заказа" style="margin-bottom: 3vh;">
        <input class="form-control" name="invent.id" placeholder="ID груза" style="margin-bottom: 3vh;">
        <input class="form-control" name="qty" placeholder="Количество" style="margin-bottom: 3vh;">
        <input class="form-control" ${ROnly1} name="startLocation.id" value="${DefValue1}" placeholder="ID стартовой ячейки" style="margin-bottom: 3vh;">
        <input class="form-control" ${ROnly2} name="finishLocation.id" value="${DefValue2}" placeholder="ID финишной ячейки" style="margin-bottom: 3vh;">
        <input class="form-control" name="user.id" placeholder="ID выполняющего" style="margin-bottom: 3vh;">
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
        <input type="hidden" name="_csrf" value="${_csrf.token}"> <!--Для безопасности-->
        <button class="btn btn-md btn-primary col-6" type="submit" style="margin-top: 2vh">${crudName}</button>
        <a class="btn btn-md btn-outline-primary col-6" href="/warehouseOrderLine" role="button" style="margin-top: 2vh">Отмена</a>
    </@adminNav.changePage>
</@common.page>
