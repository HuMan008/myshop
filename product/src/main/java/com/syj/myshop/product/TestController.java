package com.syj.myshop.product;

import com.syj.myshop.msg.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SuYajiang
 * @email suyajiang@aoscript.com
 * @create 2024-08-23 15:46
 */
@RestController
public class TestController {


    @GetMapping(value = "api/d1")
    public R<String>  d1 (@RequestParam(value = "name") String name){
        return R.data("api/d1"+name);
    }

    @GetMapping(value="web/d2")
    public R<String> d2(@RequestParam(value = "name") String name) {
        return R.data("web/d2" +name);
    }
}
