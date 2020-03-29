import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.pzy.archetypesystem.acl.user.domain.entity.SysUser;
import org.pzy.archetypesystem.acl.user.service.SysUserService;
import org.pzy.opensource.domain.PageT;
import org.pzy.opensource.domain.ResultT;
import org.pzy.opensource.domain.vo.PageVO;

/**
 * @author pan
 * @date 2020/3/29
 */
@RestController
@RequestMapping
@Api(tags = "")
public class SysUserController {

    private SysUserService service;

    @GetMapping
    @ApiOperation(value = "SysUser分页查找")
    public ResultT<PageT<SysUser>> searchPage(PageVO page) {
        PageT<SysUser> pageResult = service.searchPageAndCache(page, null);
        return ResultT.success(pageResult);
    }

    @GetMapping("${id}")
    @ApiOperation(value = "SysUser详细查找")
    @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "Long", paramType = "path", required = true)
    public ResultT<SysUser> searchById(@PathVariable("id") Long id) {
        SysUser result = service.getById(id);
        return ResultT.success(result);
    }

    @PostMapping
    @ApiOperation(value = "SysUser新增")
    public ResultT<Boolean> add(@RequestBody SysUser dto) {
        service.addAndClearCache(dto);
        return ResultT.success(true);
    }

    @PutMapping
    @ApiOperation(value = "SysUser编辑")
    public ResultT<Boolean> edit(@RequestBody SysUser dto) {
        service.editAndClearCache(dto);
        return ResultT.success();
    }
}
