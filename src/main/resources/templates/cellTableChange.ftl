<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
    <@adminNav.adminNav/>
    <@adminNav.changePage "${tableName}" "${message}" "${action}">
        <input class="form-control" name="length" placeholder="Длина" style="margin-bottom: 3vh;">
        <input class="form-control" name="width" placeholder="Ширина" style="margin-bottom: 3vh;">
        <input class="form-control" name="height" placeholder="Высота" style="margin-bottom: 3vh;">
        <input class="form-control" name="weight" placeholder="Вес" style="margin-bottom: 3vh;">
        <input class="form-control" name="warehouse.id" placeholder="ID склада" style="margin-bottom: 3vh;">
        <input class="form-control" name="zone" placeholder="Зона" style="margin-bottom: 3vh;">
        <input class="form-control" name="passageway" placeholder="Проход" style="margin-bottom: 3vh;">
        <input class="form-control" name="stillage" placeholder="Стеллаж" style="margin-bottom: 3vh;">
        <input class="form-control" name="shelf" placeholder="Полка" style="margin-bottom: 3vh;">
        <select class="form-control" name="cellStatus" placeholder="Статус ячейки" style="margin-bottom: 3vh;">
            <option>${cellStatusE}</option>
            <option>${cellStatusT}</option>
            <option>${cellStatusR}</option>
        </select>
        <input type="hidden" name="_csrf" value="${_csrf.token}"> <!--Для безопасности-->
        <button class="btn btn-md btn-primary col-6" type="submit" style="margin-top: 2vh">${crudName}</button>
        <a class="btn btn-md btn-outline-primary col-6" href="/cells" role="button" style="margin-top: 2vh">Отмена</a>
    </@adminNav.changePage>
</@common.page>
