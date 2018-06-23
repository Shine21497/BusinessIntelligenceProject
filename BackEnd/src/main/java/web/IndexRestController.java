package web;

import neo4j.Neo4jDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexRestController {
    @Autowired
    private Neo4jDriver neo4jDriver;

    @RequestMapping(value = "/api/getData" , method = RequestMethod.POST ,produces = "application/json")
    HashMap<String, List<HashMap<String,Object>>> testpost(@RequestParam("name_1") String name1,@RequestParam("name_2") String name2) {
        HashMap<String, HashMap<String, List<HashMap<String,Object>>>> hashMap = new HashMap();
        return neo4jDriver.getresult(name1,name2);
    }
}
