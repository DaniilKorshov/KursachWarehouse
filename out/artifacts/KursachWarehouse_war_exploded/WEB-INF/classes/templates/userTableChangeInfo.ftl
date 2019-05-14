<#import "parts/common.ftl" as common>
<#import "parts/menus.ftl" as adminNav>

<@common.page>
    <@adminNav.adminNav/>
    <@adminNav.changePage "${tableName}" "${message}" "${action}">
        <input type="email" class="form-control" name="email" value="<#if ThisUser.email??>${ThisUser.email}<#else></#if>" placeholder="Email" required="" autofocus=""
               style="margin-bottom: 3vh;">
        <input type="password" class="form-control" name="password" value="<#if ThisUser.password??>${ThisUser.password}<#else></#if>"placeholder="Пароль" style="margin-bottom: 3vh;" >
        <input class="form-control" name="name" value="<#if ThisUser.name??>${ThisUser.name}<#else></#if>" placeholder="Имя" style="margin-bottom: 3vh;">
        <input class="form-control" name="surname" value="<#if ThisUser.surname??>${ThisUser.surname}<#else></#if>"placeholder="Фамилия" style="margin-bottom: 3vh;">
        <input class="form-control" name="phone_number" value="<#if ThisUser.phone_number??>${ThisUser.phone_number}<#else></#if>"placeholder="Номер телефона" style="margin-bottom: 3vh;">
        <select class="form-control" name="userRoles" placeholder="Роль" style="margin-bottom: 3vh;">
            <option>${userRoleU}</option>
            <option>${userRoleA}</option>
        </select>
        <input type="hidden" name="active" value="${ThisUser.active?string('yes','no')}">
        <input type="hidden" name="id" value="${ThisUser.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}"> <!--Для безопасности-->
        <button class="btn btn-md btn-primary col-6" type="submit" style="margin-top: 2vh">${crudName}</button>
        <a class="btn btn-md btn-outline-primary col-6" href="/addUser" role="button" style="margin-top: 2vh">Отмена</a>
    </@adminNav.changePage>
</@common.page>
