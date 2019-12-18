package ${package.Controller};
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle??>import org.springframework.web.bind.annotation.RestController;<#else>import org.springframework.stereotype.Controller;</#if>
<#if superControllerClassPackage??>import ${superControllerClassPackage};</#if>
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import com.core.springbootcore.BaseResponse;
/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle??>@RestController<#else>@Controller</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
        public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
        public class ${table.controllerName} {
    </#if>

</#if>


private ${table.serviceName} targetService;

@Autowired
public ${table.controllerName}(${table.serviceName} targetService){
this.targetService = targetService;
}


/**
* 获取数据列表
*/
@RequestMapping("/list")
@ResponseBody
public BaseResponse findListByPage(@RequestParam(name = "page", defaultValue = "1") int pageIndex,@RequestParam(name = "rows", defaultValue = "20") int step){
Page page = new Page(pageIndex,step);
targetService.page(page);
return BaseResponse.success(page);
}


/**
* 获取全部数据
*/
@RequestMapping("/all")
@ResponseBody
public BaseResponse findAll(){
List<${entity}> models = targetService.list(null);
return BaseResponse.success(models);
}


/**
* 根据ID查找数据
*/
@RequestMapping("/find")
@ResponseBody
public BaseResponse find(@RequestParam("id") Long id){
${entity} ${entity} = targetService.getById(id);
if(${entity}==null){
return BaseResponse.error("尚未查询到此ID");
}
return BaseResponse.success(${entity});
}


/**
* 添加数据
*/
@RequestMapping(value = "/add", method = RequestMethod.POST)
@ResponseBody
public BaseResponse addItem(@RequestBody ${entity} ${entity}){
boolean isOk = targetService.save(${entity});
if(isOk){
return BaseResponse.success("数据添加成功！");
}
return BaseResponse.error("数据添加失败");
}


/**
* 更新数据
*/
@RequestMapping(value = "/update", method = RequestMethod.POST)
@ResponseBody
public BaseResponse updateItem(@RequestBody ${entity} ${entity}){
boolean isOk = targetService.updateById(${entity});
if(isOk){
return BaseResponse.success("数据更改成功！");
}
return BaseResponse.error("数据更改失败");
}


/**
* 删除数据
*/
@RequestMapping("/del")
@ResponseBody
public BaseResponse deleteItems(@RequestParam("ids") List
<Long> ids){
    boolean isOk = targetService.removeByIds(ids);
    if(isOk){
    return BaseResponse.success("数据删除成功！");
    }
    return BaseResponse.error("数据删除失败");
    }
    }

