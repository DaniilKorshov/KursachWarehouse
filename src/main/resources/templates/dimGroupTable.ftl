<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as menus>

<@common.page>
    <@menus.adminNav/>
    <@menus.tableHead "Таблица комплектаций" "ID комплектации" "/dimGroup"/>
    <div class="table-responsive" style="text-align: center;">
        <table class="table table-hover table-bordered ">
            <thead>
            <tr>
                <th scope="row">#</th>
                <th>Ширина объекта</th>
                <th>Длина объекта</th>
                <th>Вес объекта (кг)</th>
                <th>Цвет объекта</th>
                <th>Конфигурация</th>
                <th colspan="2">Действия</th>
            </tr>
            </thead>
            <tbody>

            <#list dimGroups as DimGroup>

                <tr>
                    <th scope="row">${DimGroup.id}</th>
                    <td>${DimGroup.width}</td>
                    <td>${DimGroup.length}</td>
                    <td>${DimGroup.weight}</td>
                    <td>${DimGroup.color}</td>
                    <td>${DimGroup.config}</td>
                    <@menus.crudButtons "/addDimGroup" "/delDimGroup?id=${DimGroup.id}"/>
                </tr>
            <#else>
                <td colspan="6">Ничего не найдено</td>
                <@menus.crudButtons "/addDimGroup" "/dimGroup"/>
            </#list>
            </tbody>
        </table>
    </div>
</@common.page>
