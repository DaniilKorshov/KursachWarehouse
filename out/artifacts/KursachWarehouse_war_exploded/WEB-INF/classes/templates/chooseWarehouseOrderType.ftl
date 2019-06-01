<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
    <@adminNav.adminNav/>
    <div class="container" style="padding-top: 10vh;">
        <div class="card-deck mb-3">
            <td><a class="btn btn-success btn-sm" href="/addWarehouseOrderLine?type=1" style="padding: 5%;margin: 5%;">MOVING</a></td>
            <td><a class="btn btn-success btn-sm" href="/addWarehouseOrderLine?type=2" style="padding: 5%;margin: 5%;">SHIPMENT</a></td>
            <td><a class="btn btn-success btn-sm" href="/addWarehouseOrderLine?type=3" style="padding: 5%;margin: 5%;">RECEIPT</a></td>
        </div>
    </div>

</@common.page>