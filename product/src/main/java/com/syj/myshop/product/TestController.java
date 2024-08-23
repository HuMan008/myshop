package com.syj.myshop.product;

/**
 * @author SuYajiang
 * @email suyajiang@aoscript.com
 * @create 2024-08-23 15:46
 */
@RestController
public class TestController {


    @GetMapping(value = "api/d1")
    public R<String>  d1 (@RequestParam(value = "name") String name){
        return R.ok("api/d1"+name);
    }

    @GettMapping(value="web/d2")
    public R<String> d2( @RequestParam(value = "name") String name) {
        return R.ok("web/d2" +name);
    }
}
