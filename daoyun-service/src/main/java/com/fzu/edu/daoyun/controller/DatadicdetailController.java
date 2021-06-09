package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.Datadic;
import com.fzu.edu.daoyun.entity.Datadicdetail;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.service.IDatadicdetailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/datadicdetail")
public class DatadicdetailController {

    @Autowired
    private IDatadicdetailService datadicdetailService;

    @PostMapping("insertDicDetail")
    @ApiOperation("增加字典细节条目,已实现")
    public ReturnBean insertDicDeaial(@RequestBody Datadicdetail datadicdetail){
        return datadicdetailService.insertDatadicdetail(datadicdetail);
    }
    @GetMapping("/selectDicDetailAll")
    @ApiOperation("查询所有字典细节条目,已实现")
    public ReturnBean selectDicDetail(){
        return datadicdetailService.selectAll();
    }

    @GetMapping("/selectDicDetailAllById/{id}")
    @ApiOperation("根据id查询字典细节条目,已实现")
    public ReturnBean selectDicDetailByDatadicId(@PathVariable String id){
        return datadicdetailService.selectDataDicdetailByDatadicId(Integer.valueOf(id));
    }

    @PostMapping("/updateDicDetail")
    @ApiOperation("通过ID更新字典细节条目,已实现")
    public ReturnBean updateDicDetail(@RequestBody Datadicdetail datadicdetail){
        return datadicdetailService.updateDatadicdetailByID(datadicdetail);
    }
    @PutMapping("/deleteDicDetail/{id}")
    @ApiOperation("删除字典细节条目,已实现")
    public ReturnBean deleteDicDetail(@PathVariable int id){
        return datadicdetailService.deleteDatadicdetail(id);
    }

    @GetMapping("/selectDicDetailByDicIdAndDataValue")
    @ApiOperation("通过DicId和DataValue组合查询DicDetail,已实现")
    public ReturnBean selectDicDetailByDicIdAndDataValue(@RequestBody Datadicdetail datadicdetail){
        return datadicdetailService.selectIdByDataDicIDandDataValue(datadicdetail.getDataDicID(),datadicdetail.getDataValue());
    }
}
