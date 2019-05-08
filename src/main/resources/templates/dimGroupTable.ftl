<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as menus>

<@common.page>
    <@menus.adminNav/>
    <@menus.tableHead "Таблица комплектация" "ID комплектации" "/dimGroup"/>
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
                <th colspan="3">Действия</th>
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
                    <@menus.crudButtons/>
                </tr>
            <#else>
                <td colspan="9">Ничего не найдено</td>
            </#list>
            </tbody>
        </table>
    </div>
</@common.page>
