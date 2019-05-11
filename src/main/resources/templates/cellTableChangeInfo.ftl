<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
    <@adminNav.adminNav/>
    <@adminNav.changePage "${tableName}" "${message}" "${action}">
        <input class="form-control" name="length" value="<#if ThisCell.length??>${ThisCell.length}<#else></#if>" placeholder="Длина" style="margin-bottom: 3vh;">
        <input class="form-control" name="width" value="<#if ThisCell.width??>${ThisCell.width}<#else></#if>" placeholder="Ширина" style="margin-bottom: 3vh;">
        <input class="form-control" name="height" value="<#if ThisCell.height??>${ThisCell.height}<#else></#if>" placeholder="Высота" style="margin-bottom: 3vh;">
        <input class="form-control" name="weight" value="<#if ThisCell.weight??>${ThisCell.weight}<#else></#if>" placeholder="Вес" style="margin-bottom: 3vh;">
        <input class="form-control" name="warehouse.id" value="<#if ThisCell.warehouse.id??>${ThisCell.warehouse.id}<#else></#if>" placeholder="ID склада" style="margin-bottom: 3vh;">
        <input class="form-control" name="zone" value="<#if ThisCell.zone??>${ThisCell.zone}<#else></#if>" placeholder="Зона" style="margin-bottom: 3vh;">
        <input class="form-control" name="passageway" value="<#if ThisCell.passageway??>${ThisCell.passageway}<#else></#if>" placeholder="Проход" style="margin-bottom: 3vh;">
        <input class="form-control" name="stillage" value="<#if ThisCell.stillage??>${ThisCell.stillage}<#else></#if>" placeholder="Стеллаж" style="margin-bottom: 3vh;">
        <input class="form-control" name="shelf" value="<#if ThisCell.shelf??>${ThisCell.shelf}<#else></#if>" placeholder="Полка" style="margin-bottom: 3vh;">
        <select class="form-control" name="cellStatus" placeholder="Статус ячейки" style="margin-bottom: 3vh;">
            <option>${cellStatusE}</option>
            <option>${cellStatusT}</option>
            <option>${cellStatusR}</option>
        </select>
        <input type="hidden" name="id" value="<#if ThisCell.id??>${ThisCell.id}<#else></#if>"> <!--Для безопасности-->
        <input type="hidden" name="_csrf" value="${_csrf.token}"> <!--Для безопасности-->
        <button class="btn btn-md btn-primary col-6" type="submit" style="margin-top: 2vh">${crudName}</button>
        <a class="btn btn-md btn-outline-primary col-6" href="/cells" role="button" style="margin-top: 2vh">Отмена</a>
    </@adminNav.changePage>
</@common.page>
