<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as menus>

<@common.page>
    <@menus.adminNav/>
    <@menus.tableHead "Таблица ячеек" "Максимальный вес ячейки" "/cells"/>
    <div class="table-responsive" style="text-align: center;">
        <table class="table table-hover table-bordered ">
            <thead>
            <tr>
                <th scope="row">#</th>
                <th>Склад</th>
                <th>Зона</th>
                <th>Проход</th>
                <th>Стиллаж</th>
                <th>Полка</th>
                <th>Высота</th>
                <th>Длина</th>
                <th>Ширина</th>
                <th>Вес</th>
                <th>Статус</th>
                <th colspan="3">Действия</th>
            </tr>
            </thead>
            <tbody>

            <#list cells as Cell>

                <tr>
                    <th scope="row">${Cell.id}</th>
                    <td>${Cell.warehouse.getWarehouseAddress()}</td>
                    <td>${Cell.zone}</td>
                    <td>${Cell.passageway}</td>
                    <td>${Cell.stillage}</td>
                    <td>${Cell.shelf}</td>
                    <td>${Cell.height}</td>
                    <td>${Cell.length}</td>
                    <td>${Cell.width}</td>
                    <td>${Cell.weight}</td>
                    <td><#list Cell.cellStatus as CellStatus>${CellStatus}</#list></td>
                    <@menus.crudButtons/>
                </tr>
            <#else>
                <td colspan="12">Ничего не найдено</td>
            </#list>
            </tbody>
        </table>
    </div>
</@common.page>
